package Assembly;

import Assembly.Inst.ASMBaseInst;
import Assembly.Inst.ASMBeqInst;
import Assembly.Inst.ASMJumpInst;
import Assembly.Operand.Reg;
import MIR.Inst.IRJump;

import java.util.HashSet;
import java.util.LinkedList;

public class ASMBlock {
    public String label;
//    public ASMBaseInst head = null, tail = null;
    public LinkedList<ASMBlock> succ = new LinkedList<>();
    public LinkedList<ASMBlock> pred = new LinkedList<>();
    public LinkedList<ASMBaseInst> insts = new LinkedList<>();
    public LinkedList<ASMBaseInst> flowInsts = new LinkedList<>();
    public LinkedList<ASMBaseInst> phiRemoval = new LinkedList<>();
    public HashSet<Reg> use = new HashSet<>();
    public HashSet<Reg> def = new HashSet<>();
    public HashSet<Reg> liveIn = new HashSet<>();
    public HashSet<Reg> liveOut = new HashSet<>();
    public int loopDepth;

    public ASMBlock(String label, int loopDepth) {
        this.label = label;
        this.loopDepth = loopDepth;
    }

    public void work() {
        for (var inst: phiRemoval) {
            insts.add(inst);
        }
        for (var inst: flowInsts) {
            insts.add(inst);
        }
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
