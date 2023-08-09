package MIR.Entity;

import MIR.Type.IRIntType;

public class IRCondConst extends IRConst{
    public boolean val;

    public IRCondConst(boolean val) {
        super(new IRIntType(1));
        this.val = val;
    }

    @Override
    public String toString() {
        return val ? "true" : "false";
    }
}
