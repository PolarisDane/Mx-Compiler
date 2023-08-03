package Utils;

import java.util.HashMap;
import AST.*;

public class Scope {
    public HashMap<String, Type> var = new HashMap<>();
    public Scope parentScope = null;
    public DefineClassNode fromClass = null;
    public DefineFunctionNode fromFunc = null;
    public 

    public Scope(Scope parentScope) {
        this.parentScope = parentScope;

    }

    public Scope parentScope() {
        return parentScope;
    }

    public void defineVariable(String name, Type t, position pos) {

    }
}
