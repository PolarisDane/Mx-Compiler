package MIR.Entity;

import MIR.Type.IRBaseType;

public class IRRegister extends Entity {
    public String name;
    public int id;
    public static int cnt = 0;

    public IRRegister(String name, IRBaseType type) {
        super(type);
        this.name = name;
        this.id = cnt++;
    }

    @Override
    public String toString() {
        return "%" + name + "." + id;
    }
}
