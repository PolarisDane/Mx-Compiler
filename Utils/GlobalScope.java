package Utils;

import java.util.HashMap;
import AST.*;
import Utils.Builtin;

public class GlobalScope extends Scope  {
    public HashMap<String, DefineFunctionNode> funcMap = new HashMap<>();
    public HashMap<String, DefineClassNode> classMap = new HashMap<>();

    public GlobalScope() {
        super();
        Builtin builtin = new Builtin();
        funcMap.put("print", builtin.printFunc);
        funcMap.put("println", builtin.printlnFunc);
        funcMap.put("printInt", builtin.printIntFunc);
        funcMap.put("printlnInt", builtin.printlnIntFunc);
        funcMap.put("getString", builtin.getStringFunc);
        funcMap.put("toString", builtin.toStringFunc);

    }
}
