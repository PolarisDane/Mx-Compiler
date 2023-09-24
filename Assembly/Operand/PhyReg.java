package Assembly.Operand;

import java.util.HashMap;
import java.util.HashSet;

public class PhyReg extends Reg {
    public String name;
    public int id;
    static public HashMap<String, PhyReg> phyRegMap = init();
    static public HashMap<Integer, PhyReg> phyRegId = initId();

    public static HashSet<Reg> callerSave() {
        HashSet<Reg> caller = new HashSet<>();
        caller.add(phyRegMap.get("ra"));
        for (int i = 0; i < 7; i++) {
            caller.add(phyRegMap.get("t" + i));
        }
        for (int i = 0; i < 8; i++) {
            caller.add(phyRegMap.get("a" + i));
        }
        return caller;
    }

    public static HashSet<Reg> calleeSave() {
        HashSet<Reg> callee = new HashSet<>();
        for (int i = 0; i < 12; i++) {
            callee.add(phyRegMap.get("s" + i));
        }
        return callee;
    }

    private static HashMap<String, PhyReg> init() {
        HashMap<String, PhyReg> newMap = new HashMap<>();
        newMap.put("x0", new PhyReg("x0", 0));
        newMap.put("ra", new PhyReg("ra", 1));
        newMap.put("sp", new PhyReg("sp", 2));
        newMap.put("gp", new PhyReg("gp", 3));
        newMap.put("tp", new PhyReg("tp", 4));
        newMap.put("t0", new PhyReg("t0", 5));
        newMap.put("t1", new PhyReg("t1", 6));
        newMap.put("t2", new PhyReg("t2", 7));
        newMap.put("s0", new PhyReg("s0", 8));
        newMap.put("s1", new PhyReg("s1", 9));
        newMap.put("a0", new PhyReg("a0", 10));
        newMap.put("a1", new PhyReg("a1", 11));
        newMap.put("a2", new PhyReg("a2", 12));
        newMap.put("a3", new PhyReg("a3", 13));
        newMap.put("a4", new PhyReg("a4", 14));
        newMap.put("a5", new PhyReg("a5", 15));
        newMap.put("a6", new PhyReg("a6", 16));
        newMap.put("a7", new PhyReg("a7", 17));
        newMap.put("s2", new PhyReg("s2", 18));
        newMap.put("s3", new PhyReg("s3", 19));
        newMap.put("s4", new PhyReg("s4", 20));
        newMap.put("s5", new PhyReg("s5", 21));
        newMap.put("s6", new PhyReg("s6", 22));
        newMap.put("s7", new PhyReg("s7", 23));
        newMap.put("s8", new PhyReg("s8", 24));
        newMap.put("s9", new PhyReg("s9", 25));
        newMap.put("s10", new PhyReg("s10", 26));
        newMap.put("s11", new PhyReg("s11", 27));
        newMap.put("t3", new PhyReg("t3", 28));
        newMap.put("t4", new PhyReg("t4", 29));
        newMap.put("t5", new PhyReg("t5", 30));
        newMap.put("t6", new PhyReg("t6", 31));
        return newMap;
    }

    private static HashMap<Integer, PhyReg> initId() {
        HashMap<Integer, PhyReg> newMap = new HashMap<>();
        newMap.put(0, phyRegMap.get("x0"));
        newMap.put(1, phyRegMap.get("ra"));
        newMap.put(2, phyRegMap.get("sp"));
        newMap.put(3, phyRegMap.get("gp"));
        newMap.put(4, phyRegMap.get("tp"));
        newMap.put(5, phyRegMap.get("t0"));
        newMap.put(6, phyRegMap.get("t1"));
        newMap.put(7, phyRegMap.get("t2"));
        newMap.put(8, phyRegMap.get("s0"));
        newMap.put(9, phyRegMap.get("s1"));
        newMap.put(10, phyRegMap.get("a0"));
        newMap.put(11, phyRegMap.get("a1"));
        newMap.put(12, phyRegMap.get("a2"));
        newMap.put(13, phyRegMap.get("a3"));
        newMap.put(14, phyRegMap.get("a4"));
        newMap.put(15, phyRegMap.get("a5"));
        newMap.put(16, phyRegMap.get("a6"));
        newMap.put(17, phyRegMap.get("a7"));
        newMap.put(18, phyRegMap.get("s2"));
        newMap.put(19, phyRegMap.get("s3"));
        newMap.put(20, phyRegMap.get("s4"));
        newMap.put(21, phyRegMap.get("s5"));
        newMap.put(22, phyRegMap.get("s6"));
        newMap.put(23, phyRegMap.get("s7"));
        newMap.put(24, phyRegMap.get("s8"));
        newMap.put(25, phyRegMap.get("s9"));
        newMap.put(26, phyRegMap.get("s10"));
        newMap.put(27, phyRegMap.get("s11"));
        newMap.put(28, phyRegMap.get("t3"));
        newMap.put(29, phyRegMap.get("t4"));
        newMap.put(30, phyRegMap.get("t5"));
        newMap.put(31, phyRegMap.get("t6"));
        return newMap;
    }

    public PhyReg(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

}
