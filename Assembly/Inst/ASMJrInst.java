package Assembly.Inst;

import Assembly.ASMBlock;
import Assembly.Operand.*;

public class ASMJrInst extends ASMBaseInst {
    public ASMJrInst() {}
    @Override
    public String toString() {
        return "jr ra";
    }
}
