package MIR.Inst;

import Backend.IRVisitor;
import MIR.BasicBlock;
import MIR.Entity.*;
import MIR.Type.IRBaseType;

import java.util.HashSet;

public class IRTrunc extends IRBaseInst {
    public IRRegister res;
    public IRBaseType fromType;
    public Entity val;
    public IRBaseType toType;

    public IRTrunc(BasicBlock parentBlock, IRRegister res, IRBaseType fromType, Entity val, IRBaseType toType) {
        super(parentBlock);
        this.res = res;
        this.fromType = fromType;
        this.val = val;
        this.toType = toType;
    }

    @Override
    public String toString() {
        return res.toString() + " = trunc " + fromType.toString() + " " + val.toString() + " to " + toType.toString();
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
        if (val instanceof IRConst) {
            return new IRBoolConst(((IRBoolConst) val).val, true);
        }
        return null;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
//done
