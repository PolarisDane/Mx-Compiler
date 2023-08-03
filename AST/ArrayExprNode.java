package AST;

import Utils.position;

public class ArrayExprNode extends ExprNode {
    ExprNode array;
    ExprNode index;

    public ArrayExprNode(position pos, ExprNode array, ExprNode index) {
        super(pos);
        this.array = array;
        this.index = index;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
