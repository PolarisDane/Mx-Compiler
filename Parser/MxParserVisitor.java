package Parser;

// Generated from MxParser.g4 by ANTLR 4.13.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MxParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MxParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MxParser#file_input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile_input(MxParser.File_inputContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#basicType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasicType(MxParser.BasicTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(MxParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#defineClass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefineClass(MxParser.DefineClassContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#defineFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefineFunction(MxParser.DefineFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#defineConstructFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefineConstructFunction(MxParser.DefineConstructFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#paramsList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamsList(MxParser.ParamsListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#defineVarStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefineVarStmt(MxParser.DefineVarStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#varAssign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarAssign(MxParser.VarAssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#exprStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStmt(MxParser.ExprStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(MxParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(MxParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#whileStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(MxParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#forStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(MxParser.ForStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#continueStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStmt(MxParser.ContinueStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#breakStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStmt(MxParser.BreakStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#returnStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(MxParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#flowStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlowStmt(MxParser.FlowStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#exprList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(MxParser.ExprListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#funcCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCall(MxParser.FuncCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code wrap_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWrap_expr(MxParser.Wrap_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code member_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMember_expr(MxParser.Member_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binary_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_expr(MxParser.Binary_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code func_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_expr(MxParser.Func_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code new_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNew_expr(MxParser.New_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_expr(MxParser.Assign_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code right_self_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRight_self_expr(MxParser.Right_self_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atom_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom_expr(MxParser.Atom_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code left_self_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeft_self_expr(MxParser.Left_self_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ternary_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTernary_expr(MxParser.Ternary_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code single_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_expr(MxParser.Single_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code array_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_expr(MxParser.Array_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#typeprefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeprefix(MxParser.TypeprefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#typename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypename(MxParser.TypenameContext ctx);
}