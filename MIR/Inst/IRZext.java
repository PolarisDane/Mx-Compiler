package MIR.Inst;

import MIR.BasicBlock;
import MIR.Entity.Entity;
import MIR.Entity.IRRegister;
import MIR.Type.IRBaseType;

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
}
