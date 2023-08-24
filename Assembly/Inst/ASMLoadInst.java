package Assembly.Inst;

import Assembly.Operand.*;

public class ASMLoadInst extends ASMBaseInst {
    int size;
    public Imm offset;

    public ASMLoadInst(int size, Reg rd, Reg rs1, Imm offset) {
        this.size = size;
        this.rd = rd;
        this.rs1 = rs1;
        this.offset = offset;
    }

    public ASMLoadInst(int size, Reg rd, Reg rs1) {
        this.size = size;
        this.rd = rd;
        this.rs1 = rs1;
        this.offset = new Imm(0);
    }
    @Override
    public String toString() {
        if (size == 32) {
            return "lw " + rd.toString() + ", " + offset.toString() + "(" + rs1.toString() + ")";
        }
        else {
            return "lb " + rd.toString() + ", " + offset.toString() + "(" + rs1.toString() + ")";
        }
    }
}
