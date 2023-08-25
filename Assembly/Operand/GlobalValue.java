package Assembly.Operand;

import MIR.Entity.*;

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
        if (gVar.initVal instanceof IRStringConst) {
            word = ((IRStringConst) gVar.initVal).id;
            size = -1;
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
        if (size == -1) {
            ret += "    .word   str." + word + "\n\n";
        }
        else {
            ret += size == 32 ? "   .word  " : "    .byte  ";
            ret += word + "\n\n";
        }
        return ret;
    }
}
