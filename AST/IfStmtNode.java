package AST;

import Utils.position;

public class IfStmtNode extends StmtNode {
    public ExprNode condition;
    public StmtNode trueThenWork;
    public StmtNode falseThenWork;

    public IfStmtNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
