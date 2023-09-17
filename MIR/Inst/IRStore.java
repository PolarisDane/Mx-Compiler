package MIR.Inst;

import Backend.IRVisitor;
import MIR.BasicBlock;
import MIR.Entity.Entity;
import MIR.Entity.IRRegister;
import MIR.Type.IRBaseType;

import java.util.HashSet;

public class IRStore extends IRBaseInst{
    public IRRegister reg;
    public Entity val;

    public IRStore(BasicBlock parentBlock, IRRegister reg, Entity val) {
        super(parentBlock);
        this.reg = reg;
        this.val = val;
    }

    @Override
    public String toString() {
        return "store " + val.type.toString() + " " + val.toString() + ", ptr " + reg.toString();
    }

    @Override
    public HashSet<IRRegister> getUse() {
        HashSet<IRRegister> use = new HashSet<>();
        use.add(reg);
        if (val instanceof IRRegister valReg) {
            use.add(valReg);
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
