package Assembly.Inst;

import Assembly.Operand.*;

public class ASMRTypeInst extends ASMBaseInst {

    public Reg rd, rs1, rs2;
    public String op;

    public ASMRTypeInst(Reg rd, Reg rs1, Reg rs2, String op) {
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.op = op;
    }

    @Override
    public String toString() {
        return op + " " + rd.toString() + ", " + rs1.toString() + ", " + rs2.toString();
    }
}
