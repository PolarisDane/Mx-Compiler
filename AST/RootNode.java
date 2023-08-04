package AST;

import Utils.position;

import java.util.ArrayList;

public class RootNode extends ASTNode {
//    public ArrayList<DefineVarStmtNode> defVars = new ArrayList<>();
//    public ArrayList<DefineClassNode> defClasses = new ArrayList<>();
//    public ArrayList<DefineFunctionNode> defFunctions = new ArrayList<>();
    public ArrayList<ASTNode> Defs = new ArrayList<>();
    //Must be stored in a mutual arraylist for sake of maintaining forward reference;
    public RootNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
