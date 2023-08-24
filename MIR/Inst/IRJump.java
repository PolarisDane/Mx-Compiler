package MIR.Inst;

import Backend.IRVisitor;
import MIR.BasicBlock;

public class IRJump extends IRBaseInst {
    public String label;

    public IRJump(BasicBlock parentBlock, String label) {
        super(parentBlock);
        this.label = label;
    }

    @Override
    public String toString() {
        return "br label %" + label;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
//done
