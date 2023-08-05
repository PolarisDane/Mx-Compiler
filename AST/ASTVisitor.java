package AST;

public interface ASTVisitor {
    void visit(FuncCallNode it);
    void visit(NewExprNode it);
    void visit(WrapExprNode it);
    void visit(ArrayExprNode it);
    void visit(MemberExprNode it);
    void visit(RightSelfExprNode it);
    void visit(LeftSelfExprNode it);
    void visit(SingleExprNode it);
    void visit(BinaryExprNode it);
    void visit(TernaryExprNode it);
    void visit(AssignExprNode it);
    void visit(AtomExprNode it);
    void visit(ExprListNode it);

    void visit(ExprStmtNode it);
    void visit(IfStmtNode it);
    void visit(ForStmtNode it);
    void visit(WhileStmtNode it);
    void visit(FlowStmtNode it);
    void visit(BreakStmtNode it);
    void visit(ContinueStmtNode it);
    void visit(ReturnStmtNode it);
    void visit(DefineVarStmtNode it);
    void visit(VarAssignStmtNode it);

    void visit(ParamsListNode it);
    void visit(DefineClassNode it);
    void visit(DefineConstructFunctionNode it);
    void visit(DefineFunctionNode it);

    void visit(BlockNode it);

    void visit(TypeNameNode it);

    void visit(RootNode it);
}