package MIR.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class IRStructType extends IRBaseType {
    public ArrayList<IRBaseType> memberType = new ArrayList<>();
    public HashMap<String, Integer> memberMap = new HashMap<>();
    public boolean constructor = false;
    public IRStructType(String content, int size) {
        super(content, size);
    }

    public void putMember(String member, IRBaseType type) {
        memberType.add(type);
        if (type.size < 32) {
            memberType.add(new IRArrayType(new IRIntType(8), (32 - type.size) / 8));
        }
        memberMap.put(member, memberType.size() - 1);
        size += 32;
    }

    @Override
    public String toString() {
        String ret = "%struct." + content;
        return ret;
    }

}
