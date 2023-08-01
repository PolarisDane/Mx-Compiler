package AST;

import Utils.position;

public class ForStmtNode extends StmtNode {
    public ExprStmtNode exprInit;
    public DefineVarStmtNode defInit;
    public ExprNode condition;
    public ExprNode step;
    public SuiteNode work;

    public ForStmtNode(position pos, ExprNode condition) {
        super(pos);
        this.condition = condition;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
