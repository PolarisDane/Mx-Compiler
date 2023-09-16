package MIR.Inst;

import Backend.IRVisitor;
import MIR.BasicBlock;
import MIR.Entity.IRRegister;

import java.util.HashSet;

public abstract class IRBaseInst {
    public BasicBlock parentBlock;
    public boolean isDead = false;

    public abstract String toString();

    public IRBaseInst(BasicBlock parentBlock) {
        this.parentBlock = parentBlock;
    }

    public abstract HashSet<IRRegister> getUse();

    public abstract IRRegister getDef();

    public abstract void accept(IRVisitor visitor);
}
