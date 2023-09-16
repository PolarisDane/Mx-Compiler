package MIR.Inst;

import Backend.IRVisitor;
import MIR.BasicBlock;
import MIR.Entity.Entity;
import MIR.Entity.IRRegister;
import MIR.Type.IRBaseType;

import java.util.ArrayList;
import java.util.HashSet;

public class IRGetElementPtr extends IRBaseInst {
    public IRRegister res;
    public Entity ptr;
    public IRBaseType type;
    public ArrayList<Entity> idx = new ArrayList<>();

    public IRGetElementPtr(BasicBlock parentBlock, Entity ptr, IRBaseType type, IRRegister res) {
        super(parentBlock);
        this.res = res;
        this.ptr = ptr;
        this.type = type;
    }

    @Override
    public String toString() {
        String ret = res.toString() + " = getelementptr " + type.toString() + ", ptr " + ptr.toString();
        for (int i = 0; i < idx.size(); i++) {
            ret += ", " + idx.get(i).type.toString() + " " + idx.get(i).toString();
        }
        return ret;
    }

    @Override
    public HashSet<IRRegister> getUse() {
        HashSet<IRRegister> use = new HashSet<>();
        if (ptr instanceof IRRegister reg) {
            use.add(reg);
        }
        for (var nxt: idx) {
            if (nxt instanceof IRRegister reg) {
                use.add(reg);
            }
        }
        return use;
    }

    @Override
    public IRRegister getDef() {
        return res;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
//done
