package AST;

// import MIR.entity;
import Utils.*;

public abstract class ExprNode extends ASTNode {
    public String content;

    public DefineFunctionNode funcProb;

    public Type type;
    // public entity val;

    public ExprNode(position pos) {
        super(pos);
    }

    public abstract boolean isAssignable();
}