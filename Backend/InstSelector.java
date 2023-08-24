package Backend;

import Assembly.ASMBlock;
import Assembly.ASMFunction;
import Assembly.ASMProgram;
import Assembly.Inst.*;
import Assembly.Operand.*;
import MIR.BasicBlock;
import MIR.Entity.*;
import MIR.Function;
import MIR.Inst.*;
import MIR.Program;
import MIR.Type.IRStructType;

import java.util.HashMap;
import java.util.Iterator;

public class InstSelector implements IRVisitor {
    public HashMap<IRRegister, VirtualReg> regMap = new HashMap<>();
    public ASMBlock curBlock;
    public ASMFunction inFunc;
    public HashMap<String, ASMBlock> blockMap = new HashMap<>();
    public ASMProgram program = new ASMProgram();
    public int blockCnt = 0;

    Reg immToReg(Imm imm) {
        VirtualReg vReg = new VirtualReg(32);
        curBlock.insts.add(new ASMLiInst(vReg, imm));
        return vReg;
    }

    Reg getReg(Entity entity) {
        if (entity.ASMReg == null) {
            if (entity instanceof IRRegister) {
                entity.ASMReg = new VirtualReg(((IRRegister) entity).type.size);
            }
            else if (entity instanceof IRConst) {
                entity.ASMReg = immToReg(new Imm(entity));
            }
        }
        else if (entity.ASMReg instanceof Global) {
            VirtualReg vReg = new VirtualReg(32);
            curBlock.insts.add(new ASMLaInst(vReg, ((Global) entity.ASMReg).name));
            return vReg;
        }
        return entity.ASMReg;
    }

    public void loadReg(int size, Reg base, Reg dest, int offset) {
        if (offset < (1 << 11)) {
            curBlock.insts.add(new ASMLoadInst(size, dest, base, new Imm(offset)));
        }
        else {
            VirtualReg res = new VirtualReg(32);
            curBlock.insts.add(new ASMRTypeInst(res, base, immToReg(new Imm(offset)), "add"));
            curBlock.insts.add(new ASMLoadInst(size, dest, res));
        }
    }

    public void storeReg(int size, Reg base, Reg src, int offset) {
        if (offset < (1 << 11)) {
            curBlock.insts.add(new ASMStoreInst(size, base, src, new Imm(offset)));
        }
        else {
            VirtualReg res = new VirtualReg(32);
            curBlock.insts.add(new ASMRTypeInst(res, base, immToReg(new Imm(offset)), "add"));
            curBlock.insts.add(new ASMStoreInst(size, res, src));
        }
    }
    @Override
    public void visit(Program it) {
        for (var nxt: it.globalVar) {
            GlobalValue gVal = new GlobalValue(nxt);
            program.gValues.add(gVal);
            nxt.ASMReg = gVal;
        }
        for (var nxt: it.IRstringMap.values()) {
            GlobalString gString = new GlobalString(nxt);
            program.gStrings.add(gString);
            nxt.ASMReg = gString;
        }
        for (var nxt: it.functions) {
            inFunc = new ASMFunction(nxt.funcName);
            program.functions.add(inFunc);
            nxt.accept(this);
        }
//        System.out.println(program.toString());
    }

    @Override
    public void visit(Function it) {
        VirtualReg.cnt = 0;
        int maxArgsCnt = 0;
        for (var nxt: it.blocks) {
            blockMap.put(nxt.label, new ASMBlock(".BBL" + (++blockCnt)));
            for (var inst: nxt.insts) {
                if (inst instanceof IRCall) {
                    maxArgsCnt = Integer.max(maxArgsCnt, ((IRCall) inst).args.size());
                }
            }
        }
        for (int i = 0; i < it.params.size(); i++) {
            if (i < 8) {
                it.params.get(i).ASMReg = new PhyReg("a" + i);
            }
            else {
                it.params.get(i).ASMReg = new VirtualReg(32);
            }
        }
        inFunc.argsStack = Integer.max(maxArgsCnt - 8, 0) * 4;
        inFunc.stackStart = inFunc.argsStack + 4;
        for (var nxt: it.blocks) {
            curBlock = blockMap.get(nxt.label);
            inFunc.blocks.add(curBlock);
            nxt.accept(this);
        }
        inFunc.stackLength = inFunc.stackStart + VirtualReg.cnt * 4;
        if (inFunc.stackLength % 16 != 0) {
            inFunc.stackLength += 16 - inFunc.stackLength % 16;
        }
        ASMBlock first = inFunc.blocks.get(0);
        ASMBlock last = inFunc.blocks.get(inFunc.blocks.size() - 1);
        first.insts.addFirst(new ASMStoreInst(32, new PhyReg("sp"), new PhyReg("ra"), new Imm(inFunc.argsStack)));
        first.insts.addFirst(new ASMRTypeInst(new PhyReg("sp"), new PhyReg("sp"), new PhyReg("t0"), "add"));
        first.insts.addFirst(new ASMLiInst(new PhyReg("t0"), new Imm(-inFunc.stackLength)));
        last.insts.add(new ASMLoadInst(32, new PhyReg("ra"), new PhyReg("sp"), new Imm(inFunc.argsStack)));
        last.insts.add(new ASMLiInst(new PhyReg("t0"), new Imm(inFunc.stackLength)));
        last.insts.add(new ASMRTypeInst(new PhyReg("sp"), new PhyReg("sp"), new PhyReg("t0"), "add"));
        inFunc.blocks.get(inFunc.blocks.size() - 1).insts.add(new ASMRetInst());
    }

    @Override
    public void visit(BasicBlock it) {
        for (var nxt: it.insts) {
            nxt.accept(this);
        }
    }

    @Override
    public void visit(IRAlloca it) {
        curBlock.insts.add(new ASMITypeInst(getReg(it.res), new PhyReg("sp"), new Imm(inFunc.stackStart), "addi"));
        inFunc.stackStart += 4;
    }

    @Override
    public void visit(IRBranch it) {
        curBlock.insts.add(new ASMBeqInst(getReg(it.condition), blockMap.get(it.falseThenWork.label)));
        curBlock.insts.add(new ASMJumpInst(blockMap.get(it.trueThenWork.label)));
    }

    @Override
    public void visit(IRBinaryOp it) {
        if (it.op.equals("add")) {
            curBlock.insts.add(new ASMRTypeInst(getReg(it.res), getReg(it.op1), getReg(it.op2), "add"));
        }
        if (it.op.equals("sub")) {
            curBlock.insts.add(new ASMRTypeInst(getReg(it.res), getReg(it.op1), getReg(it.op2), "sub"));
        }
        if (it.op.equals("mul")) {
            curBlock.insts.add(new ASMRTypeInst(getReg(it.res), getReg(it.op1), getReg(it.op2), "mul"));
        }
        if (it.op.equals("sdiv")) {
            curBlock.insts.add(new ASMRTypeInst(getReg(it.res), getReg(it.op1), getReg(it.op2), "div"));
        }
        if (it.op.equals("srem")) {
            curBlock.insts.add(new ASMRTypeInst(getReg(it.res), getReg(it.op1), getReg(it.op2), "rem"));
        }
        if (it.op.equals("and")) {
            curBlock.insts.add(new ASMRTypeInst(getReg(it.res), getReg(it.op1), getReg(it.op2), "and"));
        }
        if (it.op.equals("or")) {
            curBlock.insts.add(new ASMRTypeInst(getReg(it.res), getReg(it.op1), getReg(it.op2), "or"));
        }
        if (it.op.equals("xor")) {
            curBlock.insts.add(new ASMRTypeInst(getReg(it.res), getReg(it.op1), getReg(it.op2), "xor"));
        }
        if (it.op.equals("shl")) {
            curBlock.insts.add(new ASMRTypeInst(getReg(it.res), getReg(it.op1), getReg(it.op2), "sll"));
        }
        if (it.op.equals("ashr")) {
            curBlock.insts.add(new ASMRTypeInst(getReg(it.res), getReg(it.op1), getReg(it.op2), "sra"));
        }
    }

    @Override
    public void visit(IRCall it) {
        for (int i = 0; i < it.args.size(); i++) {
            if (i < 8) {
                curBlock.insts.add(new ASMMvInst(new PhyReg("a" + i), getReg(it.args.get(i))));
            }
            else {
                storeReg(it.args.get(i).type.size, new PhyReg("sp"), getReg(it.args.get(i)), (i - 8) * 4);
            }
        }
        curBlock.insts.add(new ASMCallInst(it.funcName));
        if (it.res != null) {
            curBlock.insts.add(new ASMMvInst(getReg(it.res), new PhyReg("a0")));
        }
    }

    @Override
    public void visit(IRGetElementPtr it) {
        if (it.type instanceof IRStructType) {
            VirtualReg res = new VirtualReg(32);
            curBlock.insts.add(new ASMITypeInst(res, getReg(it.idx.get(1)), new Imm(2), "slli"));
            curBlock.insts.add(new ASMRTypeInst(getReg(it.res), getReg(it.ptr), res, "add"));
        }//struct
        else {
            VirtualReg res = new VirtualReg(32);
            curBlock.insts.add(new ASMITypeInst(res, getReg(it.idx.get(0)), new Imm(2), "slli"));
            curBlock.insts.add(new ASMRTypeInst(getReg(it.res), getReg(it.ptr), res, "add"));
        }//array
    }
    @Override
    public void visit(IRIcmp it) {
        VirtualReg res = new VirtualReg(32);
        if (it.cond.equals("slt")) {
            curBlock.insts.add(new ASMRTypeInst(getReg(it.res), getReg(it.op1), getReg(it.op2),"slt"));
        }
        if (it.cond.equals("sle")) {
            curBlock.insts.add(new ASMRTypeInst(res, getReg(it.op2), getReg(it.op1),"slt"));
            curBlock.insts.add(new ASMITypeInst(getReg(it.res), res, new Imm(1), "xori"));
        }
        if (it.cond.equals("sgt")) {
            curBlock.insts.add(new ASMRTypeInst(getReg(it.res), getReg(it.op2), getReg(it.op1),"slt"));
        }
        if (it.cond.equals("sge")) {
            curBlock.insts.add(new ASMRTypeInst(res, getReg(it.op1), getReg(it.op2),"slt"));
            curBlock.insts.add(new ASMITypeInst(getReg(it.res), res, new Imm(1), "xori"));
        }
        if (it.cond.equals("eq")) {
            curBlock.insts.add(new ASMRTypeInst(res, getReg(it.op1), getReg(it.op2), "sub"));
            curBlock.insts.add(new ASMITypeInst(getReg(it.res), res, new Imm(0), "seqz"));//not done
        }
        if (it.cond.equals("ne")) {
            curBlock.insts.add(new ASMRTypeInst(res, getReg(it.op1), getReg(it.op2), "sub"));
            curBlock.insts.add(new ASMITypeInst(getReg(it.res), res, new Imm(0), "snez"));//not done
        }
    }

    @Override
    public void visit(IRJump it) {
        curBlock.insts.add(new ASMJumpInst(blockMap.get(it.label)));
    }

    @Override
    public void visit(IRLoad it) {
        loadReg(it.type.size, getReg(it.addr), getReg(it.reg), 0);
    }

    @Override
    public void visit(IRPhi it) {
        //not used, maybe later
    }

    @Override
    public void visit(IRRet it) {
        if (it.returnVal != null) {
            curBlock.insts.add(new ASMMvInst(new PhyReg("a0"), getReg(it.returnVal)));
        }
        loadReg(32, new PhyReg("sp"), new PhyReg("ra"), inFunc.argsStack);
    }

    @Override
    public void visit(IRStore it) {
        storeReg(it.val.type.size, getReg(it.reg), getReg(it.val), 0);
    }

    @Override
    public void visit(IRTrunc it) {
        curBlock.insts.add(new ASMMvInst(getReg(it.res), getReg(it.val)));
    }

    @Override
    public void visit(IRZext it) {
        curBlock.insts.add(new ASMMvInst(getReg(it.res), getReg(it.val)));
    }
}
