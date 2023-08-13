package MIR;

import MIR.Inst.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class BasicBlock {
    public String label;
    public LinkedList<IRBaseInst> insts = new LinkedList<>();
    public Function inFunc;
    public IRBaseInst terminal;

    public boolean returned = false;

    public static int cnt = 0;

    public int id;

    public BasicBlock(String label, Function inFunc) {
        id = cnt++;
        this.label = label + id;
        this.inFunc = inFunc;
    }

    public BasicBlock(String label, Function inFunc, boolean no_id) {
        this.label = label;
        this.inFunc = inFunc;
    }

    public void addInst(IRBaseInst inst) {
        if (inst instanceof IRAlloca) {
            inFunc.allocaInst.add((IRAlloca) inst);
        }
        else {
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
