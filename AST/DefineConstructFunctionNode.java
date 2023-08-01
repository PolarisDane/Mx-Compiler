package AST;

import java.util.ArrayList;

import Utils.position;

public class DefineConstructFunctionNode extends ASTNode {
    public ArrayList<StmtNode> stmts;

    public DefineConstructFunctionNode(position pos) {
        super(pos);
        this.stmts = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
