package Backend;

import MIR.BasicBlock;
import MIR.Function;
import MIR.Inst.*;
import MIR.Program;

public interface IRVisitor {
    public void visit(Program it);
    public void visit(Function it);
    public void visit(BasicBlock it);

    public void visit(IRAlloca it);
    public void visit(IRBranch it);
    public void visit(IRBinaryOp it);
    public void visit(IRCall it);
    public void visit(IRGetElementPtr it);
    public void visit(IRIcmp it);
    public void visit(IRJump it);
    public void visit(IRLoad it);
    public void visit(IRPhi it);
    public void visit(IRRet it);
    public void visit(IRStore it);
    public void visit(IRTrunc it);
    public void visit(IRZext it);
}
