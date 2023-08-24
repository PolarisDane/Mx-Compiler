package MIR.Entity;

import MIR.Type.IRIntType;

public class IRBoolConst extends IRConst {
    public boolean val;

    public IRBoolConst(boolean val) {
        super(new IRIntType(8));
        this.val = val;
    }

    @Override
    public String toString() {
        return val ? "1" : "0";
    }
}
