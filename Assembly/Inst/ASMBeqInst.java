package Assembly.Inst;

import Assembly.ASMBlock;
import Assembly.Operand.*;

import java.util.HashSet;

public class ASMBeqInst extends ASMBaseInst {
    public ASMBlock dest;

    public ASMBeqInst(Reg rs1, ASMBlock dest) {
        this.rs1 = rs1;
        this.dest = dest;
    }

    @Override
    public String toString() {
        return "beqz " + rs1.toString() + ", " + dest.label;
    }
}
