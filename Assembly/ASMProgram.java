package Assembly;

import Assembly.Operand.GlobalString;
import Assembly.Operand.GlobalValue;

import java.util.ArrayList;

public class ASMProgram {
    public ArrayList<ASMFunction> functions = new ArrayList<>();
    public ArrayList<GlobalValue> gValues = new ArrayList<>();
    public ArrayList<GlobalString> gStrings = new ArrayList<>();

    public ASMProgram() {}

    public String toString() {
        String ret = "";
        if (!gValues.isEmpty()) {
            ret += "    .section data\n";
            for (var nxt : gValues) {
                ret += nxt.toString();
            }
        }
        if (!gStrings.isEmpty()) {
            ret += "    .section rodata\n";
            for (var nxt : gStrings) {
                ret += nxt.toString();
            }
        }
        for (var nxt: functions) {
            ret += nxt.toString();
        }
        return ret;
    }
}
