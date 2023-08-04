package AST;

import Utils.*;

public class WrapExprNode extends ExprNode {
    ExprNode expr;
    WrapExprNode(position pos) {
        super(pos);
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