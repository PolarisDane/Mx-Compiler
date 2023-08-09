package MIR.Entity;

import MIR.Type.IRIntType;
import MIR.Type.IRNullType;

public class IRNullConst extends IRConst{

    public IRNullConst() {
        super(new IRNullType());
    }

    @Override
    public String toString() {
        return "null";
    }
}
