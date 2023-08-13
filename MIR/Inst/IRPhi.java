package MIR.Inst;

import MIR.BasicBlock;
import MIR.Entity.Entity;
import MIR.Entity.IRRegister;
import MIR.Type.IRBaseType;

import java.util.ArrayList;

public class IRPhi extends IRBaseInst {
    public IRRegister res;
    public IRBaseType type;
    public ArrayList<BasicBlock> fromBlock = new ArrayList<>();
    public ArrayList<Entity> val = new ArrayList<>();

    public IRPhi(BasicBlock parentBlock, IRRegister res, IRBaseType type) {
        super(parentBlock);
        this.res = res;
        this.type = type;
    }

    @Override
    public String toString() {
        String ret = res.toString() + " = phi " + type.toString() + " ";
        for (int i = 0; i < fromBlock.size(); i++) {
            ret += "[ " + val.get(i).toString() + ", " + fromBlock.get(i).toString() + " ]";
            if (i != fromBlock.size() - 1) {
                ret += ", ";
            }
        }
        return ret;
    }
}
//done