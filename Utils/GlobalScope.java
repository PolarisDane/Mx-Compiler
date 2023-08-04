package Utils;

import java.util.HashMap;
import AST.*;
import Utils.Builtin;
import Utils.error.semanticError;

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
        classMap.put("int", builtin.intClass);
        classMap.put("string", builtin.stringClass);
        classMap.put("bool", builtin.boolClass);
        classMap.put("void", builtin.voidClass);
    }//put all builtin classes and functions into global scope for further semantic check usage

    public void addFunc(DefineFunctionNode it, position pos) {
        if (funcMap.containsKey(it.funcName) || classMap.containsKey(it.funcName) || varMap.containsKey(it.funcName)) {
            throw new semanticError("Ambiguous meaning of " + it.funcName, pos);
        }
        funcMap.put(it.funcName, it);
    }
    public boolean containsFunc(String funcName, position pos) {
        if (funcMap.containsKey(funcName)) return true;
        return false;
    }
    public DefineFunctionNode getFunc(String funcName, position pos) {
        if (funcMap.containsKey(funcName)) return funcMap.get(funcName);
        throw new semanticError("Function " + funcName + " not defined", pos);
    }
    public void addClass(DefineClassNode it, position pos) {
        if (classMap.containsKey(it.className) || funcMap.containsKey(it.className)) {
            throw new semanticError("Ambiguous meaning of " + it.className, pos);
        }
        System.out.println("Added class " + it.className);
        classMap.put(it.className, it);
    }
    public boolean containsClass(String className, position pos) {
        if (classMap.containsKey(className)) return true;
        return false;
    }
    public DefineClassNode getClass(String className, position pos) {
        if (classMap.containsKey(className)) return classMap.get(className);
        throw new semanticError("Class " + className + " not defined", pos);
    }
}
