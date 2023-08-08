package MIR;

import MIR.Inst.IRBaseInst;
import MIR.Type.IRIntType;

import java.util.ArrayList;
import java.util.LinkedList;

public class BasicBlock {
    public String label;
    public LinkedList<IRBaseInst> insts = new LinkedList<>();
    public boolean returned = false;

    public BasicBlock(String label) {
        this.label = label;
    }

    public void addInst(IRBaseInst inst) {
        if (!returned) {
            insts.add(inst);
        }
    }

    public String toString() {
        String ret = label + ":\n";
        for (var inst: insts) {
            ret = ret + inst + "\n";
        }
        return ret;
    }
}
