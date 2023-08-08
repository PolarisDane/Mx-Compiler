package MIR.Type;

public class IRNullType extends IRBaseType {
    public IRNullType() {
        super("null", 0);
    }

    @Override
    public String toString() {
        return content;
    }
}
