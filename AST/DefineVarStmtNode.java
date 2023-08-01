package AST;

import Utils.position;
import Utils.Type;

import java.util.ArrayList;

public class DefineVarStmtNode extends StmtNode {
    public Type type;
    public ArrayList<VarAssignStmtNode> assigns;

    public DefineVarStmtNode(position pos) {
        super(pos);
        assigns = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
