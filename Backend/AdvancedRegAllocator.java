package Backend;

import Assembly.ASMFunction;
import Assembly.ASMProgram;
import Assembly.Inst.*;
import Assembly.Operand.Imm;
import Assembly.Operand.PhyReg;
import Assembly.Operand.Reg;
import Assembly.Operand.VirtualReg;
import Optimizer.LivenessAnalyzer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public class AdvancedRegAllocator {
    public static int K = 27;

    public ASMProgram program;
    public AdvancedRegAllocator(ASMProgram program) {
        this.program = program;
    }
    public HashSet<Reg> initial = new HashSet<>();
    public LinkedList<Reg> simplifyWorklist = new LinkedList<>();
    public LinkedList<Reg> freezeWorklist = new LinkedList<>();
    public LinkedList<Reg> spillWorklist = new LinkedList<>();
    public Stack<Reg> selectStack = new Stack<>();
    public HashSet<Reg> spilledNodes = new HashSet<>();
    public HashSet<Reg> coalescedNodes = new HashSet<>();
    public HashSet<Reg> coloredNodes = new HashSet<>();

    public HashSet<Reg> precolored = new HashSet<>();

    public LinkedList<ASMMvInst> worklistMoves = new LinkedList<>();
    public HashSet<ASMMvInst> coalescedMoves = new HashSet<>();
    public HashSet<ASMMvInst> constrainedMoves = new HashSet<>();
    public HashSet<ASMMvInst> frozenMoves = new HashSet<>();
    public HashSet<ASMMvInst> activeMoves = new HashSet<>();

    public HashSet<edge> adjSet = new HashSet<>();
    public HashMap<Reg, HashSet<Reg>> adjList = new HashMap<>();
    public HashMap<Reg, Integer> degree = new HashMap<>();
    public HashMap<Reg, HashSet<ASMMvInst>> moveList = new HashMap<>();
    public HashMap<Reg, Reg> alias = new HashMap<>();
    public HashMap<Reg, Integer> color = new HashMap<>();
    public HashSet<Reg> spilledTemp = new HashSet<>();
    LinkedList<ASMBaseInst> newInsts = new LinkedList<>();

    public class edge {
        public Reg x, y;
        public edge(Reg x, Reg y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof edge e)) {
                return false;
            }
            if ((x == e.x && y == e.y) || (x == e.y && y == e.x)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return x.hashCode() ^ y.hashCode();
        }
    }
    public void addEdge(Reg x, Reg y) {
        edge e = new edge(x, y);
        if (x == y || adjSet.contains(e)) {
            return;
        }
        adjSet.add(e);
        if (!precolored.contains(x)) {
            adjList.get(x).add(y);
            degree.put(x, degree.get(x) + 1);
        }
        if (!precolored.contains(y)) {
            adjList.get(y).add(x);
            degree.put(y, degree.get(y) + 1);
        }
    }
    public void work() {
        for (var nxt:program.functions) {
            graphColoring(nxt);
        }
    }

    public void init(ASMFunction it) {
        initial.clear();
        simplifyWorklist.clear();
        freezeWorklist.clear();
        spillWorklist.clear();
        selectStack.clear();
        spilledNodes.clear();
        coalescedNodes.clear();
        coloredNodes.clear();
        precolored.clear();
        worklistMoves.clear();
        coalescedMoves.clear();
        constrainedMoves.clear();
        frozenMoves.clear();
        activeMoves.clear();
        adjSet.clear();
        adjList.clear();
        degree.clear();
        moveList.clear();
        alias.clear();
        color.clear();
        spilledTemp.clear();
        for (var reg: PhyReg.phyRegMap.values()) {
            precolored.add(reg);
            adjList.put(reg, new HashSet<>());
            degree.put(reg, Integer.MAX_VALUE);
            moveList.put(reg, new HashSet<>());
            alias.put(reg, null);
            color.put(reg, reg.id);
            reg.spillWeight = 0;
        }
        for (var block: it.blocks) {
            for (var inst: block.insts) {
                initial.addAll(inst.getDef());
                initial.addAll(inst.getUse());
            }
        }
        initial.remove(precolored);
        for (var reg: initial) {
            adjList.put(reg, new HashSet<>());
            degree.put(reg, 0);
            moveList.put(reg, new HashSet<>());
            alias.put(reg, null);
            color.put(reg, null);
            reg.spillWeight = 0;
        }
        calcSpillWeight(it);
    }

    public void calcSpillWeight(ASMFunction it) {
        for (var block: it.blocks) {
            double weight = Math.pow(10, block.loopDepth);
            for (var inst: block.insts) {
                for (var reg: inst.getDef()) {
                    reg.spillWeight += weight;
                }
                for (var reg: inst.getUse()) {
                    reg.spillWeight += weight;
                }
            }
        }
    }

    public void graphColoring(ASMFunction it) {
        while(true) {
            new LivenessAnalyzer(it).analyze();
            init(it);
            build(it);
            makeWorklist();
            do {
                if (!simplifyWorklist.isEmpty()) simplify();
                else if (!worklistMoves.isEmpty()) coalesce();
                else if (!freezeWorklist.isEmpty()) freeze();
                else if (!spillWorklist.isEmpty()) selectSpill();
            } while (!simplifyWorklist.isEmpty() ||
                    !worklistMoves.isEmpty() ||
                    !freezeWorklist.isEmpty() ||
                    !spillWorklist.isEmpty());
            assignColor();
            if (spilledNodes.isEmpty()) {
                break;
            }
            rewriteProgram(it);
        }
        for (var block: it.blocks) {
            newInsts.clear();
            for (var inst: block.insts) {
                if (inst.rs1 instanceof VirtualReg) {
                    inst.rs1 = PhyReg.phyRegId.get(color.get(inst.rs1));
                }
                if (inst.rs2 instanceof VirtualReg) {
                    inst.rs2 = PhyReg.phyRegId.get(color.get(inst.rs2));
                }
                if (inst.rd instanceof VirtualReg) {
                    inst.rd = PhyReg.phyRegId.get(color.get(inst.rd));
                }
                if (inst instanceof ASMMvInst) {
                    if (inst.rs1 != inst.rd) {
                        newInsts.add(inst);
                    }
                }
                else {
                    newInsts.add(inst);
                }
            }
            block.insts = newInsts;
        }//allocate physic reg
    }

    public void build(ASMFunction it) {
        for (var block: it.blocks) {
            HashSet<Reg> liveOut = new HashSet<>(block.liveOut);
            for (int i = block.insts.size() - 1; i >= 0; i--) {
                ASMBaseInst inst = block.insts.get(i);
                if (inst instanceof ASMMvInst) {
                    liveOut.removeAll(inst.getUse());//maybe able to be coalesced, so set aside for future usage
                    for (var reg: inst.getDef()) {
                        moveList.get(reg).add((ASMMvInst) inst);
                    }
                    for (var reg: inst.getUse()) {
                        moveList.get(reg).add((ASMMvInst) inst);
                    }
                    worklistMoves.add((ASMMvInst) inst);
                }
                liveOut.addAll(inst.getDef());
                //for building edges between all alive variable
                for (var live: liveOut) {
                    for (var def: inst.getDef()) {
                        if (!live.equals(def)) {
                            addEdge(live, def);
                        }
                    }
                }
                liveOut.removeAll(inst.getDef());
                liveOut.addAll(inst.getUse());
            }
        }
    }

    public HashSet<Reg> adjacent(Reg reg) {
        HashSet<Reg> adj = new HashSet<>(adjList.get(reg));
        adj.removeAll(selectStack);//removed points in simplify
        adj.removeAll(coalescedNodes);//removed points when merging points
        return adj;
    }

    public HashSet<ASMMvInst> nodeMoves(Reg reg) {
        HashSet<ASMMvInst> moves = new HashSet<>();
        moves.addAll(activeMoves);
        moves.addAll(worklistMoves);
        moves.retainAll(moveList.get(reg));//intersection
        return moves;
    }

    public boolean moveRelated(Reg reg) {
        return !nodeMoves(reg).isEmpty();
    }

    public void makeWorklist() {
        for (var nxt: initial) {
            if (degree.get(nxt) >= K) {
                spillWorklist.add(nxt);
            }
            else if (moveRelated(nxt)) {
                freezeWorklist.add(nxt);
            }
            else {
                simplifyWorklist.add(nxt);
            }
        }
        initial.clear();
        //can not remove while iterating
    }

    public void enableMoves(HashSet<Reg> nodes) {
        for (var node: nodes) {
            var mvInsts = nodeMoves(node);
            for (var mvInst: mvInsts) {
                if (activeMoves.contains(mvInst)) {
                    activeMoves.remove(mvInst);
                    worklistMoves.add(mvInst);//possible coalesce choice
                }
            }
        }
    }

    public void decrementDegree(Reg reg) {
        int deg = degree.get(reg);
        degree.put(reg, deg - 1);
        if (deg == K) {
            HashSet<Reg> nodes = adjacent(reg);
            nodes.add(reg);
            enableMoves(nodes);
            spillWorklist.remove(reg);
            if (moveRelated(reg)) {
                freezeWorklist.add(reg);
            }
            else {
                simplifyWorklist.add(reg);
            }
        }
    }

    public void simplify() {
        while (!simplifyWorklist.isEmpty()) {
            Reg reg = simplifyWorklist.removeFirst();
            selectStack.push(reg);
            for (var adj: adjacent(reg)) {
                decrementDegree(adj);
            }
        }
    }

    public boolean Briggs(Reg x, Reg y) {
        HashSet<Reg> merge = new HashSet<>();
        merge.addAll(adjList.get(x));
        merge.addAll(adjList.get(y));
        int cnt = 0;
        for (var adj: merge) {
            if (degree.get(adj) >= K) {
                cnt++;
            }
        }
        return cnt < K;
    }

    public boolean George(Reg x, Reg y) {
        boolean flag = true;
        for (var adj: adjList.get(x)) {
            flag = flag && (degree.get(adj) < K || adjSet.contains(new edge(x,y)));
        }
        return flag;
    }

    public Reg getAlias(Reg reg) {
        if (coalescedNodes.contains(reg)) {
            Reg newAlias = getAlias(alias.get(reg));
            alias.put(reg, newAlias);
            return newAlias;
        }
        else {
            return reg;
        }
    }

    public void addWorklist(Reg reg) {
        if (!precolored.contains(reg) && !(moveRelated(reg) && degree.get(reg) < K)) {
            freezeWorklist.remove(reg);
            simplifyWorklist.add(reg);
            //not move related and low-degree -> simplify
        }
    }


    public void coalesce() {
        ASMMvInst mvInst = worklistMoves.removeFirst();
        var x = getAlias(mvInst.rd);
        var y = getAlias(mvInst.rs1);
        edge e = precolored.contains(y) ? new edge(y, x) : new edge(x, y);
        if (e.x == e.y) {
            coalescedMoves.add(mvInst);
            addWorklist(e.x);
        }
        else if (precolored.contains(e.y) || adjSet.contains(e)) {
            constrainedMoves.add(mvInst);
            addWorklist(e.x);
            addWorklist(e.y);
            //move inst constrained and can not be coalesced
        }
        else {
            if ((precolored.contains(e.x) && Briggs(e.x, e.y)) || (!precolored.contains(e.x) && George(e.y, e.x))) {
                coalescedMoves.add(mvInst);
                combine(e.x, e.y);
                addWorklist(e.x);//why
            }
            else {
                activeMoves.add(mvInst);
            }
        }
    }//not done

    public void combine(Reg x, Reg y) {
        if (freezeWorklist.contains(y)) {
            freezeWorklist.remove(y);
        }
        else {
            spillWorklist.remove(y);
        }
        coalescedNodes.add(y);
        alias.put(y, x);
        moveList.get(x).addAll(moveList.get(y));
        HashSet<Reg> combined = new HashSet<>();
        combined.add(y);
        enableMoves(combined);
        for (var adj: adjacent(y)) {
            addEdge(adj, x);
            decrementDegree(adj);//why?
        }
        if (degree.get(x) >= K && freezeWorklist.contains(x)) {
            freezeWorklist.remove(x);
            spillWorklist.add(x);
        }
    }

    public void freezeMvInst(Reg reg) {
        for (var mv: nodeMoves(reg)) {
            Reg v = getAlias(mv.rs1) == getAlias(reg) ? getAlias(mv.rd) : getAlias(mv.rs1);//get the other operand of move inst
            activeMoves.remove(mv);
            frozenMoves.add(mv);
            if (nodeMoves(v).isEmpty() && degree.get(v) < K) {
                freezeWorklist.remove(v);
                simplifyWorklist.add(v);
            }
        }
    }

    public void freeze() {
        Reg reg = freezeWorklist.removeFirst();
        simplifyWorklist.add(reg);
        freezeMvInst(reg);
    }

    public void selectSpill() {
        Reg selected = null;
        for (Reg reg: spillWorklist) {
            if (selected == null || (reg.spillWeight / degree.get(reg) > selected.spillWeight / degree.get(selected) && !spilledTemp.contains(reg))) {
                selected = reg;//we can't choose reg that originated from spill
            }
        }
        spillWorklist.remove(selected);
        simplifyWorklist.add(selected);
        freezeMvInst(selected);//move insts related to spilled regs should be frozen
    }

    public void assignColor() {
        HashSet<Integer> okColors = new HashSet<>();
        while (!selectStack.isEmpty()) {
            Reg reg = selectStack.pop();
            for (int i = 5; i < 32; i++) {
                okColors.add(i);
            }
            for (var adj: adjList.get(reg)) {
                Reg alias = getAlias(adj);
                if (coloredNodes.contains(alias) || precolored.contains(alias)) {
                    okColors.remove(color.get(alias));
                }
            }
            if (okColors.isEmpty()) {
                spilledNodes.add(reg);
            }
            else {
                coloredNodes.add(reg);
                color.put(reg, okColors.iterator().next());
            }
        }
        for (Reg reg: coalescedNodes) {
            color.put(reg, color.get(getAlias(reg)));
            //assign regs coalesced with the same color
        }
    }

    public void rewriteProgram(ASMFunction it) {
        for (Reg reg: spilledNodes) {
            reg.stackOffset = it.argsStack + it.allocaLength + it.spilledLength;
            it.spilledLength += 4;
        }//allocate space on stack
        for (var block: it.blocks) {
            newInsts.clear();
            for (var inst: block.insts) {
                VirtualReg resReg = null;
                if (inst.rs1 != null && spilledNodes.contains(inst.rs1)) {
                    VirtualReg reg = new VirtualReg(32);
                    spilledTemp.add(reg);
                    allocate(reg, (VirtualReg) inst.rs1, true);
                    inst.rs1 = reg;
                }
                if (inst.rs2 != null && spilledNodes.contains(inst.rs2)) {
                    VirtualReg reg = new VirtualReg(32);
                    spilledTemp.add(reg);
                    allocate(reg, (VirtualReg) inst.rs2, true);
                    inst.rs2 = reg;
                }
                newInsts.add(inst);
                if (inst.rd != null && spilledNodes.contains(inst.rd)) {
                    VirtualReg reg = new VirtualReg(32);
                    spilledTemp.add(reg);
                    allocate(reg, (VirtualReg) inst.rd, false);
                    inst.rd = reg;
                }
            }
            block.insts = newInsts;
        }
    }

    public void allocate(VirtualReg newReg, VirtualReg reg, boolean isSrc) {
        if (reg.stackOffset < (1 << 11)) {
            if (isSrc) {
                newInsts.add(new ASMLoadInst(32, newReg, PhyReg.phyRegMap.get("sp"), new Imm(reg.stackOffset)));
            }
            else {
                newInsts.add(new ASMStoreInst(32, PhyReg.phyRegMap.get("sp"), newReg, new Imm(reg.stackOffset)));
            }
        }
        else {
            if (isSrc) {
                newInsts.add(new ASMLiInst(newReg, new Imm(reg.stackOffset)));
                newInsts.add(new ASMRTypeInst(newReg, newReg, PhyReg.phyRegMap.get("sp"), "add"));
                newInsts.add(new ASMLoadInst(32, newReg, newReg));
            }
            else {
                VirtualReg addr = new VirtualReg(32);
                spilledTemp.add(addr);
                newInsts.add(new ASMLiInst(addr, new Imm(reg.stackOffset)));
                newInsts.add(new ASMRTypeInst(addr, addr, PhyReg.phyRegMap.get("sp"), "add"));
                newInsts.add(new ASMStoreInst(32, addr, newReg));
            }
        }
    }
}//reference to chapter 11.1,11.2
