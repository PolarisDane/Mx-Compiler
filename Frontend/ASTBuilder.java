package Frontend;

import AST.*;
import Parser.*;
import Utils.*;

public class ASTBuilder extends MxParserBaseVisitor<ASTNode> {
    private Scope gScope;
    public ASTBuilder(Scope gScope) {
        this.gScope = gScope;
    }

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
        defVar.type = ((TypeNameNode) visitTypename(ctx.typename())).type;
        for (var it: ctx.varAssign()) {
            defVar.assigns.add((VarAssignStmtNode) visit(it));
        }
        return defVar;
    }

    @Override
    public ASTNode visitVarAssign(MxParser.VarAssignContext ctx) {
        VarAssignStmtNode varAssign = new VarAssignStmtNode(new position(ctx));
        varAssign.assignTo = ctx.Identifier().getText();
        if (ctx.expression() != null) {
            varAssign.assignVal = (ExprNode) visit(ctx.expression());
        }
        return varAssign;
    }

    @Override
    public ASTNode visitDefineFunction(MxParser.DefineFunctionContext ctx) {
        DefineFunctionNode defFunc = new DefineFunctionNode(new position(ctx), ctx.Identifier().getText());
        defFunc.type = ((TypeNameNode) visit(ctx.typename())).type;
        if (ctx.paramsList() != null) {
            defFunc.paramsList = (ParamsListNode) visit(ctx.paramsList());
        }
        return defFunc;
    }

    @Override
    public ASTNode visitParamsList(MxParser.ParamsListContext ctx) {
        ParamsListNode paramsList = new ParamsListNode(new position(ctx));
        int siz = ctx.typename().size();
        for (int i = 0; i < siz; i++) {
            paramsList.types.add(((TypeNameNode) visit(ctx.typename(i))).type);
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
        if (ctx.stmt() != null) {
            for (var it : ctx.stmt()) {
                block.stmts.add((StmtNode) visit(it));
            }
        }
        return block;
    }

    @Override
    public ASTNode visitDefineClass(MxParser.DefineClassContext ctx) {
        DefineClassNode defClass = new DefineClassNode(new position(ctx), ctx.Identifier().getText());
        if (ctx.defineConstructFunction() != null) {
            defClass.constructor = (DefineConstructFunctionNode) visit(ctx.defineConstructFunction(0));
        }
        //if there exists more than one constructor then it should throw exception
        if (ctx.defineVarStmt() != null) {
            for (var it : ctx.defineVarStmt()) {
                defClass.vars.add((DefineVarStmtNode) visit(it));
            }
        }
        if (ctx.defineFunction() != null) {
            for (var it : ctx.defineFunction()) {
                defClass.functions.add((DefineFunctionNode) visit(it));
            }
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
        for (var it: ctx.expression()) {
            exprList.exprs.add((ExprNode) visit(it));
        }
        return exprList;
    }

    @Override
    public ASTNode visitWrap_expr(MxParser.Wrap_exprContext ctx) {
        return visit(ctx.expression());
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
        if (ctx.suite().size() > 1) {
            ifStmt.falseThenWork = (SuiteNode) visit(ctx.suite(1));
        }
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
        if (ctx.condition != null) {
            forStmt.condition = (ExprNode) visit(ctx.condition);
        }
        if (ctx.step != null) {
            forStmt.step = (ExprNode) visit(ctx.step);
        }
        if (ctx.suite() != null) {
            forStmt.work = (SuiteNode) visit(ctx.suite());
        }
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
        ReturnStmtNode returnStmt = new ReturnStmtNode(new position(ctx));
        if (ctx.expression() != null) {
            returnStmt.returnVal = (ExprNode) visit(ctx.expression());
        }
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
        NewExprNode newExpr = new NewExprNode(new position(ctx));
        newExpr.type.content = ctx.typeprefix().getText();
        newExpr.type.dim = ctx.LeftBracket().size();
        return newExpr;
    }

    @Override
    public ASTNode visitAssign_expr(MxParser.Assign_exprContext ctx) {
        AssignExprNode assignExpr = new AssignExprNode(new position(ctx), (ExprNode) visit(ctx.l), (ExprNode) visit(ctx.r));
        return assignExpr;
    }

    @Override
    public ASTNode visitRight_self_expr(MxParser.Right_self_exprContext ctx) {
        RightSelfExprNode rightSelfExpr = new RightSelfExprNode(new position(ctx), (ExprNode) visit(ctx.expression()), ctx.op.getText());
        return rightSelfExpr;
    }

    @Override
    public ASTNode visitLeft_self_expr(MxParser.Left_self_exprContext ctx) {
        LeftSelfExprNode leftSelfExpr = new LeftSelfExprNode(new position(ctx), (ExprNode) visit(ctx.expression()), ctx.op.getText());
        return leftSelfExpr;
    }

    @Override
    public ASTNode visitTernary_expr(MxParser.Ternary_exprContext ctx) {
        TernaryExprNode ternaryExpr = new TernaryExprNode(new position(ctx));
        ternaryExpr.condition = (ExprNode) visit(ctx.expression(0));
        ternaryExpr.trueVal = (ExprNode) visit(ctx.expression(1));
        ternaryExpr.falseVal = (ExprNode) visit(ctx.expression(2));
        return ternaryExpr;
    }

    @Override
    public ASTNode visitSingle_expr(MxParser.Single_exprContext ctx) {
        SingleExprNode singleExpr = new SingleExprNode(new position(ctx), (ExprNode) visit(ctx.expression()), ctx.op.getText());
        return singleExpr;
    }

    @Override
    public ASTNode visitArray_expr(MxParser.Array_exprContext ctx) {
        ArrayExprNode arrayExpr = new ArrayExprNode(new position(ctx), (ExprNode) visit(ctx.expression(0)), (ExprNode) visit(ctx.expression(1)));
        return arrayExpr;
    }

    @Override
    public ASTNode visitAtom_expr(MxParser.Atom_exprContext ctx) {
        AtomExprNode atomExpr = new AtomExprNode(new position(ctx), ctx.getText(), ctx.Identifier() != null);
        return atomExpr;
    }

    @Override
    public ASTNode visitSuite(MxParser.SuiteContext ctx) {
        SuiteNode suiteNode = new SuiteNode(new position(ctx));
        if (ctx.block() != null) {
            suiteNode.stmts = ((BlockNode) visit(ctx.block())).stmts;
        }
        else if (ctx.stmt() != null) {
            suiteNode.stmts.add((StmtNode) visit(ctx.stmt()));
        }
        return suiteNode;
    }

    @Override
    public ASTNode visitBasicType(MxParser.BasicTypeContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitTypeprefix(MxParser.TypeprefixContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitTypename(MxParser.TypenameContext ctx) {
        TypeNameNode typeNameNode = new TypeNameNode(new position(ctx));
        typeNameNode.type.content = ctx.typeprefix().getText();
        typeNameNode.type.dim = ctx.LeftBracket().size();
        return typeNameNode;
    }
}