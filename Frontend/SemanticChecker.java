package Frontend;

import AST.*;
import Utils.*;
import Utils.error.*;

public class SemanticChecker implements ASTVisitor {
    public Builtin builtin = new Builtin();
    public Scope curScope;
    public GlobalScope gScope;

    public SemanticChecker(GlobalScope gScope) {
        this.gScope = gScope;
        this.curScope = gScope;
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

    }

    @Override
    public void visit(TernaryExprNode it) {
        if (!it.condition.type.equals(builtin.BoolType)) {
            throw new semanticError("Condition type isn't bool in ternary expression", it.pos);
        }
        it.condition.accept(this);
        if (!it.trueVal.type.equals(it.falseVal.type)) {
            throw new semanticError("Expression of different types in ternary expression", it.pos);
        }
        it.trueVal.accept(this);
        it.falseVal.accept(this);
    }

    @Override
    public void visit(AssignExprNode it) {
        System.out.println(it.assignTo.type.content);
        System.out.println(it.assignVal.type.content);
        if (!it.assignTo.type.equals(it.assignVal.type)) {
            throw new semanticError("Assigning value of a different type to " + it.assignTo, it.pos);
        }
    }

    @Override
    public void visit(AtomExprNode it) {
//        System.out.println(it.content);
        if (it.isIdentifier) {
            if (!curScope.containsVariable(it.content, true)) {
                throw new semanticError("Variable " + it.content + " not defined", it.pos);
            }
            it.type = curScope.getVariableType(it.content, true);
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
        it.exprList.accept(this);
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
//        System.out.println("Return!");
        it.returnVal.accept(this);
        curScope.returned = it.returnVal.type;
    }

    @Override
    public void visit(DefineVarStmtNode it) {
        if (!gScope.containsClass(it.type.content, it.pos)) {
            throw new semanticError("Defined type " + it.type.content + " not found", it.pos);
        }
        for (var nxt: it.assigns) {
            nxt.accept(this);
        }
    }

    @Override
    public void visit(VarAssignStmtNode it) {
        if (it.assignVal != null) {
            it.assignVal.accept(this);
            if (!it.type.equals(it.assignVal.type)) {
                throw new semanticError("Assigning value of a different type to " + it.assignTo, it.pos);
            }
        }
        curScope.defineVariable(it.assignTo, it.type, it.pos);
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
        curScope = new Scope(curScope);
        if (it.paramsList != null) {
            it.paramsList.accept(this);
        }
        System.out.println("stmts size: " + it.stmts.size());
        for (var nxt: it.stmts) {
            nxt.accept(this);
        }
        if (!it.type.equals(builtin.VoidType) && curScope.returned == null) {
            throw new semanticError("Function " + it.funcName + " missing return statement", it.pos);
        }
        if (!it.type.equals(curScope.returned)) {
            throw new semanticError("Function " + it.funcName + " returned value is not of expected type", it.pos);
        }
        curScope = curScope.parentScope;//return to original scope
    }

    @Override
    public void visit(SuiteNode it) {

    }

    @Override
    public void visit(BlockNode it) {

    }

    @Override
    public void visit(TypeNameNode it) {

    }

    @Override
    public void visit(RootNode it) {
        DefineFunctionNode mainFunc = gScope.getFunc("main", new position(1, 0));
        if (mainFunc.paramsList != null || !mainFunc.type.equals(builtin.IntType)) {
            throw new semanticError("Main function defined incorrectly", mainFunc.pos);
        }
        for (var nxt: it.Defs) {
            nxt.accept(this);
        }
    }
}
