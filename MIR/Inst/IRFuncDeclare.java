package MIR.Inst;

import MIR.BasicBlock;
import MIR.Entity.IRRegister;
import MIR.Type.IRBaseType;

import java.util.ArrayList;
import java.util.LinkedList;

public class IRFuncDeclare {
    public String funcName;
    public IRBaseType type;
    public ArrayList<IRBaseType> params = new ArrayList<>();

    public IRFuncDeclare(String funcName, IRBaseType type, IRBaseType ... paramType) {
        this.funcName = funcName;
        this.type = type;
        if (paramType != null) {
            for (var it: paramType) {
                params.add(it);
            }
        }
    }

    public String toString() {
        String ret = "";
        ret = "declare " + type.toString() + " @" + funcName + "(";
        for (int i = 0; i < params.size(); i++) {
            ret += params.get(i).toString();
            if (i != params.size() - 1) {
                ret += ", ";
            }
        }
        ret += ")\n";
        return ret;
    }
}
