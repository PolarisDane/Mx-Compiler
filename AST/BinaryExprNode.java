package AST;

import Utils.position;

public class BinaryExprNode extends ExprNode {
    public ExprNode lop, rop;
    public String op;

    BinaryExprNode(position pos, String op, ExprNode lop, ExprNode rop) {
        super(pos);
        this.op = op;
        this.lop = lop;
        this.rop = rop;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
