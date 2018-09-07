// Generated from grammer.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class grammerLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NAME=1, TYPE=2, FUNCCALL2=3, WS=4, OP=5, CP=6, TO=7, COMMA=8, DONOGHTE=9, 
		EQUAL=10, DOT=11, ADAD=12, S1=13, S3=14, S4=15, S5=16, S6=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"NAME", "TYPE", "FUNCCALL2", "WS", "OP", "CP", "TO", "COMMA", "DONOGHTE", 
		"EQUAL", "DOT", "ADAD", "S1", "S3", "S4", "S5", "S6"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, "'('", "')'", "' ->'", "','", "'::'", "'='", 
		"'.'", null, null, null, "'mkdate'", "'one()'", "'give'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "NAME", "TYPE", "FUNCCALL2", "WS", "OP", "CP", "TO", "COMMA", "DONOGHTE", 
		"EQUAL", "DOT", "ADAD", "S1", "S3", "S4", "S5", "S6"
	};
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


	public grammerLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "grammer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23\u00ad\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\3\2\6\2\'\n\2\r\2\16\2(\3\2\7\2,\n\2\f\2\16\2/\13\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\5\3F\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4d\n\4\3\5\6"+
		"\5g\n\5\r\5\16\5h\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\n"+
		"\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\2\2\23\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23\3\2\7\3\2c|\5\2\62;c|~~\5\2\13\f\17\17\"\"\3\2\62;\5\2,-//\61\61"+
		"\2\u00b7\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\3&\3\2\2\2\5E\3\2\2\2\7c\3\2\2\2\tf\3\2\2\2\13l\3\2\2\2"+
		"\rn\3\2\2\2\17p\3\2\2\2\21t\3\2\2\2\23v\3\2\2\2\25y\3\2\2\2\27{\3\2\2"+
		"\2\31}\3\2\2\2\33\177\3\2\2\2\35\u0099\3\2\2\2\37\u009b\3\2\2\2!\u00a2"+
		"\3\2\2\2#\u00a8\3\2\2\2%\'\t\2\2\2&%\3\2\2\2\'(\3\2\2\2(&\3\2\2\2()\3"+
		"\2\2\2)-\3\2\2\2*,\t\3\2\2+*\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\4"+
		"\3\2\2\2/-\3\2\2\2\60\61\7K\2\2\61\62\7p\2\2\62F\7v\2\2\63\64\7F\2\2\64"+
		"\65\7q\2\2\65\66\7w\2\2\66\67\7d\2\2\678\7n\2\28F\7g\2\29:\7F\2\2:;\7"+
		"c\2\2;<\7v\2\2<F\7g\2\2=>\7E\2\2>?\7q\2\2?@\7p\2\2@A\7v\2\2AB\7t\2\2B"+
		"C\7c\2\2CD\7e\2\2DF\7v\2\2E\60\3\2\2\2E\63\3\2\2\2E9\3\2\2\2E=\3\2\2\2"+
		"F\6\3\2\2\2GH\7c\2\2HI\7p\2\2Id\7f\2\2JK\7q\2\2Kd\7t\2\2LM\7v\2\2MN\7"+
		"j\2\2NO\7g\2\2Od\7p\2\2PQ\7u\2\2QR\7e\2\2RS\7c\2\2ST\7n\2\2TU\7g\2\2U"+
		"d\7Z\2\2VW\7u\2\2WX\7e\2\2XY\7c\2\2YZ\7n\2\2Zd\7g\2\2[\\\7v\2\2\\]\7t"+
		"\2\2]^\7w\2\2^_\7p\2\2_`\7e\2\2`a\7c\2\2ab\7v\2\2bd\7g\2\2cG\3\2\2\2c"+
		"J\3\2\2\2cL\3\2\2\2cP\3\2\2\2cV\3\2\2\2c[\3\2\2\2d\b\3\2\2\2eg\t\4\2\2"+
		"fe\3\2\2\2gh\3\2\2\2hf\3\2\2\2hi\3\2\2\2ij\3\2\2\2jk\b\5\2\2k\n\3\2\2"+
		"\2lm\7*\2\2m\f\3\2\2\2no\7+\2\2o\16\3\2\2\2pq\7\"\2\2qr\7/\2\2rs\7@\2"+
		"\2s\20\3\2\2\2tu\7.\2\2u\22\3\2\2\2vw\7<\2\2wx\7<\2\2x\24\3\2\2\2yz\7"+
		"?\2\2z\26\3\2\2\2{|\7\60\2\2|\30\3\2\2\2}~\t\5\2\2~\32\3\2\2\2\177\u0080"+
		"\7V\2\2\u0080\u0081\7k\2\2\u0081\u0082\7o\2\2\u0082\u0083\7g\2\2\u0083"+
		"\u0084\7H\2\2\u0084\u0085\7w\2\2\u0085\u0086\7p\2\2\u0086\u0087\7e\2\2"+
		"\u0087\u0088\3\2\2\2\u0088\u0089\7*\2\2\u0089\u008a\7F\2\2\u008a\u008b"+
		"\7c\2\2\u008b\u008c\7v\2\2\u008c\u008d\7g\2\2\u008d\u008e\3\2\2\2\u008e"+
		"\u008f\7+\2\2\u008f\u0090\7/\2\2\u0090\u0091\7@\2\2\u0091\u0092\3\2\2"+
		"\2\u0092\u0093\7F\2\2\u0093\u0094\7q\2\2\u0094\u0095\7w\2\2\u0095\u0096"+
		"\7d\2\2\u0096\u0097\7n\2\2\u0097\u0098\7g\2\2\u0098\34\3\2\2\2\u0099\u009a"+
		"\t\6\2\2\u009a\36\3\2\2\2\u009b\u009c\7o\2\2\u009c\u009d\7m\2\2\u009d"+
		"\u009e\7f\2\2\u009e\u009f\7c\2\2\u009f\u00a0\7v\2\2\u00a0\u00a1\7g\2\2"+
		"\u00a1 \3\2\2\2\u00a2\u00a3\7q\2\2\u00a3\u00a4\7p\2\2\u00a4\u00a5\7g\2"+
		"\2\u00a5\u00a6\7*\2\2\u00a6\u00a7\7+\2\2\u00a7\"\3\2\2\2\u00a8\u00a9\7"+
		"i\2\2\u00a9\u00aa\7k\2\2\u00aa\u00ab\7x\2\2\u00ab\u00ac\7g\2\2\u00ac$"+
		"\3\2\2\2\b\2(-Ech\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}