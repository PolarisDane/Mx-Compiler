package MIR.Inst;

import MIR.BasicBlock;
import MIR.Entity.Entity;

public class IRBranch extends IRBaseInst {
    public Entity condition;
    public BasicBlock trueThenWork;
    public BasicBlock falseThenWork;

    public IRBranch(BasicBlock parentBlock, Entity condition, BasicBlock trueThenWork, BasicBlock falseThenWork) {
        super(parentBlock);
        this.condition = condition;
        this.trueThenWork = trueThenWork;
        this.falseThenWork = falseThenWork;
    }

    @Override
    public String toString() {
        return "br i1 " + condition.toString() + ", label " + trueThenWork + ", label " + falseThenWork;
    }
}
