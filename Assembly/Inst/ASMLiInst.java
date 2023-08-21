package Assembly.Inst;

import Assembly.Operand.*;

public class ASMLiInst extends ASMBaseInst {
    public Reg rd;
    public Imm imm;

    public ASMLiInst(Reg rd, Imm imm) {
        this.rd = rd;
        this.imm = imm;
    }

    @Override
    public String toString() {
        return "li " + rd.toString() + ", " + imm.toString();
    }
}
