package AST;

import Utils.*;

public class FuncExprNode extends ExprNode {
    public ExprNode func;
    public ExprListNode args;

    FuncExprNode(position pos, ExprNode func, ExprListNode args) {
        super(pos);
        this.func = func;
        this.args = args;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}