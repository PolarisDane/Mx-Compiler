package AST;

import Utils.position;

public class DefineVarStmtNode extends StmtNode {
    public Type type;
    public String var;
    public ExprNode val;

    public DefineVarStmtNode(position pos, Type type, String var, ExprNode val) {
        super(pos);
        this.type = type;
        this.var = var;
        this.val = val;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
