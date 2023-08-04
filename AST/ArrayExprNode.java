package AST;

import Utils.position;

public class ArrayExprNode extends ExprNode {
    public ExprNode array;
    public ExprNode index;

    public ArrayExprNode(position pos, ExprNode array, ExprNode index) {
        super(pos);
        this.array = array;
        this.index = index;
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
