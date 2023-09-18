package Optimizer;

import MIR.BasicBlock;
import MIR.Function;
import MIR.Inst.IRJump;
import MIR.Inst.IRPhi;
import MIR.Program;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class BlockMerger {
    Program program;

    public BlockMerger(Program program) {
        this.program = program;
    }

    public void mergeBlock() {
        for (var nxt: program.functions) {
            visitFunc(nxt);
        }
    }

    public void visitFunc(Function it) {
        LinkedList<BasicBlock> newBlocks = new LinkedList<>();
        HashMap<String, BasicBlock> blockMap = new HashMap<>();
        HashSet<String> isDead = new HashSet<>();
        for (var nxt: it.blocks) {
            if (isDead.contains(nxt.label)) {
                continue;
            }
            while (nxt.insts.size() == 1) {
                var inst = nxt.insts.getFirst();
                if (inst instanceof IRJump jump) {
                    var toBlock = it.blockMap.get(jump.label);
                    if (toBlock.pred.size() == 1) {
                        nxt.insts = toBlock.insts;
                        blockMap.put(toBlock.label, nxt);
                        isDead.add(toBlock.label);
                    }
                    else {
                        break;
                    }
                }
                else {
                    break;
                }
            }
        }
        for (var block: it.blocks) {
            if (isDead.contains(block.label)) {
                continue;
            }
            for (var inst: block.insts) {
                if (!(inst instanceof IRPhi phi)) {
                    break;
                }
                for (int i = 0; i < phi.fromBlock.size(); i++) {
                    if (blockMap.containsKey(phi.fromBlock.get(i).label)) {
                        phi.fromBlock.set(i, blockMap.get(phi.fromBlock.get(i).label));
                        break;
                    }
                }
            }
            newBlocks.add(block);
        }
        it.blocks = newBlocks;
    }
}
