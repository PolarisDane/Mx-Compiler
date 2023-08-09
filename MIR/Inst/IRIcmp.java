package MIR.Inst;

import MIR.BasicBlock;
import MIR.Entity.Entity;
import MIR.Entity.IRRegister;
import MIR.Type.IRBaseType;

public class IRIcmp extends IRBaseInst {
    public IRRegister res;
    public String cond;
    public IRBaseType type;
    public Entity op1;
    public Entity op2;

    public IRIcmp(BasicBlock parentBlock, IRRegister res, String cond, IRBaseType type, Entity op1, Entity op2) {
        super(parentBlock);
        this.res = res;
        this.cond = cond;
        this.type = type;
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public String toString() {
        return res.toString() + " = icmp " + cond.toString() + " " + type.toString() + " " + op1.toString() + ", " + op2.toString();
    }
}
//done
