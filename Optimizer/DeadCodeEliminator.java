package Optimizer;

import MIR.Entity.IRRegister;
import MIR.Function;
import MIR.Inst.IRBaseInst;
import MIR.Inst.IRCall;
import MIR.Program;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class DeadCodeEliminator {
    Program program;

    public DeadCodeEliminator(Program program) {
        this.program = program;
    }

    public void eliminate() {
        for (var nxt: program.functions) {
            visitFunc(nxt);
        }
    }

    public void visitFunc(Function it) {
        HashMap<IRRegister, HashSet<IRBaseInst>> use = new HashMap<>();
        HashMap<IRRegister, IRBaseInst> def = new HashMap<>();
        LinkedList<IRRegister> que = new LinkedList<>();
        HashSet<IRRegister> inQue = new HashSet<>();
        for (var block: it.blocks) {
            for (var inst: block.insts) {
                if (inst.getDef() != null) {
                    def.put(inst.getDef(), inst);
                    que.add(inst.getDef());
                    inQue.add(inst.getDef());
                }
                for (var nxt: inst.getUse()) {
                    if (!use.containsKey(nxt)) {
                        use.put(nxt, new HashSet<IRBaseInst>());
                    }
                    use.get(nxt).add(inst);
                }
            }
        }
        while (!que.isEmpty()) {
            IRRegister reg = que.removeFirst();
            inQue.remove(reg);
            if (use.containsKey(reg) && !use.get(reg).isEmpty()) {
                continue;
            }
            IRBaseInst inst = def.get(reg);
            if (inst instanceof IRCall || inst == null) {
                continue;
            }
            inst.isDead = true;
            for (var nxt: inst.getUse()) {
                use.get(nxt).remove(inst);
                if (!que.contains(nxt)) {
                    que.add(nxt);
                    inQue.add(nxt);
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
        it.use = use;
    }
}
