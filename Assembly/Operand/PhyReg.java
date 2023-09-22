package Assembly.Operand;

import java.util.HashMap;

public class PhyReg extends Reg {
    public String name;
    static public HashMap<String, PhyReg> phyRegMap = init();

    private static HashMap<String, PhyReg> init() {
        HashMap<String, PhyReg> newMap = new HashMap<>();
        newMap.put("x0", new PhyReg("x0"));
        newMap.put("ra", new PhyReg("ra"));
        newMap.put("sp", new PhyReg("sp"));
        newMap.put("gp", new PhyReg("gp"));
        newMap.put("tp", new PhyReg("tp"));
        newMap.put("t0", new PhyReg("t0"));
        newMap.put("t1", new PhyReg("t1"));
        newMap.put("t2", new PhyReg("t2"));
        newMap.put("s0", new PhyReg("s0"));
        newMap.put("s1", new PhyReg("s1"));
        newMap.put("a0", new PhyReg("a0"));
        newMap.put("a1", new PhyReg("a1"));
        newMap.put("a2", new PhyReg("a2"));
        newMap.put("a3", new PhyReg("a3"));
        newMap.put("a4", new PhyReg("a4"));
        newMap.put("a5", new PhyReg("a5"));
        newMap.put("a6", new PhyReg("a6"));
        newMap.put("a7", new PhyReg("a7"));
        newMap.put("s2", new PhyReg("s2"));
        newMap.put("s3", new PhyReg("s3"));
        newMap.put("s4", new PhyReg("s4"));
        newMap.put("s5", new PhyReg("s5"));
        newMap.put("s6", new PhyReg("s6"));
        newMap.put("s7", new PhyReg("s7"));
        newMap.put("s8", new PhyReg("s8"));
        newMap.put("s9", new PhyReg("s9"));
        newMap.put("s10", new PhyReg("s10"));
        newMap.put("s11", new PhyReg("s11"));
        newMap.put("t3", new PhyReg("t3"));
        newMap.put("t4", new PhyReg("t4"));
        newMap.put("t5", new PhyReg("t5"));
        newMap.put("t6", new PhyReg("t6"));
        return newMap;
    }

    public PhyReg(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
