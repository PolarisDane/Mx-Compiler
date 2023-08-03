package Frontend;

import AST.*;
import Utils.*;
import Utils.error.semanticError;

import java.util.HashMap;

public class SymbolCollector implements ASTVisitor {
    private GlobalScope gScope;

    public SymbolCollector(GlobalScope gScope) {
        this.gScope = gScope;
    }

    @Override
    public void visit(FuncCallNode it) {}

    @Override
    public void visit(NewExprNode it) {}

    @Override
    public void visit(WrapExprNode it) {}

    @Override
    public void visit(ArrayExprNode it) {}

    @Override
    public void visit(MemberExprNode it) {}

    @Override
    public void visit(RightSelfExprNode it) {}

    @Override
    public void visit(LeftSelfExprNode it) {}

    @Override
    public void visit(SingleExprNode it) {}

    @Override
    public void visit(BinaryExprNode it) {}

    @Override
    public void visit(TernaryExprNode it) {}

    @Override
    public void visit(AssignExprNode it) {}

    @Override
    public void visit(AtomExprNode it) {}

    @Override
    public void visit(ExprListNode it) {}

    @Override
    public void visit(ExprStmtNode it) {}

    @Override
    public void visit(IfStmtNode it) {}

    @Override
    public void visit(ForStmtNode it) {}

    @Override
    public void visit(WhileStmtNode it) {}

    @Override
    public void visit(FlowStmtNode it) {}

    @Override
    public void visit(BreakStmtNode it) {}

    @Override
    public void visit(ContinueStmtNode it) {}

    @Override
    public void visit(ReturnStmtNode it) {}

    @Override
    public void visit(DefineVarStmtNode it) {}

    @Override
    public void visit(VarAssignStmtNode it) {}

    @Override
    public void visit(ParamsListNode it) {}

    @Override
    public void visit(DefineClassNode it) {
        gScope.addClass();
        for (var defVar: it.vars) {
            for (var varName: defVar.assigns) {
                if (it.varMap.containsKey(varName.assignTo)) {
                    throw new semanticError("Multiple definition of variable " + varName.assignTo + " in class " + it.identifier, varName.pos);
                }
                it.varMap.put(varName.assignTo, varName);
            }
        }
        for (var func: it.functions) {
            if (it.funcMap.containsKey(func.funcName)) {
                throw new semanticError("Multiple definition of function " + func.funcName + " in class " + it.identifier, func.pos);
            }
            it.funcMap.put(func.funcName, func);
        }
    }

    @Override
    public void visit(DefineConstructFunctionNode it) {}

    @Override
    public void visit(DefineFunctionNode it) {}

    @Override
    public void visit(SuiteNode it) {}

    @Override
    public void visit(BlockNode it) {}

    @Override
    public void visit(TypeNameNode it) {}

    @Override
    public void visit(RootNode it) {
        for (var nxt: it.defClasses) {
            nxt.accept(this);
        }
    }
}
