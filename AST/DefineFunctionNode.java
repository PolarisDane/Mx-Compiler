package AST;

import java.util.ArrayList;

import Utils.position;

public class DefineFunctionNode extends ASTNode {
    public String funcName;
    public ParamsListNode paramsList;
    public ArrayList<StmtNode> stmts;

    public DefineFunctionNode(position pos, String funcName, ParamsListNode paramsList) {
        super(pos);
        this.funcName = funcName;
        this.paramsList = paramsList;
        this.stmts = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
