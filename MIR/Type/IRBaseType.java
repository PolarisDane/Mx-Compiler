package MIR.Type;

public abstract class IRBaseType {
    public String content;
    public int size;
    public IRBaseType(String content, int size) {
        this.content = content;
        this.size = size;
    }

    public abstract String toString();

    public boolean equals(Object obj) {
        if (!content.equals(((IRBaseType) obj).content)) {
            return false;
        }
        if (size != ((IRBaseType) obj).size) {
            return false;
        }
        return true;
    }
}
