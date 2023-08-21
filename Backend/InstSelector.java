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

    Reg getReg(Entity entity) {
        if (entity.ASMReg == null) {
            if (entity instanceof IRRegister) {
                entity.ASMReg = new VirtualReg();
            }
            else if (entity instanceof IRConst) {
                entity.ASMReg = new Imm(entity);
            }
        }
        return entity.ASMReg;
    }

    public void loadReg(int size, Reg val, Reg dest, int offset) {
        if (offset < (1 << 11)) {
            curBlock.insts.add(new ASMLoadInst(size, dest, val, offset));
        }
        else {

        }
    }
    @Override
    public void visit(Program it) {

    }

    @Override
    public void visit(Function it) {

    }

    @Override
    public void visit(BasicBlock it) {

    }

    @Override
    public void visit(IRAlloca it) {

    }

    @Override
    public void visit(IRBranch it) {

    }

    @Override
    public void visit(IRBinaryOp it) {

    }

    @Override
    public void visit(IRCall it) {

    }

    @Override
    public void visit(IRGetElementPtr it) {

    }

    @Override
    public void visit(IRIcmp it) {

    }

    @Override
    public void visit(IRJump it) {

    }

    @Override
    public void visit(IRLoad it) {
        curBlock.insts.add(new ASMLoadInst(it.reg, it.addr, new Imm(0)));
    }

    @Override
    public void visit(IRPhi it) {

    }

    @Override
    public void visit(IRRet it) {

    }

    @Override
    public void visit(IRStore it) {

    }

    @Override
    public void visit(IRTrunc it) {

    }

    @Override
    public void visit(IRZext it) {

    }
}
