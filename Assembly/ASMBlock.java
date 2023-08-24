package Assembly;

import Assembly.Inst.ASMBaseInst;

import java.util.LinkedList;

public class ASMBlock {
    public String label;
//    public ASMBaseInst head = null, tail = null;
    public LinkedList<ASMBlock> succ = new LinkedList<>();
    public LinkedList<ASMBlock> pred = new LinkedList<>();
    public LinkedList<ASMBaseInst> insts = new LinkedList<>();

    public ASMBlock(String label) {
        this.label = label;
    }

    public String toString() {
        String ret = "";
        if (label != null) {
            ret += label + ":\n";
        }
        for (var inst: insts) {
            ret += " " + inst + "\n";
        }
        ret += "\n";
        return ret;
    }
}
