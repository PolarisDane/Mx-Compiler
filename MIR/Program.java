package MIR;

import MIR.Entity.*;
import MIR.Type.*;

import java.util.ArrayList;

public class Program {
    public ArrayList<IRGlobalVar> globalVar = new ArrayList<>();
    public ArrayList<Function> functions = new ArrayList<>();
    public ArrayList<IRStructType> classes = new ArrayList<>();

    public Program() {}

    public String toString() {
        String ret = "";
        for (var nxt: globalVar) {
            ret += nxt.toString() + " = global " + nxt.type.toString() + " " + nxt.initVal.toString() + "\n";
        }
        ret += "\n";
        for (var nxt: classes) {
            ret += nxt.toString() + "\n";
        }
        ret += "\n";
        for (var nxt: functions) {
            ret += nxt.toString() + "\n\n";
        }
        ret += "\n";
        return ret;
    }
}
