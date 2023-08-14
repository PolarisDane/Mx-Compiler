package MIR;

import MIR.Entity.IRRegister;
import MIR.Inst.IRAlloca;
import MIR.Type.IRBaseType;

import java.util.ArrayList;
import java.util.LinkedList;

public class Function {
    public String funcName;
    public IRBaseType type;
    public LinkedList<BasicBlock> blocks = new LinkedList<>();
    public ArrayList<IRAlloca> allocaInst = new ArrayList<>();
    public ArrayList<IRRegister> params = new ArrayList<>();
    public IRRegister retReg;

    public Function(String funcName, IRBaseType type) {
        this.funcName = funcName;
        this.type = type;
        this.retReg = new IRRegister("funcRet", type);
    }

    public String toString() {
        String ret = "";
        ret = "define dso_local " + type.toString() + " @" + funcName + "(";
        for (int i = 0; i < params.size(); i++) {
            ret += params.get(i).type.toString() + " " + params.get(i).toString();
            if (i != params.size() - 1) {
                ret += ", ";
            }
        }
        ret += ") {\n";
        for (var inst: allocaInst) {
            blocks.get(0).insts.addFirst(inst);
        }
        for (var block: blocks) {
            ret += block.toString() + "\n";
        }
        ret += "}\n";
        return ret;
    }
}
