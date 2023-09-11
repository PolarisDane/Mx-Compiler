package Optimizer;

import MIR.BasicBlock;
import MIR.Function;
import MIR.Inst.IRBaseInst;
import MIR.Inst.IRBranch;
import MIR.Inst.IRJump;
import MIR.Program;

import java.util.LinkedList;

public class CFGBuilder {
    Program program;

    public CFGBuilder(Program program) {
        this.program = program;
    }

    public void buildCFG() {
        for (var nxt: program.functions) {
            visitFunc(nxt);
        }
    }

    public void visitFunc(Function it) {
        for (var nxt: it.blocks) {
            IRBaseInst inst = nxt.insts.get(nxt.insts.size() - 1);
            if (inst instanceof IRJump) {
                BasicBlock jumpBlock = it.blockMap.get(((IRJump) inst).label);
                nxt.succ.add(jumpBlock);
                jumpBlock.pred.add(nxt);
            }
            else if (inst instanceof IRBranch) {
                BasicBlock trueBlock = ((IRBranch) inst).trueThenWork;
                BasicBlock falseBlock = ((IRBranch) inst).falseThenWork;
                nxt.succ.add(trueBlock);
                nxt.succ.add(falseBlock);
                trueBlock.pred.add(nxt);
                falseBlock.pred.add(nxt);
            }
        }

        LinkedList<BasicBlock> blocks = new LinkedList<>();
        for (var block: it.blocks) {
            if (!block.pred.isEmpty() || block == it.blocks.get(0)) {
                blocks.add(block);
            }
            else {
                for (var nxt: block.succ) {
                    nxt.pred.remove(block);
                }
            }
        }
        it.blocks = blocks;
    }

}
