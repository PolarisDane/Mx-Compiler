package AST;

import Utils.*;

public class FuncCallNode extends ExprNode {
    public String func;
    public ExprListNode args;

    public DefineClassNode inClass;

    public FuncCallNode(position pos, String func) {
        super(pos);
        this.func = func;
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