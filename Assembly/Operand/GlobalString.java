package Assembly.Operand;

import MIR.Entity.IRGlobalVar;
import MIR.Entity.IRStringConst;

public class GlobalString extends Global {
    String string;
    public GlobalString(IRStringConst gString) {
        super("str." + gString.id);
        string = gString.val;
    }

    @Override
    public String toString() {
        String ret = "";
        ret += name + ":\n";
        ret += "    .string " + "\"" + string + "\"\n";
        ret += "\n";
        return ret;
    }
}
