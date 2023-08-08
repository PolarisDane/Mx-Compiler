package MIR.Inst;

import MIR.BasicBlock;
import MIR.Entity.Entity;
import MIR.Entity.IRRegister;
import MIR.Type.IRBaseType;

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
}
