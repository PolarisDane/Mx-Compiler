package Assembly.Operand;

import java.util.HashMap;

public class PhyReg extends Reg {
    public String name;
    static public HashMap<String, PhyReg> phyRegMap = init();
    static public HashMap<Integer, PhyReg> phyRegId = initId();

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

    private static HashMap<Integer, PhyReg> initId() {
        HashMap<Integer, PhyReg> newMap = new HashMap<>();
        newMap.put(0, new PhyReg("x0"));
        newMap.put(1, new PhyReg("ra"));
        newMap.put(2, new PhyReg("sp"));
        newMap.put(3, new PhyReg("gp"));
        newMap.put(4, new PhyReg("tp"));
        newMap.put(5, new PhyReg("t0"));
        newMap.put(6, new PhyReg("t1"));
        newMap.put(7, new PhyReg("t2"));
        newMap.put(8, new PhyReg("s0"));
        newMap.put(9, new PhyReg("s1"));
        newMap.put(10, new PhyReg("a0"));
        newMap.put(11, new PhyReg("a1"));
        newMap.put(12, new PhyReg("a2"));
        newMap.put(13, new PhyReg("a3"));
        newMap.put(14, new PhyReg("a4"));
        newMap.put(15, new PhyReg("a5"));
        newMap.put(16, new PhyReg("a6"));
        newMap.put(17, new PhyReg("a7"));
        newMap.put(18, new PhyReg("s2"));
        newMap.put(19, new PhyReg("s3"));
        newMap.put(20, new PhyReg("s4"));
        newMap.put(21, new PhyReg("s5"));
        newMap.put(22, new PhyReg("s6"));
        newMap.put(23, new PhyReg("s7"));
        newMap.put(24, new PhyReg("s8"));
        newMap.put(25, new PhyReg("s9"));
        newMap.put(26, new PhyReg("s10"));
        newMap.put(27, new PhyReg("s11"));
        newMap.put(28, new PhyReg("t3"));
        newMap.put(29, new PhyReg("t4"));
        newMap.put(30, new PhyReg("t5"));
        newMap.put(31, new PhyReg("t6"));
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
