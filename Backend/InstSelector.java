package Backend;

import Assembly.ASMBlock;
import Assembly.Inst.*;
import Assembly.Operand.*;
import MIR.BasicBlock;
import MIR.Entity.*;
import MIR.Function;
import MIR.Inst.*;
import MIR.Program;

import java.util.HashMap;

public class InstSelector implements IRVisitor {
    public HashMap<IRRegister, VirtualReg> regMap = new HashMap<>();
    public ASMBlock curBlock;
    public HashMap<String, ASMBlock> blockMap = new HashMap<>();

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
            curBlock.insts.add(new ASMLoadInst(size, base, dest, new Imm(offset)));
        }
        else {
            VirtualReg tmp = new VirtualReg(32);
            curBlock.insts.add(new ASMITypeInst(tmp, base, new Imm(offset), "add"));
            curBlock.insts.add(new ASMLoadInst(size, base, dest));
        }
    }

    public void storeReg(int size, Reg base, Reg src, int offset) {
        if (offset < (1 << 11)) {
            curBlock.insts.add(new ASMStoreInst(size, base, src, new Imm(offset)));
        }
        else {
            VirtualReg tmp = new VirtualReg(32);
            curBlock.insts.add(new ASMITypeInst(tmp, base, new Imm(offset), "add"));
            curBlock.insts.add(new ASMStoreInst(size, base, src));
        }
    }
    @Override
    public void visit(Program it) {
//        for (var nxt: it.globalVar) {
//            nxt.ASMReg = new GlobalValue(nxt);
//
//        }
//        for (var nxt: it.IRstringMap) {
//
//        }
//        for (var nxt: it.functions) {
//            nxt.accept(this);
//        }
    }

    @Override
    public void visit(Function it) {
        for (var nxt: it.blocks) {
            nxt.accept(this);
        }
    }

    @Override
    public void visit(BasicBlock it) {

    }

    @Override
    public void visit(IRAlloca it) {
        curBlock.insts.add(new ASMITypeInst(new PhyReg("sp"), new PhyReg("sp"), new Imm(it.type.size / 8), "addi"));
    }

    @Override
    public void visit(IRBranch it) {
        curBlock.insts.add(new ASMBeqInst(getReg(it.condition), blockMap.get(it.falseThenWork)));
        it.falseThenWork.accept(this);
        curBlock = new ASMBlock();
        it.trueThenWork.accept(this);
    }

    @Override
    public void visit(IRBinaryOp it) {
        VirtualReg res = new VirtualReg(32);
        if (it.op.equals("add")) {
            curBlock.insts.add(new ASMRTypeInst(res, getReg(it.op1), getReg(it.op2), "add"));
        }
        if (it.op.equals("sub")) {
            curBlock.insts.add(new ASMRTypeInst(res, getReg(it.op1), getReg(it.op2), "sub"));
        }
        if (it.op.equals("mul")) {
            curBlock.insts.add(new ASMRTypeInst(res, getReg(it.op1), getReg(it.op2), "mul"));
        }
        if (it.op.equals("sdiv")) {
            curBlock.insts.add(new ASMRTypeInst(res, getReg(it.op1), getReg(it.op2), "div"));
        }
        if (it.op.equals("srem")) {
            curBlock.insts.add(new ASMRTypeInst(res, getReg(it.op1), getReg(it.op2), "rem"));
        }
        if (it.op.equals("and")) {
            curBlock.insts.add(new ASMRTypeInst(res, getReg(it.op1), getReg(it.op2), "and"));
        }
        if (it.op.equals("or")) {
            curBlock.insts.add(new ASMRTypeInst(res, getReg(it.op1), getReg(it.op2), "or"));
        }
        if (it.op.equals("xor")) {
            curBlock.insts.add(new ASMRTypeInst(res, getReg(it.op1), getReg(it.op2), "xor"));
        }
        if (it.op.equals("shl")) {
            curBlock.insts.add(new ASMRTypeInst(res, getReg(it.op1), getReg(it.op2), "sll"));
        }
        if (it.op.equals("ashr")) {
            curBlock.insts.add(new ASMRTypeInst(res, getReg(it.op1), getReg(it.op2), "sra"));
        }
    }

    @Override
    public void visit(IRCall it) {
        for (int i = 0; i < it.args.size(); i++) {
            if (i < 8) {
                curBlock.insts.add(new ASMMvInst(new PhyReg("a" + i), getReg(it.args.get(i))));
            }
            else {
                storeReg(it.type.size, new PhyReg("sp"), getReg(it.args.get(i)), (i - 8) * 4);
            }
        }
        curBlock.insts.add(new ASMCallInst(it.funcName));
    }

    @Override
    public void visit(IRGetElementPtr it) {

    }

    @Override
    public void visit(IRIcmp it) {
        VirtualReg res = new VirtualReg(32);
        if (it.cond.equals("slt")) {
            curBlock.insts.add(new ASMRTypeInst(res, getReg(it.op1), getReg(it.op2),"slt"));
        }
        if (it.cond.equals("sle")) {
            curBlock.insts.add(new ASMRTypeInst(res, getReg(it.op1), getReg(it.op2),"sgt"));
            curBlock.insts.add(new ASMITypeInst(res, res, new Imm(1), "xori"));
        }
        if (it.cond.equals("sgt")) {
            curBlock.insts.add(new ASMRTypeInst(res, getReg(it.op1), getReg(it.op2),"sgt"));
        }
        if (it.cond.equals("sge")) {
            curBlock.insts.add(new ASMRTypeInst(res, getReg(it.op1), getReg(it.op2),"slt"));
            curBlock.insts.add(new ASMITypeInst(res, res, new Imm(1), "xori"));
        }
        if (it.cond.equals("eq")) {
            curBlock.insts.add(new ASMRTypeInst(res, getReg(it.op1), getReg(it.op2), "sub"));
            curBlock.insts.add(new ASMITypeInst(res, res, new Imm(0), "seqz"));
        }
        if (it.cond.equals("ne")) {
            curBlock.insts.add(new ASMRTypeInst(res, getReg(it.op1), getReg(it.op2), "sub"));
            curBlock.insts.add(new ASMITypeInst(res, res, new Imm(0), "snez"));
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
        curBlock.insts.add(new ASMJrInst());
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
