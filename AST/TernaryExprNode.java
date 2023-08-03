package AST;

import Utils.position;

public class TernaryExprNode extends ExprNode {
    public ExprNode condition;
    public ExprNode trueVal;
    public ExprNode falseVal;

    public TernaryExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
