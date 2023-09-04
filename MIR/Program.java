package MIR;

import Backend.IRVisitor;
import MIR.Entity.*;
import MIR.Inst.*;
import MIR.Type.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Program {
    public ArrayList<IRGlobalVar> globalVar = new ArrayList<>();
    public ArrayList<Function> functions = new ArrayList<>();
    public ArrayList<IRStructType> classes = new ArrayList<>();
    public HashMap<String, IRStructType> IRclassMap = new HashMap<>();
    public HashMap<String, IRStringConst> IRstringMap = new HashMap<>();
    public ArrayList<IRFuncDeclare> IRBuiltinFunc = new ArrayList<>();
//    public HashMap<String, BasicBlock> blockMap = new HashMap<>();

    public Program() {}

    public String handleString(String str) {
        return str.replace("\\", "\\\\").replace("\n", "\\0A").replace("\"","\\22");
    }

    public void setGlobalVar(ArrayList<IRGlobalVar> globalVar) {
        this.globalVar = globalVar;
    }

    public void work() {
        for (var func: functions) {
            func.work();
        }
    }

    public String toString() {
        String ret = "";
        for (IRStringConst val: IRstringMap.values()) {
            String str = handleString(val.val);
            ret += val.toString() + " = constant " + "[" + (val.val.length() + 1) + " x i8] c\"" + str + "\\00\"\n";
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
            ret += "%struct." + nxt.content + " = type { ";
            for (int i = 0; i < nxt.memberType.size(); i++) {
                ret += nxt.memberType.get(i).toString();
                if (i != nxt.memberType.size() - 1) {
                    ret += ", ";
                }
            }
            ret += " }\n";
        }
        ret += "\n";
        for (var nxt: functions) {
            ret += nxt.toString() + "\n\n";
        }
        ret += "\n";
        return ret;
    }

    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
