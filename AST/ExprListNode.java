package AST;

import Utils.*;
import java.util.ArrayList;

public class ExprListNode extends ASTNode {
    public ArrayList<ExprNode> exprs;

    public ExprListNode(position pos) {
        super(pos);
        exprs = new ArrayList<ExprNode>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}