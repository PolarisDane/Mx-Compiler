package MIR.Inst;

import MIR.BasicBlock;
import MIR.Entity.Entity;
import MIR.Entity.IRRegister;
import MIR.Type.IRBaseType;

public class IRLoad extends IRBaseInst{
    public IRBaseType type;
    public IRRegister reg;
    public Entity addr;

    public IRLoad(BasicBlock parentBlock, IRBaseType type, IRRegister reg, Entity addr) {
        super(parentBlock);
        this.type = type;
        this.reg = reg;
        this.addr = addr;
    }

    @Override
    public String toString() {
        return reg.toString() + " = load " + type.toString() + ", ptr " + addr.toString();
    }
}
