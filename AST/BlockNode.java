package AST;

import java.util.ArrayList;

import Utils.position;

public class BlockNode extends ASTNode {
    public ArrayList<StmtNode> stmt;

    public BlockNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
