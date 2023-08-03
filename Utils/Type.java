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
}