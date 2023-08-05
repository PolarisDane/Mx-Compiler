package Utils;

import java.util.HashMap;

public class Type {
    public String content;
    public int dim = 0;

    public boolean isReference = false;

    public Type() {}
    public Type(String content, int dim) {
        this.content = content;
        this.dim = dim;
        if (!content.equals("int") && !content.equals("bool") && !content.equals("null")) {
            isReference = true;
        }
        if (dim > 0) {
            isReference = true;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Type)) {
            return false;
        }
        return ((Type) obj).content.equals(this.content) && ((Type) obj).dim == this.dim;
    }

}