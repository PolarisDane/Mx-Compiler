package MIR.Inst;

import Backend.IRVisitor;
import MIR.BasicBlock;
import MIR.Entity.Entity;
import MIR.Entity.IRRegister;

import java.util.HashSet;

public class IRRet extends IRBaseInst {
    public Entity returnVal;

    public IRRet(BasicBlock parentBlock, Entity returnVal) {
        super(parentBlock);
        this.returnVal = returnVal;
    }

    @Override
    public String toString() {
        if (returnVal == null) {
            return "ret void";
        }
        return "ret " + returnVal.type + " " + returnVal.toString();
    }

    @Override
    public HashSet<IRRegister> getUse() {
        HashSet<IRRegister> use = new HashSet<>();
        if (returnVal instanceof IRRegister reg) {
            use.add(reg);
        }
        return use;
    }

    @Override
    public IRRegister getDef() {
        return null;
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
