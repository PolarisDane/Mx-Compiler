package Assembly.Operand;

import MIR.Entity.IRGlobalVar;
import MIR.Entity.IRStringConst;

public class GlobalString extends Global {
    String string;
    public GlobalString(IRGlobalVar gVar) {
        super(gVar.name);
        string = ((IRStringConst) gVar.initVal).val;
    }

    @Override
    public String toString() {
        String ret = "";
        ret += name + ":\n";
        ret += ".string " + "\"" + string + "\"\n";
        return ret;
    }
}
