package MIR.Entity;

import MIR.Type.IRArrayType;
import MIR.Type.IRIntType;
import MIR.Type.IRPtrType;

public class IRStringConst extends IRConst{
    public String val;
    public int id;
    public static int cnt = 0;

    public IRStringConst(String val) {
        super(new IRPtrType(new IRArrayType(new IRIntType(8), val.length() + 1)));
        this.val = val;
        this.id = cnt++;
    }

    @Override
    public String toString() {
        return "@.str." + id;
    }
}
