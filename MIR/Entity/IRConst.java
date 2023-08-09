package MIR.Entity;

import MIR.Type.IRBaseType;

public abstract class IRConst extends Entity {
    public IRConst(String name, IRBaseType type) {
        super(name, type);
    }
    public IRConst(IRBaseType type) {
        super(type);
    }

}
