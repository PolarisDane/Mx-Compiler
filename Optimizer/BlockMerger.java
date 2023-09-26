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
            if (!blockMap.containsKey(nxt.label)) {
                blockMap.put(nxt.label, nxt);
            }
            while (true) {
                var inst = nxt.insts.getLast();
                if (inst instanceof IRJump jump) {
                    var toBlock = it.blockMap.get(jump.label);
                    if (toBlock.label.equals("return")) {
                        break;
                    }
                    if (toBlock.pred.size() == 1) {
                        nxt.insts.remove(inst);
                        nxt.insts.addAll(toBlock.insts);
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
                    String replacement = phi.fromBlock.get(i).label;
                    while (blockMap.containsKey(replacement) && !replacement.equals(blockMap.get(replacement).label)) {
                        replacement = blockMap.get(replacement).label;
                    }
                    phi.fromBlock.set(i, blockMap.get(replacement));
                }
            }
            newBlocks.add(block);
        }
        it.blocks = newBlocks;
    }
}
