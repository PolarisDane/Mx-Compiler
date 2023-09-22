package Optimizer;

import Assembly.ASMBlock;
import Assembly.ASMFunction;
import Assembly.Inst.ASMJumpInst;
import Assembly.Operand.Reg;
import MIR.BasicBlock;
import MIR.Entity.IRRegister;
import MIR.Function;
import MIR.Inst.IRBaseInst;
import MIR.Program;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class LivenessAnalyzer {
    ASMFunction inFunc;
    LinkedList<ASMBlock> que = new LinkedList<>();
    HashSet<ASMBlock> inQue = new HashSet<>();

    public LivenessAnalyzer(ASMFunction func) {
        inFunc = func;
    }

    public void analyze() {
        for (var block: inFunc.blocks) {
            for (var inst: block.insts) {
                block.use.addAll(inst.getUse());
                block.def.addAll(inst.getDef());
            }
        }
        que.add(inFunc.blocks.get(inFunc.blocks.size() - 1));
        inQue.add(inFunc.blocks.get(inFunc.blocks.size() - 1));
        while (!que.isEmpty()) {
            ASMBlock block = que.removeFirst();
            inQue.remove(block);
            HashSet<Reg> newIn = new HashSet<>();
            HashSet<Reg> newOut = new HashSet<>();
            newIn.addAll(block.use);
            newIn.addAll(block.liveOut);
            newIn.removeAll(block.def);
            for (var succ: block.succ) {
                newOut.addAll(succ.liveIn);
            }
            if (!newIn.equals(block.liveIn) || !newOut.equals(block.liveOut)) {
                for (var pred: block.pred) {
                    if (!inQue.contains(pred)) {
                        que.add(pred);
                        inQue.add(pred);
                    }
                }
                block.liveIn = newIn;
                block.liveOut = newOut;
            }
        }
    }//reference to chapter 10.1.1
}
