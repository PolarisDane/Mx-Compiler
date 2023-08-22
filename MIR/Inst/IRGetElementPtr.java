package MIR.Inst;

import Backend.IRVisitor;
import MIR.BasicBlock;
import MIR.Entity.Entity;
import MIR.Entity.IRRegister;
import MIR.Type.IRBaseType;

import java.util.ArrayList;

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

    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
//done
