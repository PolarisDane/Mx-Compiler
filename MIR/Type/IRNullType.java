package MIR.Type;

public class IRNullType extends IRBaseType {
    public IRNullType() {
        super("ptr", 0);
    }

    @Override
    public String toString() {
        return "ptr";
    }
}
