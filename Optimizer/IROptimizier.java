package Optimizer;

import MIR.Program;

public class IROptimizier {
    Program program;

    public IROptimizier(Program program) {
        this.program = program;
    }

    public void optimize() {
        new Mem2Reg(program).optimize();
        new DeadCodeEliminator(program).eliminate();
        new ConstPropagation(program).propagate();
    }
}
