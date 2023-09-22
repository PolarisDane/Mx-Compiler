package Assembly.Inst;

import Assembly.Operand.*;

import java.util.HashSet;

public class ASMITypeInst extends ASMBaseInst {

    public Imm imm;
    public String op;

    public ASMITypeInst(Reg rd, Reg rs1, Imm imm, String op) {
        this.rd = rd;
        this.rs1 = rs1;
        this.imm = imm;
        this.op = op;
    }

    @Override
    public String toString() {
        if (op.equals("snez") || op.equals("seqz")) {
            return op + " " + rd.toString() + ", " + rs1.toString();
        }
        return op + " " + rd.toString() + ", " + rs1.toString() + ", " + imm.toString();
    }
}
