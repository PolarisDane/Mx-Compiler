package AST;

import Utils.position;

public class ReturnStmtNode extends StmtNode {
    public ExprNode returnVal = null;

    public ReturnStmtNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
