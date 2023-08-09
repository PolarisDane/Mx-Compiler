package MIR.Entity;

import MIR.Type.IRBaseType;

public abstract class Entity {
    String content;
    public IRBaseType type;

    public Entity(IRBaseType type) {
        this.type = type;
    }
    public Entity(String content, IRBaseType type) {
        this.content = content;
        this.type = type;
    }
    public abstract String toString();
}
