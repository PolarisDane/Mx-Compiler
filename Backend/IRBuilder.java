package Backend;

import AST.*;
import MIR.*;
import MIR.Entity.*;
import MIR.Inst.*;
import MIR.Type.*;
import Utils.*;

public class IRBuilder implements ASTVisitor {
    public Builtin builtin = new Builtin();
    public Scope curScope;
    public GlobalScope gScope;
    public BasicBlock curBlock = new BasicBlock("entry");

    public IRBuilder(GlobalScope gScope) {
        this.gScope = gScope;
        this.curScope = gScope;
    }

    public Entity getVal(ExprNode expr, boolean is_i1) {
        IRRegister res = new IRRegister("", new IRIntType(1));
        if (expr.entity != null) {
            if (is_i1 && expr.entity.type.size == 8) {
                curBlock.addInst(new IRTrunc(curBlock, res, expr.entity.type, expr.entity, new IRIntType(1)));
                return res;
            }//is stored as 8-bit and used as 1-bit
            return expr.entity;
        }//const value already given
        IRRegister loadVal = new IRRegister("", expr.addr.type);
        curBlock.addInst(new IRLoad(curBlock, expr.addr.type, loadVal, expr.addr));
        expr.entity = loadVal;//for later use
        if (is_i1 && loadVal.type.size == 8) {
            curBlock.addInst(new IRTrunc(curBlock, res, loadVal.type, loadVal, new IRIntType(1)));
            return res;
        }
        return expr.entity;
    }

    public IRBaseType getIRType(Type type) {
        IRBaseType IRType = null;
        switch (type.content) {
            case "int":
                IRType = new IRIntType(32);
                break;
            case "bool":
                IRType = new IRIntType(8);
                break;
            case "string":
                IRType = new IRPtrType(new IRArrayType(new IRIntType(8), 0));
                break;
            case "void":
                IRType = new IRVoidType();
                break;
            default:
                //for other class
        }
        if (type.dim > 0) {
            IRType = new IRPtrType(IRType, type.dim);
        }
        return IRType;
    }

    @Override
    public void visit(FuncCallNode it) {

    }

    @Override
    public void visit(NewExprNode it) {

    }

    @Override
    public void visit(WrapExprNode it) {
        //should be inaccessible
    }

    @Override
    public void visit(ArrayExprNode it) {

    }

    @Override
    public void visit(MemberExprNode it) {

    }

    @Override
    public void visit(RightSelfExprNode it) {
        it.obj.accept(this);
        IRRegister res = new IRRegister("self_expr" , new IRIntType(32));
        Entity ori = getVal(it.obj, false);
        if (it.op.equals("++")) {
            curBlock.addInst(new IRBinaryOp(curBlock, res, new IRIntType(32), "add", ori, new IRIntConst(1)));
            curBlock.addInst(new IRStore(curBlock, it.obj.addr, res));
        }
        else if (it.op.equals("--")) {
            curBlock.addInst(new IRBinaryOp(curBlock, res, new IRIntType(32), "sub", ori, new IRIntConst(1)));
            curBlock.addInst(new IRStore(curBlock, it.obj.addr, res));
        }
        it.entity = ori;
    }

    @Override
    public void visit(LeftSelfExprNode it) {
        it.obj.accept(this);
        IRRegister res = new IRRegister("self_expr" , new IRIntType(32));
        if (it.op.equals("++")) {
            curBlock.addInst(new IRBinaryOp(curBlock, res, new IRIntType(32), "add", getVal(it.obj, false), new IRIntConst(1)));
            curBlock.addInst(new IRStore(curBlock, it.obj.addr, res));
        }
        else if (it.op.equals("--")) {
            curBlock.addInst(new IRBinaryOp(curBlock, res, new IRIntType(32), "sub", getVal(it.obj, false), new IRIntConst(1)));
            curBlock.addInst(new IRStore(curBlock, it.obj.addr, res));
        }
        it.entity = res;
    }

    @Override
    public void visit(SingleExprNode it) {
        it.obj.accept(this);
        IRRegister res = new IRRegister("single_expr" , new IRIntType(32));
        if (it.op.equals("+")) {
            //nothing here
        }
        else if (it.op.equals("-")) {
            curBlock.addInst(new IRBinaryOp(curBlock, res, new IRIntType(32), "sub", new IRIntConst(0), getVal(it.obj, false)));
        }
        else if (it.op.equals("!")) {
            curBlock.addInst(new IRBinaryOp(curBlock, res, new IRIntType(8), "xor", new IRIntConst(1), getVal(it.obj, false)));
        }
        else if (it.op.equals("~")) {
            curBlock.addInst(new IRBinaryOp(curBlock, res, new IRIntType(32), "xor", new IRIntConst(-1), getVal(it.obj, false)));
        }
        it.entity = res;
    }

    @Override
    public void visit(BinaryExprNode it) {
        it.lop.accept(this);
        IRRegister res = new IRRegister("binary_op", new IRIntType(32));
        if (!it.op.equals("&&") && !it.op.equals("||")) {
            String op = null;
            it.rop.accept(this);
            boolean flag = false;
            switch(it.op) {
                case "+":
                    op = "add";
                    flag = true;
                    break;
                case "-":
                    op = "sub";
                    flag = true;
                    break;
                case "*":
                    op = "mul";
                    flag = true;
                    break;
                case "/":
                    op = "sdiv";
                    flag = true;
                    break;
                case "%":
                    op = "srem";
                    flag = true;
                    break;
                case "&":
                    op = "and";
                    flag = true;
                    break;
                case "|":
                    op = "or";
                    flag = true;
                    break;
                case "<<":
                    op = "shl";
                    flag = true;
                    break;
                case ">>":
                    op = "ashr";
                    flag = true;
                    break;
                case "^":
                    op = "xor";
                    flag = true;
                    break;
                case ">=":
                    op = "sge";
                    break;
                case "<=":
                    op = "sle";
                    break;
                case ">":
                    op = "sgt";
                    break;
                case "<":
                    op = "slt";
                    break;
                case "==":
                    op = "eq";
                    break;
                case "!=":
                    op = "ne";
                    break;
            }
            if (flag) {
                curBlock.addInst(new IRBinaryOp(curBlock, res, new IRIntType(32), op, getVal(it.lop, false), getVal(it.rop, false)));
            }
            else {
                curBlock.addInst(new IRIcmp(curBlock, res, op, new IRIntType(32), getVal(it.lop, false), getVal(it.rop, false)));
            }
        }//non short-circuit value
        else {

        }//short-circuit value
        it.addr = res;
    }

    @Override
    public void visit(TernaryExprNode it) {

    }

    @Override
    public void visit(AssignExprNode it) {

    }

    @Override
    public void visit(AtomExprNode it) {
        System.out.println(it.content);
        if (it.isIdentifier) {
            it.addr = curScope.entityMap.get(it.content);
        }
        else {
            if (it.type.equals(builtin.IntType)) {
                int val = Integer.parseInt(it.content);
                it.entity = new IRIntConst(val);
            }
            else if (it.type.equals(builtin.BoolType)) {
                boolean val = it.content.equals("true") ? true : false;
                it.entity = new IRBoolConst(val);
            }
            else if (it.type.equals(builtin.NullType)) {
                it.entity = new IRNullConst();
            }
            else if (it.type.equals(builtin.StringType)) {
                it.entity = new IRStringConst(it.content);
            }
            else if (it.content.equals("this")) {

            }
        }
    }

    @Override
    public void visit(ExprListNode it) {
        for (var nxt: it.exprs) {
            nxt.accept(this);
        }
    }

    @Override
    public void visit(ExprStmtNode it) {
        if (it.exprList != null) {
            it.exprList.accept(this);
        }
    }

    @Override
    public void visit(IfStmtNode it) {
        it.condition.accept(this);
        BasicBlock prev = curBlock;
        BasicBlock trueBranch = new BasicBlock("if.then");
        BasicBlock falseBranch = new BasicBlock("if.else");
        BasicBlock endIf = new BasicBlock("if.end");

        curScope = new Scope(curScope);
        curBlock = trueBranch;
        it.trueThenWork.accept(this);
        trueBranch.addInst(new IRJump(curBlock, endIf.label));
        curScope = curScope.parentScope;

        if (it.falseThenWork != null) {
            curScope = new Scope(curScope);
            curBlock = falseBranch;
            it.falseThenWork.accept(this);
            falseBranch.addInst(new IRJump(curBlock, endIf.label));
            curScope = curScope.parentScope;
            prev.addInst(new IRBranch(prev, getVal(it.condition, true), trueBranch, falseBranch));
        }
        else {
            prev.addInst(new IRBranch(prev, getVal(it.condition, true), trueBranch, endIf));
        }
        curBlock = endIf;
    }

    @Override
    public void visit(ForStmtNode it) {

    }

    @Override
    public void visit(WhileStmtNode it) {

    }

    @Override
    public void visit(FlowStmtNode it) {
        //should be inaccessible
    }

    @Override
    public void visit(BreakStmtNode it) {

    }

    @Override
    public void visit(ContinueStmtNode it) {

    }

    @Override
    public void visit(ReturnStmtNode it) {

    }

    @Override
    public void visit(DefineVarStmtNode it) {
        for (var nxt: it.assigns) {
            nxt.accept(this);
        }
    }

    @Override
    public void visit(VarAssignStmtNode it) {
        IRBaseType IRType = getIRType(it.type);
        IRRegister addr = new IRRegister(it.assignTo, IRType);
        curScope.entityMap.put(addr.name, addr);
        curBlock.addInst(new IRAlloca(curBlock, IRType, addr));
        if (it.assignVal != null) {
            it.assignVal.accept(this);
            curBlock.addInst(new IRStore(curBlock, addr, getVal(it.assignVal, false)));
        }
    }

    @Override
    public void visit(ParamsListNode it) {

    }

    @Override
    public void visit(DefineClassNode it) {

    }

    @Override
    public void visit(DefineConstructFunctionNode it) {

    }

    @Override
    public void visit(DefineFunctionNode it) {

    }

    @Override
    public void visit(BlockNode it) {

    }

    @Override
    public void visit(TypeNameNode it) {
        //should be inaccessible
    }

    @Override
    public void visit(RootNode it) {
        for (var nxt: it.Defs) {
            nxt.accept(this);
        }
        System.out.println(curBlock.toString());
    }
}
