package MIR.Entity;

import MIR.Type.IRBaseType;

public class IRGlobalVar extends IRRegister {
    public Entity initVal;
    public IRGlobalVar(String content, IRBaseType type) {
        super(content, type);
    }

    @Override
    public String toString() {
        return "@" + name;
    }
}
