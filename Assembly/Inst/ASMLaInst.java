package Assembly.Inst;

import Assembly.Operand.*;

public class ASMLaInst extends ASMBaseInst {
    public String symbol;

    public ASMLaInst(Reg rd, String symbol) {
        this.rd = rd;
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "la " + rd.toString() + ", " + symbol;
    }
}
