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
        DefineFunctionNode funcNode = gScope.getFunc(it.func, it.pos);
        if (funcNode.paramsList.identifiers.size() != it.args.exprs.size()) {
            throw new semanticError("Function " + it.func + " expected " + funcNode.paramsList.identifiers.size() + " arguments, got " + it.args.exprs.size(), it.pos);
        }
        it.args.accept(this);
        int siz = it.args.exprs.size();
        for (int i = 0; i < siz; i++) {
            Type expected = funcNode.paramsList.types.get(i);
            Type given = it.args.exprs.get(i).type;
            if (!given.equals(expected)) {
                throw new semanticError("Function " + it.func + " argument " + funcNode.paramsList.identifiers.get(i) + " expected type " + expected.content + ", got " + given.content, it.pos);
            }
        }
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
//        if (!curScope.containsVariable(it.obj.content, true)) {
//            throw new semanticError("Variable " + it.obj.content + " not defined", it.pos);
//        }
        it.obj.accept(this);
        if (curScope.getVariableType(it.obj.content, true) != null && !curScope.getVariableType(it.obj.content, true).equals(builtin.IntType)) {
            throw new semanticError("Variable " + it.obj.content + " type not expected", it.pos);
        }
        if (!it.obj.isAssignable()) {
            throw new semanticError("Caught right value when operation expected left value", it.pos);
        }
    }

    @Override
    public void visit(LeftSelfExprNode it) {
//        if (!curScope.containsVariable(it.obj.content, true)) {
//            throw new semanticError("Variable " + it.obj.content + " not defined", it.pos);
//        }
        it.obj.accept(this);
        if (curScope.getVariableType(it.obj.content, true) != null && !curScope.getVariableType(it.obj.content, true).equals(builtin.IntType)) {
            throw new semanticError("Variable " + it.obj.content + " type not expected", it.pos);
        }
        if (!it.obj.isAssignable()) {
            throw new semanticError("Caught right value when operation expected left value", it.pos);
        }
    }

    @Override
    public void visit(SingleExprNode it) {
        it.obj.accept(this);
        if (it.op.equals("!")) {
            if (curScope.getVariableType(it.obj.content, true) != null && !curScope.getVariableType(it.obj.content, true).equals(builtin.BoolType)){
                throw new semanticError("Variable " + it.obj.content + " type not expected", it.pos);
            }
        }
        else if (it.op.equals("+") || it.op.equals("-") || it.op.equals("~")) {
            if (curScope.getVariableType(it.obj.content, true) != null && !curScope.getVariableType(it.obj.content, true).equals(builtin.IntType)) {
                throw new semanticError("Variable " + it.obj.content + " type not expected", it.pos);
            }
        }
    }

    @Override
    public void visit(BinaryExprNode it) {
        if (!it.lop.type.equals(it.rop.type)) {
            throw new semanticError("Two operands do not have the same type", it.pos);
        }
        it.lop.accept(this);
        it.rop.accept(this);
        if (it.lop.type.equals("bool")) {
            if (!it.op.equals("&&") && !it.op.equals("||") && !it.op.equals("==") && !it.op.equals("!=")) {
                throw new semanticError("Operation not allowed for bool type", it.pos);
            }
        }
        else if (it.lop.type.equals("int")) {
            if (it.op.equals("&&") || !it.op.equals("||")) {
                throw new semanticError("Operation not allowed for int type", it.pos);
            }
        }
        else if (it.lop.type.equals("string")) {
            if (!it.op.equals("+") && !it.op.equals("==") && !it.op.equals("!=") && !it.op.equals("<") && !it.op.equals(">") && !it.op.equals("<=") && !it.op.equals(">=")){
                throw new semanticError("Operation not allowed for string type", it.pos);
            }
        }
        else {
            if (!it.op.equals("==") && !it.op.equals("!-")) {
                throw new semanticError("Operation not allowed for class", it.pos);
            }
        }
    }//not done

    @Override
    public void visit(TernaryExprNode it) {
        if (!it.condition.type.equals(builtin.BoolType)) {
            throw new semanticError("Condition type isn't bool type in ternary expression", it.pos);
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
        if (!curScope.inLoop) {
            throw new semanticError("Break statement found outside loop", it.pos);
        }
    }

    @Override
    public void visit(ContinueStmtNode it) {
        if (!curScope.inLoop) {
            throw new semanticError("Continue statement found outside loop", it.pos);
        }
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
        int siz = it.identifiers.size();
        for (int i = 0; i < siz; i++) {
            if (!gScope.containsClass(it.types.get(i).content, it.pos)) {
                throw new semanticError("Defined type " + it.types.get(i).content + " not found", it.pos);
            }
            curScope.defineVariable(it.identifiers.get(i), it.types.get(i), it.pos);
        }
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
        if (!it.type.equals(builtin.VoidType) && !it.type.equals(curScope.returned)) {
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
