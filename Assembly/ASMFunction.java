package Assembly;

import Assembly.Operand.*;
import Assembly.Inst.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ASMFunction {
    public static ArrayList<String> phyRegName = new ArrayList<>(Arrays.asList(
            "zero", "ra", "sp", "gp", "tp", "t0", "t1", "t2", "s0", "s1",
            "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7",
            "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "t3", "t4", "t5", "t6"));

    public String name;
    public ArrayList<ASMBlock> blocks = new ArrayList<>();
    public int argsStack = 0;
    public int spilledLength = 0;
    public int allocaLength = 0;

    public ASMFunction(String name) {
        this.name = name;
    }

    public void work() {
        for (var nxt: blocks) {
            nxt.work();
        }
    }

    public String toString() {
        String ret = "";
        ret += "    .section text\n";
        ret += "    .globl " + name + "\n";
        ret += name + ":\n";
        for (var nxt: blocks) {
            ret += nxt.toString();
        }
        return ret;
    }
}
