package MIR.Type;

public class IRVoidType extends IRBaseType {
    public IRVoidType() {
        super("void", 0);
    }

    @Override
    public String toString() {
        return content;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj instanceof IRVoidType) {
//            return true;
//        }
//        return false;
//    }
}
