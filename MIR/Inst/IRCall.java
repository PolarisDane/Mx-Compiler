package MIR.Inst;

import Backend.IRVisitor;
import MIR.BasicBlock;
import MIR.Entity.Entity;
import MIR.Entity.IRRegister;
import MIR.Type.IRBaseType;
import MIR.Type.IRVoidType;

import java.util.ArrayList;
import java.util.HashSet;

public class IRCall extends IRBaseInst {
    public IRRegister res;
    public IRBaseType type;
    public String funcName;
    public ArrayList<Entity> args = new ArrayList<>();

    public IRCall(BasicBlock parentBlock, IRRegister res, IRBaseType type, String funcName) {
        super(parentBlock);
        this.res = res;
        this.type = type;
        this.funcName = funcName;
    }

    @Override
    public String toString() {
        String ret;
        if (type.content.equals("void")) {
            ret = "call " + type.toString() + " @" + funcName + "(";
        }
        else {
            ret = res.toString() + " = call " + type.toString() + " @" + funcName + "(";
        }
        for (int i = 0; i < args.size(); i++) {
            ret += args.get(i).type.toString() + " " + args.get(i).toString();
            if (i != args.size() - 1) {
                ret += ", ";
            }
        }
        ret = ret + ")";
        return ret;
    }

    @Override
    public HashSet<IRRegister> getUse() {
        HashSet<IRRegister> use = new HashSet<>();
        for (var nxt: args) {
            if (nxt instanceof IRRegister arg) {
                use.add(arg);
            }
        }
        return use;
    }

    @Override
    public IRRegister getDef() {
        return res;
    }

    @Override
    public Entity getConst() {
        return null;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
//done
