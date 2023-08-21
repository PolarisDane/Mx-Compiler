package Assembly.Inst;

import Assembly.ASMBlock;
import Assembly.Operand.*;

public class ASMBeqInst extends ASMBaseInst {
    public Reg src;
    public ASMBlock dest;

    public ASMBeqInst(Reg src, ASMBlock dest) {
        this.src = src;
        this.dest = dest;
    }

    @Override
    public String toString() {
        return "beqz " + src.toString() + ", " + dest.toString();
    }
}
