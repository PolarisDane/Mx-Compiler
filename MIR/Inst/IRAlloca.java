package MIR.Inst;

import Backend.IRVisitor;
import MIR.BasicBlock;
import MIR.Entity.IRRegister;
import MIR.Type.IRBaseType;

public class IRAlloca extends IRBaseInst{
    public IRBaseType type;
    public IRRegister res;

    public IRAlloca(BasicBlock parentBlock, IRBaseType type, IRRegister res) {
        super(parentBlock);
        this.type = type;
        this.res = res;
    }

    @Override
    public String toString() {
        return res + " = alloca " + type;
    }

    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
//done
