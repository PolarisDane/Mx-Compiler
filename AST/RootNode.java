package AST;

import Utils.position;

import java.util.ArrayList;

public class RootNode extends ASTNode {
    public ArrayList<DefineVarStmtNode> defVars;
    public ArrayList<DefineClassNode> defClasses;
    public ArrayList<DefineFunctionNode> defFunctions;
    public RootNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
