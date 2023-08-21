package Assembly.Operand;

import MIR.Entity.IRBoolConst;
import MIR.Entity.IRGlobalVar;
import MIR.Entity.IRIntConst;
import MIR.Entity.IRNullConst;

public class GlobalValue extends Global {
    public int word, size;
    public GlobalValue(IRGlobalVar gVar) {
        super(gVar.name);
        if (gVar.initVal instanceof IRIntConst) {
            word = ((IRIntConst) gVar.initVal).val;
            size = 32;
        }
        if (gVar.initVal instanceof IRBoolConst) {
            word = ((IRBoolConst) gVar.initVal).val ? 1 : 0;
            size = 8;
        }
        if (gVar.initVal instanceof IRNullConst) {
            word = 0;
            size = 32;
        }
    }

    @Override
    public String toString() {
        String ret = "";
        ret += name + ":\n";
        ret += size == 32 ? ".word" : ".byte" + "\n";
        return ret;
    }
}
