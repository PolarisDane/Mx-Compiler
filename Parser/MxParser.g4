parser grammar MxParser;

options {
	tokenVocab = MxLexer;
}

file_input: (defineFunction | defineClass | defineVarStmt)* EOF;

basicType: Int | StringType | Bool | Void;

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
        stmt
    //maybe else if here?
	(Else stmt)?;

whileStmt: While LeftParenthesis expression RightParenthesis
     stmt;

forStmt:
	For LeftParenthesis
        (exprStmt | defineVarStmt)?
        condition = expression? Semicolon
        step = expression?
        RightParenthesis
		stmt;

continueStmt: Continue Semicolon;

breakStmt: Break Semicolon;

returnStmt: Return expression? Semicolon;

flowStmt: continueStmt | breakStmt | returnStmt;

exprList: expression (Comma expression)*;

funcCall: Identifier LeftParenthesis exprList? RightParenthesis;

newArrayList: (LeftBracket expression? RightBracket);

expression:
	LeftParenthesis expression RightParenthesis #wrap_expr
    | funcCall #func_expr//maybe needed to be swapped with member_expr
    | expression LeftBracket expression RightBracket #array_expr
    | expression Dot (Identifier | funcCall) #member_expr
    | New typeprefix newArrayList* (LeftParenthesis RightParenthesis)* #new_expr
    | expression op = (Increment | Decrement) #right_self_expr
    | <assoc = right> op = (Increment | Decrement) expression #left_self_expr
    | <assoc = right> op = (Not | BitInv | Add | Sub) expression #single_expr
    | l = expression op = (Mul | Div | Mod) r = expression #binary_expr
    | l = expression op = (Add | Sub) r = expression #binary_expr
    | l = expression op = (LeftShift | RightShift) r = expression #binary_expr
    | l = expression op = (Less | LessEqual | Greater | GreaterEqual) r = expression #binary_expr
    | l = expression op = (Equal | NotEqual) r = expression #binary_expr
    | l = expression op = BitAnd r = expression #binary_expr
    | l = expression op = BitOr r = expression #binary_expr
    | l = expression op = BitXor r = expression #binary_expr
    | l = expression op = And r = expression #binary_expr
    | l = expression op = Or r = expression #binary_expr
    | <assoc = right> expression Question expression Colon expression #ternary_expr
    | <assoc = right> l = expression Assign r = expression #assign_expr
    | (This | Null | Identifier | Number | String | True | False) #atom_expr;

typeprefix: (basicType | Identifier);
typename: typeprefix (LeftBracket RightBracket)*;
//matching array of more than one dimension
