package AST;

public interface ASTVisitor {
    void visit(FuncExprNode it);
    void visit(NewExprNode it);
    void visit(WrapExprNode it);
    void visit(ArrayExprNode it);
    void visit(MemberExprNode it);
    void visit(BinaryExprNode it);
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

    void visit(SuiteNode it);
    void visit(BlockNode it);
}