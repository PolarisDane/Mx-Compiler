package Assembly.Inst;

import Assembly.Operand.*;

public class ASMLoadInst extends ASMBaseInst {
    int size;
    public Reg rd, rs1;
    public Imm offset;

    public ASMLoadInst(int size, Reg rd, Reg rs1, Imm offset) {
        this.size = size;
        this.rd = rd;
        this.rs1 = rs1;
        this.offset = offset;
    }

    public ASMLoadInst(Reg rd, Reg rs1) {
        this.rd = rd;
        this.rs1 = rs1;
        this.offset = new Imm(0);
    }
    @Override
    public String toString() {
        return "lw " + rd.toString() + ", " + offset.toString() + "(" + rs1.toString() + ")";
    }
}
