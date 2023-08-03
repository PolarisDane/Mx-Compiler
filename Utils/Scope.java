package Utils;

import java.util.HashMap;
import AST.*;
import Utils.error.semanticError;

public class Scope {
    public HashMap<String, Type> var = new HashMap<>();
    public Scope parentScope = null;
    public DefineClassNode inClass = null;
    public DefineFunctionNode inFunc = null;
    public boolean inLoop = false;

    public boolean returned = false;

    public Scope() {}

    public Scope(Scope parentScope) {
        this.parentScope = parentScope;
        this.inClass = parentScope.inClass;
        this.inFunc = parentScope.inFunc;
    }

    public Scope parentScope() {
        return parentScope;
    }

    public void defineVariable(String name, Type type, position pos) {
        if (var.containsKey(name)) {
            throw new semanticError("Multiple definition of variable " + name, pos);
        }
        var.put(name, type);
    }

    public boolean containsVariable(String name) {
        if (var.containsKey(name)) {
            return true;
        }
        if (parentScope != null) {
            return parentScope.containsVariable(name);
        }
        return false;
    }

    public Type getVariableType(String name) {
        if (var.containsKey(name)) {
            return var.get(name);
        }
        if (parentScope != null) {
            return parentScope.getVariableType(name);
        }
        return null;
    }
}
