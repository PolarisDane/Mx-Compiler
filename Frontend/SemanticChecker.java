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
        DefineFunctionNode funcNode;
        if (it.inClass == null && curScope.inClass != null) {
            it.inClass = curScope.inClass;
        }
        if (it.inClass == null) {
            funcNode = gScope.getFunc(it.func, it.pos);
        }
        else {
            funcNode = it.inClass.funcMap.get(it.func);
            if (funcNode == null) {
                funcNode = gScope.getFunc(it.func, it.pos);
            }
        }
        if (funcNode.paramsList == null && it.args == null){
            it.type = funcNode.type;
            return;
        }
        if (funcNode.paramsList == null || it.args == null || funcNode.paramsList.identifiers.size() != it.args.exprs.size()) {
            throw new semanticError("Function " + it.func + " expected " + funcNode.paramsList.identifiers.size() + " arguments, got " + it.args.exprs.size(), it.pos);
        }
        it.args.accept(this);
        int siz = it.args.exprs.size();
        for (int i = 0; i < siz; i++) {
            Type expected = funcNode.paramsList.types.get(i);
            Type given = it.args.exprs.get(i).type;
            if ((expected.isReference) && given.equals(builtin.NullType)) {
                continue;
            }
            if (!given.equals(expected)) {
                throw new semanticError("Function " + it.func + " argument " + funcNode.paramsList.identifiers.get(i) + " expected type " + expected.content + ", got " + given.content, it.pos);
            }
        }
        it.type = funcNode.type;
    }

    @Override
    public void visit(NewExprNode it) {
        if (it.type.content.equals("void")) {
            throw new semanticError("Type void should not be used in a new expression", it.pos);
        }
        if (it.expr != null) {
            for (var nxt : it.expr) {
                nxt.accept(this);
                if (!nxt.type.equals(builtin.IntType)) {
                    throw new semanticError("Index is not type int", it.pos);
                }
            }
        }
    }

    @Override
    public void visit(WrapExprNode it) {
        //should be inaccessible
    }

    @Override
    public void visit(ArrayExprNode it) {
//        System.out.println("Array visited in semantic");
        if (it.array instanceof AtomExprNode) {
            if (!curScope.containsVariable(it.content, true)) {
                throw new semanticError("Array " + it.array.content + " not defined", it.pos);
            }
        }
//        System.out.println("in");
        it.array.accept(this);
//        System.out.println("out");
        it.index.accept(this);
        if (!it.index.type.equals(builtin.IntType)) {
            throw new semanticError("Array index not type int", it.pos);
        }
        if (it.array.type.dim == 0) {
            throw new semanticError("Array dimension not available", it.pos);
        }
        it.type = new Type(it.array.type.content, it.array.type.dim - 1);
//        System.out.println(it.type.content);
//        System.out.println(it.type.dim);
    }

    @Override
    public void visit(MemberExprNode it) {
        it.obj.accept(this);
        DefineClassNode classNode;
//        System.out.println("MemberExpr visited");
        if (it.obj instanceof AtomExprNode && it.obj.content.equals("this")) {
            classNode = curScope.inClass;
        }
        else {
            classNode = gScope.getClass(it.obj.type.content, it.pos);
//            System.out.println(classNode.className);
        }
        if (it.memberFunc != null) {
            if (it.obj.type.dim > 0) {
                if (it.memberFunc.func.equals("size")) {
                    it.type = builtin.IntType;
                    return;
                }
            }
            if (!classNode.funcMap.containsKey(it.memberFunc.func)) {
                throw new semanticError("Member function " + it.memberFunc.func + " not defined in class " + it.obj.type.content, it.pos);
            }
            it.memberFunc.inClass = classNode;
            it.memberFunc.accept(this);
            it.type = it.memberFunc.type;
        }
        if (it.member != null) {
            if (!classNode.varMap.containsKey(it.member)) {
                throw new semanticError("Member variable " + it.member + " not defined in class " + it.obj.type.content, it.pos);
            }
            it.type = classNode.varMap.get(it.member).type;
        }
    }

    @Override
    public void visit(RightSelfExprNode it) {
//        if (!curScope.containsVariable(it.obj.content, true)) {
//            throw new semanticError("Variable " + it.obj.content + " not defined", it.pos);
//        }
        it.obj.accept(this);
        if (it.obj.type != null && !it.obj.type.equals(builtin.IntType)) {
            throw new semanticError("Variable " + it.obj.content + " type not expected", it.pos);
        }
        if (!it.obj.isAssignable()) {
            throw new semanticError("Caught right value when operation expected left value", it.pos);
        }
        it.type = it.obj.type;
    }

    @Override
    public void visit(LeftSelfExprNode it) {
//        if (!curScope.containsVariable(it.obj.content, true)) {
//            throw new semanticError("Variable " + it.obj.content + " not defined", it.pos);
//        }
        it.obj.accept(this);
        if (it.obj.type != null && !it.obj.type.equals(builtin.IntType)) {
            throw new semanticError("Variable " + it.obj.content + " type not expected", it.pos);
        }
        if (!it.obj.isAssignable()) {
            throw new semanticError("Caught right value when operation expected left value", it.pos);
        }
        it.type = it.obj.type;
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
        it.type = it.obj.type;
    }

    @Override
    public void visit(BinaryExprNode it) {
        it.lop.accept(this);
        it.rop.accept(this);
        if (it.lop.type.equals(builtin.NullType) || it.rop.type.equals(builtin.NullType)) {
            if (!it.op.equals("==") && !it.op.equals("!=")) {
                throw new semanticError("Operation not allowed for null", it.pos);
            }
            it.type = builtin.BoolType;
            if (it.lop.type.isReference || it.rop.type.isReference) {
                return;
            }
            else if (!it.lop.type.equals(builtin.NullType) || !it.rop.type.equals(builtin.NullType)) {
                throw new semanticError("Comparing non-reference type with null", it.pos);
            }
        }
        if (!it.lop.type.equals(it.rop.type)) {
            throw new semanticError("Two operands do not have the same type", it.pos);
        }
        if (it.lop.type.equals(builtin.BoolType)) {
            if (!it.op.equals("&&") && !it.op.equals("||") && !it.op.equals("==") && !it.op.equals("!=")) {
                throw new semanticError("Operation not allowed for bool type", it.pos);
            }
            it.type = builtin.BoolType;
        }
        else if (it.lop.type.equals(builtin.IntType)) {
            if (it.op.equals("&&") || it.op.equals("||")) {
                throw new semanticError("Operation not allowed for int type", it.pos);
            }
            if (it.op.equals("==") || it.op.equals("!=") || it.op.equals("<=") || it.op.equals("<") || it.op.equals(">=") || it.op.equals(">")) {
                it.type = builtin.BoolType;
            }
            else {
                it.type = builtin.IntType;
            }
        }
        else if (it.lop.type.equals(builtin.StringType)) {
            if (!it.op.equals("+") && !it.op.equals("==") && !it.op.equals("!=") && !it.op.equals("<") && !it.op.equals(">") && !it.op.equals("<=") && !it.op.equals(">=")){
                throw new semanticError("Operation not allowed for string type", it.pos);
            }
            if (it.op.equals("+")) {
                it.type = builtin.StringType;
            }
            else {
                it.type = builtin.BoolType;
            }
        }
        else {
            if (!it.op.equals("==") && !it.op.equals("!-")) {
                throw new semanticError("Operation not allowed for class", it.pos);
            }
            it.type = builtin.BoolType;
        }
    }//not done

    @Override
    public void visit(TernaryExprNode it) {
        it.condition.accept(this);
        if (!it.condition.type.equals(builtin.BoolType)) {
            throw new semanticError("Condition type isn't bool type in ternary expression", it.pos);
        }
        it.trueVal.accept(this);
        it.falseVal.accept(this);
        if (!it.trueVal.type.equals(it.falseVal.type)) {
            if (it.trueVal.type.equals(builtin.NullType) && it.falseVal.type.isReference) {
                it.type = it.falseVal.type;
                return;
            }
            if (it.falseVal.type.equals(builtin.NullType) && it.trueVal.type.isReference) {
                it.type = it.trueVal.type;
                return;
            }
            throw new semanticError("Expression of different types in ternary expression", it.pos);
        }
        it.type = it.trueVal.type;
    }

    @Override
    public void visit(AssignExprNode it) {
        it.assignTo.accept(this);
        it.assignVal.accept(this);
//        System.out.println(it.assignTo.type.content);
//        System.out.println(it.assignTo.type.dim);
//        System.out.println(it.assignVal.type.content);
//        System.out.println(it.assignVal.type.dim);
        if (!it.assignTo.isAssignable()) {
            throw new semanticError("Assigning to a non-left value", it.pos);
        }
        if (it.assignTo.type.isReference && it.assignVal.type.equals(builtin.NullType)) {
            return;
        }
        if (!it.assignTo.type.equals(it.assignVal.type)) {
            throw new semanticError("Assigning value of a different type to " + it.assignTo.content, it.pos);
        }
    }

    @Override
    public void visit(AtomExprNode it) {
        if (it.content.equals("this") && curScope.inClass == null) {
            throw new semanticError("This used outside any class", it.pos);
        }
        if (it.content.equals("this")) {
            it.type.content = curScope.inClass.className;
        }
        if (it.content.contains("\"")) {
            it.content = it.content.substring(1, it.content.length() - 1);
            it.content = it.content.replace("\\\\", "\\").replace("\\n", "\n").replace("\\\"", "\"");
        }
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
        if (it.exprList != null) {
            it.exprList.accept(this);
        }
    }

    @Override
    public void visit(IfStmtNode it) {
        it.condition.accept(this);
        if (!it.condition.type.equals(builtin.BoolType)) {
            throw new semanticError("Statement condition is not type bool", it.pos);
        }
        curScope = new Scope(curScope);
        if (it.trueThenWork != null) {
            if (it.trueThenWork instanceof BlockNode) {
                ((BlockNode) it.trueThenWork).stmts.forEach(nxt->nxt.accept(this));
            }
            else {
                it.trueThenWork.accept(this);
            }
        }
        curScope = curScope.parentScope;
        if (it.falseThenWork != null) {
            curScope = new Scope(curScope);
            if (it.falseThenWork instanceof BlockNode) {
                ((BlockNode) it.falseThenWork).stmts.forEach(nxt->nxt.accept(this));
            }
            else {
                it.falseThenWork.accept(this);
            }
            curScope = curScope.parentScope;
        }
    }

    @Override
    public void visit(ForStmtNode it) {
        curScope = new Scope(curScope);
        curScope.inLoop = true;
        if (it.exprInit != null) {
            it.exprInit.accept(this);
        }
        if (it.defInit != null) {
            it.defInit.accept(this);
        }
        if (it.condition != null) {
            it.condition.accept(this);
            if (!it.condition.type.equals(builtin.BoolType)) {
                throw new semanticError("Statement condition is not type bool", it.pos);
            }
        }
        if (it.step != null) {
            it.step.accept(this);
        }
        if (it.work != null) {
            if (it.work instanceof BlockNode) {
                ((BlockNode) it.work).stmts.forEach(nxt->nxt.accept(this));
            }
            else {
                it.work.accept(this);
            }
        }
        curScope = curScope.parentScope;
    }

    @Override
    public void visit(WhileStmtNode it) {
        curScope = new Scope(curScope);
        curScope.inLoop = true;
        if (it.condition == null) {
            throw new semanticError("Missing condition statement in while statement", it.pos);
        }
        it.condition.accept(this);
        if (!it.condition.type.equals(builtin.BoolType)) {
            throw new semanticError("Statement condition is not type bool", it.pos);
        }
        if (it.work != null) {
            if (it.work instanceof BlockNode) {
                ((BlockNode) it.work).stmts.forEach(nxt->nxt.accept(this));
            }
            else {
                it.work.accept(this);
            }
        }
        curScope = curScope.parentScope;
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
        if (it.returnVal != null) {
            it.returnVal.accept(this);
        }
        if (curScope.inConstructor) {
            if (it.returnVal != null && !it.returnVal.type.equals(builtin.VoidType)) {
                throw new semanticError("Constructor return type not void", it.pos);
            }
            return;
        }
        if (curScope.inFunc == null) {
            throw new semanticError("Return statement found outside any function", it.pos);
        }
        if (it.returnVal == null && curScope.inFunc.type.equals(builtin.VoidType)) {
            curScope.returnScope.returned = true;
            return;
        }
        if (it.returnVal.type.equals(builtin.NullType) && curScope.inFunc.type.isReference) {
            curScope.returnScope.returned = true;
            return;
        }
        if (!curScope.inFunc.type.equals(it.returnVal.type)) {
            throw new semanticError("Function " + curScope.inFunc.funcName + " returned value is not of expected type", it.pos);
        }
        curScope.returnScope.returned = true;
    }

    @Override
    public void visit(DefineVarStmtNode it) {
        if (!gScope.containsClass(it.type.content, it.pos)) {
            throw new semanticError("Defined type " + it.type.content + " not found", it.pos);
        }
        if (it.type.content.equals("void")) {
            throw new semanticError("Type void should not be used as a variable type", it.pos);
        }
        for (var nxt: it.assigns) {
            nxt.accept(this);
        }
    }

    @Override
    public void visit(VarAssignStmtNode it) {
        //System.out.println("VarAssign visited");
        if (it.assignVal != null) {
            it.assignVal.accept(this);
            if (it.assignVal.type.equals(builtin.NullType) && it.type.isReference) {
                curScope.defineVariable(it.assignTo, it.type, it.pos);
                return;
            }
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
        curScope = new Scope(curScope);
        curScope.inClass = it;
        for (var nxt: it.vars) {
            nxt.accept(this);
        }
        if (it.constructor != null) {
            if (!it.constructor.className.equals(it.className)) {
                throw new semanticError("Constructor name fail to match", it.pos);
            }
            it.constructor.accept(this);
        }
        for (var nxt: it.functions) {
            nxt.accept(this);
        }
        curScope = curScope.parentScope;
    }

    @Override
    public void visit(DefineConstructFunctionNode it) {
        curScope = new Scope(curScope);
        curScope.returnScope = curScope;
        curScope.inConstructor = true;
        for (var nxt:it.stmts) {
            nxt.accept(this);
        }
        curScope = curScope.parentScope;
    }

    @Override
    public void visit(DefineFunctionNode it) {
        //System.out.println("Define function visited");
        curScope = new Scope(curScope);
        curScope.inFunc = it;
        curScope.returnScope = curScope;
        if (it.paramsList != null) {
            it.paramsList.accept(this);
        }
//        System.out.println("stmts size: " + it.stmts.size());
//        System.out.println("Here in " + it.funcName);
        for (var nxt: it.stmts) {
            nxt.accept(this);
        }
        if (!it.type.equals(builtin.VoidType) && !curScope.returned && !it.funcName.equals("main")) {
            throw new semanticError("Function " + it.funcName + " missing return statement", it.pos);
        }
        curScope = curScope.parentScope;//return to original scope
    }

    @Override
    public void visit(BlockNode it) {
        curScope = new Scope(curScope);
        for (var nxt: it.stmts) {
            nxt.accept(this);
        }
        curScope = curScope.parentScope;
    }

    @Override
    public void visit(TypeNameNode it) {
        //should be inaccessible
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
