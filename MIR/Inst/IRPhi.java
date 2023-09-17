package MIR.Inst;

import Backend.IRVisitor;
import MIR.BasicBlock;
import MIR.Entity.Entity;
import MIR.Entity.IRBoolConst;
import MIR.Entity.IRConst;
import MIR.Entity.IRRegister;
import MIR.Type.IRBaseType;

import java.util.ArrayList;
import java.util.HashSet;

public class IRPhi extends IRBaseInst {
    public IRRegister res;
    public IRBaseType type;
    public IRRegister addr;
    public ArrayList<BasicBlock> fromBlock = new ArrayList<>();
    public ArrayList<Entity> val = new ArrayList<>();

    public IRPhi(BasicBlock parentBlock, IRRegister res, IRRegister addr, IRBaseType type) {
        super(parentBlock);
        this.res = res;
        this.addr = addr;
        this.type = type;
    }

    @Override
    public String toString() {
        String ret = res.toString() + " = phi " + type.toString() + " ";
        for (int i = 0; i < fromBlock.size(); i++) {
            ret += "[ " + val.get(i).toString() + ", %" + fromBlock.get(i).label + " ]";
            if (i != fromBlock.size() - 1) {
                ret += ", ";
            }
        }
        return ret;
    }

    @Override
    public HashSet<IRRegister> getUse() {
        HashSet<IRRegister> use = new HashSet<>();
        for (var nxt: val) {
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
    public Entity getConst() {
        boolean flag = false;
        Entity constVal = null;
        for (var nxt: val) {
            if (constVal == null) {
                constVal = nxt;
                flag = true;
            }
            else if (!constVal.equals(nxt)) {
                flag = false;
            }
        }
        if (flag){
            return constVal;
        }
        else {
            return null;
        }
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
//done
