package Utils;

import java.util.HashMap;

public class Type {
    public String content = null;
    public HashMap<String, Type> members = null;

    public Type(String content) {
        this.content = content;
    }
}