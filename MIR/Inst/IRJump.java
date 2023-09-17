package MIR.Inst;

import Backend.IRVisitor;
import MIR.BasicBlock;
import MIR.Entity.Entity;
import MIR.Entity.IRRegister;

import java.util.HashSet;

public class IRJump extends IRBaseInst {
    public String label;

    public IRJump(BasicBlock parentBlock, String label) {
        super(parentBlock);
        this.label = label;
    }

    @Override
    public String toString() {
        return "br label %" + label;
    }

    @Override
    public HashSet<IRRegister> getUse() {
        HashSet<IRRegister> use = new HashSet<>();
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
