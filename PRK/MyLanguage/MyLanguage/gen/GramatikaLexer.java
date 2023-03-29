// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class GramatikaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, MULT=4, PLUS=5, XOR=6, BINARY=7, INT=8, NONZERODIGIT=9, 
		DIGIT=10, BOOL=11, TRUE=12, FALSE=13, WHITESPACE=14;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "MULT", "PLUS", "XOR", "BINARY", "INT", "NONZERODIGIT", 
			"DIGIT", "BOOL", "TRUE", "FALSE", "WHITESPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Convert('", "','", "')'", "'*'", "'+'", "'^'", null, null, null, 
			null, null, "'true'", "'false'", "' '"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "MULT", "PLUS", "XOR", "BINARY", "INT", "NONZERODIGIT", 
			"DIGIT", "BOOL", "TRUE", "FALSE", "WHITESPACE"
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


	public GramatikaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Gramatika.g4"; }

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
		"\u0004\u0000\u000eW\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0004\u00063\b\u0006\u000b\u0006\f\u00064\u0001\u0007\u0001\u0007"+
		"\u0005\u00079\b\u0007\n\u0007\f\u0007<\t\u0007\u0001\u0007\u0003\u0007"+
		"?\b\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0003\nG\b\n"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0000"+
		"\u0000\u000e\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b"+
		"\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b"+
		"\u000e\u0001\u0000\u0001\u0001\u000001Z\u0000\u0001\u0001\u0000\u0000"+
		"\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000"+
		"\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000"+
		"\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000"+
		"\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000"+
		"\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000"+
		"\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000"+
		"\u001b\u0001\u0000\u0000\u0000\u0001\u001d\u0001\u0000\u0000\u0000\u0003"+
		"&\u0001\u0000\u0000\u0000\u0005(\u0001\u0000\u0000\u0000\u0007*\u0001"+
		"\u0000\u0000\u0000\t,\u0001\u0000\u0000\u0000\u000b.\u0001\u0000\u0000"+
		"\u0000\r0\u0001\u0000\u0000\u0000\u000f>\u0001\u0000\u0000\u0000\u0011"+
		"@\u0001\u0000\u0000\u0000\u0013B\u0001\u0000\u0000\u0000\u0015F\u0001"+
		"\u0000\u0000\u0000\u0017H\u0001\u0000\u0000\u0000\u0019M\u0001\u0000\u0000"+
		"\u0000\u001bS\u0001\u0000\u0000\u0000\u001d\u001e\u0005C\u0000\u0000\u001e"+
		"\u001f\u0005o\u0000\u0000\u001f \u0005n\u0000\u0000 !\u0005v\u0000\u0000"+
		"!\"\u0005e\u0000\u0000\"#\u0005r\u0000\u0000#$\u0005t\u0000\u0000$%\u0005"+
		"(\u0000\u0000%\u0002\u0001\u0000\u0000\u0000&\'\u0005,\u0000\u0000\'\u0004"+
		"\u0001\u0000\u0000\u0000()\u0005)\u0000\u0000)\u0006\u0001\u0000\u0000"+
		"\u0000*+\u0005*\u0000\u0000+\b\u0001\u0000\u0000\u0000,-\u0005+\u0000"+
		"\u0000-\n\u0001\u0000\u0000\u0000./\u0005^\u0000\u0000/\f\u0001\u0000"+
		"\u0000\u000002\u0005b\u0000\u000013\u0007\u0000\u0000\u000021\u0001\u0000"+
		"\u0000\u000034\u0001\u0000\u0000\u000042\u0001\u0000\u0000\u000045\u0001"+
		"\u0000\u0000\u00005\u000e\u0001\u0000\u0000\u00006:\u0003\u0011\b\u0000"+
		"79\u0003\u0013\t\u000087\u0001\u0000\u0000\u00009<\u0001\u0000\u0000\u0000"+
		":8\u0001\u0000\u0000\u0000:;\u0001\u0000\u0000\u0000;?\u0001\u0000\u0000"+
		"\u0000<:\u0001\u0000\u0000\u0000=?\u0003\u0013\t\u0000>6\u0001\u0000\u0000"+
		"\u0000>=\u0001\u0000\u0000\u0000?\u0010\u0001\u0000\u0000\u0000@A\u0002"+
		"19\u0000A\u0012\u0001\u0000\u0000\u0000BC\u000209\u0000C\u0014\u0001\u0000"+
		"\u0000\u0000DG\u0003\u0017\u000b\u0000EG\u0003\u0019\f\u0000FD\u0001\u0000"+
		"\u0000\u0000FE\u0001\u0000\u0000\u0000G\u0016\u0001\u0000\u0000\u0000"+
		"HI\u0005t\u0000\u0000IJ\u0005r\u0000\u0000JK\u0005u\u0000\u0000KL\u0005"+
		"e\u0000\u0000L\u0018\u0001\u0000\u0000\u0000MN\u0005f\u0000\u0000NO\u0005"+
		"a\u0000\u0000OP\u0005l\u0000\u0000PQ\u0005s\u0000\u0000QR\u0005e\u0000"+
		"\u0000R\u001a\u0001\u0000\u0000\u0000ST\u0005 \u0000\u0000TU\u0001\u0000"+
		"\u0000\u0000UV\u0006\r\u0000\u0000V\u001c\u0001\u0000\u0000\u0000\u0005"+
		"\u00004:>F\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}