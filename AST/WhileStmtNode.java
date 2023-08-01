package AST;

import Utils.position;

public class WhileStmtNode extends StmtNode {
    public ExprNode condition;
    public SuiteNode work;

    public WhileStmtNode(position pos, ExprNode condition) {
        super(pos);
        this.condition = condition;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
