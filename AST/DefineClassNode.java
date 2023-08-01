package AST;

import java.util.ArrayList;

import Utils.position;

public class DefineClassNode extends ASTNode {
    public String identifier;
    public ArrayList<DefineVarStmtNode> vars;
    public DefineConstructFunctionNode constructor;
    public ArrayList<DefineFunctionNode> functions;

    public DefineClassNode(position pos, String identifier, DefineConstructFunctionNode constructor) {
        super(pos);
        this.identifier = identifier;
        this.constructor = constructor;
        this.vars = new ArrayList<>();
        this.functions = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
