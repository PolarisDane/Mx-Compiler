package AST;

import Utils.*;

public class NewExprNode extends ExprNode {

    NewExprNode(position pos, Type type) {
        super(pos);
        this.type = type;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}