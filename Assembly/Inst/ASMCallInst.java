package Assembly.Inst;

import Assembly.ASMBlock;
import Assembly.ASMFunction;
import Assembly.Operand.*;

import java.util.HashSet;

public class ASMCallInst extends ASMBaseInst {
    public String funcName;
    public ASMCallInst(String funcName) {
        this.funcName = funcName;
    }
    @Override
    public String toString() {
        return "call " + funcName;
    }
}
