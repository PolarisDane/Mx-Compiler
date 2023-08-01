package AST;

import Utils.position;

public class MemberExprNode extends ExprNode {
    public ExprNode obj;
    public ExprNode member;

    MemberExprNode(position pos, ExprNode obj, ExprNode member) {
        super(pos);
        this.obj = obj;
        this.member = member;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
