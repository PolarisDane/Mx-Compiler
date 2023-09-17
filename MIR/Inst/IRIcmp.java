package MIR.Inst;

import Backend.IRVisitor;
import MIR.BasicBlock;
import MIR.Entity.*;
import MIR.Type.IRBaseType;

import java.util.HashSet;

public class IRIcmp extends IRBaseInst {
    public IRRegister res;
    public String cond;
    public IRBaseType type;
    public Entity op1;
    public Entity op2;

    public IRIcmp(BasicBlock parentBlock, IRRegister res, String cond, IRBaseType type, Entity op1, Entity op2) {
        super(parentBlock);
        this.res = res;
        this.cond = cond;
        this.type = type;
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public String toString() {
        return res.toString() + " = icmp " + cond.toString() + " " + type.toString() + " " + op1.toString() + ", " + op2.toString();
    }

    @Override
    public HashSet<IRRegister> getUse() {
        HashSet<IRRegister> use = new HashSet<>();
        if (op1 instanceof IRRegister reg1) {
            use.add(reg1);
        }
        if (op2 instanceof IRRegister reg2) {
            use.add(reg2);
        }
        return use;
    }

    @Override
    public IRRegister getDef() {
        return res;
    }

    @Override
    public Entity getConst() {
        Entity entity = null;
        if (op1 instanceof IRConst && op2 instanceof IRConst) {
            switch(cond) {
                case "sge":
                    entity = new IRBoolConst(((IRIntConst) op1).val >= ((IRIntConst) op2).val);
                    break;
                case "sle":
                    entity = new IRBoolConst(((IRIntConst) op1).val <= ((IRIntConst) op2).val);
                    break;
                case "sgt":
                    entity = new IRBoolConst(((IRIntConst) op1).val > ((IRIntConst) op2).val);
                    break;
                case "slt":
                    entity = new IRBoolConst(((IRIntConst) op1).val < ((IRIntConst) op2).val);
                    break;
                case "eq":
                    entity = new IRBoolConst(((IRIntConst) op1).val == ((IRIntConst) op2).val);
                    break;
                case "ne":
                    entity = new IRBoolConst(((IRIntConst) op1).val != ((IRIntConst) op2).val);
                    break;
            }
        }
        return entity;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
//done
