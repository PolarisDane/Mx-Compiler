package MIR.Inst;

import Backend.IRVisitor;
import MIR.BasicBlock;

public abstract class IRBaseInst {
    public BasicBlock parentBlock;

    public abstract String toString();

    public IRBaseInst(BasicBlock parentBlock) {
        this.parentBlock = parentBlock;
    }

    public abstract void accept(IRVisitor visitor);
}
