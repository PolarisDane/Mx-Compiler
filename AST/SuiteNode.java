package AST;

import java.util.ArrayList;

import Utils.position;

public class SuiteNode extends ASTNode {
    public ArrayList<StmtNode> stmts;

    public SuiteNode(position pos) {
        super(pos);
        this.stmts = new ArrayList<>();
    }
    
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
//maybe not needed, can be resolved when visiting
