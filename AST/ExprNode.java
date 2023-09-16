package AST;

// import MIR.entity;
import MIR.Entity.Entity;
import MIR.Entity.IRRegister;
import Utils.*;

public abstract class ExprNode extends ASTNode {
    public String content;

    public Entity entity;

    public IRRegister addr;
    public boolean isConst = false;

    public Type type = new Type();

    public ExprNode(position pos) {
        super(pos);
    }

    public abstract boolean isAssignable();
}