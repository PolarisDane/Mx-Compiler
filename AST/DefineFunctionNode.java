package AST;

import java.util.ArrayList;

import Utils.Type;
import Utils.position;

public class DefineFunctionNode extends ASTNode {
    public String funcName;
    public ParamsListNode paramsList;
    public ArrayList<StmtNode> stmts;

    public Type type;

    public DefineFunctionNode(position pos, String funcName) {
        super(pos);
        this.funcName = funcName;
        this.stmts = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
