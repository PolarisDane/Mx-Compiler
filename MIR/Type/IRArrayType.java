package MIR.Type;

public class IRArrayType extends IRBaseType {
    public IRBaseType type;
    public int arraySize;

    public IRArrayType(IRBaseType type, int arraySize) {
        super("[" + arraySize + " x " + type.toString() + "]", type.size * arraySize);
        this.type = type;
        this.arraySize = arraySize;
    }

    @Override
    public String toString() {
        return content;
    }

//    @Override
//    public boolean equals(Object obj) {
//        return false;
//    }
}
