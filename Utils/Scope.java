package Utils;

import java.util.HashMap;
import AST.*;
import Utils.error.semanticError;

public class Scope {
    public HashMap<String, Type> varMap = new HashMap<>();
    public Scope parentScope = null;
    public DefineClassNode inClass = null;
    public DefineFunctionNode inFunc = null;
    public boolean inLoop = false;

    public Type returned = null;

    public Scope() {}

    public Scope(Scope parentScope) {
        this.parentScope = parentScope;
        this.inClass = parentScope.inClass;
        this.inFunc = parentScope.inFunc;
    }

    public void defineVariable(String name, Type type, position pos) {
        if (varMap.containsKey(name)) {
            throw new semanticError("Multiple definition of variable " + name, pos);
        }
        varMap.put(name, type);
    }

    public boolean containsVariable(String name, boolean lookUpon) {
        if (varMap.containsKey(name)) {
            return true;
        }
        if (parentScope != null && lookUpon) {
            return parentScope.containsVariable(name, lookUpon);
        }
        return false;
    }

    public Type getVariableType(String name, boolean lookUpon) {
        if (varMap.containsKey(name)) {
            return varMap.get(name);
        }
        if (parentScope != null && lookUpon) {
            return parentScope.getVariableType(name, lookUpon);
        }
        return null;
    }
}
