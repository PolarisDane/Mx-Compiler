package MIR.Inst;

import MIR.Entity.Entity;
import MIR.Entity.IRRegister;
import MIR.Type.IRBaseType;

public class IRGetElementPtr extends IRBaseInst {
    public IRRegister res;
    public Entity ptr;
    public IRBaseType type;

}
