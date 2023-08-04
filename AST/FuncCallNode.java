package AST;

import Utils.*;

public class FuncCallNode extends ExprNode {
    public String func;
    public ExprListNode args;

    public FuncCallNode(position pos, String func, ExprListNode args) {
        super(pos);
        this.func = func;
        this.args = args;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isAssignable() {
        return false;
    }
}