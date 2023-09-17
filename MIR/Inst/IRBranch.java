package MIR.Inst;

import Backend.IRVisitor;
import MIR.BasicBlock;
import MIR.Entity.Entity;
import MIR.Entity.IRRegister;

import java.util.HashSet;

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
        return "br i1 " + condition.toString() + ", label %" + trueThenWork.label + ", label %" + falseThenWork.label;
    }

    @Override
    public HashSet<IRRegister> getUse() {
        HashSet<IRRegister> use = new HashSet<>();
        if (condition instanceof IRRegister reg) {
            use.add(reg);
        }
        return use;
    }

    @Override
    public IRRegister getDef() {
        return null;
    }

    @Override
    public Entity getConst() {
        return null;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
//done
