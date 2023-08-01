package Frontend;

import AST.*;
import Parser.*;
import Utils.*;

public class ASTBuilder extends MxParserBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitFile_input(MxParser.File_inputContext ctx) {
        RootNode root = new RootNode(new position(ctx));
        for (var it: ctx.children) {
            if (it instanceof DefineVarStmtNode) {
                root.defVars.add((DefineVarStmtNode) visit(it));
            }
            else if (it instanceof DefineClassNode) {
                root.defClasses.add((DefineClassNode) visit(it));
            }
            else if (it instanceof DefineFunctionNode) {
                root.defFunctions.add((DefineFunctionNode) visit(it));
            }
        }
        return root;
    }

    @Override
    public ASTNode visitDefineVarStmt(MxParser.DefineVarStmtContext ctx) {
        DefineVarStmtNode defVar = new DefineVarStmtNode(new position(ctx));
        defVar.type.content = ctx.typename().getText();
        for (var it: ctx.children) {
            defVar.assigns.add((VarAssignStmtNode) visit(it));
        }
        return defVar;
    }

    @Override
    public ASTNode visitVarAssign(MxParser.VarAssignContext ctx) {
        VarAssignStmtNode varAssign = new VarAssignStmtNode(new position(ctx));
        varAssign.assignTo = ctx.Identifier().getText();
        varAssign.assignVal = (ExprNode) visit(ctx.expression());
        return varAssign;
    }

    @Override
    public ASTNode visitDefineFunction(MxParser.DefineFunctionContext ctx) {
        DefineFunctionNode defFunc = new DefineFunctionNode(new position(ctx), ctx.Identifier().getText(), (ParamsListNode) visit(ctx.paramsList()));
        return defFunc;
    }

    @Override
    public ASTNode visitParamsList(MxParser.ParamsListContext ctx) {
        ParamsListNode paramsList = new ParamsListNode(new position(ctx));
        int siz = ctx.typename().size();
        for (int i = 0; i < siz; i++) {
            paramsList.types.add(new Type(ctx.typename(i).getText()));
            paramsList.identifiers.add(ctx.Identifier(i).getText());
        }
        return paramsList;
    }

    @Override
    public ASTNode visitDefineConstructFunction(MxParser.DefineConstructFunctionContext ctx) {
        DefineConstructFunctionNode defConstructFunc = new DefineConstructFunctionNode(new position(ctx), ctx.Identifier().getText());
        defConstructFunc.stmts = ((BlockNode) visit(ctx.block())).stmts;
        return defConstructFunc;
    }

    @Override
    public ASTNode visitBlock(MxParser.BlockContext ctx) {
        BlockNode block = new BlockNode(new position(ctx));
        for (var it: ctx.stmt()) {
            block.stmts.add((StmtNode) visit(it));
        }
        return block;
    }

    @Override
    public ASTNode visitDefineClass(MxParser.DefineClassContext ctx) {
        DefineClassNode defClass = new DefineClassNode(new position(ctx), ctx.Identifier().getText());
        defClass.constructor = (DefineConstructFunctionNode) visit(ctx.defineConstructFunction(0));
        //if there exists more than one constructor then it should throw exception
        for (var it: ctx.defineVarStmt()) {
            defClass.vars.add((DefineVarStmtNode) visit(it));
        }
        for (var it: ctx.defineFunction()) {
            defClass.functions.add((DefineFunctionNode) visit(it));
        }
        return defClass;
    }

    @Override
    public ASTNode visitExprStmt(MxParser.ExprStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitExprList(MxParser.ExprListContext ctx) {
        ExprListNode exprList = new ExprListNode(new position(ctx));
        for (var it: ctx.children) {
            exprList.exprs.add((ExprNode) visit(it));
        }
        return exprList;
    }

    @Override
    public ASTNode visitWrap_expr(MxParser.Wrap_exprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitFunc_expr(MxParser.Func_exprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitFuncCall(MxParser.FuncCallContext ctx) {
        FuncCallNode funcCall = new FuncCallNode(new position(ctx), ctx.Identifier().getText(), (ExprListNode) visit(ctx.exprList()));
        return funcCall;
    }

    @Override
    public ASTNode visitStmt(MxParser.StmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitIfStmt(MxParser.IfStmtContext ctx) {
        IfStmtNode ifStmt = new IfStmtNode(new position(ctx));
        ifStmt.condition = (ExprNode) visit(ctx.expression());
        ifStmt.trueThenWork = (SuiteNode) visit(ctx.suite(0));
        ifStmt.falseThenWork = (SuiteNode) visit(ctx.suite(1));
        return ifStmt;
    }

    @Override
    public ASTNode visitWhileStmt(MxParser.WhileStmtContext ctx) {
        WhileStmtNode whileStmt = new WhileStmtNode(new position(ctx));
        whileStmt.condition = (ExprNode) visit(ctx.expression());
        whileStmt.work = (SuiteNode) visit(ctx.suite());
        return whileStmt;
    }

    @Override
    public ASTNode visitForStmt(MxParser.ForStmtContext ctx) {
        ForStmtNode forStmt = new ForStmtNode(new position(ctx));
        if (ctx.defineVarStmt() != null) {
            forStmt.defInit = (DefineVarStmtNode) visit(ctx.defineVarStmt());
        }
        if (ctx.exprStmt() != null) {
            forStmt.exprInit = (ExprStmtNode) visit(ctx.exprStmt());
        }
        forStmt.condition = (ExprNode) visit(ctx.expression(0));
        forStmt.step = (ExprNode) visit(ctx.expression(1));
        forStmt.work = (SuiteNode) visit(ctx.suite());
        return forStmt;
    }

    @Override
    public ASTNode visitFlowStmt(MxParser.FlowStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitBreakStmt(MxParser.BreakStmtContext ctx) {
        BreakStmtNode breakStmt = new BreakStmtNode(new position(ctx));
        return breakStmt;
    }

    @Override
    public ASTNode visitContinueStmt(MxParser.ContinueStmtContext ctx) {
        ContinueStmtNode continueStmt = new ContinueStmtNode(new position(ctx));
        return continueStmt;
    }

    @Override
    public ASTNode visitReturnStmt(MxParser.ReturnStmtContext ctx) {
        ReturnStmtNode returnStmt = new ReturnStmtNode(new position(ctx), (ExprNode) visit(ctx.expression()));
        return returnStmt;
    }

    @Override
    public ASTNode visitMember_expr(MxParser.Member_exprContext ctx) {
        MemberExprNode memberExpr = new MemberExprNode(new position(ctx));
        memberExpr.obj = (ExprNode) visit(ctx.expression());
        if (ctx.Identifier() != null) {
            memberExpr.member = ctx.Identifier().getText();
        }
        else if (ctx.funcCall() != null) {
            memberExpr.memberFunc = (FuncCallNode) visit(ctx.funcCall());
        }
        return memberExpr;
    }

    @Override
    public ASTNode visitBinary_expr(MxParser.Binary_exprContext ctx) {
        BinaryExprNode binaryExpr = new BinaryExprNode(new position(ctx), ctx.op.getText(), (ExprNode) visit(ctx.l), (ExprNode) visit(ctx.r));
        return binaryExpr;
    }

    @Override
    public ASTNode visitNew_expr(MxParser.New_exprContext ctx) {
        NewExprNode newExpr = new NewExprNode(new position(ctx), ctx.typeprefix().getText(), )
        return newExpr;
    }
}