package MIR.Inst;

import Backend.IRVisitor;
import MIR.BasicBlock;
import MIR.Entity.Entity;
import MIR.Entity.IRBoolConst;
import MIR.Entity.IRConst;
import MIR.Entity.IRRegister;
import MIR.Type.IRBaseType;

import java.util.HashSet;

public class IRZext extends IRBaseInst {
    public IRRegister res;
    public IRBaseType fromType;
    public Entity val;
    public IRBaseType toType;

    public IRZext(BasicBlock parentBlock, IRRegister res, IRBaseType fromType, Entity val, IRBaseType toType) {
        super(parentBlock);
        this.res = res;
        this.fromType = fromType;
        this.val = val;
        this.toType = toType;
    }

    @Override
    public String toString() {
        return res.toString() + " = zext " + fromType.toString() + " " + val.toString() + " to " + toType.toString();
    }

    @Override
    public HashSet<IRRegister> getUse() {
        HashSet<IRRegister> use = new HashSet<>();
        if (val instanceof IRRegister reg) {
            use.add(reg);
        }
        return use;
    }

    @Override
    public IRRegister getDef() {
        return res;
    }

    @Override
    public Entity getConst() {
        //should not be viable
        return null;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
