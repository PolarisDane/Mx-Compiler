package AST;

import java.util.ArrayList;

import Utils.position;
import Utils.Type;

public class VarAssignStmtNode extends StmtNode {
    public String assignTo;
    public ExprNode assignVal;

    public Type type;

    public VarAssignStmtNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
