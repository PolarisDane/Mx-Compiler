package MIR.Inst;

import MIR.BasicBlock;

public class IRJump extends IRBaseInst {
    public String label;

    public IRJump(BasicBlock parentBlock, String label) {
        super(parentBlock);
        this.label = label;
    }

    @Override
    public String toString() {
        return "jump " + label;
    }
}
//done
