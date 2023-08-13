package MIR.Entity;

import MIR.Type.IRBaseType;

public class IRGlobalVar extends Entity {
    public Entity initVal;
    public IRGlobalVar(IRBaseType type) {
        super(type);
    }

    @Override
    public String toString() {
        return "@" + content;
    }
}
