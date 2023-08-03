package AST;

import java.util.ArrayList;
import java.util.HashMap;

import Utils.position;

public class DefineClassNode extends ASTNode {
    public String identifier;
    public ArrayList<DefineVarStmtNode> vars;
    public DefineConstructFunctionNode constructor;
    public ArrayList<DefineFunctionNode> functions;
    public HashMap<String, VarAssignStmtNode> varMap;
    public HashMap<String, DefineFunctionNode> funcMap;

    public DefineClassNode(position pos, String identifier) {
        super(pos);
        this.identifier = identifier;
        this.vars = new ArrayList<>();
        this.functions = new ArrayList<>();
        this.varMap = new HashMap<>();
        this.funcMap = new HashMap<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
