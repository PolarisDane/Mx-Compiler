package MIR.Inst;

import MIR.BasicBlock;
import MIR.Entity.Entity;
import MIR.Entity.IRRegister;
import MIR.Type.IRBaseType;
import MIR.Type.IRVoidType;

import java.util.ArrayList;

public class IRCall extends IRBaseInst {
    public IRRegister res;
    public IRBaseType type;
    public String funcName;
    public ArrayList<Entity> args;

    public IRCall(BasicBlock parentBlock, IRRegister res, IRBaseType type, String funcName) {
        super(parentBlock);
        this.res = res;
        this.type = type;
        this.funcName = funcName;
    }

    @Override
    public String toString() {
        String ret;
        ret = "call void @" + funcName + "(";
        for (int i = 0; i < args.size(); i++) {
            ret += args.get(i).type.toString() + " " + args.get(i).toString();
            if (i != args.size() - 1) {
                ret += ", ";
            }
        }
        ret = ret + ")";
        return ret;
    }
}
//done