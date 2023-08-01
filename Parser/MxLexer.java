// Generated from ./Parser/MxLexer.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"Comment", "LineComment", "BlockComment", "String", "Int", "Bool", "StringType", 
			"Void", "True", "False", "Null", "New", "Class", "Return", "Continue", 
			"Break", "If", "Else", "While", "For", "This", "Not", "BitInv", "Add", 
			"Sub", "Mul", "Div", "Mod", "Dot", "BitAnd", "BitOr", "BitXor", "And", 
			"Or", "Equal", "NotEqual", "Less", "Greater", "LessEqual", "GreaterEqual", 
			"Assign", "Increment", "Decrement", "LeftShift", "RightShift", "LeftParenthesis", 
			"RightParenthesis", "LeftBracket", "RightBracket", "LeftBrace", "RightBrace", 
			"Comma", "Semicolon", "Question", "Colon", "Digit", "Number", "NewLine", 
			"Whitespace", "Identifier"
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


	public MxLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MxLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2=\u0163\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\3\2\3\2\5\2~\n\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3\u0086\n\3\f\3\16\3\u0089"+
		"\13\3\3\3\3\3\5\3\u008d\n\3\3\4\3\4\3\4\3\4\7\4\u0093\n\4\f\4\16\4\u0096"+
		"\13\4\3\4\3\4\3\4\3\5\3\5\7\5\u009d\n\5\f\5\16\5\u00a0\13\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3"+
		"\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3"+
		"\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3"+
		"%\3%\3&\3&\3\'\3\'\3(\3(\3(\3)\3)\3)\3*\3*\3+\3+\3+\3,\3,\3,\3-\3-\3-"+
		"\3.\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65"+
		"\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\6:\u0151\n:\r:\16:\u0152\3;\3"+
		";\3;\3;\3<\3<\3<\3<\3=\3=\7=\u015f\n=\f=\16=\u0162\13=\5\u0087\u0094\u009e"+
		"\2>\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67"+
		"m8o9q\2s:u;w<y=\3\2\7\3\2\62;\5\2\f\f\17\17\u202a\u202b\6\2\13\13\r\16"+
		"\"\"\u00a2\u00a2\4\2C\\c|\6\2\62;C\\aac|\2\u0168\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2"+
		"\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2"+
		"Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3"+
		"\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2s\3\2\2"+
		"\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\3}\3\2\2\2\5\u0081\3\2\2\2\7\u008e"+
		"\3\2\2\2\t\u009a\3\2\2\2\13\u00a3\3\2\2\2\r\u00a7\3\2\2\2\17\u00ac\3\2"+
		"\2\2\21\u00b3\3\2\2\2\23\u00b8\3\2\2\2\25\u00bd\3\2\2\2\27\u00c3\3\2\2"+
		"\2\31\u00c8\3\2\2\2\33\u00cc\3\2\2\2\35\u00d2\3\2\2\2\37\u00d9\3\2\2\2"+
		"!\u00e2\3\2\2\2#\u00e8\3\2\2\2%\u00eb\3\2\2\2\'\u00f0\3\2\2\2)\u00f6\3"+
		"\2\2\2+\u00fa\3\2\2\2-\u00ff\3\2\2\2/\u0101\3\2\2\2\61\u0103\3\2\2\2\63"+
		"\u0105\3\2\2\2\65\u0107\3\2\2\2\67\u0109\3\2\2\29\u010b\3\2\2\2;\u010d"+
		"\3\2\2\2=\u010f\3\2\2\2?\u0111\3\2\2\2A\u0113\3\2\2\2C\u0115\3\2\2\2E"+
		"\u0118\3\2\2\2G\u011b\3\2\2\2I\u011e\3\2\2\2K\u0121\3\2\2\2M\u0123\3\2"+
		"\2\2O\u0125\3\2\2\2Q\u0128\3\2\2\2S\u012b\3\2\2\2U\u012d\3\2\2\2W\u0130"+
		"\3\2\2\2Y\u0133\3\2\2\2[\u0136\3\2\2\2]\u0139\3\2\2\2_\u013b\3\2\2\2a"+
		"\u013d\3\2\2\2c\u013f\3\2\2\2e\u0141\3\2\2\2g\u0143\3\2\2\2i\u0145\3\2"+
		"\2\2k\u0147\3\2\2\2m\u0149\3\2\2\2o\u014b\3\2\2\2q\u014d\3\2\2\2s\u0150"+
		"\3\2\2\2u\u0154\3\2\2\2w\u0158\3\2\2\2y\u015c\3\2\2\2{~\5\7\4\2|~\5\5"+
		"\3\2}{\3\2\2\2}|\3\2\2\2~\177\3\2\2\2\177\u0080\b\2\2\2\u0080\4\3\2\2"+
		"\2\u0081\u0082\7\61\2\2\u0082\u0083\7\61\2\2\u0083\u0087\3\2\2\2\u0084"+
		"\u0086\13\2\2\2\u0085\u0084\3\2\2\2\u0086\u0089\3\2\2\2\u0087\u0088\3"+
		"\2\2\2\u0087\u0085\3\2\2\2\u0088\u008c\3\2\2\2\u0089\u0087\3\2\2\2\u008a"+
		"\u008d\5u;\2\u008b\u008d\7\2\2\3\u008c\u008a\3\2\2\2\u008c\u008b\3\2\2"+
		"\2\u008d\6\3\2\2\2\u008e\u008f\7\61\2\2\u008f\u0090\7,\2\2\u0090\u0094"+
		"\3\2\2\2\u0091\u0093\13\2\2\2\u0092\u0091\3\2\2\2\u0093\u0096\3\2\2\2"+
		"\u0094\u0095\3\2\2\2\u0094\u0092\3\2\2\2\u0095\u0097\3\2\2\2\u0096\u0094"+
		"\3\2\2\2\u0097\u0098\7,\2\2\u0098\u0099\7\61\2\2\u0099\b\3\2\2\2\u009a"+
		"\u009e\7$\2\2\u009b\u009d\13\2\2\2\u009c\u009b\3\2\2\2\u009d\u00a0\3\2"+
		"\2\2\u009e\u009f\3\2\2\2\u009e\u009c\3\2\2\2\u009f\u00a1\3\2\2\2\u00a0"+
		"\u009e\3\2\2\2\u00a1\u00a2\7$\2\2\u00a2\n\3\2\2\2\u00a3\u00a4\7k\2\2\u00a4"+
		"\u00a5\7p\2\2\u00a5\u00a6\7v\2\2\u00a6\f\3\2\2\2\u00a7\u00a8\7d\2\2\u00a8"+
		"\u00a9\7q\2\2\u00a9\u00aa\7q\2\2\u00aa\u00ab\7n\2\2\u00ab\16\3\2\2\2\u00ac"+
		"\u00ad\7u\2\2\u00ad\u00ae\7v\2\2\u00ae\u00af\7t\2\2\u00af\u00b0\7k\2\2"+
		"\u00b0\u00b1\7p\2\2\u00b1\u00b2\7i\2\2\u00b2\20\3\2\2\2\u00b3\u00b4\7"+
		"x\2\2\u00b4\u00b5\7q\2\2\u00b5\u00b6\7k\2\2\u00b6\u00b7\7f\2\2\u00b7\22"+
		"\3\2\2\2\u00b8\u00b9\7v\2\2\u00b9\u00ba\7t\2\2\u00ba\u00bb\7w\2\2\u00bb"+
		"\u00bc\7g\2\2\u00bc\24\3\2\2\2\u00bd\u00be\7h\2\2\u00be\u00bf\7c\2\2\u00bf"+
		"\u00c0\7n\2\2\u00c0\u00c1\7u\2\2\u00c1\u00c2\7g\2\2\u00c2\26\3\2\2\2\u00c3"+
		"\u00c4\7p\2\2\u00c4\u00c5\7w\2\2\u00c5\u00c6\7n\2\2\u00c6\u00c7\7n\2\2"+
		"\u00c7\30\3\2\2\2\u00c8\u00c9\7p\2\2\u00c9\u00ca\7g\2\2\u00ca\u00cb\7"+
		"y\2\2\u00cb\32\3\2\2\2\u00cc\u00cd\7e\2\2\u00cd\u00ce\7n\2\2\u00ce\u00cf"+
		"\7c\2\2\u00cf\u00d0\7u\2\2\u00d0\u00d1\7u\2\2\u00d1\34\3\2\2\2\u00d2\u00d3"+
		"\7t\2\2\u00d3\u00d4\7g\2\2\u00d4\u00d5\7v\2\2\u00d5\u00d6\7w\2\2\u00d6"+
		"\u00d7\7t\2\2\u00d7\u00d8\7p\2\2\u00d8\36\3\2\2\2\u00d9\u00da\7e\2\2\u00da"+
		"\u00db\7q\2\2\u00db\u00dc\7p\2\2\u00dc\u00dd\7v\2\2\u00dd\u00de\7k\2\2"+
		"\u00de\u00df\7p\2\2\u00df\u00e0\7w\2\2\u00e0\u00e1\7g\2\2\u00e1 \3\2\2"+
		"\2\u00e2\u00e3\7d\2\2\u00e3\u00e4\7t\2\2\u00e4\u00e5\7g\2\2\u00e5\u00e6"+
		"\7c\2\2\u00e6\u00e7\7m\2\2\u00e7\"\3\2\2\2\u00e8\u00e9\7k\2\2\u00e9\u00ea"+
		"\7h\2\2\u00ea$\3\2\2\2\u00eb\u00ec\7g\2\2\u00ec\u00ed\7n\2\2\u00ed\u00ee"+
		"\7u\2\2\u00ee\u00ef\7g\2\2\u00ef&\3\2\2\2\u00f0\u00f1\7y\2\2\u00f1\u00f2"+
		"\7j\2\2\u00f2\u00f3\7k\2\2\u00f3\u00f4\7n\2\2\u00f4\u00f5\7g\2\2\u00f5"+
		"(\3\2\2\2\u00f6\u00f7\7h\2\2\u00f7\u00f8\7q\2\2\u00f8\u00f9\7t\2\2\u00f9"+
		"*\3\2\2\2\u00fa\u00fb\7v\2\2\u00fb\u00fc\7j\2\2\u00fc\u00fd\7k\2\2\u00fd"+
		"\u00fe\7u\2\2\u00fe,\3\2\2\2\u00ff\u0100\7#\2\2\u0100.\3\2\2\2\u0101\u0102"+
		"\7\u0080\2\2\u0102\60\3\2\2\2\u0103\u0104\7-\2\2\u0104\62\3\2\2\2\u0105"+
		"\u0106\7/\2\2\u0106\64\3\2\2\2\u0107\u0108\7,\2\2\u0108\66\3\2\2\2\u0109"+
		"\u010a\7\61\2\2\u010a8\3\2\2\2\u010b\u010c\7\'\2\2\u010c:\3\2\2\2\u010d"+
		"\u010e\7\60\2\2\u010e<\3\2\2\2\u010f\u0110\7(\2\2\u0110>\3\2\2\2\u0111"+
		"\u0112\7~\2\2\u0112@\3\2\2\2\u0113\u0114\7`\2\2\u0114B\3\2\2\2\u0115\u0116"+
		"\7(\2\2\u0116\u0117\7(\2\2\u0117D\3\2\2\2\u0118\u0119\7~\2\2\u0119\u011a"+
		"\7~\2\2\u011aF\3\2\2\2\u011b\u011c\7?\2\2\u011c\u011d\7?\2\2\u011dH\3"+
		"\2\2\2\u011e\u011f\7#\2\2\u011f\u0120\7?\2\2\u0120J\3\2\2\2\u0121\u0122"+
		"\7>\2\2\u0122L\3\2\2\2\u0123\u0124\7@\2\2\u0124N\3\2\2\2\u0125\u0126\7"+
		">\2\2\u0126\u0127\7?\2\2\u0127P\3\2\2\2\u0128\u0129\7@\2\2\u0129\u012a"+
		"\7?\2\2\u012aR\3\2\2\2\u012b\u012c\7?\2\2\u012cT\3\2\2\2\u012d\u012e\7"+
		"-\2\2\u012e\u012f\7-\2\2\u012fV\3\2\2\2\u0130\u0131\7/\2\2\u0131\u0132"+
		"\7/\2\2\u0132X\3\2\2\2\u0133\u0134\7>\2\2\u0134\u0135\7>\2\2\u0135Z\3"+
		"\2\2\2\u0136\u0137\7@\2\2\u0137\u0138\7@\2\2\u0138\\\3\2\2\2\u0139\u013a"+
		"\7*\2\2\u013a^\3\2\2\2\u013b\u013c\7+\2\2\u013c`\3\2\2\2\u013d\u013e\7"+
		"]\2\2\u013eb\3\2\2\2\u013f\u0140\7_\2\2\u0140d\3\2\2\2\u0141\u0142\7}"+
		"\2\2\u0142f\3\2\2\2\u0143\u0144\7\177\2\2\u0144h\3\2\2\2\u0145\u0146\7"+
		".\2\2\u0146j\3\2\2\2\u0147\u0148\7=\2\2\u0148l\3\2\2\2\u0149\u014a\7A"+
		"\2\2\u014an\3\2\2\2\u014b\u014c\7<\2\2\u014cp\3\2\2\2\u014d\u014e\t\2"+
		"\2\2\u014er\3\2\2\2\u014f\u0151\5q9\2\u0150\u014f\3\2\2\2\u0151\u0152"+
		"\3\2\2\2\u0152\u0150\3\2\2\2\u0152\u0153\3\2\2\2\u0153t\3\2\2\2\u0154"+
		"\u0155\t\3\2\2\u0155\u0156\3\2\2\2\u0156\u0157\b;\2\2\u0157v\3\2\2\2\u0158"+
		"\u0159\t\4\2\2\u0159\u015a\3\2\2\2\u015a\u015b\b<\2\2\u015bx\3\2\2\2\u015c"+
		"\u0160\t\5\2\2\u015d\u015f\t\6\2\2\u015e\u015d\3\2\2\2\u015f\u0162\3\2"+
		"\2\2\u0160\u015e\3\2\2\2\u0160\u0161\3\2\2\2\u0161z\3\2\2\2\u0162\u0160"+
		"\3\2\2\2\n\2}\u0087\u008c\u0094\u009e\u0152\u0160\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}