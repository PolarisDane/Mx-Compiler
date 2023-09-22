package Assembly.Inst;

import Assembly.Operand.Reg;

import java.util.HashSet;

public abstract class ASMBaseInst {

    public ASMBaseInst prev = null, nxt = null;
    public Reg rs1, rs2, rd;
    public abstract String toString();
    public HashSet<Reg> getUse() {
        HashSet<Reg> use = new HashSet<>();
        if (rs1 != null) {
            use.add(rs1);
        }
        if (rs2 != null) {
            use.add(rs2);
        }
        return use;
    }
    public HashSet<Reg> getDef() {
        HashSet<Reg> def = new HashSet<>();
        if (rd != null) {
            def.add(rd);
        }
        return def;
    }
}
