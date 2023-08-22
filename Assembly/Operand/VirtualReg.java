package Assembly.Operand;

public class VirtualReg extends Reg {
    public int size;
    public int id;
    public static int cnt = 0;
    public VirtualReg(int size) {
        this.size = size;
        id = cnt++;
    }

    @Override
    public String toString() {
        return "%" + id;
    }
}
