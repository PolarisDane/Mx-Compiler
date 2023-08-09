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

    }

    @Override
    public void visit(ArrayExprNode it) {

    }

    @Override
    public void visit(MemberExprNode it) {

    }

    @Override
    public void visit(RightSelfExprNode it) {

    }

    @Override
    public void visit(LeftSelfExprNode it) {

    }

    @Override
    public void visit(SingleExprNode it) {

    }

    @Override
    public void visit(BinaryExprNode it) {
        it.lop.accept(this);
        IRRegister res = new IRRegister("binary_op", new IRIntType(32));
        if (!it.op.equals("&&") && !it.op.equals("||")) {
            String op;
            it.rop.accept(this);
            switch(it.op) {
                case "+":
                    op = "add";
                case "-":
                    op = "sub";
                case "*":
                    op = "mul";
                case "/":
                    op = "sdiv";
                case "%":
                    op = "srem";
                case "&":
                    op = "and";
                case "|":
                    op = "or";
                case "<<":
                    op = "shl";
                case ">>":
                    op = "ashr";
                case "^":
                    op = "xor";
                    curBlock.addInst(new IRBinaryOp(curBlock, res, new IRIntType(32), op, it.lop.entity, it.rop.entity));
                    break;
                case ">=":
                    op = "sge";
                case "<=":
                    op = "sle";
                case ">":
                    op = "sgt";
                case "<":
                    op = "slt";
                case "==":
                    op = "eq";
                case "!=":
                    op = "ne";
                    curBlock.addInst(new IRIcmp(curBlock, res, op, new IRIntType(32), it.lop.entity, it.rop.entity));
                    break;
            }
        }//non short-circuit value
        else {

        }//short-circuit value
    }

    @Override
    public void visit(TernaryExprNode it) {

    }

    @Override
    public void visit(AssignExprNode it) {

    }

    @Override
    public void visit(AtomExprNode it) {
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

    }

    @Override
    public void visit(ForStmtNode it) {

    }

    @Override
    public void visit(WhileStmtNode it) {

    }

    @Override
    public void visit(FlowStmtNode it) {

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
            if (it.assignVal.entity == null) {
                IRRegister res = new IRRegister("var", getIRType(it.type));
                curBlock.addInst(new IRLoad(curBlock, getIRType(it.type), res, curScope.entityMap.get(it.assignVal.content)));
            }
            curBlock.addInst(new IRStore(curBlock, addr, it.assignVal.entity));
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
