package Assembly.Operand;

public abstract class Global extends Reg {
    public String name;
    public Global(String name) {
        this.name = name;
    }
}
