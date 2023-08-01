// Generated from ./Parser/MxParser.g4 by ANTLR 4.7.2
package Parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxParser}.
 */
public interface MxParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxParser#file_input}.
	 * @param ctx the parse tree
	 */
	void enterFile_input(MxParser.File_inputContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#file_input}.
	 * @param ctx the parse tree
	 */
	void exitFile_input(MxParser.File_inputContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#basicType}.
	 * @param ctx the parse tree
	 */
	void enterBasicType(MxParser.BasicTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#basicType}.
	 * @param ctx the parse tree
	 */
	void exitBasicType(MxParser.BasicTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#suite}.
	 * @param ctx the parse tree
	 */
	void enterSuite(MxParser.SuiteContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#suite}.
	 * @param ctx the parse tree
	 */
	void exitSuite(MxParser.SuiteContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MxParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MxParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#defineClass}.
	 * @param ctx the parse tree
	 */
	void enterDefineClass(MxParser.DefineClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#defineClass}.
	 * @param ctx the parse tree
	 */
	void exitDefineClass(MxParser.DefineClassContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#defineFunction}.
	 * @param ctx the parse tree
	 */
	void enterDefineFunction(MxParser.DefineFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#defineFunction}.
	 * @param ctx the parse tree
	 */
	void exitDefineFunction(MxParser.DefineFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#defineConstructFunction}.
	 * @param ctx the parse tree
	 */
	void enterDefineConstructFunction(MxParser.DefineConstructFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#defineConstructFunction}.
	 * @param ctx the parse tree
	 */
	void exitDefineConstructFunction(MxParser.DefineConstructFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#paramsList}.
	 * @param ctx the parse tree
	 */
	void enterParamsList(MxParser.ParamsListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#paramsList}.
	 * @param ctx the parse tree
	 */
	void exitParamsList(MxParser.ParamsListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#defineVarStmt}.
	 * @param ctx the parse tree
	 */
	void enterDefineVarStmt(MxParser.DefineVarStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#defineVarStmt}.
	 * @param ctx the parse tree
	 */
	void exitDefineVarStmt(MxParser.DefineVarStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varAssign}.
	 * @param ctx the parse tree
	 */
	void enterVarAssign(MxParser.VarAssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varAssign}.
	 * @param ctx the parse tree
	 */
	void exitVarAssign(MxParser.VarAssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#exprStmt}.
	 * @param ctx the parse tree
	 */
	void enterExprStmt(MxParser.ExprStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#exprStmt}.
	 * @param ctx the parse tree
	 */
	void exitExprStmt(MxParser.ExprStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(MxParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(MxParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(MxParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(MxParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(MxParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(MxParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(MxParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(MxParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#continueStmt}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(MxParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#continueStmt}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(MxParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#breakStmt}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(MxParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#breakStmt}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(MxParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(MxParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(MxParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#flowStmt}.
	 * @param ctx the parse tree
	 */
	void enterFlowStmt(MxParser.FlowStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#flowStmt}.
	 * @param ctx the parse tree
	 */
	void exitFlowStmt(MxParser.FlowStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#exprList}.
	 * @param ctx the parse tree
	 */
	void enterExprList(MxParser.ExprListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#exprList}.
	 * @param ctx the parse tree
	 */
	void exitExprList(MxParser.ExprListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(MxParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(MxParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code wrap_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterWrap_expr(MxParser.Wrap_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code wrap_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitWrap_expr(MxParser.Wrap_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code member_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMember_expr(MxParser.Member_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code member_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMember_expr(MxParser.Member_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binary_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinary_expr(MxParser.Binary_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binary_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinary_expr(MxParser.Binary_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code func_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunc_expr(MxParser.Func_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code func_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunc_expr(MxParser.Func_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code new_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNew_expr(MxParser.New_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code new_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNew_expr(MxParser.New_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssign_expr(MxParser.Assign_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assign_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssign_expr(MxParser.Assign_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code right_self_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRight_self_expr(MxParser.Right_self_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code right_self_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRight_self_expr(MxParser.Right_self_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atom_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAtom_expr(MxParser.Atom_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atom_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAtom_expr(MxParser.Atom_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code left_self_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLeft_self_expr(MxParser.Left_self_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code left_self_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLeft_self_expr(MxParser.Left_self_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ternary_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterTernary_expr(MxParser.Ternary_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ternary_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitTernary_expr(MxParser.Ternary_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code single_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSingle_expr(MxParser.Single_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code single_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSingle_expr(MxParser.Single_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code array_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArray_expr(MxParser.Array_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code array_expr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArray_expr(MxParser.Array_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#typeprefix}.
	 * @param ctx the parse tree
	 */
	void enterTypeprefix(MxParser.TypeprefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#typeprefix}.
	 * @param ctx the parse tree
	 */
	void exitTypeprefix(MxParser.TypeprefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#typename}.
	 * @param ctx the parse tree
	 */
	void enterTypename(MxParser.TypenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#typename}.
	 * @param ctx the parse tree
	 */
	void exitTypename(MxParser.TypenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(MxParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(MxParser.AtomContext ctx);
}