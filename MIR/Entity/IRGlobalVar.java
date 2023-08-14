package MIR.Entity;

import MIR.Type.IRBaseType;

public class IRGlobalVar extends IRRegister {
    public Entity initVal;
    public IRGlobalVar(String content, IRBaseType type, Entity initVal) {
        super(content, type);
        this.initVal = initVal;
    }

    @Override
    public String toString() {
        return "@" + content;
    }
}
