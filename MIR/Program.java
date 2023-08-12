package MIR;

import MIR.Entity.*;
import MIR.Type.*;

import java.util.ArrayList;

public class Program {
    public ArrayList<IRGlobalVar> globalVar = new ArrayList<>();
    public ArrayList<Function> functions = new ArrayList<>();
    public ArrayList<IRStructType> classes = new ArrayList<>();

    public Program() {}

    public String toString() {
        return "";
    }
}
