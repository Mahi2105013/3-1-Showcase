// Generated from c:/Users/tasni/Downloads/2105013 (7)/2105013/java/C2105013Lexer.g4 by ANTLR 4.13.1

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class C2105013Lexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LINE_COMMENT=1, BLOCK_COMMENT=2, STRING=3, WS=4, IF=5, ELSE=6, FOR=7, 
		WHILE=8, PRINTLN=9, PRINTF=10, RETURN=11, INT=12, FLOAT=13, VOID=14, LPAREN=15, 
		RPAREN=16, LCURL=17, RCURL=18, LTHIRD=19, RTHIRD=20, SEMICOLON=21, COMMA=22, 
		ADDOP=23, SUBOP=24, MULOP=25, INCOP=26, DECOP=27, NOT=28, RELOP=29, LOGICOP=30, 
		ASSIGNOP=31, ID=32, CONST_INT=33, CONST_FLOAT=34, UNRECOGNIZED=35;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"LINE_COMMENT", "BLOCK_COMMENT", "STRING", "WS", "IF", "ELSE", "FOR", 
			"WHILE", "PRINTLN", "PRINTF", "RETURN", "INT", "FLOAT", "VOID", "LPAREN", 
			"RPAREN", "LCURL", "RCURL", "LTHIRD", "RTHIRD", "SEMICOLON", "COMMA", 
			"ADDOP", "SUBOP", "MULOP", "INCOP", "DECOP", "NOT", "RELOP", "LOGICOP", 
			"ASSIGNOP", "ID", "CONST_INT", "CONST_FLOAT", "UNRECOGNIZED"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "'if'", "'else'", "'for'", "'while'", "'println'", 
			"'printf'", "'return'", "'int'", "'float'", "'void'", "'('", "')'", "'{'", 
			"'}'", "'['", "']'", "';'", "','", null, null, null, "'++'", "'--'", 
			"'!'", null, null, "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LINE_COMMENT", "BLOCK_COMMENT", "STRING", "WS", "IF", "ELSE", 
			"FOR", "WHILE", "PRINTLN", "PRINTF", "RETURN", "INT", "FLOAT", "VOID", 
			"LPAREN", "RPAREN", "LCURL", "RCURL", "LTHIRD", "RTHIRD", "SEMICOLON", 
			"COMMA", "ADDOP", "SUBOP", "MULOP", "INCOP", "DECOP", "NOT", "RELOP", 
			"LOGICOP", "ASSIGNOP", "ID", "CONST_INT", "CONST_FLOAT", "UNRECOGNIZED"
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


	    void writeIntoLexLogFile(String message) {
	        try {
	            if (Main.lexLogFile != null) {
	                Main.lexLogFile.write(message + "\n");
	                Main.lexLogFile.flush();
	            }
	        } catch (IOException e) {
	            System.err.println("Lexer log error: " + e.getMessage());
	        }
	    }

	    void writeIntoParserLogFile(String message) {
	        try {
	            Main.parserLogFile.write(message);
	            Main.parserLogFile.newLine();
	            Main.parserLogFile.flush();
	        } catch (IOException e) {
	            System.err.println("Parser log error: " + e.getMessage());
	        }
	    }

	    void writeIntoErrorFile(String message) {
	        try {
	            writeIntoParserLogFile(message);
	            Main.errorFile.write(message);
	            Main.errorFile.newLine();
	            Main.errorFile.flush();
	        } catch (IOException e) {
	            System.err.println("Error file write error: " + e.getMessage());
	        }
	    }


	public C2105013Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "C2105013Lexer.g4"; }

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

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 0:
			LINE_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 1:
			BLOCK_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 2:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 4:
			IF_action((RuleContext)_localctx, actionIndex);
			break;
		case 5:
			ELSE_action((RuleContext)_localctx, actionIndex);
			break;
		case 6:
			FOR_action((RuleContext)_localctx, actionIndex);
			break;
		case 7:
			WHILE_action((RuleContext)_localctx, actionIndex);
			break;
		case 8:
			PRINTLN_action((RuleContext)_localctx, actionIndex);
			break;
		case 9:
			PRINTF_action((RuleContext)_localctx, actionIndex);
			break;
		case 10:
			RETURN_action((RuleContext)_localctx, actionIndex);
			break;
		case 11:
			INT_action((RuleContext)_localctx, actionIndex);
			break;
		case 12:
			FLOAT_action((RuleContext)_localctx, actionIndex);
			break;
		case 13:
			VOID_action((RuleContext)_localctx, actionIndex);
			break;
		case 14:
			LPAREN_action((RuleContext)_localctx, actionIndex);
			break;
		case 15:
			RPAREN_action((RuleContext)_localctx, actionIndex);
			break;
		case 16:
			LCURL_action((RuleContext)_localctx, actionIndex);
			break;
		case 17:
			RCURL_action((RuleContext)_localctx, actionIndex);
			break;
		case 18:
			LTHIRD_action((RuleContext)_localctx, actionIndex);
			break;
		case 19:
			RTHIRD_action((RuleContext)_localctx, actionIndex);
			break;
		case 20:
			SEMICOLON_action((RuleContext)_localctx, actionIndex);
			break;
		case 21:
			COMMA_action((RuleContext)_localctx, actionIndex);
			break;
		case 22:
			ADDOP_action((RuleContext)_localctx, actionIndex);
			break;
		case 23:
			SUBOP_action((RuleContext)_localctx, actionIndex);
			break;
		case 24:
			MULOP_action((RuleContext)_localctx, actionIndex);
			break;
		case 25:
			INCOP_action((RuleContext)_localctx, actionIndex);
			break;
		case 26:
			DECOP_action((RuleContext)_localctx, actionIndex);
			break;
		case 27:
			NOT_action((RuleContext)_localctx, actionIndex);
			break;
		case 28:
			RELOP_action((RuleContext)_localctx, actionIndex);
			break;
		case 29:
			LOGICOP_action((RuleContext)_localctx, actionIndex);
			break;
		case 30:
			ASSIGNOP_action((RuleContext)_localctx, actionIndex);
			break;
		case 31:
			ID_action((RuleContext)_localctx, actionIndex);
			break;
		case 32:
			CONST_INT_action((RuleContext)_localctx, actionIndex);
			break;
		case 33:
			CONST_FLOAT_action((RuleContext)_localctx, actionIndex);
			break;
		case 34:
			UNRECOGNIZED_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void LINE_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

			        writeIntoLexLogFile("Line# " + getLine() + ": Token <SINGLE LINE COMMENT> Lexeme " + getText());
			    
			break;
		}
	}
	private void BLOCK_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:

			        writeIntoLexLogFile("Line# " + getLine() + ": Token <MULTI LINE COMMENT> Lexeme " + getText());
			    
			break;
		}
	}
	private void STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:

			        writeIntoLexLogFile("Line# " + getLine() + ": Token <STRING> Lexeme " + getText());
			    
			break;
		}
	}
	private void IF_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <IF> Lexeme " + getText()); 
			break;
		}
	}
	private void ELSE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <ELSE> Lexeme " + getText()); 
			break;
		}
	}
	private void FOR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <FOR> Lexeme " + getText()); 
			break;
		}
	}
	private void WHILE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <WHILE> Lexeme " + getText()); 
			break;
		}
	}
	private void PRINTLN_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <PRINTLN> Lexeme " + getText()); 
			break;
		}
	}
	private void PRINTF_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 8:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <PRINTF> Lexeme " + getText()); 
			break;
		}
	}
	private void RETURN_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 9:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <RETURN> Lexeme " + getText()); 
			break;
		}
	}
	private void INT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 10:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <INT> Lexeme " + getText()); 
			break;
		}
	}
	private void FLOAT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 11:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <FLOAT> Lexeme " + getText()); 
			break;
		}
	}
	private void VOID_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 12:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <VOID> Lexeme " + getText()); 
			break;
		}
	}
	private void LPAREN_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 13:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <LPAREN> Lexeme " + getText()); 
			break;
		}
	}
	private void RPAREN_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 14:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <RPAREN> Lexeme " + getText()); 
			break;
		}
	}
	private void LCURL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 15:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <LCURL> Lexeme " + getText()); 
			break;
		}
	}
	private void RCURL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 16:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <RCURL> Lexeme " + getText()); 
			break;
		}
	}
	private void LTHIRD_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 17:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <LTHIRD> Lexeme " + getText()); 
			break;
		}
	}
	private void RTHIRD_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 18:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <RTHIRD> Lexeme " + getText()); 
			break;
		}
	}
	private void SEMICOLON_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 19:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <SEMICOLON> Lexeme " + getText()); 
			break;
		}
	}
	private void COMMA_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 20:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <COMMA> Lexeme " + getText()); 
			break;
		}
	}
	private void ADDOP_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 21:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <ADDOP> Lexeme " + getText()); 
			break;
		}
	}
	private void SUBOP_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 22:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <SUBOP> Lexeme " + getText()); 
			break;
		}
	}
	private void MULOP_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 23:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <MULOP> Lexeme " + getText()); 
			break;
		}
	}
	private void INCOP_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 24:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <INCOP> Lexeme " + getText()); 
			break;
		}
	}
	private void DECOP_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 25:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <DECOP> Lexeme " + getText()); 
			break;
		}
	}
	private void NOT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 26:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <NOT> Lexeme " + getText()); 
			break;
		}
	}
	private void RELOP_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 27:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <RELOP> Lexeme " + getText());
			break;
		}
	}
	private void LOGICOP_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 28:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <LOGICOP> Lexeme " + getText());
			break;
		}
	}
	private void ASSIGNOP_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 29:
			 writeIntoLexLogFile("Line# " + getLine() + ": Token <ASSIGNOP> Lexeme " + getText()); 
			break;
		}
	}
	private void ID_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 30:

			    writeIntoLexLogFile("Line# " + getLine() + ": Token <ID> Lexeme " + getText());

			break;
		}
	}
	private void CONST_INT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 31:

			    writeIntoLexLogFile("Line# " + getLine() + ": Token <CONST_INT> Lexeme " + getText());

			break;
		}
	}
	private void CONST_FLOAT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 32:

			        writeIntoLexLogFile("Line# " + getLine() + ": Token <CONST_FLOAT> Lexeme " + getText());
			    
			break;
		case 33:

			        writeIntoLexLogFile("Line# " + getLine() + ": Token <CONST_FLOAT> Lexeme " + getText());
			    
			break;
		case 34:

			        writeIntoLexLogFile("Line# " + getLine() + ": Token <CONST_FLOAT> Lexeme " + getText());
			    
			break;
		}
	}
	private void UNRECOGNIZED_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 35:
			 
			        writeIntoLexLogFile("Unrecognized character '" + getText() + "' at line " + getLine() + ":" + getCharPositionInLine());
			        writeIntoErrorFile("Error at line " + getLine() + ": Unrecognized character " + getText());
			    
			break;
		}
	}

	public static final String _serializedATN =
		"\u0004\u0000#\u0149\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0005"+
		"\u0000L\b\u0000\n\u0000\f\u0000O\t\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005"+
		"\u0001Y\b\u0001\n\u0001\f\u0001\\\t\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0005\u0002i\b\u0002\n\u0002\f\u0002l\t"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0004\u0003t\b\u0003\u000b\u0003\f\u0003u\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u00fe\b\u001c"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0003\u001d\u0106\b\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001f"+
		"\u0001\u001f\u0005\u001f\u010d\b\u001f\n\u001f\f\u001f\u0110\t\u001f\u0001"+
		"\u001f\u0001\u001f\u0001 \u0004 \u0115\b \u000b \f \u0116\u0001 \u0001"+
		" \u0001!\u0004!\u011c\b!\u000b!\f!\u011d\u0001!\u0001!\u0005!\u0122\b"+
		"!\n!\f!\u0125\t!\u0003!\u0127\b!\u0001!\u0001!\u0003!\u012b\b!\u0001!"+
		"\u0004!\u012e\b!\u000b!\f!\u012f\u0003!\u0132\b!\u0001!\u0001!\u0001!"+
		"\u0004!\u0137\b!\u000b!\f!\u0138\u0001!\u0001!\u0004!\u013d\b!\u000b!"+
		"\f!\u013e\u0001!\u0001!\u0003!\u0143\b!\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001Z\u0000#\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004"+
		"\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017"+
		"\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'"+
		"\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u001d"+
		";\u001e=\u001f? A!C\"E#\u0001\u0000\n\u0002\u0000\n\n\r\r\u0004\u0000"+
		"\n\n\r\r\"\"\\\\\u0003\u0000\t\n\f\r  \u0002\u0000++--\u0003\u0000%%*"+
		"*//\u0002\u0000<<>>\u0003\u0000AZ__az\u0004\u000009AZ__az\u0001\u0000"+
		"09\u0002\u0000EEee\u015e\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003"+
		"\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007"+
		"\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001"+
		"\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000"+
		"\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000"+
		"\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000"+
		"\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000"+
		"\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000"+
		"\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000"+
		"\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000"+
		")\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001"+
		"\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000"+
		"\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u0000"+
		"7\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000;\u0001"+
		"\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?\u0001\u0000\u0000"+
		"\u0000\u0000A\u0001\u0000\u0000\u0000\u0000C\u0001\u0000\u0000\u0000\u0000"+
		"E\u0001\u0000\u0000\u0000\u0001G\u0001\u0000\u0000\u0000\u0003T\u0001"+
		"\u0000\u0000\u0000\u0005d\u0001\u0000\u0000\u0000\u0007s\u0001\u0000\u0000"+
		"\u0000\ty\u0001\u0000\u0000\u0000\u000b~\u0001\u0000\u0000\u0000\r\u0085"+
		"\u0001\u0000\u0000\u0000\u000f\u008b\u0001\u0000\u0000\u0000\u0011\u0093"+
		"\u0001\u0000\u0000\u0000\u0013\u009d\u0001\u0000\u0000\u0000\u0015\u00a6"+
		"\u0001\u0000\u0000\u0000\u0017\u00af\u0001\u0000\u0000\u0000\u0019\u00b5"+
		"\u0001\u0000\u0000\u0000\u001b\u00bd\u0001\u0000\u0000\u0000\u001d\u00c4"+
		"\u0001\u0000\u0000\u0000\u001f\u00c7\u0001\u0000\u0000\u0000!\u00ca\u0001"+
		"\u0000\u0000\u0000#\u00cd\u0001\u0000\u0000\u0000%\u00d0\u0001\u0000\u0000"+
		"\u0000\'\u00d3\u0001\u0000\u0000\u0000)\u00d6\u0001\u0000\u0000\u0000"+
		"+\u00d9\u0001\u0000\u0000\u0000-\u00dc\u0001\u0000\u0000\u0000/\u00df"+
		"\u0001\u0000\u0000\u00001\u00e2\u0001\u0000\u0000\u00003\u00e5\u0001\u0000"+
		"\u0000\u00005\u00ea\u0001\u0000\u0000\u00007\u00ef\u0001\u0000\u0000\u0000"+
		"9\u00fd\u0001\u0000\u0000\u0000;\u0105\u0001\u0000\u0000\u0000=\u0107"+
		"\u0001\u0000\u0000\u0000?\u010a\u0001\u0000\u0000\u0000A\u0114\u0001\u0000"+
		"\u0000\u0000C\u0142\u0001\u0000\u0000\u0000E\u0144\u0001\u0000\u0000\u0000"+
		"GH\u0005/\u0000\u0000HI\u0005/\u0000\u0000IM\u0001\u0000\u0000\u0000J"+
		"L\b\u0000\u0000\u0000KJ\u0001\u0000\u0000\u0000LO\u0001\u0000\u0000\u0000"+
		"MK\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000NP\u0001\u0000\u0000"+
		"\u0000OM\u0001\u0000\u0000\u0000PQ\u0006\u0000\u0000\u0000QR\u0001\u0000"+
		"\u0000\u0000RS\u0006\u0000\u0001\u0000S\u0002\u0001\u0000\u0000\u0000"+
		"TU\u0005/\u0000\u0000UV\u0005*\u0000\u0000VZ\u0001\u0000\u0000\u0000W"+
		"Y\t\u0000\u0000\u0000XW\u0001\u0000\u0000\u0000Y\\\u0001\u0000\u0000\u0000"+
		"Z[\u0001\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000[]\u0001\u0000\u0000"+
		"\u0000\\Z\u0001\u0000\u0000\u0000]^\u0005*\u0000\u0000^_\u0005/\u0000"+
		"\u0000_`\u0001\u0000\u0000\u0000`a\u0006\u0001\u0002\u0000ab\u0001\u0000"+
		"\u0000\u0000bc\u0006\u0001\u0001\u0000c\u0004\u0001\u0000\u0000\u0000"+
		"dj\u0005\"\u0000\u0000ef\u0005\\\u0000\u0000fi\t\u0000\u0000\u0000gi\b"+
		"\u0001\u0000\u0000he\u0001\u0000\u0000\u0000hg\u0001\u0000\u0000\u0000"+
		"il\u0001\u0000\u0000\u0000jh\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000"+
		"\u0000km\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000\u0000mn\u0005\"\u0000"+
		"\u0000no\u0006\u0002\u0003\u0000op\u0001\u0000\u0000\u0000pq\u0006\u0002"+
		"\u0001\u0000q\u0006\u0001\u0000\u0000\u0000rt\u0007\u0002\u0000\u0000"+
		"sr\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000us\u0001\u0000\u0000"+
		"\u0000uv\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000wx\u0006\u0003"+
		"\u0001\u0000x\b\u0001\u0000\u0000\u0000yz\u0005i\u0000\u0000z{\u0005f"+
		"\u0000\u0000{|\u0001\u0000\u0000\u0000|}\u0006\u0004\u0004\u0000}\n\u0001"+
		"\u0000\u0000\u0000~\u007f\u0005e\u0000\u0000\u007f\u0080\u0005l\u0000"+
		"\u0000\u0080\u0081\u0005s\u0000\u0000\u0081\u0082\u0005e\u0000\u0000\u0082"+
		"\u0083\u0001\u0000\u0000\u0000\u0083\u0084\u0006\u0005\u0005\u0000\u0084"+
		"\f\u0001\u0000\u0000\u0000\u0085\u0086\u0005f\u0000\u0000\u0086\u0087"+
		"\u0005o\u0000\u0000\u0087\u0088\u0005r\u0000\u0000\u0088\u0089\u0001\u0000"+
		"\u0000\u0000\u0089\u008a\u0006\u0006\u0006\u0000\u008a\u000e\u0001\u0000"+
		"\u0000\u0000\u008b\u008c\u0005w\u0000\u0000\u008c\u008d\u0005h\u0000\u0000"+
		"\u008d\u008e\u0005i\u0000\u0000\u008e\u008f\u0005l\u0000\u0000\u008f\u0090"+
		"\u0005e\u0000\u0000\u0090\u0091\u0001\u0000\u0000\u0000\u0091\u0092\u0006"+
		"\u0007\u0007\u0000\u0092\u0010\u0001\u0000\u0000\u0000\u0093\u0094\u0005"+
		"p\u0000\u0000\u0094\u0095\u0005r\u0000\u0000\u0095\u0096\u0005i\u0000"+
		"\u0000\u0096\u0097\u0005n\u0000\u0000\u0097\u0098\u0005t\u0000\u0000\u0098"+
		"\u0099\u0005l\u0000\u0000\u0099\u009a\u0005n\u0000\u0000\u009a\u009b\u0001"+
		"\u0000\u0000\u0000\u009b\u009c\u0006\b\b\u0000\u009c\u0012\u0001\u0000"+
		"\u0000\u0000\u009d\u009e\u0005p\u0000\u0000\u009e\u009f\u0005r\u0000\u0000"+
		"\u009f\u00a0\u0005i\u0000\u0000\u00a0\u00a1\u0005n\u0000\u0000\u00a1\u00a2"+
		"\u0005t\u0000\u0000\u00a2\u00a3\u0005f\u0000\u0000\u00a3\u00a4\u0001\u0000"+
		"\u0000\u0000\u00a4\u00a5\u0006\t\t\u0000\u00a5\u0014\u0001\u0000\u0000"+
		"\u0000\u00a6\u00a7\u0005r\u0000\u0000\u00a7\u00a8\u0005e\u0000\u0000\u00a8"+
		"\u00a9\u0005t\u0000\u0000\u00a9\u00aa\u0005u\u0000\u0000\u00aa\u00ab\u0005"+
		"r\u0000\u0000\u00ab\u00ac\u0005n\u0000\u0000\u00ac\u00ad\u0001\u0000\u0000"+
		"\u0000\u00ad\u00ae\u0006\n\n\u0000\u00ae\u0016\u0001\u0000\u0000\u0000"+
		"\u00af\u00b0\u0005i\u0000\u0000\u00b0\u00b1\u0005n\u0000\u0000\u00b1\u00b2"+
		"\u0005t\u0000\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b4\u0006"+
		"\u000b\u000b\u0000\u00b4\u0018\u0001\u0000\u0000\u0000\u00b5\u00b6\u0005"+
		"f\u0000\u0000\u00b6\u00b7\u0005l\u0000\u0000\u00b7\u00b8\u0005o\u0000"+
		"\u0000\u00b8\u00b9\u0005a\u0000\u0000\u00b9\u00ba\u0005t\u0000\u0000\u00ba"+
		"\u00bb\u0001\u0000\u0000\u0000\u00bb\u00bc\u0006\f\f\u0000\u00bc\u001a"+
		"\u0001\u0000\u0000\u0000\u00bd\u00be\u0005v\u0000\u0000\u00be\u00bf\u0005"+
		"o\u0000\u0000\u00bf\u00c0\u0005i\u0000\u0000\u00c0\u00c1\u0005d\u0000"+
		"\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2\u00c3\u0006\r\r\u0000"+
		"\u00c3\u001c\u0001\u0000\u0000\u0000\u00c4\u00c5\u0005(\u0000\u0000\u00c5"+
		"\u00c6\u0006\u000e\u000e\u0000\u00c6\u001e\u0001\u0000\u0000\u0000\u00c7"+
		"\u00c8\u0005)\u0000\u0000\u00c8\u00c9\u0006\u000f\u000f\u0000\u00c9 \u0001"+
		"\u0000\u0000\u0000\u00ca\u00cb\u0005{\u0000\u0000\u00cb\u00cc\u0006\u0010"+
		"\u0010\u0000\u00cc\"\u0001\u0000\u0000\u0000\u00cd\u00ce\u0005}\u0000"+
		"\u0000\u00ce\u00cf\u0006\u0011\u0011\u0000\u00cf$\u0001\u0000\u0000\u0000"+
		"\u00d0\u00d1\u0005[\u0000\u0000\u00d1\u00d2\u0006\u0012\u0012\u0000\u00d2"+
		"&\u0001\u0000\u0000\u0000\u00d3\u00d4\u0005]\u0000\u0000\u00d4\u00d5\u0006"+
		"\u0013\u0013\u0000\u00d5(\u0001\u0000\u0000\u0000\u00d6\u00d7\u0005;\u0000"+
		"\u0000\u00d7\u00d8\u0006\u0014\u0014\u0000\u00d8*\u0001\u0000\u0000\u0000"+
		"\u00d9\u00da\u0005,\u0000\u0000\u00da\u00db\u0006\u0015\u0015\u0000\u00db"+
		",\u0001\u0000\u0000\u0000\u00dc\u00dd\u0007\u0003\u0000\u0000\u00dd\u00de"+
		"\u0006\u0016\u0016\u0000\u00de.\u0001\u0000\u0000\u0000\u00df\u00e0\u0007"+
		"\u0003\u0000\u0000\u00e0\u00e1\u0006\u0017\u0017\u0000\u00e10\u0001\u0000"+
		"\u0000\u0000\u00e2\u00e3\u0007\u0004\u0000\u0000\u00e3\u00e4\u0006\u0018"+
		"\u0018\u0000\u00e42\u0001\u0000\u0000\u0000\u00e5\u00e6\u0005+\u0000\u0000"+
		"\u00e6\u00e7\u0005+\u0000\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8"+
		"\u00e9\u0006\u0019\u0019\u0000\u00e94\u0001\u0000\u0000\u0000\u00ea\u00eb"+
		"\u0005-\u0000\u0000\u00eb\u00ec\u0005-\u0000\u0000\u00ec\u00ed\u0001\u0000"+
		"\u0000\u0000\u00ed\u00ee\u0006\u001a\u001a\u0000\u00ee6\u0001\u0000\u0000"+
		"\u0000\u00ef\u00f0\u0005!\u0000\u0000\u00f0\u00f1\u0006\u001b\u001b\u0000"+
		"\u00f18\u0001\u0000\u0000\u0000\u00f2\u00f3\u0005<\u0000\u0000\u00f3\u00fe"+
		"\u0005=\u0000\u0000\u00f4\u00f5\u0005=\u0000\u0000\u00f5\u00fe\u0005="+
		"\u0000\u0000\u00f6\u00f7\u0005>\u0000\u0000\u00f7\u00fe\u0005=\u0000\u0000"+
		"\u00f8\u00fe\u0007\u0005\u0000\u0000\u00f9\u00fa\u0005!\u0000\u0000\u00fa"+
		"\u00fb\u0005=\u0000\u0000\u00fb\u00fc\u0001\u0000\u0000\u0000\u00fc\u00fe"+
		"\u0006\u001c\u001c\u0000\u00fd\u00f2\u0001\u0000\u0000\u0000\u00fd\u00f4"+
		"\u0001\u0000\u0000\u0000\u00fd\u00f6\u0001\u0000\u0000\u0000\u00fd\u00f8"+
		"\u0001\u0000\u0000\u0000\u00fd\u00f9\u0001\u0000\u0000\u0000\u00fe:\u0001"+
		"\u0000\u0000\u0000\u00ff\u0100\u0005&\u0000\u0000\u0100\u0106\u0005&\u0000"+
		"\u0000\u0101\u0102\u0005|\u0000\u0000\u0102\u0103\u0005|\u0000\u0000\u0103"+
		"\u0104\u0001\u0000\u0000\u0000\u0104\u0106\u0006\u001d\u001d\u0000\u0105"+
		"\u00ff\u0001\u0000\u0000\u0000\u0105\u0101\u0001\u0000\u0000\u0000\u0106"+
		"<\u0001\u0000\u0000\u0000\u0107\u0108\u0005=\u0000\u0000\u0108\u0109\u0006"+
		"\u001e\u001e\u0000\u0109>\u0001\u0000\u0000\u0000\u010a\u010e\u0007\u0006"+
		"\u0000\u0000\u010b\u010d\u0007\u0007\u0000\u0000\u010c\u010b\u0001\u0000"+
		"\u0000\u0000\u010d\u0110\u0001\u0000\u0000\u0000\u010e\u010c\u0001\u0000"+
		"\u0000\u0000\u010e\u010f\u0001\u0000\u0000\u0000\u010f\u0111\u0001\u0000"+
		"\u0000\u0000\u0110\u010e\u0001\u0000\u0000\u0000\u0111\u0112\u0006\u001f"+
		"\u001f\u0000\u0112@\u0001\u0000\u0000\u0000\u0113\u0115\u0007\b\u0000"+
		"\u0000\u0114\u0113\u0001\u0000\u0000\u0000\u0115\u0116\u0001\u0000\u0000"+
		"\u0000\u0116\u0114\u0001\u0000\u0000\u0000\u0116\u0117\u0001\u0000\u0000"+
		"\u0000\u0117\u0118\u0001\u0000\u0000\u0000\u0118\u0119\u0006  \u0000\u0119"+
		"B\u0001\u0000\u0000\u0000\u011a\u011c\u0007\b\u0000\u0000\u011b\u011a"+
		"\u0001\u0000\u0000\u0000\u011c\u011d\u0001\u0000\u0000\u0000\u011d\u011b"+
		"\u0001\u0000\u0000\u0000\u011d\u011e\u0001\u0000\u0000\u0000\u011e\u0126"+
		"\u0001\u0000\u0000\u0000\u011f\u0123\u0005.\u0000\u0000\u0120\u0122\u0007"+
		"\b\u0000\u0000\u0121\u0120\u0001\u0000\u0000\u0000\u0122\u0125\u0001\u0000"+
		"\u0000\u0000\u0123\u0121\u0001\u0000\u0000\u0000\u0123\u0124\u0001\u0000"+
		"\u0000\u0000\u0124\u0127\u0001\u0000\u0000\u0000\u0125\u0123\u0001\u0000"+
		"\u0000\u0000\u0126\u011f\u0001\u0000\u0000\u0000\u0126\u0127\u0001\u0000"+
		"\u0000\u0000\u0127\u0131\u0001\u0000\u0000\u0000\u0128\u012a\u0007\t\u0000"+
		"\u0000\u0129\u012b\u0007\u0003\u0000\u0000\u012a\u0129\u0001\u0000\u0000"+
		"\u0000\u012a\u012b\u0001\u0000\u0000\u0000\u012b\u012d\u0001\u0000\u0000"+
		"\u0000\u012c\u012e\u0007\b\u0000\u0000\u012d\u012c\u0001\u0000\u0000\u0000"+
		"\u012e\u012f\u0001\u0000\u0000\u0000\u012f\u012d\u0001\u0000\u0000\u0000"+
		"\u012f\u0130\u0001\u0000\u0000\u0000\u0130\u0132\u0001\u0000\u0000\u0000"+
		"\u0131\u0128\u0001\u0000\u0000\u0000\u0131\u0132\u0001\u0000\u0000\u0000"+
		"\u0132\u0133\u0001\u0000\u0000\u0000\u0133\u0143\u0006!!\u0000\u0134\u0136"+
		"\u0005.\u0000\u0000\u0135\u0137\u0007\b\u0000\u0000\u0136\u0135\u0001"+
		"\u0000\u0000\u0000\u0137\u0138\u0001\u0000\u0000\u0000\u0138\u0136\u0001"+
		"\u0000\u0000\u0000\u0138\u0139\u0001\u0000\u0000\u0000\u0139\u013a\u0001"+
		"\u0000\u0000\u0000\u013a\u0143\u0006!\"\u0000\u013b\u013d\u0007\b\u0000"+
		"\u0000\u013c\u013b\u0001\u0000\u0000\u0000\u013d\u013e\u0001\u0000\u0000"+
		"\u0000\u013e\u013c\u0001\u0000\u0000\u0000\u013e\u013f\u0001\u0000\u0000"+
		"\u0000\u013f\u0140\u0001\u0000\u0000\u0000\u0140\u0141\u0005.\u0000\u0000"+
		"\u0141\u0143\u0006!#\u0000\u0142\u011b\u0001\u0000\u0000\u0000\u0142\u0134"+
		"\u0001\u0000\u0000\u0000\u0142\u013c\u0001\u0000\u0000\u0000\u0143D\u0001"+
		"\u0000\u0000\u0000\u0144\u0145\t\u0000\u0000\u0000\u0145\u0146\u0006\""+
		"$\u0000\u0146\u0147\u0001\u0000\u0000\u0000\u0147\u0148\u0006\"\u0001"+
		"\u0000\u0148F\u0001\u0000\u0000\u0000\u0013\u0000MZhju\u00fd\u0105\u010e"+
		"\u0116\u011d\u0123\u0126\u012a\u012f\u0131\u0138\u013e\u0142%\u0001\u0000"+
		"\u0000\u0006\u0000\u0000\u0001\u0001\u0001\u0001\u0002\u0002\u0001\u0004"+
		"\u0003\u0001\u0005\u0004\u0001\u0006\u0005\u0001\u0007\u0006\u0001\b\u0007"+
		"\u0001\t\b\u0001\n\t\u0001\u000b\n\u0001\f\u000b\u0001\r\f\u0001\u000e"+
		"\r\u0001\u000f\u000e\u0001\u0010\u000f\u0001\u0011\u0010\u0001\u0012\u0011"+
		"\u0001\u0013\u0012\u0001\u0014\u0013\u0001\u0015\u0014\u0001\u0016\u0015"+
		"\u0001\u0017\u0016\u0001\u0018\u0017\u0001\u0019\u0018\u0001\u001a\u0019"+
		"\u0001\u001b\u001a\u0001\u001c\u001b\u0001\u001d\u001c\u0001\u001e\u001d"+
		"\u0001\u001f\u001e\u0001 \u001f\u0001! \u0001!!\u0001!\"\u0001\"#";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}