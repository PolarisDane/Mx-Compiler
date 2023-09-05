package Optimizer;

import MIR.BasicBlock;
import MIR.Function;
import MIR.Inst.IRAlloca;
import MIR.Inst.IRLoad;
import MIR.Inst.IRStore;
import MIR.Program;

import java.util.ArrayList;

public class Mem2Reg {
    Program program;
    Function curFunc;
    ArrayList<BasicBlock> def;
    ArrayList<BasicBlock> use;
    public Mem2Reg(Program program) {
        this.program = program;
    }

    public void optimize() {
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
                    use.add(nxt);
                }
                if (inst instanceof IRLoad && ((IRLoad) inst).reg.equals(it.res)) {
                    def.add(nxt);
                }
            }
        }
        if (use.isEmpty()) {

        }//can be discarded
        for (BasicBlock block: nxt.DomFrontier) {

        }//visit Dominance Frontier
    }

    public void visitFunc(Function it) {
        for (var inst: it.allocaInst) {
            promoteAlloca(inst);
        }
    }
}
