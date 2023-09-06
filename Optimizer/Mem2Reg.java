package Optimizer;

import MIR.BasicBlock;
import MIR.Entity.IRRegister;
import MIR.Function;
import MIR.Inst.IRAlloca;
import MIR.Inst.IRLoad;
import MIR.Inst.IRPhi;
import MIR.Inst.IRStore;
import MIR.Program;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Mem2Reg {
    Program program;
    Function curFunc;
    ArrayList<BasicBlock> def = new ArrayList<>();
    ArrayList<BasicBlock> use = new ArrayList<>();
    public Mem2Reg(Program program) {
        this.program = program;
    }

    public void optimize() {
        new CFGBuilder(program).buildCFG();
        new DTBuilder(program).buildDT();
        for (var nxt: program.functions) {
            curFunc = nxt;
            visitFunc(nxt);
        }
    }

//    public boolean isPromotable(IRAlloca it) {
//
//    }

    public void promoteAlloca(IRAlloca it) {
        use.clear();
        def.clear();
        for (var nxt: curFunc.blocks) {
            for (var inst: nxt.insts) {
                if (inst instanceof IRStore && ((IRStore) inst).reg.equals(it.res)) {
                    def.add(nxt);
                }
                if (inst instanceof IRLoad && ((IRLoad) inst).reg.equals(it.res)) {
                    use.add(nxt);
                }
            }
        }
        if (use.isEmpty()) {

        }//can be discarded
        //judge promotable maybe?
        LinkedList<BasicBlock> que = new LinkedList<>(def);
        HashMap<BasicBlock, Boolean> addPhi = new HashMap<>();
        HashMap<BasicBlock, Boolean> visit = new HashMap<>();
        while (!que.isEmpty()) {
            BasicBlock block = que.removeFirst();
            for (BasicBlock nxt: block.DomFrontier) {
                if (!addPhi.containsKey(nxt)) {
                    nxt.addInst(new IRPhi(nxt, new IRRegister("", it.type), it.type));
                    addPhi.put(nxt, true);
                    if (!visit.containsKey(nxt)) {
                        visit.put(nxt, true);
                        que.add(nxt);
                    }
                }
            }//visit Dominance Frontier
        }
    }

    public void renamePass() {

    }

    public void removeAlloca() {

    }

    public void visitFunc(Function it) {
        for (var inst: it.allocaInst) {
            promoteAlloca(inst);
        }
    }
}
