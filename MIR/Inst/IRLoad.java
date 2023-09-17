package MIR.Inst;

import Backend.IRVisitor;
import MIR.BasicBlock;
import MIR.Entity.Entity;
import MIR.Entity.IRRegister;
import MIR.Type.IRBaseType;

import java.util.HashSet;

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

    @Override
    public HashSet<IRRegister> getUse() {
        HashSet<IRRegister> use = new HashSet<>();
        if (addr instanceof IRRegister reg) {
            use.add(reg);
        }
        return use;
    }

    @Override
    public IRRegister getDef() {
        return reg;
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
