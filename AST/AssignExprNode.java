package AST;

import Utils.position;

public class AssignExprNode extends ExprNode {
    public ExprNode assignTo;
    public ExprNode assignVal;

    public AssignExprNode(position pos, ExprNode assignTo, ExprNode assignVal) {
        super(pos);
        this.assignTo = assignTo;
        this.assignVal = assignVal;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
