package Assembly.Inst;

import Assembly.Operand.Reg;

public abstract class ASMBaseInst {

    public ASMBaseInst prev = null, nxt = null;
    public Reg rs1, rs2, rd;
    public abstract String toString();
}
