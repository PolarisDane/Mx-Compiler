package AST;

import Utils.Type;
import Utils.position;

public class TypeNameNode extends ASTNode {
    public Type type = new Type();
    public TypeNameNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
