package MIR.Inst;

import Backend.IRVisitor;
import MIR.BasicBlock;
import MIR.Entity.*;
import MIR.Type.IRBaseType;

import java.util.HashSet;

public class IRBinaryOp extends IRBaseInst {
    public IRRegister res;
    public String op;
    public IRBaseType type;
    public Entity op1;
    public Entity op2;
    public IRBinaryOp(BasicBlock parentBlock, IRRegister res, IRBaseType type, String op, Entity op1, Entity op2) {
        super(parentBlock);
        this.res = res;
        this.type = type;
        this.op = op;
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public String toString() {
        return res + " = " + op + " " + type.toString() + " " + op1.toString() + ", " +op2.toString();
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
            switch (op) {
                case "add":
                    entity = new IRIntConst(((IRIntConst) op1).val + ((IRIntConst) op2).val);
                    break;
                case "sub":
                    entity = new IRIntConst(((IRIntConst) op1).val - ((IRIntConst) op2).val);
                    break;
                case "mul":
                    entity = new IRIntConst(((IRIntConst) op1).val * ((IRIntConst) op2).val);
                    break;
                case "sdiv":
                    if (((IRIntConst) op2).val == 0) {
                        return null;
                    }
                    entity = new IRIntConst(((IRIntConst) op1).val / ((IRIntConst) op2).val);
                    break;
                case "srem":
                    if (((IRIntConst) op2).val == 0) {
                        return null;
                    }
                    entity = new IRIntConst(((IRIntConst) op1).val % ((IRIntConst) op2).val);
                    break;
                case "and":
                    entity = new IRIntConst(((IRIntConst) op1).val & ((IRIntConst) op2).val);
                    break;
                case "or":
                    entity = new IRIntConst(((IRIntConst) op1).val | ((IRIntConst) op2).val);
                    break;
                case "shl":
                    entity = new IRIntConst(((IRIntConst) op1).val << ((IRIntConst) op2).val);
                    break;
                case "ashr":
                    entity = new IRIntConst(((IRIntConst) op1).val >> ((IRIntConst) op2).val);
                    break;
                case "xor":
                    if (op2 instanceof IRBoolConst) {
                        entity = new IRBoolConst((((IRIntConst) op1).val ^ (((IRBoolConst) op2).val ? 1 : 0)) != 0);
                    }
                    else {
                        entity = new IRIntConst(((IRIntConst) op1).val ^ ((IRIntConst) op2).val);
                    }
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
