package AST;

import Utils.position;

public class WhileStmtNode extends StmtNode {
    public ExprNode condition;
    public SuiteNode work;

    public WhileStmtNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
