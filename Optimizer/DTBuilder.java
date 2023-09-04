package Optimizer;

import MIR.BasicBlock;
import MIR.Function;
import MIR.Program;

import java.util.*;

public class DTBuilder {
    Program program;

    HashMap<BasicBlock, Integer> blockOrder = new HashMap<>();
    Stack<BasicBlock> blockStack = new Stack<>();
    HashMap<BasicBlock, Boolean> visit = new HashMap<>();
    LinkedList<BasicBlock> RPO = new LinkedList<>();

    public DTBuilder(Program program) {
        this.program = program;
    }

    public void buildDT() {
        for (var nxt: program.functions) {
            visitFunc(nxt);
        }
    }

    public void getRPO(BasicBlock it) {
        visit.put(it, true);
        for (var succ: it.succ) {
            if (!visit.containsKey(succ)) {
                getRPO(succ);
            }
        }
        blockStack.push(it);
        RPO.addFirst(it);
    }

    public void visitFunc(Function it) {
        RPO.clear();
        blockStack.clear();
        visit.clear();
        getRPO(it.blocks.get(0));
        boolean changed = true;
        while (changed) {
            changed = false;
            for (var nxt: RPO) {
                BasicBlock newIDom = null;
                for (var pred: nxt.pred) {
                    if (newIDom == null) {
                        newIDom = pred;
                    }
                    else if (pred.IDom != null) {
                        newIDom = calcIntersect(newIDom, pred.IDom);
                    }
                }
                if (newIDom != nxt.IDom) {
                    nxt.IDom = newIDom;
                    changed = true;
                }
            }
        }//we find the set of dom by finding its IDom (which takes the form of a tree), by adding all its ancestors we get the complete dom set
        for (var nxt: RPO) {
            for (var pred: nxt.pred) {

            }
        }
    }

    public BasicBlock calcIntersect(BasicBlock b1, BasicBlock b2) {
        while (b1 != b2) {
            while (blockOrder.get(b1.IDom) < blockOrder.get(b2.IDom)) {
                b1 = b1.IDom;
            }
            while (blockOrder.get(b1.IDom) > blockOrder.get(b2.IDom)) {
                b2 = b2.IDom;
            }
        }
        return b1;
    }
}//Dominance tree built based on a rather naive algorithm (Just too lazy to write Lengauer-Tarjan algorithm, maybe later)
