package Assembly.Inst;

import Assembly.Operand.*;

public class ASMITypeInst extends ASMBaseInst {

    public Reg rd, rs1;
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
        return op + " " + rd.toString() + ", " + rs1.toString() + ", " + imm.toString();
    }
}
