package MIR.Inst;

import Backend.IRVisitor;
import MIR.BasicBlock;
import MIR.Entity.Entity;
import MIR.Entity.IRRegister;
import MIR.Type.IRBaseType;

public class IRBinaryOp extends IRBaseInst {
    public IRRegister res;
    public String op;
    public IRBaseType type;
    public Entity op1;
    public Entity op2;
    public IRBinaryOp(BasicBlock parentBlock, IRRegister res, IRBaseType type, String op, Entity op1, Entity op2) {
        super(parentBlock);
        this.res = res;
        this.type = type;
        this.op = op;
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public String toString() {
        return res + " = " + op + " " + type.toString() + " " + op1.toString() + ", " +op2.toString();
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
//done
