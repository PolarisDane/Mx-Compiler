package AST;

import Utils.position;

public class SingleExprNode extends ExprNode {
    public ExprNode obj;
    public String op;

    public SingleExprNode(position pos, ExprNode obj, String op) {
        super(pos);
        this.obj = obj;
        this.op = op;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
