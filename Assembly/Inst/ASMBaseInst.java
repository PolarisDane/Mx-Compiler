package Assembly.Inst;

public abstract class ASMBaseInst {

    public ASMBaseInst prev = null, nxt = null;
    public abstract String toString();
}
