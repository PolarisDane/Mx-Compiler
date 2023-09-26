package Optimizer;

import MIR.Program;

public class IROptimizier {
    Program program;

    public IROptimizier(Program program) {
        this.program = program;
    }

    public void optimize() {
        new Mem2Reg(program).optimize();
//        System.out.println(program.toString());
        new DeadCodeEliminator(program).eliminate();
//        System.out.println(program.toString());
        new ConstPropagation(program).propagate();
        new BlockMerger(program).mergeBlock();
    }
}
