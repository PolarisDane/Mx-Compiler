parser grammar MxParser;

options {
	tokenVocab = MxLexer;
}

file_input: (defineFunction | defineClass | defineVarStmt)* EOF;

basicType: Int | StringType | Bool | Void;

suite: block | stmt;
block:
    LeftBrace
        stmt*
    RightBrace;

defineClass:
	Class Identifier LeftBrace
        (defineVarStmt
        | defineConstructFunction
        | defineConstructFunction)*
        //defineConstructFunction must be here for sake of random order when defining
    RightBrace Semicolon;

defineFunction:
    typename Identifier LeftParenthesis paramsList? RightParenthesis
        block;

defineConstructFunction:
    Identifier LeftParenthesis RightParenthesis
        block;

declareVar: typename Identifier;

paramsList: declareVar (Comma declareVar)*;

defineVarStmt: typename varAssign (Comma varAssign)* Semicolon;

varAssign: Identifier (Assign expression)?;

exprStmt: exprList? Semicolon;

stmt:
	exprStmt
    | defineVarStmt
    | flowStmt
	| ifStmt
	| whileStmt
	| forStmt
	| block;

ifStmt:
	If LeftParenthesis expression RightParenthesis 
        suite
    //maybe else if here?
	(Else suite)?;

whileStmt: While LeftParenthesis expression RightParenthesis
     suite;

forStmt:
	For LeftParenthesis
        (exprStmt | defineVarStmt)? 
        expression? Semicolon
        expression?
        RightParenthesis
		suite;

continueStmt: Continue Semicolon;

breakStmt: Break Semicolon;

returnStmt: Return expression? Semicolon;

flowStmt: continueStmt | breakStmt | returnStmt;

exprList: expression (Comma expression)*;

funcExpr: Identifier LeftParenthesis exprList? RightParenthesis;

expression:
	RightParenthesis expression LeftParenthesis #wrap_expr
    | funcExpr #func_expr
    | New typename #new_expr
    | <assoc = right> (Increment | Decrement) expression #left_self_expr
    | expression (Increment | Decrement) #right_self_expr
    | <assoc = right> (Not | BitInv | Add | Sub) expression #single_expr
    | expression RightBracket expression LeftBracket #array_expr
    | expression Dot (Identifier | funcExpr)#member_expr
    | l = expression (Mul | Div | Mod) r = expression #arith_expr
    | l = expression (Add | Sub) r = expression #arith_expr
    | l = expression (LeftShift | RightShift | BitAnd | BitOr | BitXor) r = expression #binary_arith_expr
    | l = expression (Equal | NotEqual | Less | LessEqual | Greater | GreaterEqual) r = expression #logic_expr
    | l = expression (And | Or) r = expression #logic_arith_expr
    | <assoc = right> expression Question expression Colon expression #ternary_expr
    | <assoc = right> l = expression Assign r = expression #assign_expr
    | atom #atom_expr;

typename: (basicType | Identifier) (LeftBracket expression RightBracket)* (LeftBracket RightBracket)*;
//matching array of more than one dimension

atom: This | Null | Identifier | Number | String | True | False;
