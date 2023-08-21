package Assembly.Operand;

import MIR.Entity.Entity;
import MIR.Entity.IRBoolConst;
import MIR.Entity.IRIntConst;

public class Imm extends Operand {
    public int val = 0;
    public Imm(int val) {
        super();
        this.val = val;
    }

    public Imm(Entity val) {
        super();
        if (val instanceof IRIntConst) {
            this.val = ((IRIntConst) val).val;
        }
        if (val instanceof IRBoolConst) {
            this.val = ((IRBoolConst) val).val ? 1 : 0;
        }
    }

    @Override
    public String toString() {
        return "" + val;
    }
}
