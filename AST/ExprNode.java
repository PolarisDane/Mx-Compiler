package AST;

// import MIR.entity;
import Utils.*;

public abstract class ExprNode extends ASTNode {
    public Type type;
    // public entity val;

    public ExprNode(position pos) {
        super(pos);
    }

    public boolean isAssignable() {
        return false;
    }
}