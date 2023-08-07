lexer grammar MxLexer;

Comment: (BlockComment | LineComment) -> skip;
//has to be above lexer of comment if wanted to be skipped
//or else it will only match comment and not skip it

LineComment: '//' .*? (NewLine | EOF);
BlockComment: '/*' .*? '*/';
//using .*? must set an end or else it will stop immediately

fragment Escape: '\n' | '\\\\' | '\\"';

String: '"' (Escape | .)*? '"';
//escape character not dealt with yet

Int: 'int';
Bool: 'bool';
StringType: 'string';
Void: 'void';
True: 'true';
False: 'false';
Null: 'null';
New: 'new';
Class: 'class';
Return: 'return';
Continue: 'continue';
Break: 'break';
If: 'if';
Else: 'else';
While: 'while';
For: 'for';
This: 'this';

Not: '!';
BitInv: '~';
Add: '+';
Sub: '-';
Mul: '*';
Div: '/';
Mod: '%';
Dot: '.';
BitAnd: '&';
BitOr: '|';
BitXor: '^';
And: '&&';
Or: '||';
Equal: '==';
NotEqual: '!=';
Less: '<';
Greater: '>';
LessEqual: '<=';
GreaterEqual: '>=';
Assign: '=';
Increment: '++';
Decrement: '--';
LeftShift: '<<';
RightShift: '>>';

LeftParenthesis: '(';
RightParenthesis: ')';
LeftBracket: '[';
RightBracket: ']';
LeftBrace: '{';
RightBrace: '}';
Comma: ',';
Semicolon: ';';
Question: '?';
Colon: ':';

fragment Digit: [0-9];
Number: Digit+;

NewLine: ('\r' | '\n' | '\u2028' | '\u2029') -> skip;
Whitespace: (' ' | '\t' | '\u000B' | '\u000C' | '\u00A0') -> skip;

Identifier: [A-Za-z][A-Za-z_0-9]*;
