package AST;

import Utils.position;

public class RightSelfExprNode extends ExprNode {
    public ExprNode obj;
    public String op;

    public RightSelfExprNode(position pos, ExprNode obj, String op) {
        super(pos);
        this.obj = obj;
        this.op = op;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isAssignable() {
        return false;
    }
}
