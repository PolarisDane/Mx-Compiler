package AST;

import Utils.position;

public class FlowStmtNode extends StmtNode {
    public FlowStmtNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
