package MIR.Type;

public class IRIntType extends IRBaseType {
    public IRIntType(int bitWidth) {
        super("i" + bitWidth, bitWidth);
    }

    @Override
    public String toString() {
        return content;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj instanceof IRIntType) {
//            if (((IRIntType) obj).bitWidth == this.bitWidth) {
//                return true;
//            }
//        }
//        return false;
//    }
}
