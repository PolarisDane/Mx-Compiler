package Backend;

import AST.*;
import MIR.*;
import MIR.Entity.*;
import MIR.Inst.*;
import MIR.Type.*;
import Utils.*;

public class IRBuilder implements ASTVisitor {
    public Builtin builtin = new Builtin();
    public Scope curScope;
    public GlobalScope gScope;
    public BasicBlock curBlock = null;

    public Function inFunc = null;

    public IRBuilder(GlobalScope gScope) {
        this.gScope = gScope;
        this.curScope = gScope;
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
        IRRegister loadVal = new IRRegister("", expr.addr.type);
        curBlock.addInst(new IRLoad(curBlock, expr.addr.type, loadVal, expr.addr));
        expr.entity = loadVal;//for later use
        if (is_i1 && loadVal.type.size == 8) {
            curBlock.addInst(new IRTrunc(curBlock, res, loadVal.type, loadVal, new IRIntType(1)));
            return res;
        }
        return expr.entity;
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
                //for other class
        }
        if (type.dim > 0) {
            IRType = new IRPtrType(IRType, type.dim);
        }
        return IRType;
    }

    @Override
    public void visit(FuncCallNode it) {
        DefineFunctionNode funcNode;
        if (it.inClass == null && curScope.inClass != null) {
            it.inClass = curScope.inClass;
        }
        if (it.inClass == null) {
            funcNode = gScope.getFunc(it.func, it.pos);
        }
        else {
            funcNode = it.inClass.funcMap.get(it.func);
            if (funcNode == null) {
                funcNode = gScope.getFunc(it.func, it.pos);
            }
        }
        IRCall callInst = new IRCall(curBlock, new IRRegister("func." + funcNode.funcName, getIRType(funcNode.type)), getIRType(funcNode.type), funcNode.funcName);
        if (it.args != null) {
            it.args.accept(this);
        }
        for (int i = 0; i < it.args.exprs.size(); i++) {
            callInst.args.add(getVal(it.args.exprs.get(i), false));
        }
        curBlock.addInst(callInst);
        it.entity = callInst.res;
    }

    @Override
    public void visit(NewExprNode it) {

    }

    @Override
    public void visit(WrapExprNode it) {
        //should be inaccessible
    }

    @Override
    public void visit(ArrayExprNode it) {

    }

    @Override
    public void visit(MemberExprNode it) {

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
                it.addr = res;
            }
            else {
                IRRegister res = new IRRegister("binary_op", new IRIntType(1));
                curBlock.addInst(new IRIcmp(curBlock, res, op, new IRIntType(1), getVal(it.lop, false), getVal(it.rop, false)));
                it.addr = res;
            }
        }//non short-circuit value
        else {
            IRRegister res = new IRRegister("binary_op", new IRIntType(32));
            Entity noZext;
            IRRegister zext = new IRRegister("zero_extended", new IRIntType(8));
            curBlock.addInst(new IRAlloca(curBlock, new IRIntType(8), res));
            BasicBlock rightBranch = new BasicBlock("binary_op.rhs", inFunc);
            BasicBlock shortCircuitbranch = new BasicBlock("binary_op.short", inFunc);
            BasicBlock endBinaryOp = new BasicBlock("binary_op.end", inFunc);
            BasicBlock prev = curBlock;
            if (it.op.equals("&&")) {
                prev.addInst(new IRBranch(curBlock, getVal(it.lop, true), rightBranch, shortCircuitbranch));

                curBlock = rightBranch;
                it.rop.accept(this);
                noZext = getVal(it.rop, true);
                curBlock.addInst(new IRZext(curBlock, zext, new IRIntType(1), noZext, new IRIntType(8)));
                curBlock.addInst(new IRStore(curBlock, res, zext));
                curBlock.addInst(new IRJump(curBlock, "binary_op.end"));

                curBlock = shortCircuitbranch;
                curBlock.addInst(new IRStore(curBlock, res, new IRBoolConst(false)));
                curBlock.addInst(new IRJump(curBlock, "binary_op.end"));
                curBlock = endBinaryOp;

                inFunc.blocks.add(prev);
                inFunc.blocks.add(rightBranch);
                inFunc.blocks.add(shortCircuitbranch);
            }
            else if (it.op.equals("||")) {
                prev.addInst(new IRBranch(curBlock, getVal(it.lop, true), shortCircuitbranch, rightBranch));

                curBlock = rightBranch;
                it.rop.accept(this);
                noZext = getVal(it.rop, true);
                curBlock.addInst(new IRZext(curBlock, zext, new IRIntType(1), noZext, new IRIntType(8)));
                curBlock.addInst(new IRStore(curBlock, res, zext));

                curBlock = shortCircuitbranch;
                curBlock.addInst(new IRStore(curBlock, res, new IRBoolConst(true)));
                curBlock.addInst(new IRJump(curBlock, "binary_op.end"));
                curBlock = endBinaryOp;

                inFunc.blocks.add(prev);
                inFunc.blocks.add(rightBranch);
                inFunc.blocks.add(shortCircuitbranch);
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

        IRRegister res = new IRRegister("ternary", getIRType(it.type));

        prev.addInst(new IRAlloca(prev, getIRType(it.type), res));
        prev.addInst(new IRBranch(prev, getVal(it.condition, true), trueBranch, falseBranch));

        curBlock = trueBranch;
        it.trueVal.accept(this);
        trueBranch.addInst(new IRStore(curBlock, res, getVal(it.trueVal, false)));
        trueBranch.addInst(new IRJump(curBlock, endTernary.label));

        curBlock = falseBranch;
        it.falseVal.accept(this);
        falseBranch.addInst(new IRStore(curBlock, res, getVal(it.falseVal, false)));
        falseBranch.addInst(new IRJump(curBlock, endTernary.label));

        it.addr = res;
        inFunc.blocks.add(prev);
        inFunc.blocks.add(trueBranch);
        inFunc.blocks.add(falseBranch);
        curBlock = endTernary;
    }

    @Override
    public void visit(AssignExprNode it) {
        it.assignTo.accept(this);
        it.assignVal.accept(this);
        IRRegister res = curScope.entityMap.get(it.assignTo.content);
        curBlock.addInst(new IRStore(curBlock, res, getVal(it.assignVal, false)));
    }

    @Override
    public void visit(AtomExprNode it) {
        if (it.isIdentifier) {
            it.addr = curScope.entityMap.get(it.content);
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
                it.entity = new IRStringConst(it.content);
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
        trueBranch.addInst(new IRJump(curBlock, endIf.label));
        curScope = curScope.parentScope;

        if (it.falseThenWork != null) {
            curScope = new Scope(curScope);
            curBlock = falseBranch;
            it.falseThenWork.accept(this);
            falseBranch.addInst(new IRJump(curBlock, endIf.label));
            curScope = curScope.parentScope;
        }
        inFunc.blocks.add(prev);
        inFunc.blocks.add(trueBranch);
        inFunc.blocks.add(falseBranch);
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
        if (it.defInit != null) {
            it.defInit.accept(this);
        }
        if (it.exprInit != null) {
            it.exprInit.accept(this);
        }
        curBlock.addInst(new IRJump(curBlock, "for.cond"));
        curBlock = forCond;
        if (it.condition != null) {
            it.condition.accept(this);
            curBlock.addInst(new IRBranch(curBlock, getVal(it.condition, true), forBody, forEnd));
        }
        else {
            curBlock.addInst(new IRJump(curBlock, "for.Body"));
        }
        curBlock = forInc;
        if (it.step != null) {
            it.step.accept(this);
        }
        curBlock.addInst(new IRJump(curBlock, "for.cond"));
        curBlock = forBody;
        if (it.work != null) {
            if (it.work instanceof BlockNode) {
                ((BlockNode) it.work).stmts.forEach(nxt->nxt.accept(this));
            }
            else {
                it.work.accept(this);
            }
        }
        curBlock.addInst(new IRJump(curBlock, "for.inc"));
        curBlock = forEnd;
        inFunc.blocks.add(prev);
        inFunc.blocks.add(forCond);
        inFunc.blocks.add(forInc);
        inFunc.blocks.add(forBody);
        curScope = curScope.parentScope;
    }

    @Override
    public void visit(WhileStmtNode it) {
        curScope = new Scope(curScope);
        BasicBlock prev = curBlock;
        BasicBlock whileCond = new BasicBlock("while.cond", inFunc);
        BasicBlock whileBody = new BasicBlock("while.body", inFunc);
        BasicBlock whileEnd = new BasicBlock("while.end", inFunc);
        curBlock = whileCond;
        it.condition.accept(this);
//        curBlock.addInst();
        curBlock = whileBody;
        it.work.accept(this);
        curBlock.addInst(new IRJump(curBlock, "while.cond"));
        inFunc.blocks.add(prev);
        inFunc.blocks.add(whileCond);
        inFunc.blocks.add(whileBody);
        curScope = curScope.parentScope;
    }

    @Override
    public void visit(FlowStmtNode it) {
        //should be inaccessible
    }

    @Override
    public void visit(BreakStmtNode it) {

    }

    @Override
    public void visit(ContinueStmtNode it) {

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
            curBlock.addInst(new IRStore(curBlock, addr, getVal(it.assignVal, false)));
        }
    }

    @Override
    public void visit(ParamsListNode it) {
        for (int i = 0; i < it.identifiers.size(); i++) {
            IRRegister reg = new IRRegister(it.identifiers.get(i), getIRType(it.types.get(i)));//register allocated for parameters
            inFunc.params.add(reg);
            IRRegister addr = new IRRegister(reg.name + ".addr", new IRPtrType(reg.type));
            curBlock.addInst(new IRAlloca(curBlock, new IRIntType(32), addr));
            curBlock.addInst(new IRStore(curBlock, addr, reg));
            curScope.entityMap.put(it.identifiers.get(i), addr);
        }
    }

    @Override
    public void visit(DefineClassNode it) {

    }

    @Override
    public void visit(DefineConstructFunctionNode it) {

    }

    @Override
    public void visit(DefineFunctionNode it) {
        Function func = new Function(it.funcName, getIRType(it.type));
        inFunc = func;
        curBlock = new BasicBlock("entry", inFunc);
        curScope = new Scope(curScope);
        if (it.paramsList != null) {
            it.paramsList.accept(this);
        }
        if (!it.type.equals(builtin.VoidType)) {
            inFunc.retReg = new IRRegister("retVal", new IRPtrType(getIRType(it.type)));
        }
        for (var nxt: it.stmts) {
            nxt.accept(this);
        }
        if (curBlock != null) {
            if (!curBlock.returned) {
                curBlock.addInst(new IRJump(curBlock, "return"));
            }
            inFunc.blocks.add(curBlock);
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
        System.out.println(func.toString());
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
            nxt.accept(this);
        }
        //System.out.println(curBlock.toString());
    }
}
