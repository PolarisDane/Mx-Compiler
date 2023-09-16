package Optimizer;

import MIR.Function;
import MIR.Program;

public class ConstPropagation {
    Program program;

    public ConstPropagation(Program program) {
        this.program = program;
    }

    public void propagate() {
        for (var nxt: program.functions) {
            visitFunc(nxt);
        }
    }

    public void visitFunc(Function it) {

    }
}
