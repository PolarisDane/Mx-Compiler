// Generated from ./Parser/MxParser.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Comment=1, LineComment=2, BlockComment=3, String=4, Int=5, Bool=6, StringType=7, 
		Void=8, True=9, False=10, Null=11, New=12, Class=13, Return=14, Continue=15, 
		Break=16, If=17, Else=18, While=19, For=20, This=21, Not=22, BitInv=23, 
		Add=24, Sub=25, Mul=26, Div=27, Mod=28, Dot=29, BitAnd=30, BitOr=31, BitXor=32, 
		And=33, Or=34, Equal=35, NotEqual=36, Less=37, Greater=38, LessEqual=39, 
		GreaterEqual=40, Assign=41, Increment=42, Decrement=43, LeftShift=44, 
		RightShift=45, LeftParenthesis=46, RightParenthesis=47, LeftBracket=48, 
		RightBracket=49, LeftBrace=50, RightBrace=51, Comma=52, Semicolon=53, 
		Question=54, Colon=55, Number=56, NewLine=57, Whitespace=58, Identifier=59;
	public static final int
		RULE_file_input = 0, RULE_basicType = 1, RULE_suite = 2, RULE_block = 3, 
		RULE_defineClass = 4, RULE_defineFunction = 5, RULE_defineConstructFunction = 6, 
		RULE_declareVar = 7, RULE_paramsList = 8, RULE_defineVarStmt = 9, RULE_varAssign = 10, 
		RULE_exprStmt = 11, RULE_stmt = 12, RULE_ifStmt = 13, RULE_whileStmt = 14, 
		RULE_forStmt = 15, RULE_continueStmt = 16, RULE_breakStmt = 17, RULE_returnStmt = 18, 
		RULE_flowStmt = 19, RULE_exprList = 20, RULE_funcExpr = 21, RULE_expression = 22, 
		RULE_typeprefix = 23, RULE_typename = 24, RULE_atom = 25;
	private static String[] makeRuleNames() {
		return new String[] {
			"file_input", "basicType", "suite", "block", "defineClass", "defineFunction", 
			"defineConstructFunction", "declareVar", "paramsList", "defineVarStmt", 
			"varAssign", "exprStmt", "stmt", "ifStmt", "whileStmt", "forStmt", "continueStmt", 
			"breakStmt", "returnStmt", "flowStmt", "exprList", "funcExpr", "expression", 
			"typeprefix", "typename", "atom"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "'int'", "'bool'", "'string'", "'void'", 
			"'true'", "'false'", "'null'", "'new'", "'class'", "'return'", "'continue'", 
			"'break'", "'if'", "'else'", "'while'", "'for'", "'this'", "'!'", "'~'", 
			"'+'", "'-'", "'*'", "'/'", "'%'", "'.'", "'&'", "'|'", "'^'", "'&&'", 
			"'||'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'='", "'++'", 
			"'--'", "'<<'", "'>>'", "'('", "')'", "'['", "']'", "'{'", "'}'", "','", 
			"';'", "'?'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Comment", "LineComment", "BlockComment", "String", "Int", "Bool", 
			"StringType", "Void", "True", "False", "Null", "New", "Class", "Return", 
			"Continue", "Break", "If", "Else", "While", "For", "This", "Not", "BitInv", 
			"Add", "Sub", "Mul", "Div", "Mod", "Dot", "BitAnd", "BitOr", "BitXor", 
			"And", "Or", "Equal", "NotEqual", "Less", "Greater", "LessEqual", "GreaterEqual", 
			"Assign", "Increment", "Decrement", "LeftShift", "RightShift", "LeftParenthesis", 
			"RightParenthesis", "LeftBracket", "RightBracket", "LeftBrace", "RightBrace", 
			"Comma", "Semicolon", "Question", "Colon", "Number", "NewLine", "Whitespace", 
			"Identifier"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MxParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class File_inputContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MxParser.EOF, 0); }
		public List<DefineFunctionContext> defineFunction() {
			return getRuleContexts(DefineFunctionContext.class);
		}
		public DefineFunctionContext defineFunction(int i) {
			return getRuleContext(DefineFunctionContext.class,i);
		}
		public List<DefineClassContext> defineClass() {
			return getRuleContexts(DefineClassContext.class);
		}
		public DefineClassContext defineClass(int i) {
			return getRuleContext(DefineClassContext.class,i);
		}
		public List<DefineVarStmtContext> defineVarStmt() {
			return getRuleContexts(DefineVarStmtContext.class);
		}
		public DefineVarStmtContext defineVarStmt(int i) {
			return getRuleContext(DefineVarStmtContext.class,i);
		}
		public File_inputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file_input; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterFile_input(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitFile_input(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitFile_input(this);
			else return visitor.visitChildren(this);
		}
	}

	public final File_inputContext file_input() throws RecognitionException {
		File_inputContext _localctx = new File_inputContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file_input);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << StringType) | (1L << Void) | (1L << Class) | (1L << Identifier))) != 0)) {
				{
				setState(55);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(52);
					defineFunction();
					}
					break;
				case 2:
					{
					setState(53);
					defineClass();
					}
					break;
				case 3:
					{
					setState(54);
					defineVarStmt();
					}
					break;
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(60);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BasicTypeContext extends ParserRuleContext {
		public TerminalNode Int() { return getToken(MxParser.Int, 0); }
		public TerminalNode StringType() { return getToken(MxParser.StringType, 0); }
		public TerminalNode Bool() { return getToken(MxParser.Bool, 0); }
		public TerminalNode Void() { return getToken(MxParser.Void, 0); }
		public BasicTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basicType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterBasicType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitBasicType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitBasicType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BasicTypeContext basicType() throws RecognitionException {
		BasicTypeContext _localctx = new BasicTypeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_basicType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << StringType) | (1L << Void))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuiteContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public SuiteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterSuite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitSuite(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitSuite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_suite);
		try {
			setState(66);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				stmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LeftBrace() { return getToken(MxParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxParser.RightBrace, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(LeftBrace);
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << String) | (1L << Int) | (1L << Bool) | (1L << StringType) | (1L << Void) | (1L << True) | (1L << False) | (1L << Null) | (1L << New) | (1L << Return) | (1L << Continue) | (1L << Break) | (1L << If) | (1L << While) | (1L << For) | (1L << This) | (1L << Not) | (1L << BitInv) | (1L << Add) | (1L << Sub) | (1L << Increment) | (1L << Decrement) | (1L << LeftParenthesis) | (1L << LeftBrace) | (1L << Semicolon) | (1L << Number) | (1L << Identifier))) != 0)) {
				{
				{
				setState(69);
				stmt();
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(75);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefineClassContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(MxParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LeftBrace() { return getToken(MxParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxParser.RightBrace, 0); }
		public TerminalNode Semicolon() { return getToken(MxParser.Semicolon, 0); }
		public List<DefineVarStmtContext> defineVarStmt() {
			return getRuleContexts(DefineVarStmtContext.class);
		}
		public DefineVarStmtContext defineVarStmt(int i) {
			return getRuleContext(DefineVarStmtContext.class,i);
		}
		public List<DefineFunctionContext> defineFunction() {
			return getRuleContexts(DefineFunctionContext.class);
		}
		public DefineFunctionContext defineFunction(int i) {
			return getRuleContext(DefineFunctionContext.class,i);
		}
		public List<DefineConstructFunctionContext> defineConstructFunction() {
			return getRuleContexts(DefineConstructFunctionContext.class);
		}
		public DefineConstructFunctionContext defineConstructFunction(int i) {
			return getRuleContext(DefineConstructFunctionContext.class,i);
		}
		public DefineClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defineClass; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterDefineClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitDefineClass(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitDefineClass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefineClassContext defineClass() throws RecognitionException {
		DefineClassContext _localctx = new DefineClassContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_defineClass);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(Class);
			setState(78);
			match(Identifier);
			setState(79);
			match(LeftBrace);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << StringType) | (1L << Void) | (1L << Identifier))) != 0)) {
				{
				setState(83);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(80);
					defineVarStmt();
					}
					break;
				case 2:
					{
					setState(81);
					defineFunction();
					}
					break;
				case 3:
					{
					setState(82);
					defineConstructFunction();
					}
					break;
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(88);
			match(RightBrace);
			setState(89);
			match(Semicolon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefineFunctionContext extends ParserRuleContext {
		public TypenameContext typename() {
			return getRuleContext(TypenameContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LeftParenthesis() { return getToken(MxParser.LeftParenthesis, 0); }
		public TerminalNode RightParenthesis() { return getToken(MxParser.RightParenthesis, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ParamsListContext paramsList() {
			return getRuleContext(ParamsListContext.class,0);
		}
		public DefineFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defineFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterDefineFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitDefineFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitDefineFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefineFunctionContext defineFunction() throws RecognitionException {
		DefineFunctionContext _localctx = new DefineFunctionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_defineFunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			typename();
			setState(92);
			match(Identifier);
			setState(93);
			match(LeftParenthesis);
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << StringType) | (1L << Void) | (1L << Identifier))) != 0)) {
				{
				setState(94);
				paramsList();
				}
			}

			setState(97);
			match(RightParenthesis);
			setState(98);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefineConstructFunctionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LeftParenthesis() { return getToken(MxParser.LeftParenthesis, 0); }
		public TerminalNode RightParenthesis() { return getToken(MxParser.RightParenthesis, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public DefineConstructFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defineConstructFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterDefineConstructFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitDefineConstructFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitDefineConstructFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefineConstructFunctionContext defineConstructFunction() throws RecognitionException {
		DefineConstructFunctionContext _localctx = new DefineConstructFunctionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_defineConstructFunction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(Identifier);
			setState(101);
			match(LeftParenthesis);
			setState(102);
			match(RightParenthesis);
			setState(103);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclareVarContext extends ParserRuleContext {
		public TypenameContext typename() {
			return getRuleContext(TypenameContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public DeclareVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declareVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterDeclareVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitDeclareVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitDeclareVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclareVarContext declareVar() throws RecognitionException {
		DeclareVarContext _localctx = new DeclareVarContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_declareVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			typename();
			setState(106);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsListContext extends ParserRuleContext {
		public List<DeclareVarContext> declareVar() {
			return getRuleContexts(DeclareVarContext.class);
		}
		public DeclareVarContext declareVar(int i) {
			return getRuleContext(DeclareVarContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxParser.Comma, i);
		}
		public ParamsListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramsList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterParamsList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitParamsList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitParamsList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsListContext paramsList() throws RecognitionException {
		ParamsListContext _localctx = new ParamsListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_paramsList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			declareVar();
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(109);
				match(Comma);
				setState(110);
				declareVar();
				}
				}
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefineVarStmtContext extends ParserRuleContext {
		public TypenameContext typename() {
			return getRuleContext(TypenameContext.class,0);
		}
		public List<VarAssignContext> varAssign() {
			return getRuleContexts(VarAssignContext.class);
		}
		public VarAssignContext varAssign(int i) {
			return getRuleContext(VarAssignContext.class,i);
		}
		public TerminalNode Semicolon() { return getToken(MxParser.Semicolon, 0); }
		public List<TerminalNode> Comma() { return getTokens(MxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxParser.Comma, i);
		}
		public DefineVarStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defineVarStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterDefineVarStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitDefineVarStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitDefineVarStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefineVarStmtContext defineVarStmt() throws RecognitionException {
		DefineVarStmtContext _localctx = new DefineVarStmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_defineVarStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			typename();
			setState(117);
			varAssign();
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(118);
				match(Comma);
				setState(119);
				varAssign();
				}
				}
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(125);
			match(Semicolon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarAssignContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode Assign() { return getToken(MxParser.Assign, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VarAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterVarAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitVarAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitVarAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarAssignContext varAssign() throws RecognitionException {
		VarAssignContext _localctx = new VarAssignContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_varAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(Identifier);
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(128);
				match(Assign);
				setState(129);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprStmtContext extends ParserRuleContext {
		public TerminalNode Semicolon() { return getToken(MxParser.Semicolon, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public ExprStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterExprStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitExprStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitExprStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprStmtContext exprStmt() throws RecognitionException {
		ExprStmtContext _localctx = new ExprStmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_exprStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << String) | (1L << True) | (1L << False) | (1L << Null) | (1L << New) | (1L << This) | (1L << Not) | (1L << BitInv) | (1L << Add) | (1L << Sub) | (1L << Increment) | (1L << Decrement) | (1L << LeftParenthesis) | (1L << Number) | (1L << Identifier))) != 0)) {
				{
				setState(132);
				exprList();
				}
			}

			setState(135);
			match(Semicolon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public ExprStmtContext exprStmt() {
			return getRuleContext(ExprStmtContext.class,0);
		}
		public DefineVarStmtContext defineVarStmt() {
			return getRuleContext(DefineVarStmtContext.class,0);
		}
		public FlowStmtContext flowStmt() {
			return getRuleContext(FlowStmtContext.class,0);
		}
		public IfStmtContext ifStmt() {
			return getRuleContext(IfStmtContext.class,0);
		}
		public WhileStmtContext whileStmt() {
			return getRuleContext(WhileStmtContext.class,0);
		}
		public ForStmtContext forStmt() {
			return getRuleContext(ForStmtContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_stmt);
		try {
			setState(144);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				exprStmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				defineVarStmt();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(139);
				flowStmt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(140);
				ifStmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(141);
				whileStmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(142);
				forStmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(143);
				block();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStmtContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(MxParser.If, 0); }
		public TerminalNode LeftParenthesis() { return getToken(MxParser.LeftParenthesis, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParenthesis() { return getToken(MxParser.RightParenthesis, 0); }
		public List<SuiteContext> suite() {
			return getRuleContexts(SuiteContext.class);
		}
		public SuiteContext suite(int i) {
			return getRuleContext(SuiteContext.class,i);
		}
		public TerminalNode Else() { return getToken(MxParser.Else, 0); }
		public IfStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitIfStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStmtContext ifStmt() throws RecognitionException {
		IfStmtContext _localctx = new IfStmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_ifStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(If);
			setState(147);
			match(LeftParenthesis);
			setState(148);
			expression(0);
			setState(149);
			match(RightParenthesis);
			setState(150);
			suite();
			setState(153);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(151);
				match(Else);
				setState(152);
				suite();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileStmtContext extends ParserRuleContext {
		public TerminalNode While() { return getToken(MxParser.While, 0); }
		public TerminalNode LeftParenthesis() { return getToken(MxParser.LeftParenthesis, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParenthesis() { return getToken(MxParser.RightParenthesis, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public WhileStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterWhileStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitWhileStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitWhileStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStmtContext whileStmt() throws RecognitionException {
		WhileStmtContext _localctx = new WhileStmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_whileStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(While);
			setState(156);
			match(LeftParenthesis);
			setState(157);
			expression(0);
			setState(158);
			match(RightParenthesis);
			setState(159);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForStmtContext extends ParserRuleContext {
		public TerminalNode For() { return getToken(MxParser.For, 0); }
		public TerminalNode LeftParenthesis() { return getToken(MxParser.LeftParenthesis, 0); }
		public TerminalNode Semicolon() { return getToken(MxParser.Semicolon, 0); }
		public TerminalNode RightParenthesis() { return getToken(MxParser.RightParenthesis, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public ExprStmtContext exprStmt() {
			return getRuleContext(ExprStmtContext.class,0);
		}
		public DefineVarStmtContext defineVarStmt() {
			return getRuleContext(DefineVarStmtContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ForStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitForStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitForStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStmtContext forStmt() throws RecognitionException {
		ForStmtContext _localctx = new ForStmtContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_forStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(For);
			setState(162);
			match(LeftParenthesis);
			setState(165);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(163);
				exprStmt();
				}
				break;
			case 2:
				{
				setState(164);
				defineVarStmt();
				}
				break;
			}
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << String) | (1L << True) | (1L << False) | (1L << Null) | (1L << New) | (1L << This) | (1L << Not) | (1L << BitInv) | (1L << Add) | (1L << Sub) | (1L << Increment) | (1L << Decrement) | (1L << LeftParenthesis) | (1L << Number) | (1L << Identifier))) != 0)) {
				{
				setState(167);
				expression(0);
				}
			}

			setState(170);
			match(Semicolon);
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << String) | (1L << True) | (1L << False) | (1L << Null) | (1L << New) | (1L << This) | (1L << Not) | (1L << BitInv) | (1L << Add) | (1L << Sub) | (1L << Increment) | (1L << Decrement) | (1L << LeftParenthesis) | (1L << Number) | (1L << Identifier))) != 0)) {
				{
				setState(171);
				expression(0);
				}
			}

			setState(174);
			match(RightParenthesis);
			setState(175);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContinueStmtContext extends ParserRuleContext {
		public TerminalNode Continue() { return getToken(MxParser.Continue, 0); }
		public TerminalNode Semicolon() { return getToken(MxParser.Semicolon, 0); }
		public ContinueStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterContinueStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitContinueStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitContinueStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStmtContext continueStmt() throws RecognitionException {
		ContinueStmtContext _localctx = new ContinueStmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_continueStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(Continue);
			setState(178);
			match(Semicolon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BreakStmtContext extends ParserRuleContext {
		public TerminalNode Break() { return getToken(MxParser.Break, 0); }
		public TerminalNode Semicolon() { return getToken(MxParser.Semicolon, 0); }
		public BreakStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterBreakStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitBreakStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitBreakStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStmtContext breakStmt() throws RecognitionException {
		BreakStmtContext _localctx = new BreakStmtContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_breakStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(Break);
			setState(181);
			match(Semicolon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStmtContext extends ParserRuleContext {
		public TerminalNode Return() { return getToken(MxParser.Return, 0); }
		public TerminalNode Semicolon() { return getToken(MxParser.Semicolon, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitReturnStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStmtContext returnStmt() throws RecognitionException {
		ReturnStmtContext _localctx = new ReturnStmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_returnStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(Return);
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << String) | (1L << True) | (1L << False) | (1L << Null) | (1L << New) | (1L << This) | (1L << Not) | (1L << BitInv) | (1L << Add) | (1L << Sub) | (1L << Increment) | (1L << Decrement) | (1L << LeftParenthesis) | (1L << Number) | (1L << Identifier))) != 0)) {
				{
				setState(184);
				expression(0);
				}
			}

			setState(187);
			match(Semicolon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FlowStmtContext extends ParserRuleContext {
		public ContinueStmtContext continueStmt() {
			return getRuleContext(ContinueStmtContext.class,0);
		}
		public BreakStmtContext breakStmt() {
			return getRuleContext(BreakStmtContext.class,0);
		}
		public ReturnStmtContext returnStmt() {
			return getRuleContext(ReturnStmtContext.class,0);
		}
		public FlowStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flowStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterFlowStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitFlowStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitFlowStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FlowStmtContext flowStmt() throws RecognitionException {
		FlowStmtContext _localctx = new FlowStmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_flowStmt);
		try {
			setState(192);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Continue:
				enterOuterAlt(_localctx, 1);
				{
				setState(189);
				continueStmt();
				}
				break;
			case Break:
				enterOuterAlt(_localctx, 2);
				{
				setState(190);
				breakStmt();
				}
				break;
			case Return:
				enterOuterAlt(_localctx, 3);
				{
				setState(191);
				returnStmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxParser.Comma, i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterExprList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitExprList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitExprList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			expression(0);
			setState(199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(195);
				match(Comma);
				setState(196);
				expression(0);
				}
				}
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncExprContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LeftParenthesis() { return getToken(MxParser.LeftParenthesis, 0); }
		public TerminalNode RightParenthesis() { return getToken(MxParser.RightParenthesis, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public FuncExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterFuncExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitFuncExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitFuncExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncExprContext funcExpr() throws RecognitionException {
		FuncExprContext _localctx = new FuncExprContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_funcExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			match(Identifier);
			setState(203);
			match(LeftParenthesis);
			setState(205);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << String) | (1L << True) | (1L << False) | (1L << Null) | (1L << New) | (1L << This) | (1L << Not) | (1L << BitInv) | (1L << Add) | (1L << Sub) | (1L << Increment) | (1L << Decrement) | (1L << LeftParenthesis) | (1L << Number) | (1L << Identifier))) != 0)) {
				{
				setState(204);
				exprList();
				}
			}

			setState(207);
			match(RightParenthesis);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Wrap_exprContext extends ExpressionContext {
		public TerminalNode LeftParenthesis() { return getToken(MxParser.LeftParenthesis, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParenthesis() { return getToken(MxParser.RightParenthesis, 0); }
		public Wrap_exprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterWrap_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitWrap_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitWrap_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Member_exprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Dot() { return getToken(MxParser.Dot, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public FuncExprContext funcExpr() {
			return getRuleContext(FuncExprContext.class,0);
		}
		public Member_exprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterMember_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitMember_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitMember_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Binary_exprContext extends ExpressionContext {
		public ExpressionContext l;
		public ExpressionContext r;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Mul() { return getToken(MxParser.Mul, 0); }
		public TerminalNode Div() { return getToken(MxParser.Div, 0); }
		public TerminalNode Mod() { return getToken(MxParser.Mod, 0); }
		public TerminalNode Add() { return getToken(MxParser.Add, 0); }
		public TerminalNode Sub() { return getToken(MxParser.Sub, 0); }
		public TerminalNode LeftShift() { return getToken(MxParser.LeftShift, 0); }
		public TerminalNode RightShift() { return getToken(MxParser.RightShift, 0); }
		public TerminalNode Less() { return getToken(MxParser.Less, 0); }
		public TerminalNode LessEqual() { return getToken(MxParser.LessEqual, 0); }
		public TerminalNode Greater() { return getToken(MxParser.Greater, 0); }
		public TerminalNode GreaterEqual() { return getToken(MxParser.GreaterEqual, 0); }
		public TerminalNode Equal() { return getToken(MxParser.Equal, 0); }
		public TerminalNode NotEqual() { return getToken(MxParser.NotEqual, 0); }
		public TerminalNode BitAnd() { return getToken(MxParser.BitAnd, 0); }
		public TerminalNode BitOr() { return getToken(MxParser.BitOr, 0); }
		public TerminalNode BitXor() { return getToken(MxParser.BitXor, 0); }
		public TerminalNode And() { return getToken(MxParser.And, 0); }
		public TerminalNode Or() { return getToken(MxParser.Or, 0); }
		public Binary_exprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterBinary_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitBinary_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitBinary_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Func_exprContext extends ExpressionContext {
		public FuncExprContext funcExpr() {
			return getRuleContext(FuncExprContext.class,0);
		}
		public Func_exprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterFunc_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitFunc_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitFunc_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class New_exprContext extends ExpressionContext {
		public TerminalNode New() { return getToken(MxParser.New, 0); }
		public TypeprefixContext typeprefix() {
			return getRuleContext(TypeprefixContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(MxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxParser.LeftBracket, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxParser.RightBracket, i);
		}
		public List<TerminalNode> LeftParenthesis() { return getTokens(MxParser.LeftParenthesis); }
		public TerminalNode LeftParenthesis(int i) {
			return getToken(MxParser.LeftParenthesis, i);
		}
		public List<TerminalNode> RightParenthesis() { return getTokens(MxParser.RightParenthesis); }
		public TerminalNode RightParenthesis(int i) {
			return getToken(MxParser.RightParenthesis, i);
		}
		public New_exprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterNew_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitNew_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitNew_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Assign_exprContext extends ExpressionContext {
		public ExpressionContext l;
		public ExpressionContext r;
		public TerminalNode Assign() { return getToken(MxParser.Assign, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public Assign_exprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterAssign_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitAssign_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitAssign_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Right_self_exprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Increment() { return getToken(MxParser.Increment, 0); }
		public TerminalNode Decrement() { return getToken(MxParser.Decrement, 0); }
		public Right_self_exprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterRight_self_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitRight_self_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitRight_self_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Atom_exprContext extends ExpressionContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public Atom_exprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterAtom_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitAtom_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitAtom_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Left_self_exprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Increment() { return getToken(MxParser.Increment, 0); }
		public TerminalNode Decrement() { return getToken(MxParser.Decrement, 0); }
		public Left_self_exprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterLeft_self_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitLeft_self_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitLeft_self_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Ternary_exprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Question() { return getToken(MxParser.Question, 0); }
		public TerminalNode Colon() { return getToken(MxParser.Colon, 0); }
		public Ternary_exprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterTernary_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitTernary_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitTernary_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Single_exprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Not() { return getToken(MxParser.Not, 0); }
		public TerminalNode BitInv() { return getToken(MxParser.BitInv, 0); }
		public TerminalNode Add() { return getToken(MxParser.Add, 0); }
		public TerminalNode Sub() { return getToken(MxParser.Sub, 0); }
		public Single_exprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterSingle_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitSingle_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitSingle_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Array_exprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LeftBracket() { return getToken(MxParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(MxParser.RightBracket, 0); }
		public Array_exprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterArray_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitArray_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitArray_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 44;
		enterRecursionRule(_localctx, 44, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				_localctx = new Wrap_exprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(210);
				match(LeftParenthesis);
				setState(211);
				expression(0);
				setState(212);
				match(RightParenthesis);
				}
				break;
			case 2:
				{
				_localctx = new Func_exprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(214);
				funcExpr();
				}
				break;
			case 3:
				{
				_localctx = new New_exprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(215);
				match(New);
				setState(216);
				typeprefix();
				setState(223);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(217);
						match(LeftBracket);
						setState(218);
						expression(0);
						setState(219);
						match(RightBracket);
						}
						} 
					}
					setState(225);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				}
				setState(230);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(226);
						match(LeftBracket);
						setState(227);
						match(RightBracket);
						}
						} 
					}
					setState(232);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				}
				setState(237);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(233);
						match(LeftParenthesis);
						setState(234);
						match(RightParenthesis);
						}
						} 
					}
					setState(239);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				}
				}
				break;
			case 4:
				{
				_localctx = new Left_self_exprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(240);
				_la = _input.LA(1);
				if ( !(_la==Increment || _la==Decrement) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(241);
				expression(18);
				}
				break;
			case 5:
				{
				_localctx = new Single_exprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(242);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Not) | (1L << BitInv) | (1L << Add) | (1L << Sub))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(243);
				expression(16);
				}
				break;
			case 6:
				{
				_localctx = new Atom_exprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(244);
				atom();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(301);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(299);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
					case 1:
						{
						_localctx = new Binary_exprContext(new ExpressionContext(_parentctx, _parentState));
						((Binary_exprContext)_localctx).l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(247);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(248);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Mul) | (1L << Div) | (1L << Mod))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(249);
						((Binary_exprContext)_localctx).r = expression(14);
						}
						break;
					case 2:
						{
						_localctx = new Binary_exprContext(new ExpressionContext(_parentctx, _parentState));
						((Binary_exprContext)_localctx).l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(250);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(251);
						_la = _input.LA(1);
						if ( !(_la==Add || _la==Sub) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(252);
						((Binary_exprContext)_localctx).r = expression(13);
						}
						break;
					case 3:
						{
						_localctx = new Binary_exprContext(new ExpressionContext(_parentctx, _parentState));
						((Binary_exprContext)_localctx).l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(253);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(254);
						_la = _input.LA(1);
						if ( !(_la==LeftShift || _la==RightShift) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(255);
						((Binary_exprContext)_localctx).r = expression(12);
						}
						break;
					case 4:
						{
						_localctx = new Binary_exprContext(new ExpressionContext(_parentctx, _parentState));
						((Binary_exprContext)_localctx).l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(256);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(257);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Less) | (1L << Greater) | (1L << LessEqual) | (1L << GreaterEqual))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(258);
						((Binary_exprContext)_localctx).r = expression(11);
						}
						break;
					case 5:
						{
						_localctx = new Binary_exprContext(new ExpressionContext(_parentctx, _parentState));
						((Binary_exprContext)_localctx).l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(259);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(260);
						_la = _input.LA(1);
						if ( !(_la==Equal || _la==NotEqual) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(261);
						((Binary_exprContext)_localctx).r = expression(10);
						}
						break;
					case 6:
						{
						_localctx = new Binary_exprContext(new ExpressionContext(_parentctx, _parentState));
						((Binary_exprContext)_localctx).l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(262);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(263);
						match(BitAnd);
						setState(264);
						((Binary_exprContext)_localctx).r = expression(9);
						}
						break;
					case 7:
						{
						_localctx = new Binary_exprContext(new ExpressionContext(_parentctx, _parentState));
						((Binary_exprContext)_localctx).l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(265);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(266);
						match(BitOr);
						setState(267);
						((Binary_exprContext)_localctx).r = expression(8);
						}
						break;
					case 8:
						{
						_localctx = new Binary_exprContext(new ExpressionContext(_parentctx, _parentState));
						((Binary_exprContext)_localctx).l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(268);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(269);
						match(BitXor);
						setState(270);
						((Binary_exprContext)_localctx).r = expression(7);
						}
						break;
					case 9:
						{
						_localctx = new Binary_exprContext(new ExpressionContext(_parentctx, _parentState));
						((Binary_exprContext)_localctx).l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(271);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(272);
						match(And);
						setState(273);
						((Binary_exprContext)_localctx).r = expression(6);
						}
						break;
					case 10:
						{
						_localctx = new Binary_exprContext(new ExpressionContext(_parentctx, _parentState));
						((Binary_exprContext)_localctx).l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(274);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(275);
						match(Or);
						setState(276);
						((Binary_exprContext)_localctx).r = expression(5);
						}
						break;
					case 11:
						{
						_localctx = new Ternary_exprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(277);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(278);
						match(Question);
						setState(279);
						expression(0);
						setState(280);
						match(Colon);
						setState(281);
						expression(3);
						}
						break;
					case 12:
						{
						_localctx = new Assign_exprContext(new ExpressionContext(_parentctx, _parentState));
						((Assign_exprContext)_localctx).l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(283);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(284);
						match(Assign);
						setState(285);
						((Assign_exprContext)_localctx).r = expression(2);
						}
						break;
					case 13:
						{
						_localctx = new Right_self_exprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(286);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(287);
						_la = _input.LA(1);
						if ( !(_la==Increment || _la==Decrement) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case 14:
						{
						_localctx = new Array_exprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(288);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(289);
						match(LeftBracket);
						setState(290);
						expression(0);
						setState(291);
						match(RightBracket);
						}
						break;
					case 15:
						{
						_localctx = new Member_exprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(293);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(294);
						match(Dot);
						setState(297);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
						case 1:
							{
							setState(295);
							match(Identifier);
							}
							break;
						case 2:
							{
							setState(296);
							funcExpr();
							}
							break;
						}
						}
						break;
					}
					} 
				}
				setState(303);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TypeprefixContext extends ParserRuleContext {
		public BasicTypeContext basicType() {
			return getRuleContext(BasicTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TypeprefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeprefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterTypeprefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitTypeprefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitTypeprefix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeprefixContext typeprefix() throws RecognitionException {
		TypeprefixContext _localctx = new TypeprefixContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_typeprefix);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Int:
			case Bool:
			case StringType:
			case Void:
				{
				setState(304);
				basicType();
				}
				break;
			case Identifier:
				{
				setState(305);
				match(Identifier);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypenameContext extends ParserRuleContext {
		public TypeprefixContext typeprefix() {
			return getRuleContext(TypeprefixContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(MxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxParser.RightBracket, i);
		}
		public TypenameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typename; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterTypename(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitTypename(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitTypename(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypenameContext typename() throws RecognitionException {
		TypenameContext _localctx = new TypenameContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_typename);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			typeprefix();
			setState(313);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LeftBracket) {
				{
				{
				setState(309);
				match(LeftBracket);
				setState(310);
				match(RightBracket);
				}
				}
				setState(315);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public TerminalNode This() { return getToken(MxParser.This, 0); }
		public TerminalNode Null() { return getToken(MxParser.Null, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode Number() { return getToken(MxParser.Number, 0); }
		public TerminalNode String() { return getToken(MxParser.String, 0); }
		public TerminalNode True() { return getToken(MxParser.True, 0); }
		public TerminalNode False() { return getToken(MxParser.False, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_atom);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << String) | (1L << True) | (1L << False) | (1L << Null) | (1L << This) | (1L << Number) | (1L << Identifier))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 22:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 13);
		case 1:
			return precpred(_ctx, 12);
		case 2:
			return precpred(_ctx, 11);
		case 3:
			return precpred(_ctx, 10);
		case 4:
			return precpred(_ctx, 9);
		case 5:
			return precpred(_ctx, 8);
		case 6:
			return precpred(_ctx, 7);
		case 7:
			return precpred(_ctx, 6);
		case 8:
			return precpred(_ctx, 5);
		case 9:
			return precpred(_ctx, 4);
		case 10:
			return precpred(_ctx, 3);
		case 11:
			return precpred(_ctx, 2);
		case 12:
			return precpred(_ctx, 17);
		case 13:
			return precpred(_ctx, 15);
		case 14:
			return precpred(_ctx, 14);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3=\u0141\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\3\2\3\2\3\2\7\2:\n\2\f\2\16\2=\13\2\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\5\4E\n\4\3\5\3\5\7\5I\n\5\f\5\16\5L\13\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\7\6V\n\6\f\6\16\6Y\13\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\5\7"+
		"b\n\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\7\nr\n\n"+
		"\f\n\16\nu\13\n\3\13\3\13\3\13\3\13\7\13{\n\13\f\13\16\13~\13\13\3\13"+
		"\3\13\3\f\3\f\3\f\5\f\u0085\n\f\3\r\5\r\u0088\n\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\5\16\u0093\n\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\5\17\u009c\n\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\5\21\u00a8\n\21\3\21\5\21\u00ab\n\21\3\21\3\21\5\21\u00af\n\21\3\21\3"+
		"\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\5\24\u00bc\n\24\3\24"+
		"\3\24\3\25\3\25\3\25\5\25\u00c3\n\25\3\26\3\26\3\26\7\26\u00c8\n\26\f"+
		"\26\16\26\u00cb\13\26\3\27\3\27\3\27\5\27\u00d0\n\27\3\27\3\27\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\7\30\u00e0\n\30"+
		"\f\30\16\30\u00e3\13\30\3\30\3\30\7\30\u00e7\n\30\f\30\16\30\u00ea\13"+
		"\30\3\30\3\30\7\30\u00ee\n\30\f\30\16\30\u00f1\13\30\3\30\3\30\3\30\3"+
		"\30\3\30\5\30\u00f8\n\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30"+
		"\u012c\n\30\7\30\u012e\n\30\f\30\16\30\u0131\13\30\3\31\3\31\5\31\u0135"+
		"\n\31\3\32\3\32\3\32\7\32\u013a\n\32\f\32\16\32\u013d\13\32\3\33\3\33"+
		"\3\33\2\3.\34\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64"+
		"\2\13\3\2\7\n\3\2,-\3\2\30\33\3\2\34\36\3\2\32\33\3\2./\3\2\'*\3\2%&\7"+
		"\2\6\6\13\r\27\27::==\2\u015d\2;\3\2\2\2\4@\3\2\2\2\6D\3\2\2\2\bF\3\2"+
		"\2\2\nO\3\2\2\2\f]\3\2\2\2\16f\3\2\2\2\20k\3\2\2\2\22n\3\2\2\2\24v\3\2"+
		"\2\2\26\u0081\3\2\2\2\30\u0087\3\2\2\2\32\u0092\3\2\2\2\34\u0094\3\2\2"+
		"\2\36\u009d\3\2\2\2 \u00a3\3\2\2\2\"\u00b3\3\2\2\2$\u00b6\3\2\2\2&\u00b9"+
		"\3\2\2\2(\u00c2\3\2\2\2*\u00c4\3\2\2\2,\u00cc\3\2\2\2.\u00f7\3\2\2\2\60"+
		"\u0134\3\2\2\2\62\u0136\3\2\2\2\64\u013e\3\2\2\2\66:\5\f\7\2\67:\5\n\6"+
		"\28:\5\24\13\29\66\3\2\2\29\67\3\2\2\298\3\2\2\2:=\3\2\2\2;9\3\2\2\2;"+
		"<\3\2\2\2<>\3\2\2\2=;\3\2\2\2>?\7\2\2\3?\3\3\2\2\2@A\t\2\2\2A\5\3\2\2"+
		"\2BE\5\b\5\2CE\5\32\16\2DB\3\2\2\2DC\3\2\2\2E\7\3\2\2\2FJ\7\64\2\2GI\5"+
		"\32\16\2HG\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KM\3\2\2\2LJ\3\2\2\2M"+
		"N\7\65\2\2N\t\3\2\2\2OP\7\17\2\2PQ\7=\2\2QW\7\64\2\2RV\5\24\13\2SV\5\f"+
		"\7\2TV\5\16\b\2UR\3\2\2\2US\3\2\2\2UT\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3"+
		"\2\2\2XZ\3\2\2\2YW\3\2\2\2Z[\7\65\2\2[\\\7\67\2\2\\\13\3\2\2\2]^\5\62"+
		"\32\2^_\7=\2\2_a\7\60\2\2`b\5\22\n\2a`\3\2\2\2ab\3\2\2\2bc\3\2\2\2cd\7"+
		"\61\2\2de\5\b\5\2e\r\3\2\2\2fg\7=\2\2gh\7\60\2\2hi\7\61\2\2ij\5\b\5\2"+
		"j\17\3\2\2\2kl\5\62\32\2lm\7=\2\2m\21\3\2\2\2ns\5\20\t\2op\7\66\2\2pr"+
		"\5\20\t\2qo\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2t\23\3\2\2\2us\3\2\2"+
		"\2vw\5\62\32\2w|\5\26\f\2xy\7\66\2\2y{\5\26\f\2zx\3\2\2\2{~\3\2\2\2|z"+
		"\3\2\2\2|}\3\2\2\2}\177\3\2\2\2~|\3\2\2\2\177\u0080\7\67\2\2\u0080\25"+
		"\3\2\2\2\u0081\u0084\7=\2\2\u0082\u0083\7+\2\2\u0083\u0085\5.\30\2\u0084"+
		"\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\27\3\2\2\2\u0086\u0088\5*\26"+
		"\2\u0087\u0086\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a"+
		"\7\67\2\2\u008a\31\3\2\2\2\u008b\u0093\5\30\r\2\u008c\u0093\5\24\13\2"+
		"\u008d\u0093\5(\25\2\u008e\u0093\5\34\17\2\u008f\u0093\5\36\20\2\u0090"+
		"\u0093\5 \21\2\u0091\u0093\5\b\5\2\u0092\u008b\3\2\2\2\u0092\u008c\3\2"+
		"\2\2\u0092\u008d\3\2\2\2\u0092\u008e\3\2\2\2\u0092\u008f\3\2\2\2\u0092"+
		"\u0090\3\2\2\2\u0092\u0091\3\2\2\2\u0093\33\3\2\2\2\u0094\u0095\7\23\2"+
		"\2\u0095\u0096\7\60\2\2\u0096\u0097\5.\30\2\u0097\u0098\7\61\2\2\u0098"+
		"\u009b\5\6\4\2\u0099\u009a\7\24\2\2\u009a\u009c\5\6\4\2\u009b\u0099\3"+
		"\2\2\2\u009b\u009c\3\2\2\2\u009c\35\3\2\2\2\u009d\u009e\7\25\2\2\u009e"+
		"\u009f\7\60\2\2\u009f\u00a0\5.\30\2\u00a0\u00a1\7\61\2\2\u00a1\u00a2\5"+
		"\6\4\2\u00a2\37\3\2\2\2\u00a3\u00a4\7\26\2\2\u00a4\u00a7\7\60\2\2\u00a5"+
		"\u00a8\5\30\r\2\u00a6\u00a8\5\24\13\2\u00a7\u00a5\3\2\2\2\u00a7\u00a6"+
		"\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00aa\3\2\2\2\u00a9\u00ab\5.\30\2\u00aa"+
		"\u00a9\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ae\7\67"+
		"\2\2\u00ad\u00af\5.\30\2\u00ae\u00ad\3\2\2\2\u00ae\u00af\3\2\2\2\u00af"+
		"\u00b0\3\2\2\2\u00b0\u00b1\7\61\2\2\u00b1\u00b2\5\6\4\2\u00b2!\3\2\2\2"+
		"\u00b3\u00b4\7\21\2\2\u00b4\u00b5\7\67\2\2\u00b5#\3\2\2\2\u00b6\u00b7"+
		"\7\22\2\2\u00b7\u00b8\7\67\2\2\u00b8%\3\2\2\2\u00b9\u00bb\7\20\2\2\u00ba"+
		"\u00bc\5.\30\2\u00bb\u00ba\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\3\2"+
		"\2\2\u00bd\u00be\7\67\2\2\u00be\'\3\2\2\2\u00bf\u00c3\5\"\22\2\u00c0\u00c3"+
		"\5$\23\2\u00c1\u00c3\5&\24\2\u00c2\u00bf\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2"+
		"\u00c1\3\2\2\2\u00c3)\3\2\2\2\u00c4\u00c9\5.\30\2\u00c5\u00c6\7\66\2\2"+
		"\u00c6\u00c8\5.\30\2\u00c7\u00c5\3\2\2\2\u00c8\u00cb\3\2\2\2\u00c9\u00c7"+
		"\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca+\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cc"+
		"\u00cd\7=\2\2\u00cd\u00cf\7\60\2\2\u00ce\u00d0\5*\26\2\u00cf\u00ce\3\2"+
		"\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\7\61\2\2\u00d2"+
		"-\3\2\2\2\u00d3\u00d4\b\30\1\2\u00d4\u00d5\7\60\2\2\u00d5\u00d6\5.\30"+
		"\2\u00d6\u00d7\7\61\2\2\u00d7\u00f8\3\2\2\2\u00d8\u00f8\5,\27\2\u00d9"+
		"\u00da\7\16\2\2\u00da\u00e1\5\60\31\2\u00db\u00dc\7\62\2\2\u00dc\u00dd"+
		"\5.\30\2\u00dd\u00de\7\63\2\2\u00de\u00e0\3\2\2\2\u00df\u00db\3\2\2\2"+
		"\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e8"+
		"\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00e5\7\62\2\2\u00e5\u00e7\7\63\2\2"+
		"\u00e6\u00e4\3\2\2\2\u00e7\u00ea\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9"+
		"\3\2\2\2\u00e9\u00ef\3\2\2\2\u00ea\u00e8\3\2\2\2\u00eb\u00ec\7\60\2\2"+
		"\u00ec\u00ee\7\61\2\2\u00ed\u00eb\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00ed"+
		"\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f8\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f2"+
		"\u00f3\t\3\2\2\u00f3\u00f8\5.\30\24\u00f4\u00f5\t\4\2\2\u00f5\u00f8\5"+
		".\30\22\u00f6\u00f8\5\64\33\2\u00f7\u00d3\3\2\2\2\u00f7\u00d8\3\2\2\2"+
		"\u00f7\u00d9\3\2\2\2\u00f7\u00f2\3\2\2\2\u00f7\u00f4\3\2\2\2\u00f7\u00f6"+
		"\3\2\2\2\u00f8\u012f\3\2\2\2\u00f9\u00fa\f\17\2\2\u00fa\u00fb\t\5\2\2"+
		"\u00fb\u012e\5.\30\20\u00fc\u00fd\f\16\2\2\u00fd\u00fe\t\6\2\2\u00fe\u012e"+
		"\5.\30\17\u00ff\u0100\f\r\2\2\u0100\u0101\t\7\2\2\u0101\u012e\5.\30\16"+
		"\u0102\u0103\f\f\2\2\u0103\u0104\t\b\2\2\u0104\u012e\5.\30\r\u0105\u0106"+
		"\f\13\2\2\u0106\u0107\t\t\2\2\u0107\u012e\5.\30\f\u0108\u0109\f\n\2\2"+
		"\u0109\u010a\7 \2\2\u010a\u012e\5.\30\13\u010b\u010c\f\t\2\2\u010c\u010d"+
		"\7!\2\2\u010d\u012e\5.\30\n\u010e\u010f\f\b\2\2\u010f\u0110\7\"\2\2\u0110"+
		"\u012e\5.\30\t\u0111\u0112\f\7\2\2\u0112\u0113\7#\2\2\u0113\u012e\5.\30"+
		"\b\u0114\u0115\f\6\2\2\u0115\u0116\7$\2\2\u0116\u012e\5.\30\7\u0117\u0118"+
		"\f\5\2\2\u0118\u0119\78\2\2\u0119\u011a\5.\30\2\u011a\u011b\79\2\2\u011b"+
		"\u011c\5.\30\5\u011c\u012e\3\2\2\2\u011d\u011e\f\4\2\2\u011e\u011f\7+"+
		"\2\2\u011f\u012e\5.\30\4\u0120\u0121\f\23\2\2\u0121\u012e\t\3\2\2\u0122"+
		"\u0123\f\21\2\2\u0123\u0124\7\62\2\2\u0124\u0125\5.\30\2\u0125\u0126\7"+
		"\63\2\2\u0126\u012e\3\2\2\2\u0127\u0128\f\20\2\2\u0128\u012b\7\37\2\2"+
		"\u0129\u012c\7=\2\2\u012a\u012c\5,\27\2\u012b\u0129\3\2\2\2\u012b\u012a"+
		"\3\2\2\2\u012c\u012e\3\2\2\2\u012d\u00f9\3\2\2\2\u012d\u00fc\3\2\2\2\u012d"+
		"\u00ff\3\2\2\2\u012d\u0102\3\2\2\2\u012d\u0105\3\2\2\2\u012d\u0108\3\2"+
		"\2\2\u012d\u010b\3\2\2\2\u012d\u010e\3\2\2\2\u012d\u0111\3\2\2\2\u012d"+
		"\u0114\3\2\2\2\u012d\u0117\3\2\2\2\u012d\u011d\3\2\2\2\u012d\u0120\3\2"+
		"\2\2\u012d\u0122\3\2\2\2\u012d\u0127\3\2\2\2\u012e\u0131\3\2\2\2\u012f"+
		"\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130/\3\2\2\2\u0131\u012f\3\2\2\2"+
		"\u0132\u0135\5\4\3\2\u0133\u0135\7=\2\2\u0134\u0132\3\2\2\2\u0134\u0133"+
		"\3\2\2\2\u0135\61\3\2\2\2\u0136\u013b\5\60\31\2\u0137\u0138\7\62\2\2\u0138"+
		"\u013a\7\63\2\2\u0139\u0137\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139\3"+
		"\2\2\2\u013b\u013c\3\2\2\2\u013c\63\3\2\2\2\u013d\u013b\3\2\2\2\u013e"+
		"\u013f\t\n\2\2\u013f\65\3\2\2\2\379;DJUWas|\u0084\u0087\u0092\u009b\u00a7"+
		"\u00aa\u00ae\u00bb\u00c2\u00c9\u00cf\u00e1\u00e8\u00ef\u00f7\u012b\u012d"+
		"\u012f\u0134\u013b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}