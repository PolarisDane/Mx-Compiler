package Optimizer;

import MIR.BasicBlock;
import MIR.Entity.*;
import MIR.Function;
import MIR.Inst.*;
import MIR.Program;
import MIR.Type.IRPtrType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Mem2Reg {
    Program program;
    Function curFunc;
    ArrayList<BasicBlock> def = new ArrayList<>();
    ArrayList<BasicBlock> use = new ArrayList<>();
    HashMap<IRRegister, Entity> replacement = new HashMap<>();
    HashMap<IRRegister, Boolean> promotable = new HashMap<>();
    public Mem2Reg(Program program) {
        this.program = program;
    }

    public void optimize() {
        new CFGBuilder(program).buildCFG();
        new DTBuilder(program).buildDT();
        for (var nxt: program.functions) {
            curFunc = nxt;
            visitFunc(nxt);
        }
        program.mergePhi();
    }

//    public boolean isPromotable() {
//
//    }
    //notice: parameters with index bigger than 8?

    public Entity finalReplace(IRRegister reg) {
        Entity res = reg;
        while (replacement.containsKey(reg)) {
            res = replacement.get(reg);
            if (!(res instanceof IRRegister)) {
                break;
            }
            reg = (IRRegister) res;
        }//path compact not implemented yet
        return res;
    }

    public void promoteAlloca(IRAlloca it) {
        use.clear();
        def.clear();
        for (var nxt: curFunc.blocks) {
            for (var inst: nxt.insts) {
                if (inst instanceof IRStore st && st.reg.equals(it.res)) {
                    def.add(nxt);
                }
                if (inst instanceof IRLoad ld && ld.reg.equals(it.res)) {
                    use.add(nxt);
                }
            }
        }
        if (use.isEmpty()) {

        }//can be discarded
        promotable.put(it.res, true);
        //judge promotable maybe?
        LinkedList<BasicBlock> que = new LinkedList<>(def);
        HashMap<BasicBlock, Boolean> addPhi = new HashMap<>();
        HashSet<BasicBlock> visit = new HashSet<>();
        while (!que.isEmpty()) {
            BasicBlock block = que.removeFirst();
            for (BasicBlock nxt: block.DomFrontier) {
                if (!addPhi.containsKey(nxt)) {
                    nxt.addInst(new IRPhi(nxt, new IRRegister(it.res.name, it.type), it.res, it.type));
                    addPhi.put(nxt, true);
                    if (!visit.contains(nxt)) {
                        visit.add(nxt);
                        que.add(nxt);
                    }
                }
            }//visit Dominance Frontier
        }
    }

    public void renamePass(BasicBlock it) {
        HashMap<IRRegister, Entity> oldReplacement = new HashMap<>(replacement);
        LinkedList<IRBaseInst> insts = new LinkedList<>();
        for (var phi: it.phiInst) {
            replacement.put(phi.addr, phi.res);
        }
        for (int i = 0; i < it.insts.size(); i++) {
            IRBaseInst inst = it.insts.get(i);
            if (inst instanceof IRAlloca) {
                continue;
            }
            if (inst instanceof IRLoad ld && promotable.containsKey(ld.addr)) {
                replacement.put(ld.reg, finalReplace((IRRegister) ld.addr));
                continue;
            }
            else if (inst instanceof IRStore st && promotable.containsKey(st.reg)) {
                if (st.val instanceof IRConst) {
                    replacement.put(st.reg, st.val);
                }
                else {
                    replacement.put(st.reg, finalReplace((IRRegister) st.val));
                }
                continue;
            }
            replace(inst);
            insts.add(inst);
        }
        it.insts = insts;
        for (var nxt: it.succ) {
            for (var phi: nxt.phiInst) {
                phi.fromBlock.add(it);
                if (replacement.containsKey(phi.addr)) {
                    phi.val.add(replacement.get(phi.addr));
                }
                else {
                    if (phi.type instanceof IRPtrType) {
                        phi.val.add(new IRNullConst());
                    }
                    else {
                        phi.val.add(new IRIntConst(0));
                    }
                }
            }
        }
        for (var nxt: it.DTChild) {
            renamePass(nxt);
        }
        replacement = oldReplacement;
    }

    public void replace(IRBaseInst inst) {
        if (inst instanceof IRStore st) {
            if (st.val instanceof IRRegister reg && replacement.containsKey(reg)) {
                st.val = replacement.get(reg);
            }
        }
        if (inst instanceof IRBinaryOp binary) {
            if (binary.op1 instanceof IRRegister reg1 && replacement.containsKey(reg1)) {
                binary.op1 = replacement.get(reg1);
            }
            if (binary.op2 instanceof IRRegister reg2 && replacement.containsKey(reg2)) {
                binary.op2 = replacement.get(reg2);
            }
        }
        if (inst instanceof IRBranch branch) {
            if (branch.condition instanceof IRRegister cond && replacement.containsKey(cond)) {
                branch.condition = replacement.get(cond);
            }
        }
        if (inst instanceof IRCall call) {
            for (int i = 0; i < call.args.size(); i++) {
                Entity arg = call.args.get(i);
                if (arg instanceof IRRegister reg && replacement.containsKey(reg)) {
                    call.args.set(i, replacement.get(reg));
                }
            }
        }
        if (inst instanceof IRGetElementPtr getPtr) {
            if (getPtr.ptr instanceof IRRegister reg && replacement.containsKey(reg)) {
                getPtr.ptr = replacement.get(reg);
            }
            for (int i = 0; i < getPtr.idx.size(); i++) {
                Entity id = getPtr.idx.get(i);
                if (id instanceof IRRegister reg && replacement.containsKey(reg)) {
                    getPtr.idx.set(i, replacement.get(reg));
                }
            }
        }
        if (inst instanceof IRIcmp icmp) {
            if (icmp.op1 instanceof IRRegister reg1 && replacement.containsKey(reg1)) {
                icmp.op1 = replacement.get(reg1);
            }
            if (icmp.op2 instanceof IRRegister reg2 && replacement.containsKey(reg2)) {
                icmp.op2 = replacement.get(reg2);
            }
        }
        if (inst instanceof IRRet ret) {
            if (ret.returnVal instanceof IRRegister reg && replacement.containsKey(reg)) {
                ret.returnVal = replacement.get(reg);
            }
        }
        if (inst instanceof IRTrunc trunc) {
            if (trunc.val instanceof IRRegister reg && replacement.containsKey(reg)) {
                trunc.val = replacement.get(reg);
            }
        }
        if (inst instanceof IRZext zext) {
            if (zext.val instanceof IRRegister reg && replacement.containsKey(reg)) {
                zext.val = replacement.get(zext);
            }
        }
    }

    public void visitFunc(Function it) {
        for (var inst: it.allocaInst) {
            promoteAlloca(inst);
        }
        renamePass(it.blocks.get(0));
    }
}
