package Backend;

import Assembly.ASMFunction;
import Assembly.ASMProgram;
import Assembly.Inst.ASMBaseInst;
import Assembly.Inst.ASMLiInst;
import Assembly.Inst.ASMMvInst;
import Assembly.Operand.Reg;
import Optimizer.LivenessAnalyzer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public class AdvancedRegAllocator {
    public static int K;

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
    public void graphColoring(ASMFunction it) {
        while(true) {
            new LivenessAnalyzer(it).analyze();
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
            if (spilledStack.empty()) return;
            rewrite();
        }
    }

    public void build(ASMFunction it) {
        for (var block: it.blocks) {
            HashSet<Reg> liveOut = new HashSet<Reg>(block.liveOut);
            for (int i = block.insts.size() - 1; i >= 0; i--) {
                ASMBaseInst inst = block.insts.get(i);
                if (inst instanceof ASMMvInst) {
                    liveOut.removeAll(inst.getUse());

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
            initial.remove(nxt);
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
        }
        else if (precolored.contains(e.x) || ) {

        }
        else {
            activeMoves.add(mvInst);
        }
    }

    public void freezeMvInst(Reg reg) {
        for (var mv: nodeMoves(reg)) {
            activeMoves.remove();
            frozenMoves.add();
            if () {
                simplifyWorklist.add();
            }
        }
    }

    public void freeze() {
        Reg reg = freezeWorklist.removeFirst();
        simplifyWorklist.add(reg);
        freezeMvInst(reg);
    }

    public void selectSpill() {

    }

    public void assignColor() {
        while (!selectStack.isEmpty()) {
            Reg reg = selectStack.pop();

        }
    }

    public void rewrite() {

    }
}//reference to chapter 11.1,11.2
