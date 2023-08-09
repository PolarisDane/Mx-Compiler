package AST;

import MIR.Type.IRBaseType;
import Utils.Type;
import Utils.position;

public class TypeNameNode extends ASTNode {
    public Type type = new Type();
    public IRBaseType IRType;

    public TypeNameNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
