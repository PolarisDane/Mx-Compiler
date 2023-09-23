package Assembly.Operand;

public abstract class Reg extends Operand {
    public double spillWeight = 0;
    public Integer stackOffset = null;
}
