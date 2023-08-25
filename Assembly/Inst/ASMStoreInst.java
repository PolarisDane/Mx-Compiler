package Assembly.Inst;

import Assembly.Operand.*;

public class ASMStoreInst extends ASMBaseInst {
    public int size;
    public Imm offset;

    public ASMStoreInst(int size, Reg rs1, Reg rs2, Imm offset) {
        this.size = size;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.offset = offset;
    }

    public ASMStoreInst(int size, Reg rs1, Reg rs2) {
        this.size = size;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.offset = new Imm(0);
    }
    @Override
    public String toString() {
        if (size ==32) {
            return "sw " + rs2.toString() + ", " + offset.toString() + "(" + rs1.toString() + ")";
        }
        else {
            return "sb " + rs2.toString() + ", " + offset.toString() + "(" + rs1.toString() + ")";
        }
    }
}
