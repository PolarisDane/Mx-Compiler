package Assembly.Inst;

import Assembly.ASMBlock;
import Assembly.Operand.*;

import java.util.HashSet;

public class ASMJumpInst extends ASMBaseInst {
    public ASMBlock dest;
    public ASMJumpInst(ASMBlock dest) {
        this.dest = dest;
    }
    @Override
    public String toString() {
        return "j " + dest.label;
    }
}
