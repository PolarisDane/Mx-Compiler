package AST;

import Utils.*;

import java.util.ArrayList;

public class NewExprNode extends ExprNode {

    public ArrayList<ExprNode> expr = new ArrayList<>();

    public NewExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isAssignable() {
        return false;
    }
}