package AST;

import Utils.*;

public class WrapExprNode extends ExprNode {
    WrapExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}