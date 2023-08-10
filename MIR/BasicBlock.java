package MIR;

import MIR.Inst.IRBaseInst;
import MIR.Type.IRIntType;

import java.util.ArrayList;
import java.util.LinkedList;

public class BasicBlock {
    public String label;
    public LinkedList<IRBaseInst> insts = new LinkedList<>();
    public IRBaseInst terminal;
    public boolean returned = false;

    public static int cnt = 0;

    public int id;

    public BasicBlock(String label) {
        id = cnt++;
        this.label = label + id;
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
