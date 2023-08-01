package AST;

import Utils.position;

public class AtomExprNode extends ExprNode {
    public String content;
    public boolean isIdentifier;

    public AtomExprNode(position pos, String content, boolean isIdentifier) {
        super(pos);
        this.content = content;
        this.isIdentifier = isIdentifier;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
