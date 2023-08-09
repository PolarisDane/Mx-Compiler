package MIR.Entity;

import MIR.Type.IRIntType;

public class IRIntConst extends IRConst{
    public int val;

    public IRIntConst(int val) {
        super(new IRIntType(32));
        this.val = val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
