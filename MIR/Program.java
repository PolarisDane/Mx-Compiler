package MIR;

import MIR.Entity.*;
import MIR.Inst.*;
import MIR.Type.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Program {
    public ArrayList<IRGlobalVar> globalVar = new ArrayList<>();
    public ArrayList<Function> functions = new ArrayList<>();
    public ArrayList<IRStructType> classes = new ArrayList<>();
    public HashMap<String, IRStringConst> IRstringMap = new HashMap<>();
    public ArrayList<IRFuncDeclare> IRBuiltinFunc = new ArrayList<>();

    public Program() {}

    public String toString() {
        String ret = "";
        for (IRStringConst val: IRstringMap.values()) {
            ret += val.toString() + " = constant " + "[" + (val.val.length() + 1) + " x i8] c\"" + val.val + "\\00\"\n";
        }
        for (var nxt: globalVar) {
            ret += nxt.toString() + " = global " + nxt.type.toString() + " " + nxt.initVal.toString() + "\n";
        }
        ret += "\n";
        for (var nxt: IRBuiltinFunc) {
            ret += nxt.toString() + "\n";
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
