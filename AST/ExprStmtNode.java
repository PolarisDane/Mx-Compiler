package AST;

import Utils.*;

public class ExprStmtNode extends StmtNode {
    public ExprListNode exprList;

    public ExprStmtNode(position pos, ExprListNode exprList) {
        super(pos);
        this.exprList = exprList;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}