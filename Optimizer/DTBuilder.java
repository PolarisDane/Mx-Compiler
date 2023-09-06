package Optimizer;

import MIR.BasicBlock;
import MIR.Function;
import MIR.Program;

import java.util.*;

public class DTBuilder {
    Program program;

    HashMap<BasicBlock, Integer> blockOrder = new HashMap<>();
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



    public void getRPO(Function it) {
        blockOrder.clear();
        visit.clear();
        RPO.clear();
        dfs(it.blocks.get(0));
        for (int i = 0; i < RPO.size(); i++) {
            blockOrder.put(RPO.get(i), i);
        }
        RPO.removeFirst();
        it.blocks.get(0).IDom = it.blocks.get(0);
    }

    public void dfs(BasicBlock it) {
        visit.put(it, true);
        for (var succ: it.succ) {
            if (!visit.containsKey(succ)) {
                dfs(succ);
            }
        }
        RPO.addFirst(it);
    }

    public void visitFunc(Function it) {
        getRPO(it);
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
            nxt.IDom.DTChild.add(nxt);
        }//construct DT according to IDom

        for (var nxt: RPO) {
            if (nxt.pred.size() < 2) {
                continue;
            }
            for (var pred: nxt.pred) {
                var runner = pred;
                while (runner != nxt.IDom) {
                    runner.DomFrontier.add(nxt);
                    runner = runner.IDom;
                }
            }
        }
    }

    public BasicBlock calcIntersect(BasicBlock b1, BasicBlock b2) {
        while (b1 != b2) {
            while (blockOrder.get(b1) > blockOrder.get(b2)) {
                b1 = b1.IDom;
            }
            while (blockOrder.get(b1) < blockOrder.get(b2)) {
                b2 = b2.IDom;
            }
        }
        return b1;
    }
}//Dominance tree built based on a rather naive algorithm (Just too lazy to write Lengauer-Tarjan algorithm, maybe later)
