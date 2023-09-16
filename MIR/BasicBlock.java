package MIR;

import Backend.IRVisitor;
import MIR.Inst.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class BasicBlock {
    public String label;
    public LinkedList<IRBaseInst> insts = new LinkedList<>();
    public Function inFunc;
    public boolean terminal = false;

    public boolean returned = false;

    public static int cnt = 0;

    public int id;
    public BasicBlock IDom = null;

    public ArrayList<BasicBlock> pred = new ArrayList<>();
    public ArrayList<BasicBlock> succ = new ArrayList<>();
    public ArrayList<BasicBlock> DomFrontier = new ArrayList<>();
    public ArrayList<BasicBlock> DTChild = new ArrayList<>();
    public ArrayList<IRPhi> phiInst = new ArrayList<>();

    public BasicBlock(String label, Function inFunc) {
        id = cnt++;
        this.label = label + id;
        this.inFunc = inFunc;
        inFunc.blockMap.put(this.label, this);
    }

    public BasicBlock(String label, Function inFunc, boolean no_id) {
        this.label = label;
        this.inFunc = inFunc;
        inFunc.blockMap.put(this.label, this);
    }

    public void addInst(IRBaseInst inst) {
        if (inst instanceof IRPhi) {
            phiInst.add((IRPhi) inst);
            return;
        }
        if (terminal) {
            return;
        }
        if (inst instanceof IRAlloca) {
            inFunc.allocaInst.add((IRAlloca) inst);
            return;
        }
        if (inst instanceof IRBranch || inst instanceof  IRJump) {
            terminal = true;
        }
        insts.add(inst);
    }

    public void mergePhi() {
        for (var phi: phiInst) {
            insts.addFirst(phi);
        }
    }

    public String toString() {
        String ret = label + ":\n";
        for (var inst: insts) {
            ret = ret + inst + "\n";
        }
        return ret;
    }

    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
