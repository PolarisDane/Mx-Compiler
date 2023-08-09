package MIR.Type;

public class IRPtrType extends IRBaseType {
    public IRBaseType type;
    public int dim = 1;
    public IRPtrType(IRBaseType type) {
        super(type.content + "*", 4);
        this.type = type;
        if (type instanceof IRPtrType) {
            this.type = ((IRPtrType) type).type;
            this.dim = ((IRPtrType) type).dim + 1;
        }
        else {
            this.dim = 0;
        }
    }

    public IRPtrType(IRBaseType type, int dim) {
        super(type.content + "*", 4);
        this.type = type;
        this.dim = dim;
    }

    @Override
    public String toString() {
        return type.content + "*" + dim;
    }
}
