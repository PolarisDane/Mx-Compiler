package Optimizer;

import MIR.BasicBlock;
import MIR.Entity.Entity;
import MIR.Entity.IRBoolConst;
import MIR.Entity.IRConst;
import MIR.Entity.IRRegister;
import MIR.Function;
import MIR.Inst.*;
import MIR.Program;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class ConstPropagation {
    Program program;

    public ConstPropagation(Program program) {
        this.program = program;
    }

    public void propagate() {
        for (var nxt: program.functions) {
            visitFunc(nxt);
        }
    }

    public void visitFunc(Function it) {
        LinkedList<IRBaseInst> que = new LinkedList<>();
        HashSet<IRBaseInst> inQue = new HashSet<>();
        for (var block: it.blocks) {
            for (var inst: block.insts) {
                if (inst.getConst() != null) {
                    que.add(inst);
                    inQue.add(inst);
                }
            }
        }
        while (!que.isEmpty()) {
            IRBaseInst inst = que.removeFirst();
            inQue.remove(inst);
            var defVal = inst.getDef();
            var constVal = inst.getConst();
            if (constVal != null) {
                inst.isDead = true;
                for (var nxt : it.use.get(defVal)) {
                    replace(nxt, defVal, constVal);
                    if (!inQue.contains(nxt)) {
                        que.add(nxt);
                        inQue.add(nxt);
                    }
                }
            }
            else if (inst instanceof IRBranch branch && branch.condition instanceof IRBoolConst cond) {
                BasicBlock curBlock = branch.parentBlock;
                BasicBlock removeBlock = cond.val ? branch.falseThenWork : branch.trueThenWork;
                BasicBlock remainBlock = cond.val ? branch.trueThenWork : branch.falseThenWork;

                curBlock.insts.removeLast();
                curBlock.insts.add(new IRJump(curBlock, remainBlock.label));
                curBlock.succ.remove(removeBlock);

                for (var succ: removeBlock.succ) {
                    for (var phiInst : succ.insts) {
                        if (!(phiInst instanceof IRPhi phi)) {
                            break;
                        }
                        boolean flag = false;
                        for (int i = 0; i < phi.fromBlock.size(); i++) {
                            if (phi.fromBlock.get(i) == removeBlock) {
                                phi.fromBlock.remove(i);
                                phi.val.remove(i);
                                flag = true;
                            }
                        }
                        if (flag && !inQue.contains(phi)) {
                            que.add(phi);
                            inQue.add(phi);
                        }
                    }//phi already added into insts
                    succ.pred.remove(removeBlock);
                }

                removeBlock.pred.remove(curBlock);
                if (removeBlock.pred.isEmpty()) {
                    removeBlock(removeBlock);
                    it.blocks.remove(removeBlock);
                }
            }
        }
        for (var block: it.blocks) {
            Iterator<IRBaseInst> iter = block.insts.iterator();
            while (iter.hasNext()) {
                if (iter.next().isDead) {
                    iter.remove();
                }
            }
        }
    }

    public void removeBlock(BasicBlock it) {

    }

    public void replace(IRBaseInst inst, IRRegister reg, Entity val) {
        if (inst instanceof IRPhi phi) {
            for (int i = 0; i < phi.val.size(); i++) {
                Entity value = phi.val.get(i);
                if (value == reg) {
                    phi.val.set(i, val);
                }
            }
        }
        if (inst instanceof IRStore st) {
            if (st.val == reg) {
                st.val = val;
            }
        }
        if (inst instanceof IRBinaryOp binary) {
            if (binary.op1 == reg) {
                binary.op1 = val;
            }
            if (binary.op2 == reg) {
                binary.op2 = val;
            }
        }
        if (inst instanceof IRBranch branch) {
            if (branch.condition == reg) {
                branch.condition = val;
            }
        }
        if (inst instanceof IRCall call) {
            for (int i = 0; i < call.args.size(); i++) {
                Entity arg = call.args.get(i);
                if (arg == reg) {
                    call.args.set(i, val);
                }
            }
        }
        if (inst instanceof IRGetElementPtr getPtr) {
            if (getPtr.ptr == reg) {
                getPtr.ptr = val;
            }
            for (int i = 0; i < getPtr.idx.size(); i++) {
                Entity id = getPtr.idx.get(i);
                if (id == reg) {
                    getPtr.idx.set(i, val);
                }
            }
        }
        if (inst instanceof IRIcmp icmp) {
            if (icmp.op1 == reg) {
                icmp.op1 = val;
            }
            if (icmp.op2 == reg) {
                icmp.op2 = val;
            }
        }
        if (inst instanceof IRRet ret) {
            if (ret.returnVal == reg) {
                ret.returnVal = val;
            }
        }
        if (inst instanceof IRTrunc trunc) {
            if (trunc.val == reg) {
                trunc.val = val;
            }
        }
        if (inst instanceof IRZext zext) {
            if (zext.val == reg) {
                zext.val = val;
            }
        }
    }
}
