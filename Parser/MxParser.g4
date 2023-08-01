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
        | defineFunction
        | defineConstructFunction)*
        //defineConstructFunction must be here for sake of random order when defining
    RightBrace Semicolon;

defineFunction:
    typename Identifier LeftParenthesis paramsList? RightParenthesis
        block;

defineConstructFunction:
    Identifier LeftParenthesis RightParenthesis
        block;

paramsList: typename Identifier (Comma typename Identifier)*;

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
	LeftParenthesis expression RightParenthesis #wrap_expr
    | funcExpr #func_expr//maybe needed to be swapped with member_expr
    | New typeprefix (LeftBracket expression RightBracket)* (LeftBracket RightBracket)* (LeftParenthesis RightParenthesis)* #new_expr
    | expression (Increment | Decrement) #right_self_expr
    | <assoc = right> (Increment | Decrement) expression #left_self_expr
    | <assoc = right> (Not | BitInv | Add | Sub) expression #single_expr
    | expression LeftBracket expression RightBracket #array_expr
    | expression Dot (Identifier | funcExpr) #member_expr
    | l = expression (Mul | Div | Mod) r = expression #binary_expr
    | l = expression (Add | Sub) r = expression #binary_expr
    | l = expression (LeftShift | RightShift) r = expression #binary_expr
    | l = expression (Less | LessEqual | Greater | GreaterEqual) r = expression #binary_expr
    | l = expression (Equal | NotEqual) r = expression #binary_expr
    | l = expression BitAnd r = expression #binary_expr
    | l = expression BitOr r = expression #binary_expr
    | l = expression BitXor r = expression #binary_expr
    | l = expression And r = expression #binary_expr
    | l = expression Or r = expression #binary_expr
    | <assoc = right> expression Question expression Colon expression #ternary_expr
    | <assoc = right> l = expression Assign r = expression #assign_expr
    | atom #atom_expr;

typeprefix: (basicType | Identifier);
typename: typeprefix (LeftBracket RightBracket)*;
//matching array of more than one dimension

atom: This | Null | Identifier | Number | String | True | False;
