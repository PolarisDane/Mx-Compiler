package AST;

import Utils.position;

public class TernaryExprNode extends ExprNode {
    public ExprNode condition;
    public ExprNode trueVal;
    public ExprNode falseVal;

    public TernaryExprNode(position pos, ExprNode condition, ExprNode trueVal, ExprNode falseVal) {
        super(pos);
        this.condition = condition;
        this.trueVal = trueVal;
        this.falseVal = falseVal;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
