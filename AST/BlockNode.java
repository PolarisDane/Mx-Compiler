package AST;

import java.util.ArrayList;

import Utils.position;

public class BlockNode extends StmtNode {
    public ArrayList<StmtNode> stmts;

    public BlockNode(position pos) {
        super(pos);
        this.stmts = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
