package AST;

import java.util.ArrayList;

import Utils.position;

public class DefineConstructFunctionNode extends ASTNode {
    public String className;
    public ArrayList<StmtNode> stmts;

    public DefineConstructFunctionNode(position pos, String className) {
        super(pos);
        this.className = className;
        this.stmts = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
