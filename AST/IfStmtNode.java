package AST;

import Utils.position;

public class IfStmtNode extends StmtNode {
    public ExprNode condition;
    public SuiteNode trueThenWork;
    public SuiteNode falseThenWork;

    public IfStmtNode(position pos, ExprNode condition) {
        super(pos);
        this.condition = condition;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
