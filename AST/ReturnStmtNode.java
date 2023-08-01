package AST;

import Utils.position;

public class ReturnStmtNode extends StmtNode {
    public ExprNode returnVal;

    public ReturnStmtNode(position pos, ExprNode returnVal) {
        super(pos);
        this.returnVal = returnVal;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
