package AST;

import Utils.*;

public class NewExprNode extends ExprNode {

    public NewExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}