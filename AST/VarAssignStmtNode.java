package AST;

import java.util.ArrayList;

import Utils.position;

public class VarAssignStmtNode extends StmtNode {
    Type type;
    ArrayList<VarAssignStmtNode> assign;

    public VarAssignStmtNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
