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
                entity.ASMReg = new VirtualReg(entity.type.size);
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
        program.work();
//        System.out.println(program.toString());
    }

    @Override
    public void visit(Function it) {
        VirtualReg.cnt = 0;
        int maxArgsCnt = 0;
        for (var nxt: it.blocks) {
            blockMap.put(nxt.label, new ASMBlock(".BBL" + (++blockCnt), nxt.loopDepth));
            for (var inst: nxt.insts) {
                if (inst instanceof IRCall) {
                    maxArgsCnt = Integer.max(maxArgsCnt, ((IRCall) inst).args.size());
                }
            }
        }
        for (int i = 0; i < it.params.size(); i++) {
            it.params.get(i).ASMReg = new VirtualReg(32);
        }
        inFunc.argsStack = Integer.max(maxArgsCnt - 8, 0) * 4;
        for (int i = 0; i < it.blocks.size(); i++) {
            BasicBlock nxt = it.blocks.get(i);
            curBlock = blockMap.get(nxt.label);
            inFunc.blocks.add(curBlock);
            if (i == 0) {
                storeReg(32, PhyReg.phyRegMap.get("sp"), PhyReg.phyRegMap.get("ra"), inFunc.argsStack);
            }
            nxt.accept(this);
        }
        for (int i = 0; i < it.params.size() && i < 8; i++) {
            inFunc.blocks.get(0).insts.addFirst(new ASMMvInst(it.params.get(i).ASMReg, PhyReg.phyRegMap.get("a" + i)));
        }
        ASMBlock first = inFunc.blocks.get(0);
        ASMBlock last = inFunc.blocks.get(inFunc.blocks.size() - 1);
        for (var reg: PhyReg.calleeSave()) {
            VirtualReg vReg = new VirtualReg(32);
            first.insts.addFirst(new ASMMvInst(vReg, reg));
            last.insts.add(new ASMMvInst(reg, vReg));
        }//callee save regs
    }

    @Override
    public void visit(BasicBlock it) {
        for (var nxt: it.insts) {
            nxt.accept(this);
        }
    }

    @Override
    public void visit(IRAlloca it) {
        curBlock.insts.add(new ASMITypeInst(getReg(it.res), PhyReg.phyRegMap.get("sp"), new Imm(inFunc.argsStack + inFunc.allocaLength), "addi"));
        inFunc.allocaLength += 4;
    }

    @Override
    public void visit(IRBranch it) {
        curBlock.flowInsts.add(new ASMBeqInst(getReg(it.condition), blockMap.get(it.falseThenWork.label)));
        curBlock.flowInsts.add(new ASMJumpInst(blockMap.get(it.trueThenWork.label)));
        curBlock.succ.add(blockMap.get(it.trueThenWork.label));
        blockMap.get(it.trueThenWork.label).pred.add(curBlock);
        curBlock.succ.add(blockMap.get(it.falseThenWork.label));
        blockMap.get(it.falseThenWork.label).pred.add(curBlock);
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
                curBlock.insts.add(new ASMMvInst(PhyReg.phyRegMap.get("a" + i), getReg(it.args.get(i))));
            }
            else {
                storeReg(it.args.get(i).type.size, PhyReg.phyRegMap.get("sp"), getReg(it.args.get(i)), (i - 8) * 4);
            }
        }
        curBlock.insts.add(new ASMCallInst(it.funcName));
        if (it.res != null) {
            curBlock.insts.add(new ASMMvInst(getReg(it.res), PhyReg.phyRegMap.get("a0")));
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
        curBlock.flowInsts.add(new ASMJumpInst(blockMap.get(it.label)));
        curBlock.succ.add(blockMap.get(it.label));
        blockMap.get(it.label).pred.add(curBlock);
    }

    @Override
    public void visit(IRLoad it) {
        loadReg(it.type.size, getReg(it.addr), getReg(it.reg), 0);
    }

    @Override
    public void visit(IRPhi it) {
        VirtualReg res = new VirtualReg(32);
        curBlock.insts.add(new ASMMvInst(getReg(it.res), res));
        for (int i = 0; i < it.val.size(); i++) {
            Entity val = it.val.get(i);
            if (val instanceof IRConst) {
                if (val instanceof IRStringConst) {
                    VirtualReg vReg = new VirtualReg(32);
                    blockMap.get(it.fromBlock.get(i).label).phiRemoval.add(new ASMLaInst(vReg, ((Global) val.ASMReg).name));
                    blockMap.get(it.fromBlock.get(i).label).phiRemoval.add(new ASMMvInst(res, vReg));
                } else {
                    blockMap.get(it.fromBlock.get(i).label).phiRemoval.add(new ASMLiInst(res, new Imm(val)));
                }
            }
            else {
                blockMap.get(it.fromBlock.get(i).label).phiRemoval.add(new ASMMvInst(res, getReg(val)));
            }
        }
        //not used, maybe later
    }

    @Override
    public void visit(IRRet it) {
        if (it.returnVal != null) {
            curBlock.insts.add(new ASMMvInst(PhyReg.phyRegMap.get("a0"), getReg(it.returnVal)));
        }
        loadReg(32, PhyReg.phyRegMap.get("sp"), PhyReg.phyRegMap.get("ra"), inFunc.argsStack);
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
