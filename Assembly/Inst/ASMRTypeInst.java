package Assembly.Inst;

import Assembly.Operand.*;

import java.util.HashSet;

public class ASMRTypeInst extends ASMBaseInst {
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
