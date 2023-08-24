package Assembly.Inst;

import Assembly.Operand.*;

public class ASMMvInst extends ASMBaseInst {
    public ASMMvInst(Reg rd, Reg rs1) {
        this.rd = rd;
        this.rs1 = rs1;
    }

    @Override
    public String toString() {
        return "mv " + rd + ", " + rs1;
    }
}
