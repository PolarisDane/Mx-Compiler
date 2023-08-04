package AST;

import Utils.position;

public class LeftSelfExprNode extends ExprNode {
    public ExprNode obj;
    public String op;

    public LeftSelfExprNode(position pos, ExprNode obj, String op) {
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
        return true;
    }
}
