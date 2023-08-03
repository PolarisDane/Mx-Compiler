package Utils;

import AST.*;
import Utils.position;
import Utils.Type;

public class Builtin {
    public Type IntType = new Type("int");
    public Type StringType = new Type("string");
    public Type BoolType = new Type("bool");
    public Type VoidType = new Type("void");

    public DefineFunctionNode printFunc = new DefineFunctionNode(null, "print");
    public DefineFunctionNode printlnFunc = new DefineFunctionNode(null, "println");
    public DefineFunctionNode printIntFunc = new DefineFunctionNode(null, "printInt");
    public DefineFunctionNode printlnIntFunc = new DefineFunctionNode(null, "printlnInt");
    public DefineFunctionNode getStringFunc = new DefineFunctionNode(null, "getString");
    public DefineFunctionNode getIntFunc = new DefineFunctionNode(null, "getInt");
    public DefineFunctionNode toStringFunc = new DefineFunctionNode(null, "toString");
    public DefineFunctionNode sizeFunc = new DefineFunctionNode(null, "size");

    public DefineClassNode IntClass = new DefineClassNode(null, "Int");
    public DefineClassNode BoolClass = new DefineClassNode(null, "Bool");
    public DefineClassNode StringClass = new DefineClassNode(null, "String");
    public DefineFunctionNode lengthFunc = new DefineFunctionNode(null, "length");
    public DefineFunctionNode substringFunc = new DefineFunctionNode(null, "substring");
    public DefineFunctionNode parseIntFunc = new DefineFunctionNode(null, "parseInt");
    public DefineFunctionNode ordFunc = new DefineFunctionNode(null, "ord");
    public Builtin() {
        sizeFunc.type = IntType;
        sizeFunc.paramsList = null;

        ParamsListNode paramsListInt = new ParamsListNode(null);
        paramsListInt.types.add(IntType);
        paramsListInt.identifiers.add("n");
        ParamsListNode paramsListString = new ParamsListNode(null);
        paramsListString.types.add(StringType);
        paramsListString.identifiers.add("str");
        printFunc.type = VoidType;
        printFunc.paramsList = paramsListInt;
        printlnFunc.type = VoidType;
        printlnFunc.paramsList = paramsListInt;
        printIntFunc.type = VoidType;
        printIntFunc.paramsList = paramsListInt;
        printlnIntFunc.type = VoidType;
        printlnIntFunc.paramsList = paramsListInt;
        getStringFunc.type = StringType;
        getStringFunc.paramsList = null;
        getIntFunc.type = IntType;
        getIntFunc.paramsList = null;
        toStringFunc.type = StringType;
        toStringFunc.paramsList = paramsListString;

        ParamsListNode paramsListSubstring = new ParamsListNode(null);
        paramsListSubstring.types.add(IntType);
        paramsListSubstring.identifiers.add("left");
        paramsListSubstring.types.add(IntType);
        paramsListSubstring.identifiers.add("right");
        ParamsListNode paramsListOrd = new ParamsListNode(null);
        paramsListOrd.types.add(IntType);
        paramsListOrd.identifiers.add("pos");
        lengthFunc.type = IntType;
        lengthFunc.paramsList = null;
        substringFunc.type = StringType;
        substringFunc.paramsList = paramsListSubstring;
        parseIntFunc.type = IntType;
        parseIntFunc.paramsList = null;
        ordFunc.type = IntType;
        ordFunc.paramsList = paramsListOrd;
        StringClass.funcMap.put("length", lengthFunc);
        StringClass.funcMap.put("substring", substringFunc);
        StringClass.funcMap.put("parseInt", parseIntFunc);
        StringClass.funcMap.put("ord", ordFunc);
        //Maybe something to add here?
    }
}
