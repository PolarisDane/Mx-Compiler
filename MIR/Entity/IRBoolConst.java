package MIR.Entity;

import MIR.Type.IRIntType;

public class IRBoolConst extends IRConst {
    public boolean val;

    public IRBoolConst(boolean val) {
        super(new IRIntType(8));
        this.val = val;
    }

    public IRBoolConst(boolean val, boolean cond) {
        super(new IRIntType(1));
        this.val = val;
    }

    @Override
    public boolean equals(Object it) {
        if (!(it instanceof IRBoolConst)) {
            return false;
        }
        return val == ((IRBoolConst) it).val;
    }

    @Override
    public String toString() {
        return val ? "1" : "0";
    }
}
