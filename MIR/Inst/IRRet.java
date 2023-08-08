package MIR.Inst;

import MIR.BasicBlock;
import MIR.Entity.Entity;

public class IRRet extends IRBaseInst {
    public Entity returnVal;

    public IRRet(BasicBlock parentBlock, Entity returnVal) {
        super(parentBlock);
        this.returnVal = returnVal;
    }

    @Override
    public String toString() {
        if (returnVal == null) {
            return "ret void";
        }
        return "ret " + returnVal.type + " " + returnVal.toString();
    }
}
