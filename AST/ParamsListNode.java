package AST;

import java.util.ArrayList;

import Utils.position;

public class ParamsListNode extends ASTNode {
    public ArrayList<Type> types;
    public ArrayList<String> identifiers;

    public ParamsListNode(position pos) {
        super(pos);
        this.types = new ArrayList<>();
        this.identifiers = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
