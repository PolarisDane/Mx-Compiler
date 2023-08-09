package MIR.Inst;

import MIR.BasicBlock;
import MIR.Entity.Entity;
import MIR.Entity.IRRegister;
import MIR.Type.IRBaseType;

import java.util.ArrayList;

public class IRGetElementPtr extends IRBaseInst {
    public IRRegister res;
    public Entity ptr;
    public IRBaseType type;
    public ArrayList<Entity> idx;

    public IRGetElementPtr(BasicBlock parentBlock, Entity ptr, IRBaseType type) {
        super(parentBlock);
        this.res = res;
        this.ptr = ptr;
    }

    @Override
    public String toString() {
        String ret = res.toString() + " = getelementptr " + type.toString() + ", ptr " + ptr.toString();
        for (int i = 0; i < idx.size(); i++) {
            ret += idx.get(i).type.toString() + " " + idx.get(i).toString();
            if (i != idx.size() - 1) {
                ret += ", ";
            }
        }
        return ret;
    }
}
//done
