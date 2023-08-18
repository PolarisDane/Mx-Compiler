package Backend;

import AST.*;
import MIR.*;
import MIR.Entity.*;
import MIR.Inst.*;
import MIR.Type.*;
import Utils.*;

import java.util.ArrayList;

public class IRBuilder implements ASTVisitor {
    public Builtin builtin = new Builtin();
    public Scope curScope;
    public GlobalScope gScope;
    public BasicBlock curBlock = null;

    public Function inFunc = null;
    public BasicBlock breakNxt = null;
    public BasicBlock continueNxt = null;
    public Program program = new Program();
    public boolean global_init = false;

    public IRBuilder(GlobalScope gScope) {
        this.gScope = gScope;
        this.curScope = gScope;
        IRBaseType CharStar = new IRPtrType(new IRIntType(8), 0);
        IRBaseType Int = new IRIntType(32);
        program.IRBuiltinFunc.add(new IRFuncDeclare("print", new IRVoidType(), CharStar));
        program.IRBuiltinFunc.add(new IRFuncDeclare("println", new IRVoidType(), CharStar));
        program.IRBuiltinFunc.add(new IRFuncDeclare("printInt", new IRVoidType(), Int));
        program.IRBuiltinFunc.add(new IRFuncDeclare("printlnInt", new IRVoidType(), Int));
        program.IRBuiltinFunc.add(new IRFuncDeclare("getString", CharStar));
        program.IRBuiltinFunc.add(new IRFuncDeclare("getInt", Int));
        program.IRBuiltinFunc.add(new IRFuncDeclare("toString", Int, CharStar));
        program.IRBuiltinFunc.add(new IRFuncDeclare("string__substring", CharStar, CharStar));
        program.IRBuiltinFunc.add(new IRFuncDeclare("string__parseInt", Int, CharStar));
        program.IRBuiltinFunc.add(new IRFuncDeclare("string__ord", Int, CharStar));
        program.IRBuiltinFunc.add(new IRFuncDeclare("string__stradd", CharStar, CharStar, CharStar));
        program.IRBuiltinFunc.add(new IRFuncDeclare("string__strlt", CharStar, CharStar, CharStar));
        program.IRBuiltinFunc.add(new IRFuncDeclare("string__strle", CharStar, CharStar, CharStar));
        program.IRBuiltinFunc.add(new IRFuncDeclare("string__strgt", CharStar, CharStar, CharStar));
        program.IRBuiltinFunc.add(new IRFuncDeclare("string__strge", CharStar, CharStar, CharStar));
        program.IRBuiltinFunc.add(new IRFuncDeclare("string__streq", CharStar, CharStar, CharStar));
        program.IRBuiltinFunc.add(new IRFuncDeclare("string__strneq", CharStar, CharStar, CharStar));
        program.IRBuiltinFunc.add(new IRFuncDeclare("__malloc", CharStar, Int));
        program.IRBuiltinFunc.add(new IRFuncDeclare("__size", Int, CharStar));
    }

    public Entity getVal(ExprNode expr, boolean is_i1) {
        IRRegister res = new IRRegister("", new IRIntType(1));
        if (expr.entity != null) {
            if (is_i1 && expr.entity.type.size == 8) {
                curBlock.addInst(new IRTrunc(curBlock, res, expr.entity.type, expr.entity, new IRIntType(1)));
                return res;
            }//is stored as 8-bit and used as 1-bit
            return expr.entity;
        }//const value already given
        IRRegister loadVal = new IRRegister("", getIRType(expr.type));
        curBlock.addInst(new IRLoad(curBlock, getIRType(expr.type), loadVal, expr.addr));
        expr.entity = loadVal;//for later use
        if (is_i1 && loadVal.type.size == 8) {
            curBlock.addInst(new IRTrunc(curBlock, res, loadVal.type, loadVal, new IRIntType(1)));
            return res;
        }
        return expr.entity;
    }

    public void storeVal(IRRegister ptr, ExprNode expr) {
        if (getVal(expr, false).type.equals(new IRIntType(1))) {
            IRRegister zext = new IRRegister("frombool", new IRIntType(8));
            curBlock.addInst(new IRZext(curBlock, zext, new IRIntType(1), getVal(expr, false), new IRIntType(8)));
            curBlock.addInst(new IRStore(curBlock, ptr, zext));
        }
        else {
            curBlock.addInst(new IRStore(curBlock, ptr, getVal(expr, false)));
        }
    }

    public IRBaseType getIRType(Type type) {
        IRBaseType IRType = null;
        switch (type.content) {
            case "int":
                IRType = new IRIntType(32);
                break;
            case "bool":
                IRType = new IRIntType(8);
                break;
            case "string":
                IRType = new IRPtrType(new IRArrayType(new IRIntType(8), 0));
                break;
            case "void":
                IRType = new IRVoidType();
                break;
            default:
                IRType = new IRPtrType(program.IRclassMap.get(type.content), 0);
                //for other class
        }
        if (type.dim > 0) {
            IRType = new IRPtrType(IRType, type.dim);
        }
        return IRType;
    }

    public void declareClass(DefineClassNode it) {
        IRStructType struct = new IRStructType(it.className, 0);
        program.classes.add(struct);
        program.IRclassMap.put(it.className, struct);
    }

    public void declareFunc(DefineFunctionNode it) {
        Function func = new Function(it.funcName, getIRType(it.type));
        program.functions.add(func);
        if (it.paramsList != null) {
            for (int i = 0; i < it.paramsList.identifiers.size(); i++) {
                IRRegister reg = new IRRegister(it.paramsList.identifiers.get(i), getIRType(it.paramsList.types.get(i)));//register allocated for parameters
                func.params.add(reg);
            }
        }
        gScope.IRfuncMap.put(func.funcName, func);
    }

    public void declareGlobalVar(DefineVarStmtNode it) {
        for (var nxt: it.assigns) {
            IRGlobalVar gVar = new IRGlobalVar(nxt.assignTo, getIRType(it.type));
            program.globalVar.add(gVar);
            gScope.entityMap.put(nxt.assignTo, gVar);
        }
    }//declare first for use of initialize

    public void initGlobalVar(DefineVarStmtNode it) {
        for (var nxt: it.assigns) {
            IRGlobalVar gVar = (IRGlobalVar) gScope.entityMap.get(nxt.assignTo);
            if (nxt.assignVal != null) {
                nxt.assignVal.accept(this);
                if (nxt.assignVal.entity instanceof IRConst) {
                    gVar.initVal = nxt.assignVal.entity;
                }
                else {
//                    System.out.println("here");
                    if (nxt.assignVal.entity instanceof IRConst) {
                        gVar.initVal = getVal(nxt.assignVal, false);
                    }//useless???
                    else {
                        if (nxt.type.equals(builtin.IntType) || nxt.type.equals(builtin.BoolType)) {
                            gVar.initVal = new IRIntConst(0);
                        }
                        else {
                            gVar.initVal = new IRNullConst();
                        }
                    }//initialization should be in init function
                    storeVal(gVar, nxt.assignVal);
                }
            }
            else {
                if (nxt.type.equals(builtin.IntType) || nxt.type.equals(builtin.BoolType)) {
                    gVar.initVal = new IRIntConst(0);
                }
                else {
                    gVar.initVal = new IRNullConst();
                }
            }
        }
    }

    public IRBaseType dereference(IRBaseType type) {
        IRBaseType new_type;
        if (((IRPtrType) type).dim != 0) {
            new_type = new IRPtrType(((IRPtrType) type).type, ((IRPtrType) type).dim - 1);
        }
        else {
            new_type = ((IRPtrType) type).type;
        }
        return new_type;
    }

    public Entity allocaNew(IRBaseType type, int index, ArrayList<ExprNode> siz) {
        IRBaseType nxt_type = dereference(type);

        IRBaseType VoidStar = new IRPtrType(new IRIntType(8), 0);
        IRRegister tmp_reg = new IRRegister("tmp_reg", new IRIntType(32));
        IRRegister malloc_size = new IRRegister("malloc_size", new IRIntType(32));
        curBlock.addInst(new IRBinaryOp(curBlock, tmp_reg, new IRIntType(32), "mul", new IRIntConst(nxt_type.size), getVal(siz.get(index), false)));
        curBlock.addInst(new IRBinaryOp(curBlock, malloc_size, new IRIntType(32), "add", tmp_reg, new IRIntConst(4)));

        IRRegister malloc_head = new IRRegister("malloc_head", VoidStar);
        IRCall callInst = new IRCall(curBlock, malloc_head, VoidStar, "__malloc");
        callInst.args.add(malloc_size);
        curBlock.addInst(callInst);
        curBlock.addInst(new IRStore(curBlock, malloc_head, getVal(siz.get(index), false)));

        IRRegister array_head = new IRRegister("array_ptr", type);
        IRGetElementPtr getPtrInst = new IRGetElementPtr(curBlock, malloc_head, type, array_head);
        getPtrInst.idx.add(new IRIntConst(1));
        curBlock.addInst(getPtrInst);

        if (index == siz.size() - 1) {
            return array_head;
        }

        curScope = new Scope(curScope);
        BasicBlock forCond = new BasicBlock("new_for.cond", inFunc);
        BasicBlock forInc = new BasicBlock("new_for.inc", inFunc);
        BasicBlock forBody = new BasicBlock("new_for.body", inFunc);
        BasicBlock forEnd = new BasicBlock("new_for.end", inFunc);

        IRRegister iter_var = new IRRegister("iter_var", new IRPtrType(new IRIntType(32), 0));
        curBlock.addInst(new IRAlloca(curBlock, new IRIntType(32), iter_var));
        curBlock.addInst(new IRStore(curBlock, iter_var, new IRIntConst(0)));
        curBlock.addInst(new IRJump(curBlock, forCond.label));

        curBlock = forCond;
        IRRegister tmp_cmp = new IRRegister("", new IRIntType(32));
        IRRegister cond = new IRRegister("", new IRIntType(1));
        curBlock.addInst(new IRLoad(curBlock, new IRIntType(32), tmp_cmp, iter_var));
        curBlock.addInst(new IRIcmp(curBlock, cond, "slt", new IRIntType(32), tmp_cmp, getVal(siz.get(index), false)));
        curBlock.addInst(new IRBranch(curBlock, cond, forBody, forEnd));
        inFunc.blocks.add(forCond);

        curBlock = forInc;
        IRRegister tmp = new IRRegister("", new IRIntType(32));
        IRRegister inc = new IRRegister("", new IRIntType(32));
        curBlock.addInst(new IRLoad(curBlock, new IRIntType(32), tmp, iter_var));
        curBlock.addInst(new IRBinaryOp(curBlock, inc, new IRIntType(32), "add", tmp, new IRIntConst(1)));
        curBlock.addInst(new IRStore(curBlock, iter_var, inc));
        curBlock.addInst(new IRJump(curBlock, forCond.label));
        inFunc.blocks.add(forInc);

        curBlock = forBody;
        var nxt_ptr = allocaNew(nxt_type, index + 1, siz);
        IRRegister array_i = new IRRegister("", nxt_type), ind = new IRRegister("", new IRIntType(32));
        curBlock.addInst(new IRLoad(curBlock, new IRIntType(32), ind, iter_var));
        IRGetElementPtr getPtrIInst = new IRGetElementPtr(curBlock, array_head, type, array_i);
        getPtrIInst.idx.add(ind);
        curBlock.addInst(getPtrIInst);
        curBlock.addInst(new IRStore(curBlock, array_i, nxt_ptr));
        curBlock.addInst(new IRJump(curBlock, forInc.label));
        inFunc.blocks.add(forBody);

        curBlock = forEnd;
        inFunc.blocks.add(forEnd);

        curScope = curScope.parentScope;

        return array_head;
    }

    @Override
    public void visit(FuncCallNode it) {
        DefineFunctionNode funcNode;
        if (it.inClass == null && curScope.inClass != null) {
            it.inClass = curScope.inClass;
        }
        String funcName;
        if (it.inClass == null) {
            funcNode = gScope.getFunc(it.func, it.pos);
            funcName = funcNode.funcName;
        }
        else {
            funcNode = it.inClass.funcMap.get(it.func);
            if (funcNode == null) {
                funcNode = gScope.getFunc(it.func, it.pos);
                funcName = funcNode.funcName;
            }
            else {
                funcName = it.inClass.className + "__" + funcNode.funcName;
            }
        }
        IRCall callInst;
        if (funcNode.type.equals(builtin.VoidType)) {
            callInst = new IRCall(curBlock, null, getIRType(funcNode.type), funcName);
        }
        else {
            callInst = new IRCall(curBlock, new IRRegister("func." + funcNode.funcName, getIRType(funcNode.type)), getIRType(funcNode.type), funcName);
        }
        if (it.args != null) {
            it.args.accept(this);
            for (int i = 0; i < it.args.exprs.size(); i++) {
                callInst.args.add(getVal(it.args.exprs.get(i), false));
            }
        }
        if (it.inClass != null) {
            callInst.args.add(it.thisPtr);
        }
        curBlock.addInst(callInst);
        it.entity = callInst.res;
    }

    @Override
    public void visit(NewExprNode it) {
        if (it.type.dim != 0) {
            if (it.expr.isEmpty()) {
                it.entity = new IRNullConst();
            }
            else {
                for (var size: it.expr) {
                    size.accept(this);
                }
                it.entity = allocaNew(getIRType(it.type), 0, it.expr);
            }
        }
        else {
            it.entity = new IRRegister("new_expr", getIRType(it.type));
            IRCall callInst = new IRCall(curBlock, (IRRegister) it.entity, getIRType(it.type), "__malloc");
            IRPtrType classType = (IRPtrType) (getIRType(it.type));
            callInst.args.add(new IRIntConst(classType.type.size));
            curBlock.addInst(callInst);
            if (((IRStructType) classType.type).constructor) {
                IRCall callConstruct = new IRCall(curBlock, null, new IRVoidType(), it.type.content + "__" + it.type.content);
                callConstruct.args.add(it.entity);
                curBlock.addInst(callConstruct);
            }
        }
    }

    @Override
    public void visit(WrapExprNode it) {
        //should be inaccessible
    }

    @Override
    public void visit(ArrayExprNode it) {
        it.array.accept(this);
        it.index.accept(this);
        IRRegister res = new IRRegister("array_ptr", dereference(getVal(it.array, false).type));
        IRGetElementPtr inst = new IRGetElementPtr(curBlock, getVal(it.array, false), getIRType(it.type), res);
        inst.idx.add(getVal(it.index, false));
        curBlock.addInst(inst);
        it.addr = res;
    }

    @Override
    public void visit(MemberExprNode it) {
        it.obj.accept(this);
        IRStructType struct = program.IRclassMap.get(it.obj.type.content);
        if (it.memberFunc != null) {
            if (it.obj.type.dim > 0) {
                if (it.memberFunc.func.equals("size")) {
                    IRRegister res = new IRRegister("size", new IRIntType(32));
                    IRCall callInst = new IRCall(curBlock, res, new IRIntType(32), "__size");
                    callInst.args.add(getVal(it.obj, false));
                    curBlock.addInst(callInst);
                    it.entity = res;
                    return;
                }
            }
            it.memberFunc.thisPtr = (IRRegister) getVal(it.obj, false);
            it.memberFunc.accept(this);
            it.entity = it.memberFunc.entity;
        }
        if (it.member != null) {
            IRRegister res = new IRRegister("member", struct.memberType.get(struct.memberMap.get(it.member)));
            IRGetElementPtr getPtrInst = new IRGetElementPtr(curBlock, getVal(it.obj, false), struct, res);
            getPtrInst.idx.add(new IRIntConst(0));
            getPtrInst.idx.add(new IRIntConst(struct.memberMap.get(it.member)));
            curBlock.addInst(getPtrInst);
            it.addr = res;
        }
    }

    @Override
    public void visit(RightSelfExprNode it) {
        it.obj.accept(this);
        IRRegister res = new IRRegister("self_expr" , new IRIntType(32));
        Entity ori = getVal(it.obj, false);
        if (it.op.equals("++")) {
            curBlock.addInst(new IRBinaryOp(curBlock, res, new IRIntType(32), "add", ori, new IRIntConst(1)));
            curBlock.addInst(new IRStore(curBlock, it.obj.addr, res));
        }
        else if (it.op.equals("--")) {
            curBlock.addInst(new IRBinaryOp(curBlock, res, new IRIntType(32), "sub", ori, new IRIntConst(1)));
            curBlock.addInst(new IRStore(curBlock, it.obj.addr, res));
        }
        it.entity = ori;
    }

    @Override
    public void visit(LeftSelfExprNode it) {
        it.obj.accept(this);
        IRRegister res = new IRRegister("self_expr" , new IRIntType(32));
        if (it.op.equals("++")) {
            curBlock.addInst(new IRBinaryOp(curBlock, res, new IRIntType(32), "add", getVal(it.obj, false), new IRIntConst(1)));
            curBlock.addInst(new IRStore(curBlock, it.obj.addr, res));
        }
        else if (it.op.equals("--")) {
            curBlock.addInst(new IRBinaryOp(curBlock, res, new IRIntType(32), "sub", getVal(it.obj, false), new IRIntConst(1)));
            curBlock.addInst(new IRStore(curBlock, it.obj.addr, res));
        }
        it.entity = res;
    }

    @Override
    public void visit(SingleExprNode it) {
        it.obj.accept(this);
        IRRegister res = new IRRegister("single_expr" , new IRIntType(32));
        if (it.op.equals("+")) {
            //nothing here
        }
        else if (it.op.equals("-")) {
            curBlock.addInst(new IRBinaryOp(curBlock, res, new IRIntType(32), "sub", new IRIntConst(0), getVal(it.obj, false)));
        }
        else if (it.op.equals("!")) {
            curBlock.addInst(new IRBinaryOp(curBlock, res, new IRIntType(8), "xor", new IRIntConst(1), getVal(it.obj, false)));
        }
        else if (it.op.equals("~")) {
            curBlock.addInst(new IRBinaryOp(curBlock, res, new IRIntType(32), "xor", new IRIntConst(-1), getVal(it.obj, false)));
        }
        it.entity = res;
    }

    @Override
    public void visit(BinaryExprNode it) {
        it.lop.accept(this);
        if (!it.op.equals("&&") && !it.op.equals("||")) {
            String op = null;
            it.rop.accept(this);
            if (it.lop.type.equals(builtin.StringType) || it.rop.type.equals(builtin.StringType)) {
                IRRegister res = null;
                IRCall callInst;
                switch(it.op) {
                    case "+":
                        it.entity = new IRRegister("binary_op", new IRPtrType(new IRIntType(8), 0));
                        callInst = new IRCall(curBlock, (IRRegister) it.entity, it.entity.type, "string__stradd");
                        callInst.args.add(getVal(it.lop, false));
                        callInst.args.add(getVal(it.rop, false));
                        curBlock.addInst(callInst);
                        break;
                    case "==":
                        res = new IRRegister("call_builtin", new IRIntType(8));
                        it.entity = new IRRegister("binary_op", new IRIntType(1));
                        callInst = new IRCall(curBlock, res, res.type, "string__streq");
                        callInst.args.add(getVal(it.lop, false));
                        callInst.args.add(getVal(it.rop, false));
                        curBlock.addInst(callInst);
                        curBlock.addInst(new IRTrunc(curBlock, (IRRegister) it.entity, new IRIntType(8), res, new IRIntType(1)));
                        break;
                    case "!=":
                        res = new IRRegister("call_builtin", new IRIntType(8));
                        it.entity = new IRRegister("binary_op", new IRIntType(1));
                        callInst = new IRCall(curBlock, res, res.type, "string__strneq");
                        callInst.args.add(getVal(it.lop, false));
                        callInst.args.add(getVal(it.rop, false));
                        curBlock.addInst(callInst);
                        curBlock.addInst(new IRTrunc(curBlock, (IRRegister) it.entity, new IRIntType(8), res, new IRIntType(1)));
                        break;
                    case "<":
                        res = new IRRegister("call_builtin", new IRIntType(8));
                        it.entity = new IRRegister("binary_op", new IRIntType(1));
                        callInst = new IRCall(curBlock, res, res.type, "string__strlt");
                        callInst.args.add(getVal(it.lop, false));
                        callInst.args.add(getVal(it.rop, false));
                        curBlock.addInst(callInst);
                        curBlock.addInst(new IRTrunc(curBlock, (IRRegister) it.entity, new IRIntType(8), res, new IRIntType(1)));
                        break;
                    case ">":
                        res = new IRRegister("call_builtin", new IRIntType(8));
                        it.entity = new IRRegister("binary_op", new IRIntType(1));
                        callInst = new IRCall(curBlock, res, res.type, "string__strgt");
                        callInst.args.add(getVal(it.lop, false));
                        callInst.args.add(getVal(it.rop, false));
                        curBlock.addInst(callInst);
                        curBlock.addInst(new IRTrunc(curBlock, (IRRegister) it.entity, new IRIntType(8), res, new IRIntType(1)));
                        break;
                    case "<=":
                        res = new IRRegister("call_builtin", new IRIntType(8));
                        it.entity = new IRRegister("binary_op", new IRIntType(1));
                        callInst = new IRCall(curBlock, res, res.type, "string__strle");
                        callInst.args.add(getVal(it.lop, false));
                        callInst.args.add(getVal(it.rop, false));
                        curBlock.addInst(callInst);
                        curBlock.addInst(new IRTrunc(curBlock, (IRRegister) it.entity, new IRIntType(8), res, new IRIntType(1)));
                        break;
                    case ">=":
                        res = new IRRegister("call_builtin", new IRIntType(8));
                        it.entity = new IRRegister("binary_op", new IRIntType(1));
                        callInst = new IRCall(curBlock, res, res.type, "string__strge");
                        callInst.args.add(getVal(it.lop, false));
                        callInst.args.add(getVal(it.rop, false));
                        curBlock.addInst(callInst);
                        curBlock.addInst(new IRTrunc(curBlock, (IRRegister) it.entity, new IRIntType(8), res, new IRIntType(1)));
                        break;
                }
                return;
            }
            boolean flag = false;
            switch(it.op) {
                case "+":
                    op = "add";
                    flag = true;
                    break;
                case "-":
                    op = "sub";
                    flag = true;
                    break;
                case "*":
                    op = "mul";
                    flag = true;
                    break;
                case "/":
                    op = "sdiv";
                    flag = true;
                    break;
                case "%":
                    op = "srem";
                    flag = true;
                    break;
                case "&":
                    op = "and";
                    flag = true;
                    break;
                case "|":
                    op = "or";
                    flag = true;
                    break;
                case "<<":
                    op = "shl";
                    flag = true;
                    break;
                case ">>":
                    op = "ashr";
                    flag = true;
                    break;
                case "^":
                    op = "xor";
                    flag = true;
                    break;
                case ">=":
                    op = "sge";
                    break;
                case "<=":
                    op = "sle";
                    break;
                case ">":
                    op = "sgt";
                    break;
                case "<":
                    op = "slt";
                    break;
                case "==":
                    op = "eq";
                    break;
                case "!=":
                    op = "ne";
                    break;
            }
            if (flag) {
                IRRegister res = new IRRegister("binary_op", new IRIntType(32));
                curBlock.addInst(new IRBinaryOp(curBlock, res, new IRIntType(32), op, getVal(it.lop, false), getVal(it.rop, false)));
                it.entity = res;
            }
            else {
                IRRegister res = new IRRegister("binary_op", new IRIntType(1));
                curBlock.addInst(new IRIcmp(curBlock, res, op, new IRIntType(32), getVal(it.lop, false), getVal(it.rop, false)));
                it.entity = res;
            }
        }//non short-circuit value
        else {
            IRRegister res = new IRRegister("binary_op", new IRPtrType(new IRIntType(8), 0));
            curBlock.addInst(new IRAlloca(curBlock, new IRIntType(8), res));
            BasicBlock rightBranch = new BasicBlock("binary_op.rhs", inFunc);
            BasicBlock shortCircuitbranch = new BasicBlock("binary_op.short", inFunc);
            BasicBlock endBinaryOp = new BasicBlock("binary_op.end", inFunc);
            BasicBlock prev = curBlock;
            if (it.op.equals("&&")) {
                prev.addInst(new IRBranch(curBlock, getVal(it.lop, true), rightBranch, shortCircuitbranch));

                curBlock = rightBranch;
                inFunc.blocks.add(rightBranch);
                it.rop.accept(this);
                storeVal(res, it.rop);
                curBlock.addInst(new IRJump(curBlock, endBinaryOp.label));

                curBlock = shortCircuitbranch;
                curBlock.addInst(new IRStore(curBlock, res, new IRBoolConst(false)));
                curBlock.addInst(new IRJump(curBlock, endBinaryOp.label));
                inFunc.blocks.add(shortCircuitbranch);
                inFunc.blocks.add(endBinaryOp);
                curBlock = endBinaryOp;
            }
            else if (it.op.equals("||")) {
                prev.addInst(new IRBranch(curBlock, getVal(it.lop, true), shortCircuitbranch, rightBranch));

                curBlock = rightBranch;
                inFunc.blocks.add(rightBranch);
                it.rop.accept(this);
                storeVal(res, it.rop);
                curBlock.addInst(new IRJump(curBlock, endBinaryOp.label));

                curBlock = shortCircuitbranch;
                curBlock.addInst(new IRStore(curBlock, res, new IRBoolConst(true)));
                curBlock.addInst(new IRJump(curBlock, endBinaryOp.label));
                inFunc.blocks.add(shortCircuitbranch);
                inFunc.blocks.add(endBinaryOp);
                curBlock = endBinaryOp;
            }
            it.addr = res;
        }//short-circuit value
    }

    @Override
    public void visit(TernaryExprNode it) {
        it.condition.accept(this);
        BasicBlock prev = curBlock;
        BasicBlock trueBranch = new BasicBlock("ternary.then", inFunc);
        BasicBlock falseBranch = new BasicBlock("ternary.else", inFunc);
        BasicBlock endTernary = new BasicBlock("ternary.end", inFunc);

        IRRegister res = new IRRegister("ternary", new IRPtrType(getIRType(it.type)));

        if (!it.type.equals(builtin.VoidType)) {
            prev.addInst(new IRAlloca(prev, getIRType(it.type), res));
        }
        prev.addInst(new IRBranch(prev, getVal(it.condition, true), trueBranch, falseBranch));

        curBlock = trueBranch;
        it.trueVal.accept(this);
        if (!it.trueVal.type.equals(builtin.VoidType)) {
            storeVal(res, it.trueVal);
        }
        curBlock.addInst(new IRJump(curBlock, endTernary.label));
        inFunc.blocks.add(trueBranch);

        curBlock = falseBranch;
        it.falseVal.accept(this);
        if (!it.falseVal.type.equals(builtin.VoidType)) {
            storeVal(res, it.falseVal);
        }
        curBlock.addInst(new IRJump(curBlock, endTernary.label));
        inFunc.blocks.add(falseBranch);

        it.addr = res;
        inFunc.blocks.add(endTernary);
        curBlock = endTernary;
    }

    @Override
    public void visit(AssignExprNode it) {
        it.assignTo.accept(this);
        it.assignVal.accept(this);
        storeVal(it.assignTo.addr, it.assignVal);
    }

    @Override
    public void visit(AtomExprNode it) {
        if (it.isIdentifier) {
            if (curScope.inClass != null) {
                var classType = dereference(inFunc.thisPtr.type);
                IRRegister val = new IRRegister("this.val", classType);
                curBlock.addInst(new IRLoad(curBlock, val.type, val, inFunc.thisPtr));
                IRRegister res = new IRRegister("this." + it.content, new IRPtrType(getIRType(it.type)));
                IRGetElementPtr getPtrInst = new IRGetElementPtr(curBlock, val, dereference(classType), res);
                getPtrInst.idx.add(new IRIntConst(0));
                getPtrInst.idx.add(new IRIntConst(((IRStructType) dereference(classType)).memberMap.get(it.content)));
                curBlock.addInst(getPtrInst);
                it.addr = res;
            }
            if (it.addr == null) {
                it.addr = curScope.getReg(it.content, true);
            }
        }
        else {
            if (it.type.equals(builtin.IntType)) {
                int val = Integer.parseInt(it.content);
                it.entity = new IRIntConst(val);
            }
            else if (it.type.equals(builtin.BoolType)) {
                boolean val = it.content.equals("true") ? true : false;
                it.entity = new IRBoolConst(val);
            }
            else if (it.type.equals(builtin.NullType)) {
                it.entity = new IRNullConst();
            }
            else if (it.type.equals(builtin.StringType)) {
                if (!program.IRstringMap.containsKey(it.content)) {
                    program.IRstringMap.put(it.content, new IRStringConst(it.content));
                }
                it.entity = program.IRstringMap.get(it.content);
            }
            else if (it.content.equals("this")) {

            }
        }
    }

    @Override
    public void visit(ExprListNode it) {
        for (var nxt: it.exprs) {
            nxt.accept(this);
        }
    }

    @Override
    public void visit(ExprStmtNode it) {
        if (it.exprList != null) {
            it.exprList.accept(this);
        }
    }

    @Override
    public void visit(IfStmtNode it) {
        it.condition.accept(this);
        BasicBlock prev = curBlock;
        BasicBlock trueBranch = new BasicBlock("if.then", inFunc);
        BasicBlock falseBranch = new BasicBlock("if.else", inFunc);
        BasicBlock endIf = new BasicBlock("if.end", inFunc);

        if (it.falseThenWork != null) {
            prev.addInst(new IRBranch(prev, getVal(it.condition, true), trueBranch, falseBranch));
        }
        else {
            prev.addInst(new IRBranch(prev, getVal(it.condition, true), trueBranch, endIf));
        }
        curScope = new Scope(curScope);
        curBlock = trueBranch;
        it.trueThenWork.accept(this);
        curBlock.addInst(new IRJump(curBlock, endIf.label));
        inFunc.blocks.add(trueBranch);
        curScope = curScope.parentScope;

        if (it.falseThenWork != null) {
            curScope = new Scope(curScope);
            curBlock = falseBranch;
            it.falseThenWork.accept(this);
            curBlock.addInst(new IRJump(curBlock, endIf.label));
            curScope = curScope.parentScope;
        }
        if (it.falseThenWork != null) {
            inFunc.blocks.add(falseBranch);
        }
        inFunc.blocks.add(endIf);
        curBlock = endIf;
    }

    @Override
    public void visit(ForStmtNode it) {
        curScope = new Scope(curScope);
        BasicBlock prev = curBlock;
        BasicBlock forCond = new BasicBlock("for.cond", inFunc);
        BasicBlock forInc = new BasicBlock("for.inc", inFunc);
        BasicBlock forBody = new BasicBlock("for.body", inFunc);
        BasicBlock forEnd = new BasicBlock("for.end", inFunc);
        breakNxt = forEnd;
        continueNxt = forInc;
        if (it.defInit != null) {
            it.defInit.accept(this);
        }
        if (it.exprInit != null) {
            it.exprInit.accept(this);
        }
        prev.addInst(new IRJump(curBlock, forCond.label));
        curBlock = forCond;
        if (it.condition != null) {
            it.condition.accept(this);
            curBlock.addInst(new IRBranch(curBlock, getVal(it.condition, true), forBody, forEnd));
        }
        else {
            curBlock.addInst(new IRJump(curBlock, forBody.label));
        }
        inFunc.blocks.add(forCond);
        curBlock = forInc;
        if (it.step != null) {
            it.step.accept(this);
        }
        curBlock.addInst(new IRJump(curBlock, forCond.label));
        inFunc.blocks.add(forInc);
        curBlock = forBody;
        if (it.work != null) {
            if (it.work instanceof BlockNode) {
                ((BlockNode) it.work).stmts.forEach(nxt->nxt.accept(this));
            }
            else {
                it.work.accept(this);
            }
        }
        curBlock.addInst(new IRJump(curBlock, forInc.label));
        inFunc.blocks.add(forBody);
        curBlock = forEnd;
        inFunc.blocks.add(forEnd);
        curScope = curScope.parentScope;
    }

    @Override
    public void visit(WhileStmtNode it) {
        curScope = new Scope(curScope);
        BasicBlock prev = curBlock;
        BasicBlock whileCond = new BasicBlock("while.cond", inFunc);
        BasicBlock whileBody = new BasicBlock("while.body", inFunc);
        BasicBlock whileEnd = new BasicBlock("while.end", inFunc);
        breakNxt = whileEnd;
        continueNxt = whileCond;
        curBlock.addInst(new IRJump(curBlock, whileCond.label));
        curBlock = whileCond;
        it.condition.accept(this);
        curBlock.addInst(new IRBranch(curBlock, getVal(it.condition, true), whileBody, whileEnd));
        inFunc.blocks.add(whileCond);
        curBlock = whileBody;
        it.work.accept(this);
        curBlock.addInst(new IRJump(curBlock, whileCond.label));
        inFunc.blocks.add(whileBody);
        curBlock = whileEnd;
        inFunc.blocks.add(whileEnd);
        curScope = curScope.parentScope;
    }

    @Override
    public void visit(FlowStmtNode it) {
        //should be inaccessible
    }

    @Override
    public void visit(BreakStmtNode it) {
        curBlock.addInst(new IRJump(curBlock, breakNxt.label));
    }

    @Override
    public void visit(ContinueStmtNode it) {
        curBlock.addInst(new IRJump(curBlock, continueNxt.label));
    }

    @Override
    public void visit(ReturnStmtNode it) {
        if (it.returnVal != null) {
            it.returnVal.accept(this);
            curBlock.addInst(new IRStore(curBlock, inFunc.retReg, getVal(it.returnVal, true)));
        }
        curBlock.addInst(new IRJump(curBlock, "return"));
        curBlock.returned = true;
    }

    @Override
    public void visit(DefineVarStmtNode it) {
        for (var nxt: it.assigns) {
            nxt.accept(this);
        }
    }

    @Override
    public void visit(VarAssignStmtNode it) {
        IRBaseType IRType = getIRType(it.type);
        IRRegister addr = new IRRegister(it.assignTo, IRType);
        curScope.entityMap.put(addr.name, addr);
        curBlock.addInst(new IRAlloca(curBlock, IRType, addr));
        if (it.assignVal != null) {
            it.assignVal.accept(this);
            storeVal(addr, it.assignVal);
        }
    }

    @Override
    public void visit(ParamsListNode it) {
        for (int i = 0; i < it.identifiers.size(); i++) {
            IRRegister reg = inFunc.params.get(i);
            IRRegister addr = new IRRegister(reg.name + ".addr", new IRPtrType(reg.type));
            curBlock.addInst(new IRAlloca(curBlock, new IRIntType(32), addr));
            curBlock.addInst(new IRStore(curBlock, addr, reg));
            curScope.entityMap.put(it.identifiers.get(i), addr);
        }
    }

    @Override
    public void visit(DefineClassNode it) {
        curScope = new Scope(curScope);
        curScope.inClass = it;
        IRStructType struct = program.IRclassMap.get(it.className);

        for (VarAssignStmtNode nxt: it.varMap.values()) {
            struct.putMember(nxt.assignTo, getIRType(nxt.type));
        }

        for (var nxt: it.functions) {
            nxt.funcName = it.className + "__" + nxt.funcName;
            nxt.accept(this);
        }

        if (it.constructor != null) {
            it.constructor.accept(this);
            struct.constructor = true;
        }
        curScope = curScope.parentScope;
    }

    @Override
    public void visit(DefineConstructFunctionNode it) {
        Function func = new Function(it.className + "__" + it.className, new IRVoidType());
        inFunc = func;
        program.functions.add(inFunc);
        curBlock = new BasicBlock("entry", inFunc, false);
        inFunc.blocks.add(curBlock);
        curScope = new Scope(curScope);
        IRStructType struct = program.IRclassMap.get(curScope.inClass.className);
        IRPtrType ptr = new IRPtrType(struct);
        IRRegister thisAddr = new IRRegister("this.addr", new IRPtrType(ptr));
        IRRegister thisReg = new IRRegister("this", ptr);
        inFunc.params.add(thisReg);
        inFunc.thisPtr = thisAddr;
        curBlock.addInst(new IRAlloca(curBlock, thisAddr.type, thisAddr));
        curBlock.addInst(new IRStore(curBlock, thisAddr, thisReg));
        for (var nxt: it.stmts) {
            nxt.accept(this);
        }
        if (curBlock != null) {
            if (!curBlock.returned) {
                curBlock.addInst(new IRJump(curBlock, "return"));
            }
        }
        curBlock = new BasicBlock("return", inFunc, false);
        curBlock.addInst(new IRRet(curBlock, null));
        inFunc.blocks.add(curBlock);
        curScope = curScope.parentScope;
        inFunc = null;
        curBlock = null;
    }

    @Override
    public void visit(DefineFunctionNode it) {
        Function func = gScope.IRfuncMap.get(it.funcName);
        inFunc = func;
        curBlock = new BasicBlock("entry", inFunc, false);
        inFunc.blocks.add(curBlock);
        curScope = new Scope(curScope);
        if (curScope.inClass != null) {
            IRStructType struct = program.IRclassMap.get(curScope.inClass.className);
            IRRegister thisReg = new IRRegister("this", struct);
            inFunc.params.add(thisReg);
        }
        if (it.paramsList != null) {
            it.paramsList.accept(this);
        }
        if (!it.type.equals(builtin.VoidType)) {
            inFunc.retReg = new IRRegister("retVal", new IRPtrType(getIRType(it.type)));
            curBlock.addInst(new IRAlloca(curBlock, getIRType(it.type), inFunc.retReg));
        }
        if (it.funcName.equals("main") && global_init) {
            curBlock.addInst(new IRCall(curBlock, null, new IRVoidType(), "__mx_global_var_init"));
        }
        for (var nxt: it.stmts) {
            nxt.accept(this);
        }
        if (curBlock != null) {
            if (!curBlock.returned) {
                curBlock.addInst(new IRJump(curBlock, "return"));
            }
        }
        if (it.type.equals(builtin.VoidType)) {
            curBlock = new BasicBlock("return", inFunc, false);
            curBlock.addInst(new IRRet(curBlock, null));
            inFunc.blocks.add(curBlock);
        }
        else {
            curBlock = new BasicBlock("return", inFunc, false);
            IRRegister res = new IRRegister("", getIRType(it.type));
            curBlock.addInst(new IRLoad(curBlock, getIRType(it.type), res, inFunc.retReg));
            curBlock.addInst(new IRRet(curBlock, res));
            inFunc.blocks.add(curBlock);
        }
        curScope = curScope.parentScope;
        inFunc = null;
        curBlock = null;
    }

    @Override
    public void visit(BlockNode it) {
        curScope = new Scope(curScope);
        for (var nxt: it.stmts) {
            nxt.accept(this);
        }
        curScope = curScope.parentScope;
    }

    @Override
    public void visit(TypeNameNode it) {
        //should be inaccessible
    }

    @Override
    public void visit(RootNode it) {
        for (var nxt: it.Defs) {
            if (nxt instanceof DefineClassNode) {
                declareClass((DefineClassNode) nxt);
            }
            if (nxt instanceof DefineVarStmtNode) {
                declareGlobalVar((DefineVarStmtNode) nxt);
            }
            if (nxt instanceof DefineFunctionNode) {
                declareFunc((DefineFunctionNode) nxt);
            }
        }
        for (var nxt: it.Defs) {
            if (nxt instanceof DefineClassNode) {
                nxt.accept(this);
            }
        }
        if (!program.globalVar.isEmpty()) {
            Function init = new Function("__mx_global_var_init", new IRVoidType());
            inFunc = init;
            curBlock = new BasicBlock("entry", inFunc, false);
            init.blocks.add(curBlock);
            for (var nxt : it.Defs) {
                if (nxt instanceof DefineVarStmtNode) {
                    initGlobalVar((DefineVarStmtNode) nxt);
                }
            }
            if (!inFunc.blocks.get(0).insts.isEmpty()) {
                curBlock.addInst(new IRJump(curBlock, "return"));
                curBlock = new BasicBlock("return", inFunc, false);
                curBlock.addInst(new IRRet(curBlock, null));
                inFunc.blocks.add(curBlock);
                program.functions.add(inFunc);
                global_init = true;
            }
            inFunc = null;
            curBlock = null;
        }
        for (var nxt: it.Defs) {
            if (nxt instanceof DefineFunctionNode) {
                nxt.accept(this);
            }
        }
        System.out.println(program.toString());
    }
}
