package Utils;

import java.util.HashMap;

public class Type {
    public String content;
    public HashMap<String, Type> members = new HashMap<>();
    public int dim = 0;

    public Type() {}
    public Type(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Type)) {
            return false;
        }
        return ((Type) obj).content.equals(this.content) && ((Type) obj).dim == this.dim;
    }
}