// Generated from c:/Users/tasni/Downloads/2105013 (7)/2105013/java/C2105013Parser.g4 by ANTLR 4.13.1

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class C2105013Parser extends Parser {
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
	public static final int
		RULE_start = 0, RULE_program = 1, RULE_unit = 2, RULE_func_declaration = 3, 
		RULE_func_definition = 4, RULE_parameter_list = 5, RULE_compound_statement = 6, 
		RULE_var_declaration = 7, RULE_declaration_list_err = 8, RULE_type_specifier = 9, 
		RULE_type_specifier_func = 10, RULE_declaration_list = 11, RULE_declaration_tail = 12, 
		RULE_declaration_item = 13, RULE_statements = 14, RULE_statement = 15, 
		RULE_expression_statement = 16, RULE_variable = 17, RULE_expression = 18, 
		RULE_logic_expression = 19, RULE_rel_expression = 20, RULE_simple_expression = 21, 
		RULE_term = 22, RULE_unary_expression = 23, RULE_factor = 24, RULE_argument_list = 25, 
		RULE_arguments = 26, RULE_argument = 27, RULE_expression_fragment = 28;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "program", "unit", "func_declaration", "func_definition", "parameter_list", 
			"compound_statement", "var_declaration", "declaration_list_err", "type_specifier", 
			"type_specifier_func", "declaration_list", "declaration_tail", "declaration_item", 
			"statements", "statement", "expression_statement", "variable", "expression", 
			"logic_expression", "rel_expression", "simple_expression", "term", "unary_expression", 
			"factor", "argument_list", "arguments", "argument", "expression_fragment"
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

	@Override
	public String getGrammarFileName() { return "C2105013Parser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    private int currentStackPointer = 0;
	    private int currentParamOffset = 4;
	    private int labelCounter = 0;
	    private boolean atTheGlobalScope = true;
	    private ArrayList<String> elseLabels = new ArrayList<>();
	    private ArrayList<String> exitLabels = new ArrayList<>();
	    private String currentTrueLabel = "";
	    private String currentFalseLabel = "";
	    private boolean codeSegmentHasntBeenInitialized = true;
	    private String currentFunctionName = "";


	    // for FOR loop
	    private String tempExpressionLabel = "";
	    private String tempStatementLabel = "";

	    String newLabel() { return "L" + (labelCounter++); }

	    public void invertTopStackElements(int size) {
	        List<String> regs = new ArrayList<>(Arrays.asList("AX", "BX", "CX", "DX", "SI", "DI", "BP", "SP"));
	    
	        if (size > regs.size()) {
	            throw new IllegalArgumentException("Too many arguments to invert!");
	        }

	        // Step 1: Pop all arguments into temp registers
	        for (int i = 0; i < size; i++) {
	                writeIntoAssemblyLogFile("POP " + regs.get(i));
	        }

	        // Step 2: Push them back in reverse
	        for (int i = 0; i < size; i++) {
	            writeIntoAssemblyLogFile("PUSH " + regs.get(i));
	        }
	    }


	    String ending() {
	        return "\n;------------------------------- \n" +
	    "; print library\n" +
	    ";------------------------------- \n" +
	    "new_line proc \n" +
	    "push ax \n" +
	    "push dx \n" +
	    "mov ah,2 \n" +
	    "mov dl,0Dh \n" +
	    "int 21h \n" +
	    "mov ah,2 \n" +
	    "mov dl,0Ah \n" +
	    "int 21h \n" +
	    "pop dx \n" +
	    "pop ax \n" +
	    "ret \n" +
	    "new_line endp \n" +
	    "print_output proc  ;print what is in ax \n" +
	    "push ax \n" +
	    "push bx \n" +
	    "push cx \n" +
	    "push dx \n" +
	    "push si \n" +
	    "lea si,number \n" +
	    "mov bx,10 \n" +
	    "add si,4 \n" +
	    "cmp ax,0 \n" +
	    "jnge negate \n" +
	    "print: \n" +
	    "xor dx,dx \n" +
	    "div bx \n" +
	    "mov [si],dl \n" +
	    "add [si],'0' \n" +
	    "dec si \n" +
	    "cmp ax,0 \n" +
	    "jne print \n" +
	    "inc si \n" +
	    "lea dx,si \n" +
	    "mov ah,9 \n" +
	    "int 21h \n" +
	    "pop si \n" +
	    "pop dx \n" +
	    "pop cx \n" +
	    "pop bx \n" +
	    "pop ax \n" +
	    "ret \n" +
	    "negate: \n" +
	    "push ax \n" +
	    "mov ah,2 \n" +
	    "mov dl,'-' \n" +
	    "int 21h \n" +
	    "pop ax \n" +
	    "neg ax \n" +
	    "jmp print \n" +
	    "print_output endp \n" +
	    ";------------------------------- \n" +
	    "END main\n";
	    }

	    // helper to write into assemblyLogFile
	    void writeIntoAssemblyLogFile(String message) {
	        try {
	            Main.assemblyLogFile.write(message);
	            Main.assemblyLogFile.newLine();
	            Main.assemblyLogFile.flush();
	        } catch (IOException e) {
	            System.err.println("assembly log error: " + e.getMessage());
	        }
	    }

	    
	    // helper to write into parserLogFile
	    void writeIntoParserLogFile(String message) {
	        try {
	            Main.parserLogFile.write(message);
	            Main.parserLogFile.newLine();
	            Main.parserLogFile.flush();
	        } catch (IOException e) {
	            System.err.println("Parser log error: " + e.getMessage());
	        }
	    }

	    // helper to write into Main.errorFile
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

	    private void initAsmWriter() throws IOException {
	        // Write header
	        writeIntoAssemblyLogFile(".MODEL SMALL\n");
	        writeIntoAssemblyLogFile(".STACK 1000H\n");
	        writeIntoAssemblyLogFile(".DATA\n");
	        writeIntoAssemblyLogFile("\tnumber DB \"00000$\"\n");
	    }

	    private void finishAsm() throws IOException {
	        writeIntoAssemblyLogFile("\tMOV AX, 4CH\n");
	        writeIntoAssemblyLogFile("\tINT 21H\n");
	        writeIntoAssemblyLogFile("main ENDP\n");
	    }

	    private int currentLine = 1;
	    private int errorCount = 0;
	    private int numberOfBuckets = 7;
	    private boolean insertSuccess = false;
	    private boolean printMessage = true;
	    private SymbolTable symbolTable = new SymbolTable(numberOfBuckets, printMessage);

	    private boolean isFunction = false;
	    private String currentFunctionReturnType = null;
	    private List<String> currentFunctionParams = new ArrayList<>();
	    private List<String> currentFunctionParamTypes = new ArrayList<>();


	    void updateLineNumber(Token token) {
	        currentLine = token.getLine();
	    }

	    

	public C2105013Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public ProgramContext program() {
			return getRuleContext(ProgramContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{

			        try { initAsmWriter(); }
			        catch(IOException e) { System.out.println(e); }
			    
			setState(59);
			program(0);


			        writeIntoAssemblyLogFile(ending());

			        symbolTable.printAllScopeTables(); // console printing
			        if (errorCount > 0) System.out.println("THERE ARE ERRORS IN THE INPUT FILE");
			        else {
			            
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

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public UnitContext unit() {
			return getRuleContext(UnitContext.class,0);
		}
		public ProgramContext program() {
			return getRuleContext(ProgramContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		return program(0);
	}

	private ProgramContext program(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ProgramContext _localctx = new ProgramContext(_ctx, _parentState);
		ProgramContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_program, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(63);
			unit();
			 
			        { { writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": program : unit : \n" + _localctx.getText() + "\n"); } }
			    
			}
			_ctx.stop = _input.LT(-1);
			setState(72);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ProgramContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_program);
					setState(66);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(67);
					unit();
					 { writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": program : program unit\n" + _localctx.getText() + "\n"); } 
					}
					} 
				}
				setState(74);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class UnitContext extends ParserRuleContext {
		public String type;
		public String name_line;
		public Var_declarationContext var_declaration;
		public Func_declarationContext func_declaration;
		public Func_definitionContext func_definition;
		public Var_declarationContext var_declaration() {
			return getRuleContext(Var_declarationContext.class,0);
		}
		public Func_declarationContext func_declaration() {
			return getRuleContext(Func_declarationContext.class,0);
		}
		public Func_definitionContext func_definition() {
			return getRuleContext(Func_definitionContext.class,0);
		}
		public UnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unit; }
	}

	public final UnitContext unit() throws RecognitionException {
		UnitContext _localctx = new UnitContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_unit);
		try {
			setState(84);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				((UnitContext)_localctx).var_declaration = var_declaration();
				 
				        ((UnitContext)_localctx).type =  "var_declaration";
				        ((UnitContext)_localctx).name_line =  ((UnitContext)_localctx).var_declaration.name_line;
				        { { writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": unit : var_declaration\n" + _localctx.getText() + "\n"); } }
				      
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(78);
				((UnitContext)_localctx).func_declaration = func_declaration();
				 
				        ((UnitContext)_localctx).type =  ((UnitContext)_localctx).func_declaration.type;
				        ((UnitContext)_localctx).name_line =  ((UnitContext)_localctx).func_declaration.name_line;
				        { { writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": unit : func_declaration\n" + _localctx.getText() + "\n"); } }
				      
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(81);
				((UnitContext)_localctx).func_definition = func_definition();
				 
				        ((UnitContext)_localctx).type =  ((UnitContext)_localctx).func_definition.type;
				        ((UnitContext)_localctx).name_line =  ((UnitContext)_localctx).func_definition.name_line;
				        { { writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": unit : func_definition\n" + _localctx.getText() + "\n"); } }
				      
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

	@SuppressWarnings("CheckReturnValue")
	public static class Func_declarationContext extends ParserRuleContext {
		public String type;
		public String name_line;
		public Type_specifierContext t;
		public Token id;
		public Parameter_listContext p;
		public TerminalNode LPAREN() { return getToken(C2105013Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(C2105013Parser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(C2105013Parser.SEMICOLON, 0); }
		public Type_specifierContext type_specifier() {
			return getRuleContext(Type_specifierContext.class,0);
		}
		public TerminalNode ID() { return getToken(C2105013Parser.ID, 0); }
		public Parameter_listContext parameter_list() {
			return getRuleContext(Parameter_listContext.class,0);
		}
		public Func_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_declaration; }
	}

	public final Func_declarationContext func_declaration() throws RecognitionException {
		Func_declarationContext _localctx = new Func_declarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_func_declaration);
		try {
			setState(101);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(86);
				((Func_declarationContext)_localctx).t = type_specifier();
				setState(87);
				((Func_declarationContext)_localctx).id = match(ID);
				setState(88);
				match(LPAREN);
				setState(89);
				((Func_declarationContext)_localctx).p = parameter_list();
				setState(90);
				match(RPAREN);
				setState(91);
				match(SEMICOLON);

				        ((Func_declarationContext)_localctx).type =  "func_declaration";
				        ((Func_declarationContext)_localctx).name_line =  _localctx.getText();
				        updateLineNumber(((Func_declarationContext)_localctx).id);
				        writeIntoParserLogFile("Line " + ((Func_declarationContext)_localctx).id.getLine() + ": func_declaration : type_specifier ID LPAREN parameter_list RPAREN SEMICOLON\n" + _localctx.getText() + "\n");
				        

				        insertSuccess = symbolTable.insertFunction((((Func_declarationContext)_localctx).id!=null?((Func_declarationContext)_localctx).id.getText():null), ((Func_declarationContext)_localctx).t.name_line, ((Func_declarationContext)_localctx).p.paramTypes, ((Func_declarationContext)_localctx).p.paramNames, printMessage);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
				((Func_declarationContext)_localctx).t = type_specifier();
				setState(95);
				((Func_declarationContext)_localctx).id = match(ID);
				setState(96);
				match(LPAREN);
				setState(97);
				match(RPAREN);
				setState(98);
				match(SEMICOLON);

				        insertSuccess = symbolTable.insertFunction((((Func_declarationContext)_localctx).id!=null?((Func_declarationContext)_localctx).id.getText():null), ((Func_declarationContext)_localctx).t.name_line, null, null, printMessage);
				        // if (insertSuccess) writeIntoParserLogFile("Function declaration: " + (((Func_declarationContext)_localctx).id!=null?((Func_declarationContext)_localctx).id.getText():null) + " at line " + ((Func_declarationContext)_localctx).id.getLine());
				        // else writeIntoParserLogFile("NOOOOO!");

				        ((Func_declarationContext)_localctx).type =  "func_declaration";
				        ((Func_declarationContext)_localctx).name_line =  _localctx.getText();
				        writeIntoParserLogFile("Line " + ((Func_declarationContext)_localctx).id.getLine() + ": func_declaration : type_specifier ID LPAREN RPAREN SEMICOLON\n" + _localctx.getText() + "\n");
				    
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

	@SuppressWarnings("CheckReturnValue")
	public static class Func_definitionContext extends ParserRuleContext {
		public String type;
		public String name_line;
		public Type_specifierContext t;
		public Token id;
		public Parameter_listContext p;
		public Token LCURL;
		public Token RCURL;
		public TerminalNode LPAREN() { return getToken(C2105013Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(C2105013Parser.RPAREN, 0); }
		public TerminalNode LCURL() { return getToken(C2105013Parser.LCURL, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode RCURL() { return getToken(C2105013Parser.RCURL, 0); }
		public Type_specifierContext type_specifier() {
			return getRuleContext(Type_specifierContext.class,0);
		}
		public TerminalNode ID() { return getToken(C2105013Parser.ID, 0); }
		public Parameter_listContext parameter_list() {
			return getRuleContext(Parameter_listContext.class,0);
		}
		public Compound_statementContext compound_statement() {
			return getRuleContext(Compound_statementContext.class,0);
		}
		public List<Expression_fragmentContext> expression_fragment() {
			return getRuleContexts(Expression_fragmentContext.class);
		}
		public Expression_fragmentContext expression_fragment(int i) {
			return getRuleContext(Expression_fragmentContext.class,i);
		}
		public Func_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_definition; }
	}

	public final Func_definitionContext func_definition() throws RecognitionException {
		Func_definitionContext _localctx = new Func_definitionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_func_definition);
		try {
			int _alt;
			setState(136);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(103);
				((Func_definitionContext)_localctx).t = type_specifier();
				setState(104);
				((Func_definitionContext)_localctx).id = match(ID);
				setState(105);
				match(LPAREN);
				setState(106);
				((Func_definitionContext)_localctx).p = parameter_list();
				setState(107);
				match(RPAREN);
				  

				        currentFunctionName = (((Func_definitionContext)_localctx).id!=null?((Func_definitionContext)_localctx).id.getText():null).toUpperCase();
				        System.out.println(currentFunctionName);
				        if (atTheGlobalScope) {
				            atTheGlobalScope = false;
				            if (codeSegmentHasntBeenInitialized) {
				                writeIntoAssemblyLogFile("\n.CODE\n");
				                codeSegmentHasntBeenInitialized = false;
				            }

				            writeIntoAssemblyLogFile(currentFunctionName + " PROC\n");
				            if (currentFunctionName.equals("MAIN")) writeIntoAssemblyLogFile("MOV AX, @DATA\nMOV DS, AX\n");
				            writeIntoAssemblyLogFile("PUSH BP\nMOV BP, SP\n");
				        }

				        // Check against previous declaration
				        SymbolInfo existing = symbolTable.lookup((((Func_definitionContext)_localctx).id!=null?((Func_definitionContext)_localctx).id.getText():null));

				        if (existing != null && !existing.isFunction()) {
				            writeIntoErrorFile("Error at line " + ((Func_definitionContext)_localctx).id.getLine() + 
				                                 ": Multiple declaration of " + (((Func_definitionContext)_localctx).id!=null?((Func_definitionContext)_localctx).id.getText():null));
				                errorCount++;
				        }

				        if (existing != null && existing.isFunction()) {
				            // Check return type
				            if (!existing.getFunctionInfo().getReturnType().equals(((Func_definitionContext)_localctx).t.name_line)) {
				                writeIntoErrorFile("Error at line " + ((Func_definitionContext)_localctx).id.getLine() + 
				                                 ": Return type mismatch of " + (((Func_definitionContext)_localctx).id!=null?((Func_definitionContext)_localctx).id.getText():null));
				                errorCount++;
				            }
				            // Check parameter count
				            if (existing.getFunctionInfo().getParameterCount() != ((Func_definitionContext)_localctx).p.paramTypes.size()) {
				                writeIntoErrorFile("Error at line " + ((Func_definitionContext)_localctx).id.getLine() + 
				                                 ": Total number of arguments mismatch with declaration in function " + (((Func_definitionContext)_localctx).id!=null?((Func_definitionContext)_localctx).id.getText():null));
				                errorCount++;
				            }
				        }



				        ((Func_definitionContext)_localctx).type =  "func_definition";
				        ((Func_definitionContext)_localctx).name_line =  _localctx.getText();
				        updateLineNumber(((Func_definitionContext)_localctx).id);
				        // writeIntoParserLogFile("Line " + ((Func_definitionContext)_localctx).id.getLine() + ": func_definition : type_specifier ID LPAREN parameter_list RPAREN\n" + _localctx.getText() + "\n");

				        insertSuccess = symbolTable.insertFunction((((Func_definitionContext)_localctx).id!=null?((Func_definitionContext)_localctx).id.getText():null), ((Func_definitionContext)_localctx).t.name_line, ((Func_definitionContext)_localctx).p.paramTypes, ((Func_definitionContext)_localctx).p.paramNames, printMessage);
				        System.out.println("insert success: " + insertSuccess);

				        currentFunctionReturnType = ((Func_definitionContext)_localctx).t.name_line;
				        currentFunctionName = (((Func_definitionContext)_localctx).id!=null?((Func_definitionContext)_localctx).id.getText():null).toUpperCase();
				        if (true) {
				            isFunction = true;

				            for (int i = 0; i < ((Func_definitionContext)_localctx).p.paramTypes.size(); i++) {
				                String paramType = ((Func_definitionContext)_localctx).p.paramTypes.get(i);
				                String paramName = ((Func_definitionContext)_localctx).p.paramNames.get(i);
				                if (!paramName.isEmpty()) {
				                // dont insert now
				                //  insertSuccess = symbolTable.insertIntoCurrentScope(paramName, "ID", printMessage);
				                currentFunctionParams.add(paramName);
				                currentFunctionParamTypes.add(paramType);
				               }
				            }

				            System.out.println("Before entering scope, currentparams: " + currentFunctionParams);

				            // symbolTable.enterScope(printMessage);        
				        }
				    
				setState(109);
				((Func_definitionContext)_localctx).LCURL = match(LCURL);

				        symbolTable.enterScope(printMessage);
				        System.out.println("current params is: " + currentFunctionParams);

				        if (true) {
				            // insert parameters into symbol table
				            for (int i = 0; i < currentFunctionParams.size(); i++) {
				                System.out.println("We are at line " + ((Func_definitionContext)_localctx).LCURL.getLine() + " and current params is: " + currentFunctionParams);
				                String paramName = currentFunctionParams.get(i);
				                String paramType = currentFunctionParamTypes.get(i);
				                if (!paramName.isEmpty()) {
				                    boolean success = symbolTable.insertIntoCurrentScope(paramName, "ID", printMessage);
				                    if (success) {
				                        SymbolInfo param = symbolTable.lookup(paramName, true);
				                        param.setDataType(paramType);
				                        param.setStackOffset(currentParamOffset);
				                        currentParamOffset += 2;
				                        param.setFunctionParam(true); // it is indeed a function param

				                        // writeIntoParserLogFile("Parameter: " + paramName + " of type " + paramType + " at line " + currentLine);
				                    } else {
				                        //writeIntoErrorFile("Line " + currentLine + ": Parameter '" + paramName + "' already declared");
				                        // Main.syntaxErrorCount++;
				                        //errorCount++;
				                    }
				                }
				            }

				            // currentFunctionParams.clear();
				            // currentFunctionParamTypes.clear();
				        }
				     
				setState(111);
				statements(0);
				setState(112);
				((Func_definitionContext)_localctx).RCURL = match(RCURL);

				        writeIntoParserLogFile ( symbolTable.generateAllScopeTableString() );
				        writeIntoParserLogFile("Line " + ((Func_definitionContext)_localctx).RCURL.getLine() + ": func_definition : type_specifier ID LPAREN parameter_list RPAREN compound_statement\n" + _localctx.getText() + "\n");
				        symbolTable.exitScope(printMessage);

				        atTheGlobalScope = true;
				        
				        writeIntoAssemblyLogFile("\n" + currentFunctionName + "_EXIT_LABEL:" + "\n");

				        writeIntoAssemblyLogFile("MOV SP, BP\n");
				        writeIntoAssemblyLogFile("POP BP\n");


				        if(!currentFunctionName.equals("MAIN")){
				            String return_statement = "RET "+ (currentFunctionParams.size() != 0 ? ((2*currentFunctionParams.size()) + "") : "");
				            writeIntoAssemblyLogFile(return_statement + "\n");
				        }

				        if(currentFunctionName.equals("MAIN")){
				            writeIntoAssemblyLogFile("MOV AX,4CH\nINT 21H\n");
				        }

				        writeIntoAssemblyLogFile(currentFunctionName + " ENDP\n");

				        currentFunctionParams.clear();
				        currentFunctionParamTypes.clear();
				        currentParamOffset = 4;
				     
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				((Func_definitionContext)_localctx).t = type_specifier();
				setState(116);
				((Func_definitionContext)_localctx).id = match(ID);
				setState(117);
				match(LPAREN);
				setState(118);
				match(RPAREN);


				        
				        currentFunctionName = (((Func_definitionContext)_localctx).id!=null?((Func_definitionContext)_localctx).id.getText():null).toUpperCase();
				        System.out.println(currentFunctionName);
				        if (atTheGlobalScope) {
				            atTheGlobalScope = false;
				            if (codeSegmentHasntBeenInitialized) {
				                writeIntoAssemblyLogFile("\n.CODE\n");
				                codeSegmentHasntBeenInitialized = false;
				            }

				            writeIntoAssemblyLogFile(currentFunctionName + " PROC\n");
				            if (currentFunctionName.equals("MAIN")) writeIntoAssemblyLogFile("MOV AX, @DATA\nMOV DS, AX\n");
				            writeIntoAssemblyLogFile("PUSH BP\nMOV BP, SP\n");
				        }


				        ((Func_definitionContext)_localctx).type =  "func_definition";
				        ((Func_definitionContext)_localctx).name_line =  _localctx.getText();
				        currentFunctionReturnType = ((Func_definitionContext)_localctx).t.name_line;
				        currentFunctionName = (((Func_definitionContext)_localctx).id!=null?((Func_definitionContext)_localctx).id.getText():null).toUpperCase();
				        // writeIntoParserLogFile("Line " + ((Func_definitionContext)_localctx).id.getLine() + ": func_definition : type_specifier ID LPAREN RPAREN\n" + _localctx.getText() + "\n");
				        insertSuccess = symbolTable.insertFunction((((Func_definitionContext)_localctx).id!=null?((Func_definitionContext)_localctx).id.getText():null), ((Func_definitionContext)_localctx).t.name_line, null, null, printMessage);
				        if (insertSuccess) {
				            // writeIntoParserLogFile("Function definition: " + (((Func_definitionContext)_localctx).id!=null?((Func_definitionContext)_localctx).id.getText():null) + " at line " + ((Func_definitionContext)_localctx).id.getLine());
				            symbolTable.enterScope(printMessage);
				        }

				        else {
				            // writeIntoParserLogFile("FAILED Function definition: " + (((Func_definitionContext)_localctx).id!=null?((Func_definitionContext)_localctx).id.getText():null) + " at line " + ((Func_definitionContext)_localctx).id.getLine());
				        }
				     
				setState(120);
				compound_statement();

				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": func_definition : type_specifier ID LPAREN RPAREN compound_statement\n" + _localctx.getText() + "\n");
				        // symbolTable.exitScope(printMessage);
				        
				        writeIntoParserLogFile ( symbolTable.generateAllScopeTableString() );
				        symbolTable.exitScope(printMessage);

				        atTheGlobalScope = true;
				        writeIntoAssemblyLogFile("MOV SP, BP\n");
				        writeIntoAssemblyLogFile("POP BP\n");

				        if(!currentFunctionName.equals("MAIN")){
				            String return_statement = "RET "+ (currentFunctionParams.size() == 0 ? ((2*currentFunctionParams.size()) + "") : "");
				            writeIntoAssemblyLogFile(return_statement + "\n");
				        }

				        if(currentFunctionName.equals("MAIN")){
				            writeIntoAssemblyLogFile("MOV AX,4CH\nINT 21H\n");
				        }

				        writeIntoAssemblyLogFile(currentFunctionName + " ENDP\n");

				     
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(123);
				((Func_definitionContext)_localctx).t = type_specifier();
				setState(124);
				((Func_definitionContext)_localctx).id = match(ID);
				setState(125);
				match(LPAREN);
				setState(129);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(126);
						expression_fragment();
						}
						} 
					}
					setState(131);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				}
				setState(132);
				match(RPAREN);
				setState(133);
				compound_statement();


				     
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

	@SuppressWarnings("CheckReturnValue")
	public static class Parameter_listContext extends ParserRuleContext {
		public List<String> paramTypes;
		public List<String> paramNames;
		public Type_specifier_funcContext t;
		public Token id;
		public Type_specifier_funcContext t2;
		public Token id2;
		public Token op;
		public List<Type_specifier_funcContext> type_specifier_func() {
			return getRuleContexts(Type_specifier_funcContext.class);
		}
		public Type_specifier_funcContext type_specifier_func(int i) {
			return getRuleContext(Type_specifier_funcContext.class,i);
		}
		public List<TerminalNode> ID() { return getTokens(C2105013Parser.ID); }
		public TerminalNode ID(int i) {
			return getToken(C2105013Parser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(C2105013Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(C2105013Parser.COMMA, i);
		}
		public List<TerminalNode> ADDOP() { return getTokens(C2105013Parser.ADDOP); }
		public TerminalNode ADDOP(int i) {
			return getToken(C2105013Parser.ADDOP, i);
		}
		public List<TerminalNode> SUBOP() { return getTokens(C2105013Parser.SUBOP); }
		public TerminalNode SUBOP(int i) {
			return getToken(C2105013Parser.SUBOP, i);
		}
		public List<TerminalNode> MULOP() { return getTokens(C2105013Parser.MULOP); }
		public TerminalNode MULOP(int i) {
			return getToken(C2105013Parser.MULOP, i);
		}
		public List<TerminalNode> INCOP() { return getTokens(C2105013Parser.INCOP); }
		public TerminalNode INCOP(int i) {
			return getToken(C2105013Parser.INCOP, i);
		}
		public List<TerminalNode> DECOP() { return getTokens(C2105013Parser.DECOP); }
		public TerminalNode DECOP(int i) {
			return getToken(C2105013Parser.DECOP, i);
		}
		public List<TerminalNode> RELOP() { return getTokens(C2105013Parser.RELOP); }
		public TerminalNode RELOP(int i) {
			return getToken(C2105013Parser.RELOP, i);
		}
		public List<TerminalNode> NOT() { return getTokens(C2105013Parser.NOT); }
		public TerminalNode NOT(int i) {
			return getToken(C2105013Parser.NOT, i);
		}
		public Parameter_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter_list; }
	}

	public final Parameter_listContext parameter_list() throws RecognitionException {
		Parameter_listContext _localctx = new Parameter_listContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_parameter_list);

		    ((Parameter_listContext)_localctx).paramTypes =  new ArrayList<>();
		    ((Parameter_listContext)_localctx).paramNames =  new ArrayList<>();
		    Set<String> seenParams = new HashSet<>();

		int _la;
		try {
			setState(179);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(138);
				((Parameter_listContext)_localctx).t = type_specifier_func();

				            writeIntoParserLogFile("Line " + (((Parameter_listContext)_localctx).t!=null?(((Parameter_listContext)_localctx).t.start):null).getLine() + ": type_specifier: \n" + ((Parameter_listContext)_localctx).t.name_line.toUpperCase() + "\n" + ((Parameter_listContext)_localctx).t.name_line + "\n");
				        
				setState(140);
				((Parameter_listContext)_localctx).id = match(ID);

				          _localctx.paramTypes.add(((Parameter_listContext)_localctx).t.name_line);
				          _localctx.paramNames.add(((Parameter_listContext)_localctx).id.getText());
				        //   if (!seenParams.add((((Parameter_listContext)_localctx).id!=null?((Parameter_listContext)_localctx).id.getText():null))) {
				        //       writeIntoErrorFile("Error at line " + ((Parameter_listContext)_localctx).id.getLine() + 
				        //                        ": Multiple declaration of " + (((Parameter_listContext)_localctx).id!=null?((Parameter_listContext)_localctx).id.getText():null) + " in parameter");
				        //       errorCount++;
				        //   }
				          writeIntoParserLogFile("Line " + (((Parameter_listContext)_localctx).t!=null?(((Parameter_listContext)_localctx).t.start):null).getLine() + ": parameter_list : type_specifier ID\n" + (((Parameter_listContext)_localctx).t!=null?_input.getText(((Parameter_listContext)_localctx).t.start,((Parameter_listContext)_localctx).t.stop):null) + " " + (((Parameter_listContext)_localctx).id!=null?((Parameter_listContext)_localctx).id.getText():null) + "\n");
				      
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(142);
					match(COMMA);
					setState(143);
					((Parameter_listContext)_localctx).t2 = type_specifier_func();

					            writeIntoParserLogFile("Line " + (((Parameter_listContext)_localctx).t!=null?(((Parameter_listContext)_localctx).t.start):null).getLine() + ": type_specifier: " + ((Parameter_listContext)_localctx).t.name_line.toUpperCase() + "\n" + ((Parameter_listContext)_localctx).t.name_line + "\n");
					        
					setState(145);
					((Parameter_listContext)_localctx).id2 = match(ID);

					           _localctx.paramTypes.add(((Parameter_listContext)_localctx).t2.name_line);
					           _localctx.paramNames.add(((Parameter_listContext)_localctx).id2.getText());
					           writeIntoParserLogFile("Line " + (((Parameter_listContext)_localctx).t2!=null?(((Parameter_listContext)_localctx).t2.start):null).getLine() + ": parameter_list : parameter_list COMMA type_specifier ID\n" + _localctx.getText() + "\n");
					           if (!seenParams.add((((Parameter_listContext)_localctx).id!=null?((Parameter_listContext)_localctx).id.getText():null))) {
					              writeIntoErrorFile("Error at line " + ((Parameter_listContext)_localctx).id.getLine() + 
					                               ": Multiple declaration of " + (((Parameter_listContext)_localctx).id!=null?((Parameter_listContext)_localctx).id.getText():null) + " in parameter");
					              errorCount++;
					          }


					       
					}
					}
					setState(152);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				((Parameter_listContext)_localctx).t = type_specifier_func();

				          _localctx.paramTypes.add(((Parameter_listContext)_localctx).t.name_line);
				          _localctx.paramNames.add("");
				      
				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(155);
					match(COMMA);
					setState(156);
					((Parameter_listContext)_localctx).t2 = type_specifier_func();

					           _localctx.paramTypes.add(((Parameter_listContext)_localctx).t2.name_line);
					           _localctx.paramNames.add("");
					       
					}
					}
					setState(163);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(164);
				((Parameter_listContext)_localctx).t = type_specifier_func();

				          _localctx.paramTypes.add(((Parameter_listContext)_localctx).t.name_line);
				          _localctx.paramNames.add("");
				    
				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1065353216L) != 0)) {
					{
					{
					setState(166);
					((Parameter_listContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1065353216L) != 0)) ) {
						((Parameter_listContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(170);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 28672L) != 0)) {
						{
						{
						setState(167);
						((Parameter_listContext)_localctx).t2 = type_specifier_func();
						}
						}
						setState(172);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}

					           writeIntoErrorFile("Error at line " + ((Parameter_listContext)_localctx).op.getLine() + ": syntax error, unexpected " + ((Parameter_listContext)_localctx).op.getText() + ", expecting RPAREN or COMMA");
					        //    _localctx.paramTypes.add(((Parameter_listContext)_localctx).t2.name_line);
					        //    _localctx.paramNames.add("");
					       
					}
					}
					setState(178);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class Compound_statementContext extends ParserRuleContext {
		public Token RCURL;
		public TerminalNode LCURL() { return getToken(C2105013Parser.LCURL, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode RCURL() { return getToken(C2105013Parser.RCURL, 0); }
		public Compound_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_statement; }
	}

	public final Compound_statementContext compound_statement() throws RecognitionException {
		Compound_statementContext _localctx = new Compound_statementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_compound_statement);
		try {
			setState(191);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(181);
				match(LCURL);
				 
				        symbolTable.enterScope(printMessage);
				    
				setState(183);
				statements(0);
				setState(184);
				((Compound_statementContext)_localctx).RCURL = match(RCURL);

				        { { writeIntoParserLogFile("Line " + ((Compound_statementContext)_localctx).RCURL.getLine() + ": compound_statement : LCURL statements RCURL\n" + _localctx.getText() + "\n"); } }

				        writeIntoParserLogFile ( symbolTable.generateAllScopeTableString() );
				        symbolTable.exitScope(printMessage);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(187);
				match(LCURL);
				 
				        symbolTable.enterScope(printMessage);
				    
				setState(189);
				((Compound_statementContext)_localctx).RCURL = match(RCURL);

				        { { writeIntoParserLogFile("Line " + ((Compound_statementContext)_localctx).RCURL.getLine() + ": compound_statement : LCURL RCURL\n" + _localctx.getText() + "\n"); } }
				        writeIntoParserLogFile ( symbolTable.generateAllScopeTableString() );
				        symbolTable.exitScope(printMessage);
				    
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

	@SuppressWarnings("CheckReturnValue")
	public static class Var_declarationContext extends ParserRuleContext {
		public String name_line;
		public Type_specifierContext t;
		public Declaration_listContext dl;
		public Token sm;
		public Declaration_list_errContext de;
		public Token UNRECOGNIZED;
		public Type_specifierContext type_specifier() {
			return getRuleContext(Type_specifierContext.class,0);
		}
		public Declaration_listContext declaration_list() {
			return getRuleContext(Declaration_listContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(C2105013Parser.SEMICOLON, 0); }
		public Declaration_list_errContext declaration_list_err() {
			return getRuleContext(Declaration_list_errContext.class,0);
		}
		public TerminalNode UNRECOGNIZED() { return getToken(C2105013Parser.UNRECOGNIZED, 0); }
		public Var_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_declaration; }
	}

	public final Var_declarationContext var_declaration() throws RecognitionException {
		Var_declarationContext _localctx = new Var_declarationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_var_declaration);
		try {
			setState(209);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(193);
				((Var_declarationContext)_localctx).t = type_specifier();
				setState(194);
				((Var_declarationContext)_localctx).dl = declaration_list();
				setState(195);
				((Var_declarationContext)_localctx).sm = match(SEMICOLON);

				        if (((Var_declarationContext)_localctx).t.name_line.equals("void")) {
				              writeIntoErrorFile("Error at line " + (((Var_declarationContext)_localctx).t!=null?(((Var_declarationContext)_localctx).t.start):null).getLine() + 
				                               ": Variable type cannot be void");
				              errorCount++;
				        }

				        String[] allTheDeclarations = ((Var_declarationContext)_localctx).dl.name_line.split(",");
				        for (String dec: allTheDeclarations) {

				            // may be ->
				            // 1. global arr
				            // 2. local arr
				            // 3. global non-arr
				            // 4. local non-arr

				            String arrName = "";
				            if (dec.contains("[")) {
				                int start = dec.indexOf('['), end = dec.indexOf(']');
				                int size = Integer.parseInt(dec.substring(start + 1, end));
				                arrName = dec.substring(0, start);
				            }
				            
				            if (!dec.contains("[")) symbolTable.insertIntoCurrentScope(dec, "ID", printMessage);
				            else symbolTable.insertIntoCurrentScope(arrName, "ID", printMessage);

				            boolean isGlobal = symbolTable.areWeAtTheRootScopeTable();

				            SymbolInfo varInfo;
				            if (!dec.contains("[")) varInfo = symbolTable.lookup(dec, true);
				            else varInfo = symbolTable.lookup(arrName, true);

				            if (isGlobal) {
				                varInfo.setGlobal();
				                // addToDATASegment("\t" + dec + " DW 1 DUP (0000H)\n");
				                

				                if (!dec.contains("[")) writeIntoAssemblyLogFile("\t" + dec + " DW 1 DUP (0000H)      ; declare global variable " + dec + "\n");
				                else {
				                    int start = dec.indexOf('['), end = dec.indexOf(']');
				                    int size = Integer.parseInt(dec.substring(start + 1, end));
				                    arrName = dec.substring(0, start);
				                    writeIntoAssemblyLogFile("\t" + arrName + " DW " + size + " DUP (0000H)      ; declare global array " + dec + "\n");                    
				                }
				            }
				            else if (!isGlobal && !dec.contains("[")) { 
				                // local variable
				                varInfo.setStackOffset(currentStackPointer + 2);
				                currentStackPointer += 2; 

				                writeIntoAssemblyLogFile("\tSUB SP, 2\t; Allocate " + dec + " , Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n");
				            }

				            else if (!isGlobal && dec.contains("[")) { 
				                // this is an array (local)
				                int start = dec.indexOf('['), end = dec.indexOf(']');
				                varInfo.setArray(Integer.parseInt(dec.substring(start + 1, end)));

				                varInfo.setStackOffset(currentStackPointer + 2);
				                currentStackPointer += 2*Integer.parseInt(dec.substring(start + 1, end)); 
				                writeIntoAssemblyLogFile("\tSUB SP, " + 2*Integer.parseInt(dec.substring(start + 1, end)) + "\t; Allocate " + dec + "\n");
				            }

				            if (varInfo != null && varInfo.isArray())  varInfo.setDataType((((Var_declarationContext)_localctx).t!=null?_input.getText(((Var_declarationContext)_localctx).t.start,((Var_declarationContext)_localctx).t.stop):null));

				            if(varInfo != null) varInfo.setDataType(((Var_declarationContext)_localctx).t.name_line);
				        }

				        updateLineNumber(((Var_declarationContext)_localctx).sm);
				        writeIntoParserLogFile(
				            "Line " + currentLine + ": " +
				            "var_declaration: type_specifier declaration_list SEMICOLON"

				        );
				        // writeIntoParserLogFile(
				        //     "type_specifier name_line: "
				        //     + ((Var_declarationContext)_localctx).t.name_line
				        // );

				        // print stuff like int x, y, z;
				        writeIntoParserLogFile(
				            ((Var_declarationContext)_localctx).t.name_line + " " + ((Var_declarationContext)_localctx).dl.name_line + ";\n\n"
				        );

				        ((Var_declarationContext)_localctx).name_line =  ((Var_declarationContext)_localctx).t.name_line + " " + ((Var_declarationContext)_localctx).dl.name_line + ";\n\n";
				      
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(198);
				((Var_declarationContext)_localctx).t = type_specifier();
				setState(199);
				((Var_declarationContext)_localctx).de = declaration_list_err();
				setState(200);
				((Var_declarationContext)_localctx).sm = match(SEMICOLON);

				        writeIntoErrorFile(
				            "Error at Line "
				            + ((Var_declarationContext)_localctx).sm.getLine()
				            + " with error name: "
				            + ((Var_declarationContext)_localctx).de.error_name
				            + " - Syntax error at declaration list of variable declaration"
				        );
				        // Main.syntaxErrorCount++;
				        errorCount++;
				      
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(203);
				((Var_declarationContext)_localctx).UNRECOGNIZED = match(UNRECOGNIZED);

				        writeIntoErrorFile(
				            "Error at line " + ((Var_declarationContext)_localctx).UNRECOGNIZED.getLine() +
				            " Unrecognized character " + (((Var_declarationContext)_localctx).UNRECOGNIZED!=null?((Var_declarationContext)_localctx).UNRECOGNIZED.getText():null)
				        );
				      
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(205);
				((Var_declarationContext)_localctx).t = type_specifier();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(206);
				((Var_declarationContext)_localctx).t = type_specifier();

				          writeIntoErrorFile("Error at line " + (((Var_declarationContext)_localctx).t!=null?(((Var_declarationContext)_localctx).t.start):null).getLine() + ": incomplete variable declaration");
				          errorCount++;
				      
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

	@SuppressWarnings("CheckReturnValue")
	public static class Declaration_list_errContext extends ParserRuleContext {
		public String error_name;
		public Token id;
		public Token id2;
		public List<TerminalNode> ID() { return getTokens(C2105013Parser.ID); }
		public TerminalNode ID(int i) {
			return getToken(C2105013Parser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(C2105013Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(C2105013Parser.COMMA, i);
		}
		public TerminalNode ADDOP() { return getToken(C2105013Parser.ADDOP, 0); }
		public Declaration_list_errContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration_list_err; }
	}

	public final Declaration_list_errContext declaration_list_err() throws RecognitionException {
		Declaration_list_errContext _localctx = new Declaration_list_errContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_declaration_list_err);
		int _la;
		try {
			setState(223);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(211);
				((Declaration_list_errContext)_localctx).id = match(ID);
				setState(216);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(212);
					match(COMMA);
					setState(213);
					((Declaration_list_errContext)_localctx).id2 = match(ID);
					}
					}
					setState(218);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(219);
				((Declaration_list_errContext)_localctx).id = match(ID);
				setState(220);
				match(ADDOP);
				setState(221);
				((Declaration_list_errContext)_localctx).id2 = match(ID);
				 ((Declaration_list_errContext)_localctx).error_name =  "Error in declaration list"; 
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

	@SuppressWarnings("CheckReturnValue")
	public static class Type_specifierContext extends ParserRuleContext {
		public String name_line;
		public Token INT;
		public Token FLOAT;
		public Token VOID;
		public TerminalNode INT() { return getToken(C2105013Parser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(C2105013Parser.FLOAT, 0); }
		public TerminalNode VOID() { return getToken(C2105013Parser.VOID, 0); }
		public Type_specifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_specifier; }
	}

	public final Type_specifierContext type_specifier() throws RecognitionException {
		Type_specifierContext _localctx = new Type_specifierContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_type_specifier);
		try {
			setState(231);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(225);
				((Type_specifierContext)_localctx).INT = match(INT);

				        // ((Type_specifierContext)_localctx).name_line =  "type: INT at line" + ((Type_specifierContext)_localctx).INT.getLine();
				        ((Type_specifierContext)_localctx).name_line =  "int";
				        updateLineNumber(((Type_specifierContext)_localctx).INT);
				        writeIntoParserLogFile(
				            "Line " + currentLine + " type_specifier : INT\n\n"
				        );

				        writeIntoParserLogFile(
				            "int\n\n"
				        );
				      
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(227);
				((Type_specifierContext)_localctx).FLOAT = match(FLOAT);

				        // ((Type_specifierContext)_localctx).name_line =  "type: FLOAT at line" + ((Type_specifierContext)_localctx).FLOAT.getLine();
				        ((Type_specifierContext)_localctx).name_line =  "float";
				        updateLineNumber(((Type_specifierContext)_localctx).FLOAT);
				        writeIntoParserLogFile(
				            "Line " + currentLine + " type_specifier : FLOAT\n\n"
				        );

				        writeIntoParserLogFile(
				            "float\n\n"
				        );
				      
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 3);
				{
				setState(229);
				((Type_specifierContext)_localctx).VOID = match(VOID);

				        // ((Type_specifierContext)_localctx).name_line =  "type: VOID at line" + ((Type_specifierContext)_localctx).VOID.getLine();
				        ((Type_specifierContext)_localctx).name_line =  "void";
				        updateLineNumber(((Type_specifierContext)_localctx).VOID);
				        writeIntoParserLogFile(
				            "Line " + currentLine + " type_specifier : VOID\n\n"
				        );

				        writeIntoParserLogFile(
				            "void\n\n"
				        );
				      
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

	@SuppressWarnings("CheckReturnValue")
	public static class Type_specifier_funcContext extends ParserRuleContext {
		public String name_line;
		public TerminalNode INT() { return getToken(C2105013Parser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(C2105013Parser.FLOAT, 0); }
		public TerminalNode VOID() { return getToken(C2105013Parser.VOID, 0); }
		public Type_specifier_funcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_specifier_func; }
	}

	public final Type_specifier_funcContext type_specifier_func() throws RecognitionException {
		Type_specifier_funcContext _localctx = new Type_specifier_funcContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_type_specifier_func);
		try {
			setState(239);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(233);
				match(INT);
				 ((Type_specifier_funcContext)_localctx).name_line =  "int"; 
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(235);
				match(FLOAT);
				 ((Type_specifier_funcContext)_localctx).name_line =  "float"; 
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 3);
				{
				setState(237);
				match(VOID);
				 ((Type_specifier_funcContext)_localctx).name_line =  "void"; 
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

	@SuppressWarnings("CheckReturnValue")
	public static class Declaration_listContext extends ParserRuleContext {
		public String name_line;
		public Declaration_itemContext first;
		public Declaration_tailContext declaration_tail;
		public List<Declaration_tailContext> rest = new ArrayList<Declaration_tailContext>();
		public Declaration_itemContext declaration_item() {
			return getRuleContext(Declaration_itemContext.class,0);
		}
		public List<Declaration_tailContext> declaration_tail() {
			return getRuleContexts(Declaration_tailContext.class);
		}
		public Declaration_tailContext declaration_tail(int i) {
			return getRuleContext(Declaration_tailContext.class,i);
		}
		public Declaration_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration_list; }
	}

	public final Declaration_listContext declaration_list() throws RecognitionException {
		Declaration_listContext _localctx = new Declaration_listContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_declaration_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			((Declaration_listContext)_localctx).first = declaration_item();
			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4290772992L) != 0)) {
				{
				{
				setState(242);
				((Declaration_listContext)_localctx).declaration_tail = declaration_tail();
				((Declaration_listContext)_localctx).rest.add(((Declaration_listContext)_localctx).declaration_tail);
				}
				}
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			        // Start with first ID
			        ((Declaration_listContext)_localctx).name_line =  ((Declaration_listContext)_localctx).first.name;

			        // Log first
			        if (!((Declaration_listContext)_localctx).first.name.contains("["))  writeIntoParserLogFile("Line " + currentLine + ": declaration_list : ID\n\n" + ((Declaration_listContext)_localctx).first.name + "\n");
			        else writeIntoParserLogFile("Line " + currentLine + ": declaration_list : ID LTHIRD CONST_INT RTHIRD\n\n" + ((Declaration_listContext)_localctx).first.name + "\n");

			        // Then for each comma ID, update the name_line and log
			        for (var item : ((Declaration_listContext)_localctx).rest) {
			            _localctx.name_line += "," + item.name;
			            writeIntoParserLogFile("Line " + currentLine + ": declaration_list : declaration_list COMMA ID\n\n" + _localctx.name_line + "\n");
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

	@SuppressWarnings("CheckReturnValue")
	public static class Declaration_tailContext extends ParserRuleContext {
		public String name;
		public Declaration_itemContext decl;
		public Token op;
		public TerminalNode COMMA() { return getToken(C2105013Parser.COMMA, 0); }
		public Declaration_itemContext declaration_item() {
			return getRuleContext(Declaration_itemContext.class,0);
		}
		public TerminalNode ADDOP() { return getToken(C2105013Parser.ADDOP, 0); }
		public TerminalNode SUBOP() { return getToken(C2105013Parser.SUBOP, 0); }
		public TerminalNode MULOP() { return getToken(C2105013Parser.MULOP, 0); }
		public TerminalNode INCOP() { return getToken(C2105013Parser.INCOP, 0); }
		public TerminalNode DECOP() { return getToken(C2105013Parser.DECOP, 0); }
		public TerminalNode RELOP() { return getToken(C2105013Parser.RELOP, 0); }
		public TerminalNode NOT() { return getToken(C2105013Parser.NOT, 0); }
		public TerminalNode LOGICOP() { return getToken(C2105013Parser.LOGICOP, 0); }
		public TerminalNode ASSIGNOP() { return getToken(C2105013Parser.ASSIGNOP, 0); }
		public Declaration_tailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration_tail; }
	}

	public final Declaration_tailContext declaration_tail() throws RecognitionException {
		Declaration_tailContext _localctx = new Declaration_tailContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_declaration_tail);
		int _la;
		try {
			setState(258);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(250);
				match(COMMA);
				setState(251);
				((Declaration_tailContext)_localctx).decl = declaration_item();
				 ((Declaration_tailContext)_localctx).name =  ((Declaration_tailContext)_localctx).decl.name; 
				}
				break;
			case ADDOP:
			case SUBOP:
			case MULOP:
			case INCOP:
			case DECOP:
			case NOT:
			case RELOP:
			case LOGICOP:
			case ASSIGNOP:
				enterOuterAlt(_localctx, 2);
				{
				setState(254);
				((Declaration_tailContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4286578688L) != 0)) ) {
					((Declaration_tailContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(255);
				((Declaration_tailContext)_localctx).decl = declaration_item();

				          ((Declaration_tailContext)_localctx).name =  ((Declaration_tailContext)_localctx).decl.name;
				          writeIntoErrorFile("Error at line " + ((Declaration_tailContext)_localctx).op.getLine() + ": syntax error, unexpected ADDOP, expecting COMMA or SEMICOLON");
				          errorCount++;
				        //   ((Declaration_tailContext)_localctx).name =  "";
				      
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

	@SuppressWarnings("CheckReturnValue")
	public static class Declaration_itemContext extends ParserRuleContext {
		public String name;
		public Token id;
		public Token CONST_INT;
		public TerminalNode ID() { return getToken(C2105013Parser.ID, 0); }
		public TerminalNode LTHIRD() { return getToken(C2105013Parser.LTHIRD, 0); }
		public TerminalNode CONST_INT() { return getToken(C2105013Parser.CONST_INT, 0); }
		public TerminalNode RTHIRD() { return getToken(C2105013Parser.RTHIRD, 0); }
		public TerminalNode CONST_FLOAT() { return getToken(C2105013Parser.CONST_FLOAT, 0); }
		public Declaration_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration_item; }
	}

	public final Declaration_itemContext declaration_item() throws RecognitionException {
		Declaration_itemContext _localctx = new Declaration_itemContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_declaration_item);
		try {
			setState(272);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(260);
				((Declaration_itemContext)_localctx).id = match(ID);

				        updateLineNumber(((Declaration_itemContext)_localctx).id);
				        boolean success = symbolTable.insertIntoCurrentScope((((Declaration_itemContext)_localctx).id!=null?((Declaration_itemContext)_localctx).id.getText():null), "ID", printMessage);
				        if (!success) {
				            errorCount++;
				            writeIntoErrorFile("Error at line " + currentLine + ": Multiple declaration of " + (((Declaration_itemContext)_localctx).id!=null?((Declaration_itemContext)_localctx).id.getText():null));
				        }
				        ((Declaration_itemContext)_localctx).name =  (((Declaration_itemContext)_localctx).id!=null?((Declaration_itemContext)_localctx).id.getText():null);
				        // writeIntoParserLogFile("Line " + ((Declaration_itemContext)_localctx).id.getLine() + ": declaration_list : ID\n\n" + (((Declaration_itemContext)_localctx).id!=null?((Declaration_itemContext)_localctx).id.getText():null) + "\n"); 
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(262);
				((Declaration_itemContext)_localctx).id = match(ID);
				setState(263);
				match(LTHIRD);
				setState(264);
				((Declaration_itemContext)_localctx).CONST_INT = match(CONST_INT);
				setState(265);
				match(RTHIRD);

				        updateLineNumber(((Declaration_itemContext)_localctx).id);
				        // symbolTable.insertIntoCurrentScope((((Declaration_itemContext)_localctx).id!=null?((Declaration_itemContext)_localctx).id.getText():null), "ID", printMessage);
				        boolean success = symbolTable.insertArray((((Declaration_itemContext)_localctx).id!=null?((Declaration_itemContext)_localctx).id.getText():null), "ID", Integer.parseInt((((Declaration_itemContext)_localctx).CONST_INT!=null?((Declaration_itemContext)_localctx).CONST_INT.getText():null)), printMessage);
				        
				        // SymbolInfo arr = symbolTable.lookup((((Declaration_itemContext)_localctx).id!=null?((Declaration_itemContext)_localctx).id.getText():null), true);
				        // arr.setDataType("int");

				        if (!success) {
				            errorCount++;
				            writeIntoErrorFile("Error at line " + currentLine + ": Multiple declaration of " + (((Declaration_itemContext)_localctx).id!=null?((Declaration_itemContext)_localctx).id.getText():null));
				        }
				        // ((Declaration_itemContext)_localctx).name =  (((Declaration_itemContext)_localctx).id!=null?((Declaration_itemContext)_localctx).id.getText():null); 
				        ((Declaration_itemContext)_localctx).name =  (((Declaration_itemContext)_localctx).id!=null?((Declaration_itemContext)_localctx).id.getText():null) + "[" + (((Declaration_itemContext)_localctx).CONST_INT!=null?((Declaration_itemContext)_localctx).CONST_INT.getText():null) + "]";
				        // writeIntoParserLogFile("Line " + ((Declaration_itemContext)_localctx).id.getLine() + ": declaration_list : ID LTHIRD CONST_INT RTHIRD\n\n" + (((Declaration_itemContext)_localctx).id!=null?((Declaration_itemContext)_localctx).id.getText():null) + "[" + (((Declaration_itemContext)_localctx).CONST_INT!=null?((Declaration_itemContext)_localctx).CONST_INT.getText():null) + "]\n");
				    
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(267);
				((Declaration_itemContext)_localctx).id = match(ID);
				setState(268);
				match(LTHIRD);
				setState(269);
				match(CONST_FLOAT);
				setState(270);
				match(RTHIRD);

				        updateLineNumber(((Declaration_itemContext)_localctx).id);
				        errorCount++;
				        writeIntoErrorFile("Error at line " + currentLine + ": Expression inside third brackets not an integer");
				    
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

	@SuppressWarnings("CheckReturnValue")
	public static class StatementsContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
	}

	public final StatementsContext statements() throws RecognitionException {
		return statements(0);
	}

	private StatementsContext statements(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatementsContext _localctx = new StatementsContext(_ctx, _parentState);
		StatementsContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_statements, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(275);
			statement();
			 { writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": statements : statement\n" + _localctx.getText() + "\n"); } 
			}
			_ctx.stop = _input.LT(-1);
			setState(284);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StatementsContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_statements);
					setState(278);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(279);
					statement();
					 { writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": statements : statements : statement\n" + _localctx.getText() + "\n"); } 
					}
					} 
				}
				setState(286);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public Expression_statementContext es1;
		public Expression_statementContext es2;
		public StatementContext s;
		public StatementContext S;
		public Token ID;
		public Var_declarationContext var_declaration() {
			return getRuleContext(Var_declarationContext.class,0);
		}
		public List<Expression_statementContext> expression_statement() {
			return getRuleContexts(Expression_statementContext.class);
		}
		public Expression_statementContext expression_statement(int i) {
			return getRuleContext(Expression_statementContext.class,i);
		}
		public Compound_statementContext compound_statement() {
			return getRuleContext(Compound_statementContext.class,0);
		}
		public TerminalNode FOR() { return getToken(C2105013Parser.FOR, 0); }
		public TerminalNode LPAREN() { return getToken(C2105013Parser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(C2105013Parser.RPAREN, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode IF() { return getToken(C2105013Parser.IF, 0); }
		public TerminalNode ELSE() { return getToken(C2105013Parser.ELSE, 0); }
		public TerminalNode WHILE() { return getToken(C2105013Parser.WHILE, 0); }
		public TerminalNode PRINTLN() { return getToken(C2105013Parser.PRINTLN, 0); }
		public TerminalNode ID() { return getToken(C2105013Parser.ID, 0); }
		public TerminalNode SEMICOLON() { return getToken(C2105013Parser.SEMICOLON, 0); }
		public TerminalNode PRINTF() { return getToken(C2105013Parser.PRINTF, 0); }
		public TerminalNode RETURN() { return getToken(C2105013Parser.RETURN, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_statement);
		try {
			setState(354);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(287);
				var_declaration();
				 writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": statement : var_declaration\n" + _localctx.getText() + "\n"); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(290);
				expression_statement();
				 writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": statement : expression_statement\n" + _localctx.getText() + "\n"); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(293);
				compound_statement();
				 writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": statement : compound_statement\n" + _localctx.getText() + "\n"); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(296);
				match(FOR);
				setState(297);
				match(LPAREN);
				setState(298);
				((StatementContext)_localctx).es1 = expression_statement();
				 
				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": statement : FOR LPAREN expression_statement expression_statement expression RPAREN statement\n" + _localctx.getText() + "\n"); 

				        String loop_label = newLabel();
						String label_start = "FOR_START_" + loop_label;
						String label_end = "FOR_END_" + loop_label;
						String label_body = "FOR_BODY_" + loop_label;
						String label_update = "FOR_UPDATE_" + loop_label;
						writeIntoAssemblyLogFile(label_start + ":"); // start of for loop
					 
				setState(300);
				((StatementContext)_localctx).es2 = expression_statement();

						writeIntoAssemblyLogFile("POP AX" + " ; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n");
						writeIntoAssemblyLogFile("CMP AX, 0");
						writeIntoAssemblyLogFile("JE " + label_end);  // if condition is false,
						writeIntoAssemblyLogFile("JMP " + label_body); // jump to body if condition is true
						writeIntoAssemblyLogFile(label_update + ":"); // update section starts here

					  
				setState(302);
				expression();

						writeIntoAssemblyLogFile("JMP " + label_start); // jump back to start of loop
						writeIntoAssemblyLogFile(label_body + ":"); // body of the loop starts here

					  
				setState(304);
				match(RPAREN);
				setState(305);
				((StatementContext)_localctx).s = statement();

						 writeIntoAssemblyLogFile("JMP " + label_update);
						 writeIntoAssemblyLogFile(label_end + ":");
					
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(308);
				match(IF);
				setState(309);
				match(LPAREN);
				setState(310);
				expression();

				        // if without else
				        String endLabel = newLabel();
				        elseLabels.add(endLabel);
				        writeIntoAssemblyLogFile("POP AX" + "; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()));
				        writeIntoAssemblyLogFile("CMP AX, 0\n");
				        writeIntoAssemblyLogFile("JE " + endLabel + "\n");
				    
				setState(312);
				match(RPAREN);
				setState(313);
				statement();
				         
				        // if without else continued
				        writeIntoAssemblyLogFile(elseLabels.get(elseLabels.size() - 1) + ":\n" + "; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()));
				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": statement : IF LPAREN expression RPAREN statement\n" + _localctx.getText() + "\n"); 
				    
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(316);
				match(IF);
				setState(317);
				match(LPAREN);
				setState(318);
				expression();

				        String elseLabel = newLabel();
				        elseLabels.add(elseLabel);
				        writeIntoAssemblyLogFile("POP AX" + "; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()));
				        writeIntoAssemblyLogFile("CMP AX, 0\n");   
				        writeIntoAssemblyLogFile("JE " + elseLabel + "\n");
				    
				setState(320);
				match(RPAREN);
				setState(321);
				statement();

				        String exitLabel = newLabel();
				        exitLabels.add(exitLabel);
				        writeIntoAssemblyLogFile("JMP " + exitLabel + "\n");
				        writeIntoAssemblyLogFile(elseLabels.get(elseLabels.size() - 1) + ":\n");
				        elseLabels.remove(elseLabels.size() - 1);
				     
				setState(323);
				match(ELSE);
				setState(324);
				statement();

				        writeIntoAssemblyLogFile(exitLabels.get(exitLabels.size() - 1) + ":\n");
				        exitLabels.remove(exitLabels.size() - 1);
				      
				 writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": statement : IF LPAREN expression RPAREN statement ELSE statement\n" + _localctx.getText() + "\n"); 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(328);
				match(WHILE);

				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": statement : WHILE LPAREN expression RPAREN statement\n" + _localctx.getText() + "\n");

				        String loop_label = newLabel();
						String label_start = "WHILE_START_" + loop_label;
						String label_end = "WHILE_END_" + loop_label;
						writeIntoAssemblyLogFile(label_start + ":");        // start of while loop
					  
				setState(330);
				match(LPAREN);
				setState(331);
				expression();

						writeIntoAssemblyLogFile("POP AX"  + " ; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n");
						writeIntoAssemblyLogFile("CMP AX, 0\n");
						writeIntoAssemblyLogFile("JE " + label_end + "\n");  // if condition is false, exit loop

					 
				setState(333);
				match(RPAREN);
				setState(334);
				((StatementContext)_localctx).S = statement();

						writeIntoAssemblyLogFile("JMP " + label_start + "\n"); // jump back to start of loop
						writeIntoAssemblyLogFile(label_end + ":");          // end of while loop
					
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(337);
				match(PRINTLN);
				setState(338);
				match(LPAREN);
				setState(339);
				((StatementContext)_localctx).ID = match(ID);
				setState(340);
				match(RPAREN);
				setState(341);
				match(SEMICOLON);
				 
				            writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": statement : PRINTLN LPAREN ID RPAREN SEMICOLON\n" + _localctx.getText() + "\n"); 
				            String var = (((StatementContext)_localctx).ID!=null?((StatementContext)_localctx).ID.getText():null);

				            SymbolInfo symbolInfo = symbolTable.lookup(var);

				            

				            if (symbolInfo.isGlobal()) {
				                writeIntoAssemblyLogFile("MOV AX, " + var + "\t; Line " + ((StatementContext)_localctx).ID.getLine() + "\n");
				            } else { // local variable
				                int offset = symbolInfo.getStackOffset();
				                writeIntoAssemblyLogFile("MOV AX, [BP" + (offset >= 0 ? "-" : "") + offset + "]\t; Line " + ((StatementContext)_localctx).ID.getLine() + "\n");
				            }
				            writeIntoAssemblyLogFile("CALL print_output\n" + "; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()));
				            writeIntoAssemblyLogFile("CALL new_line\n");
				    
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(343);
				match(PRINTF);
				setState(344);
				match(LPAREN);
				setState(345);
				((StatementContext)_localctx).ID = match(ID);
				setState(346);
				match(RPAREN);
				setState(347);
				match(SEMICOLON);
				 
				        // check if id is declared
				        if (symbolTable.lookup((((StatementContext)_localctx).ID!=null?((StatementContext)_localctx).ID.getText():null)) == null) {
				            writeIntoErrorFile("Error at Line " + ((StatementContext)_localctx).ID.getLine() + 
				                             ": Undeclared variable '" + (((StatementContext)_localctx).ID!=null?((StatementContext)_localctx).ID.getText():null) + "'");
				        }
				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": statement : PRINTF LPAREN ID RPAREN SEMICOLON\n" + _localctx.getText() + "\n"); 
				    
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(349);
				match(RETURN);
				setState(350);
				expression();
				setState(351);
				match(SEMICOLON);
				 
				        

				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": statement : RETURN expression SEMICOLON\n" + _localctx.getText() + "\n"); 
				        
				        if (currentFunctionReturnType != null && currentFunctionReturnType.equals("void")) {
				              writeIntoErrorFile("Error at line " + _localctx.start.getLine() + 
				                               ": Cannot return value from function " + currentFunctionName + " with void return type");
				              errorCount++;
				        }

				        writeIntoAssemblyLogFile("POP AX");
				        if(!currentFunctionName.equals("MAIN")) writeIntoAssemblyLogFile("JMP " + currentFunctionName + "_EXIT_LABEL" + "\n");
				        // writeIntoAssemblyLogFile("RET 4"); // HARDCODED

				    
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

	@SuppressWarnings("CheckReturnValue")
	public static class Expression_statementContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(C2105013Parser.SEMICOLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Expression_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_statement; }
	}

	public final Expression_statementContext expression_statement() throws RecognitionException {
		Expression_statementContext _localctx = new Expression_statementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_expression_statement);
		try {
			setState(362);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SEMICOLON:
				enterOuterAlt(_localctx, 1);
				{
				setState(356);
				match(SEMICOLON);
				 writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": expression_statement : SEMICOLON\n;\n"); 
				}
				break;
			case LPAREN:
			case ADDOP:
			case NOT:
			case ID:
			case CONST_INT:
			case CONST_FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(358);
				expression();
				setState(359);
				match(SEMICOLON);
				 writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": expression_statement : expression SEMICOLON\n" + _localctx.getText() + "\n"); 
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

	@SuppressWarnings("CheckReturnValue")
	public static class VariableContext extends ParserRuleContext {
		public boolean isArray;
		public Token ID;
		public Token id;
		public ExpressionContext e;
		public TerminalNode ID() { return getToken(C2105013Parser.ID, 0); }
		public TerminalNode LTHIRD() { return getToken(C2105013Parser.LTHIRD, 0); }
		public TerminalNode RTHIRD() { return getToken(C2105013Parser.RTHIRD, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_variable);
		try {
			setState(372);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(364);
				((VariableContext)_localctx).ID = match(ID);
				 
				        ((VariableContext)_localctx).isArray =  false;
				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": variable : ID\n" + _localctx.getText() + "\n"); 

				        // if (symbolTable.lookup((((VariableContext)_localctx).ID!=null?((VariableContext)_localctx).ID.getText():null)) == null) {
				        //     writeIntoErrorFile("Error at Line " + ((VariableContext)_localctx).ID.getLine() + 
				        //                      ": Undeclared variable '" + (((VariableContext)_localctx).ID!=null?((VariableContext)_localctx).ID.getText():null) + "'");
				        //     errorCount++;
				        // }

				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(366);
				((VariableContext)_localctx).id = match(ID);
				setState(367);
				match(LTHIRD);
				setState(368);
				((VariableContext)_localctx).e = expression();
				setState(369);
				match(RTHIRD);
				 
				        SymbolInfo info = symbolTable.lookup((((VariableContext)_localctx).id!=null?((VariableContext)_localctx).id.getText():null));
				          if (info != null && !info.isArray()) {
				              writeIntoErrorFile("Error at line " + ((VariableContext)_localctx).id.getLine() + 
				                               ": " + (((VariableContext)_localctx).id!=null?((VariableContext)_localctx).id.getText():null) + " not an array");
				              errorCount++;
				          }

				        if (info != null && info.isArray()) {
				            // Check array bounds if constant index
				            if ((((VariableContext)_localctx).e!=null?_input.getText(((VariableContext)_localctx).e.start,((VariableContext)_localctx).e.stop):null).matches("\\d+")) {
				                int index = Integer.parseInt((((VariableContext)_localctx).e!=null?_input.getText(((VariableContext)_localctx).e.start,((VariableContext)_localctx).e.stop):null));
				                int arraySize = info.getArraySize();
				                if (index >= arraySize) {
				                    writeIntoErrorFile("Error at line " + _localctx.start.getLine() + 
				                                     ": Array index " + index + " out of bounds for size " + arraySize);
				                    errorCount++;
				                }
				            }
				        }

				        ((VariableContext)_localctx).isArray =  true;
				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": variable : ID LTHIRD expression RTHIRD\n" + _localctx.getText() + "\n"); 
				        if ((((VariableContext)_localctx).e!=null?_input.getText(((VariableContext)_localctx).e.start,((VariableContext)_localctx).e.stop):null).matches(".*\\..*")) { // Check for float index
				            writeIntoErrorFile("Error at Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + 
				                             ": Expression inside third brackets not an integer");
				            errorCount++;
				        }
				    
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public Logic_expressionContext logic_expression;
		public VariableContext v;
		public Logic_expressionContext e;
		public Token ASSIGNOP;
		public Expression_fragmentContext expression_fragment;
		public List<Expression_fragmentContext> junk = new ArrayList<Expression_fragmentContext>();
		public Logic_expressionContext logic_expression() {
			return getRuleContext(Logic_expressionContext.class,0);
		}
		public List<TerminalNode> ASSIGNOP() { return getTokens(C2105013Parser.ASSIGNOP); }
		public TerminalNode ASSIGNOP(int i) {
			return getToken(C2105013Parser.ASSIGNOP, i);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode LTHIRD() { return getToken(C2105013Parser.LTHIRD, 0); }
		public TerminalNode CONST_INT() { return getToken(C2105013Parser.CONST_INT, 0); }
		public TerminalNode RTHIRD() { return getToken(C2105013Parser.RTHIRD, 0); }
		public List<Expression_fragmentContext> expression_fragment() {
			return getRuleContexts(Expression_fragmentContext.class);
		}
		public Expression_fragmentContext expression_fragment(int i) {
			return getRuleContext(Expression_fragmentContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_expression);
		int _la;
		try {
			setState(403);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(374);
				((ExpressionContext)_localctx).logic_expression = logic_expression();
				 writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": expression : logic_expression\n" + (((ExpressionContext)_localctx).logic_expression!=null?_input.getText(((ExpressionContext)_localctx).logic_expression.start,((ExpressionContext)_localctx).logic_expression.stop):null) + "\n"); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(377);
				((ExpressionContext)_localctx).v = variable();
				setState(381);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LTHIRD) {
					{
					setState(378);
					match(LTHIRD);
					setState(379);
					match(CONST_INT);
					setState(380);
					match(RTHIRD);
					}
				}

				setState(383);
				match(ASSIGNOP);
				setState(384);
				((ExpressionContext)_localctx).e = logic_expression();
				 
				        SymbolInfo var = symbolTable.lookup((((ExpressionContext)_localctx).v!=null?_input.getText(((ExpressionContext)_localctx).v.start,((ExpressionContext)_localctx).v.stop):null));
				        writeIntoAssemblyLogFile("POP AX" + "; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()));

				        
				        if (_localctx.LTHIRD() != null) {
				            int index = Integer.parseInt(_localctx.CONST_INT().getText());

				            // this is an array
				            if (var.isGlobal()) {
				                // global array
				                writeIntoAssemblyLogFile("MOV WORD PTR [" + (((ExpressionContext)_localctx).v!=null?_input.getText(((ExpressionContext)_localctx).v.start,((ExpressionContext)_localctx).v.stop):null) + " + " + 2*index + "]" + ", AX\n");
				            }

				            else {
				                // local array
				                int stkoff = var.getStackOffset();

				                writeIntoAssemblyLogFile("MOV [BP-" + (stkoff + index*2) + "], AX\n");
				            }
				        }
				      

				        if (var.isGlobal() && _localctx.LTHIRD() == null) writeIntoAssemblyLogFile("MOV " + var.getName() + ", AX\n");
				        else if ((!var.isGlobal() && _localctx.LTHIRD() == null)) {
				            int stkoff = var.getStackOffset();
				            if (!var.isFunctionParam())  writeIntoAssemblyLogFile("MOV [BP-" + stkoff + "], AX\n");
				            else writeIntoAssemblyLogFile("MOV [BP+" + stkoff + "], AX\n");
				        }

				        String rawVar = (((ExpressionContext)_localctx).v!=null?_input.getText(((ExpressionContext)_localctx).v.start,((ExpressionContext)_localctx).v.stop):null).replaceAll("\\[.*\\]", ""); // Removes anything inside [...]
				        if (symbolTable.lookup(rawVar) == null) {
				            writeIntoErrorFile("Error at Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + 
				                             ": Undeclared variable '" + (((ExpressionContext)_localctx).v!=null?_input.getText(((ExpressionContext)_localctx).v.start,((ExpressionContext)_localctx).v.stop):null) + "'");
				            errorCount++;
				        }


				        // handle type mismatches
				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": expression : variable ASSIGNOP logic_expression\n" + _localctx.getText() + "\n"); 
				        boolean vIsArray; 
				        vIsArray = (symbolTable.lookup((((ExpressionContext)_localctx).v!=null?_input.getText(((ExpressionContext)_localctx).v.start,((ExpressionContext)_localctx).v.stop):null), true) != null && symbolTable.lookup((((ExpressionContext)_localctx).v!=null?_input.getText(((ExpressionContext)_localctx).v.start,((ExpressionContext)_localctx).v.stop):null), true).isArray());
				        
				        if (((ExpressionContext)_localctx).e.isArray && vIsArray) {
				                  writeIntoErrorFile("Error at line " + _localctx.start.getLine() + 
				                                   ": Type mismatch, " + (((ExpressionContext)_localctx).v!=null?_input.getText(((ExpressionContext)_localctx).v.start,((ExpressionContext)_localctx).v.stop):null) + " is not an array");
				                  errorCount++;
				        }


				        SymbolInfo varInfo = symbolTable.lookup(((ExpressionContext)_localctx).v.getText());
				        if (varInfo != null) {
				            // Check if assigning float to int
				            if (varInfo.getDataType().equals("int") && (((ExpressionContext)_localctx).e!=null?_input.getText(((ExpressionContext)_localctx).e.start,((ExpressionContext)_localctx).e.stop):null).matches(".*\\d+\\.\\d+.*") && !(((ExpressionContext)_localctx).e!=null?_input.getText(((ExpressionContext)_localctx).e.start,((ExpressionContext)_localctx).e.stop):null).contains("%")) {
				                writeIntoErrorFile("Error at Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + 
				                                 ": Type mismatch : cannot assign float to int");
				                errorCount++;
				            }
				        }


				        // int c[4]; c[3] = 2.7; -> error!
				        if (varInfo != null) {
				            // Check if assigning to array element
				            if (vIsArray) {
				                // For array assignments, check if assigning float to int array
				                if (varInfo.getDataType().contains("int") && ((ExpressionContext)_localctx).e.isConstFloat) {
				                    writeIntoErrorFile("Error at line " + _localctx.start.getLine() + 
				                                     ": Cannot assign float value to int array");
				                    errorCount++;
				                }
				            }
				        }
				      
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(387);
				((ExpressionContext)_localctx).v = variable();
				setState(388);
				match(ASSIGNOP);
				setState(389);
				((ExpressionContext)_localctx).e = logic_expression();

				            SymbolInfo var = symbolTable.lookup((((ExpressionContext)_localctx).v!=null?_input.getText(((ExpressionContext)_localctx).v.start,((ExpressionContext)_localctx).v.stop):null));

				            writeIntoAssemblyLogFile("POP AX " + "; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()));
				            if (var.isGlobal()) writeIntoAssemblyLogFile("MOV " + var.getName() + ", AX\n");
				            else {
				                int stkoff = var.getStackOffset();
				                if (!var.isFunctionParam()) writeIntoAssemblyLogFile("MOV [BP-" + stkoff + "], AX\n");
				                else writeIntoAssemblyLogFile("MOV [BP+" + stkoff + "], AX\n");
				            }

				            boolean vIsArray; 
				            vIsArray = (symbolTable.lookup((((ExpressionContext)_localctx).v!=null?_input.getText(((ExpressionContext)_localctx).v.start,((ExpressionContext)_localctx).v.stop):null), true) != null && symbolTable.lookup((((ExpressionContext)_localctx).v!=null?_input.getText(((ExpressionContext)_localctx).v.start,((ExpressionContext)_localctx).v.stop):null), true).isArray());

				            if(vIsArray && !((ExpressionContext)_localctx).e.isArray && !(((ExpressionContext)_localctx).v!=null?_input.getText(((ExpressionContext)_localctx).v.start,((ExpressionContext)_localctx).v.stop):null).contains("[")) {
				                writeIntoErrorFile("Error at Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + 
				                                 ": Type mismatch : " + (((ExpressionContext)_localctx).v!=null?_input.getText(((ExpressionContext)_localctx).v.start,((ExpressionContext)_localctx).v.stop):null) + " is an array");
				            } 

				      
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(392);
				((ExpressionContext)_localctx).v = variable();
				setState(393);
				((ExpressionContext)_localctx).ASSIGNOP = match(ASSIGNOP);
				setState(397);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 64667877376L) != 0)) {
					{
					{
					setState(394);
					((ExpressionContext)_localctx).expression_fragment = expression_fragment();
					((ExpressionContext)_localctx).junk.add(((ExpressionContext)_localctx).expression_fragment);
					}
					}
					setState(399);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(400);
				((ExpressionContext)_localctx).ASSIGNOP = match(ASSIGNOP);

				          writeIntoErrorFile("Error at line " + ((ExpressionContext)_localctx).ASSIGNOP.getLine() + ": syntax error, unexpected ASSIGNOP");
				          errorCount++;
				      
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

	@SuppressWarnings("CheckReturnValue")
	public static class Logic_expressionContext extends ParserRuleContext {
		public boolean isArray;
		public boolean isConstFloat;
		public Rel_expressionContext rel_expression;
		public Rel_expressionContext r1;
		public Token LOGICOP;
		public Rel_expressionContext r2;
		public List<Rel_expressionContext> rel_expression() {
			return getRuleContexts(Rel_expressionContext.class);
		}
		public Rel_expressionContext rel_expression(int i) {
			return getRuleContext(Rel_expressionContext.class,i);
		}
		public TerminalNode LOGICOP() { return getToken(C2105013Parser.LOGICOP, 0); }
		public Logic_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logic_expression; }
	}

	public final Logic_expressionContext logic_expression() throws RecognitionException {
		Logic_expressionContext _localctx = new Logic_expressionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_logic_expression);
		try {
			setState(415);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(405);
				((Logic_expressionContext)_localctx).rel_expression = rel_expression();
				 
				        ((Logic_expressionContext)_localctx).isArray =  ((Logic_expressionContext)_localctx).rel_expression.isArray;
				        ((Logic_expressionContext)_localctx).isConstFloat =  ((Logic_expressionContext)_localctx).rel_expression.isConstFloat;
				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": logic_expression : rel_expression\n" + (((Logic_expressionContext)_localctx).rel_expression!=null?_input.getText(((Logic_expressionContext)_localctx).rel_expression.start,((Logic_expressionContext)_localctx).rel_expression.stop):null) + "\n"); 
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				 
				        // Declare labels at the start 
				        String trueLabel = newLabel();
				        String falseLabel = newLabel();
				        String endLabel = newLabel();
				      
				setState(409);
				((Logic_expressionContext)_localctx).r1 = rel_expression();
				setState(410);
				((Logic_expressionContext)_localctx).LOGICOP = match(LOGICOP);

				        if ((((Logic_expressionContext)_localctx).LOGICOP!=null?((Logic_expressionContext)_localctx).LOGICOP.getText():null).equals("&&")) {
				            // For AND: if first is false, jump to false (short-circuit)
				            writeIntoAssemblyLogFile("POP AX " + "; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()));
				            writeIntoAssemblyLogFile("CMP AX, 0\n");
				            writeIntoAssemblyLogFile("JE " + falseLabel + "\n");
				        }
				        else if ((((Logic_expressionContext)_localctx).LOGICOP!=null?((Logic_expressionContext)_localctx).LOGICOP.getText():null).equals("||")) {
				            // For OR: if first is true, jump to true (short-circuit)
				            writeIntoAssemblyLogFile("POP AX "  + " ; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n");
				            writeIntoAssemblyLogFile("CMP AX, 1\n");
				            writeIntoAssemblyLogFile("JE " + trueLabel + "\n");
				        }
				      
				setState(412);
				((Logic_expressionContext)_localctx).r2 = rel_expression();
				 
				        ((Logic_expressionContext)_localctx).isArray =  ((Logic_expressionContext)_localctx).r2.isArray;
				        ((Logic_expressionContext)_localctx).isConstFloat =  ((Logic_expressionContext)_localctx).r2.isConstFloat;
				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": logic_expression : rel_expression LOGICOP rel_expression\n" + _localctx.getText() + "\n"); 
				        
				        // Evaluate second operand
				        writeIntoAssemblyLogFile("POP AX " + "; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()));
				        writeIntoAssemblyLogFile("CMP AX, 0\n");
				        
				        if ((((Logic_expressionContext)_localctx).LOGICOP!=null?((Logic_expressionContext)_localctx).LOGICOP.getText():null).equals("&&")) {
				            // For AND: if second is false, jump to false, else to true
				            writeIntoAssemblyLogFile("JE " + falseLabel + "\n");
				            writeIntoAssemblyLogFile("JMP " + trueLabel + "\n");
				        }
				        else if ((((Logic_expressionContext)_localctx).LOGICOP!=null?((Logic_expressionContext)_localctx).LOGICOP.getText():null).equals("||")) {
				            // For OR: if second is true, jump to true, else to false
				            writeIntoAssemblyLogFile("JNE " + trueLabel + "\n");
				            writeIntoAssemblyLogFile("JMP " + falseLabel + "\n");
				        }
				        
				        // Define the result labels
				        writeIntoAssemblyLogFile(trueLabel + ":\n");
				        writeIntoAssemblyLogFile("PUSH 1\n");
				        writeIntoAssemblyLogFile("JMP " + endLabel + "\n");
				        
				        writeIntoAssemblyLogFile(falseLabel + ":\n");
				        writeIntoAssemblyLogFile("PUSH 0\n");
				        
				        writeIntoAssemblyLogFile(endLabel + ":\n");
				      
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

	@SuppressWarnings("CheckReturnValue")
	public static class Rel_expressionContext extends ParserRuleContext {
		public boolean isArray;
		public boolean isConstFloat;
		public Simple_expressionContext simple_expression;
		public Token RELOP;
		public List<Simple_expressionContext> simple_expression() {
			return getRuleContexts(Simple_expressionContext.class);
		}
		public Simple_expressionContext simple_expression(int i) {
			return getRuleContext(Simple_expressionContext.class,i);
		}
		public TerminalNode RELOP() { return getToken(C2105013Parser.RELOP, 0); }
		public Rel_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rel_expression; }
	}

	public final Rel_expressionContext rel_expression() throws RecognitionException {
		Rel_expressionContext _localctx = new Rel_expressionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_rel_expression);
		try {
			setState(425);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(417);
				((Rel_expressionContext)_localctx).simple_expression = simple_expression(0);
				 
				        ((Rel_expressionContext)_localctx).isConstFloat =  ((Rel_expressionContext)_localctx).simple_expression.isConstFloat;
				        ((Rel_expressionContext)_localctx).isArray =  ((Rel_expressionContext)_localctx).simple_expression.isArray;
				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": rel_expression : simple_expression\n" + (((Rel_expressionContext)_localctx).simple_expression!=null?_input.getText(((Rel_expressionContext)_localctx).simple_expression.start,((Rel_expressionContext)_localctx).simple_expression.stop):null) + "\n"); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(420);
				((Rel_expressionContext)_localctx).simple_expression = simple_expression(0);
				setState(421);
				((Rel_expressionContext)_localctx).RELOP = match(RELOP);
				setState(422);
				((Rel_expressionContext)_localctx).simple_expression = simple_expression(0);
				 
				        ((Rel_expressionContext)_localctx).isConstFloat =  ((Rel_expressionContext)_localctx).simple_expression.isConstFloat;
				        ((Rel_expressionContext)_localctx).isArray =  ((Rel_expressionContext)_localctx).simple_expression.isArray;
				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": rel_expression : simple_expression RELOP simple_expression\n" + _localctx.getText() + "\n"); 

				        String relop = ""; 
				        if ((((Rel_expressionContext)_localctx).RELOP!=null?((Rel_expressionContext)_localctx).RELOP.getText():null).equals("<=")) { relop = "JLE"; }
				        if ((((Rel_expressionContext)_localctx).RELOP!=null?((Rel_expressionContext)_localctx).RELOP.getText():null).equals(">=")) { relop = "JGE"; }
				        if ((((Rel_expressionContext)_localctx).RELOP!=null?((Rel_expressionContext)_localctx).RELOP.getText():null).equals("<")) { relop = "JL"; }
				        if ((((Rel_expressionContext)_localctx).RELOP!=null?((Rel_expressionContext)_localctx).RELOP.getText():null).equals(">")) { relop = "JG"; }
				        if ((((Rel_expressionContext)_localctx).RELOP!=null?((Rel_expressionContext)_localctx).RELOP.getText():null).equals("==")) { relop = "JE"; }
				        if ((((Rel_expressionContext)_localctx).RELOP!=null?((Rel_expressionContext)_localctx).RELOP.getText():null).equals("!=")) { relop = "JNE"; }

				        String trueLabel = newLabel();
				        String falseLabel = newLabel();

				        writeIntoAssemblyLogFile("POP AX " + "; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()));
				        writeIntoAssemblyLogFile("POP CX\n");
				        writeIntoAssemblyLogFile("CMP CX, AX\n");
				        writeIntoAssemblyLogFile(relop + " " + trueLabel + "\n");
				        writeIntoAssemblyLogFile("PUSH 0\n");
				        writeIntoAssemblyLogFile("JMP " + falseLabel + "\n");
				        writeIntoAssemblyLogFile(trueLabel + ":\n");
				        writeIntoAssemblyLogFile("PUSH 1\n");
				        writeIntoAssemblyLogFile(falseLabel + ":\n");
				    
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

	@SuppressWarnings("CheckReturnValue")
	public static class Simple_expressionContext extends ParserRuleContext {
		public boolean isArray;
		public boolean isConstFloat;
		public TermContext term;
		public Token ADDOP;
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public Simple_expressionContext simple_expression() {
			return getRuleContext(Simple_expressionContext.class,0);
		}
		public TerminalNode ADDOP() { return getToken(C2105013Parser.ADDOP, 0); }
		public Simple_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_expression; }
	}

	public final Simple_expressionContext simple_expression() throws RecognitionException {
		return simple_expression(0);
	}

	private Simple_expressionContext simple_expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Simple_expressionContext _localctx = new Simple_expressionContext(_ctx, _parentState);
		Simple_expressionContext _prevctx = _localctx;
		int _startState = 42;
		enterRecursionRule(_localctx, 42, RULE_simple_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(428);
			((Simple_expressionContext)_localctx).term = term(0);
			 
			        ((Simple_expressionContext)_localctx).isArray =  ((Simple_expressionContext)_localctx).term.isArray;
			        ((Simple_expressionContext)_localctx).isConstFloat =  ((Simple_expressionContext)_localctx).term.isConstFloat;
			        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": simple_expression : term\n" + (((Simple_expressionContext)_localctx).term!=null?_input.getText(((Simple_expressionContext)_localctx).term.start,((Simple_expressionContext)_localctx).term.stop):null) + "\n"); 
			}
			_ctx.stop = _input.LT(-1);
			setState(438);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Simple_expressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_simple_expression);
					setState(431);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(432);
					((Simple_expressionContext)_localctx).ADDOP = match(ADDOP);
					setState(433);
					((Simple_expressionContext)_localctx).term = term(0);
					 
					                  ((Simple_expressionContext)_localctx).isArray =  ((Simple_expressionContext)_localctx).term.isArray;
					                  ((Simple_expressionContext)_localctx).isConstFloat =  ((Simple_expressionContext)_localctx).term.isConstFloat;
					                  writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": simple_expression : simple_expression ADDOP term\n" + _localctx.getText() + "\n"); 

					                  writeIntoAssemblyLogFile("POP AX " + "; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()));
					                  writeIntoAssemblyLogFile("POP CX\n");

					                  if((((Simple_expressionContext)_localctx).ADDOP!=null?((Simple_expressionContext)_localctx).ADDOP.getText():null).equals("+")) {
					                      writeIntoAssemblyLogFile("ADD CX, AX\n");
					                  } 
					                  else{
					                      writeIntoAssemblyLogFile("SUB CX, AX\n");
					                  }

					                  writeIntoAssemblyLogFile("PUSH CX\n");
					              
					}
					} 
				}
				setState(440);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public boolean isArray;
		public boolean isConstFloat;
		public TermContext t1;
		public Unary_expressionContext unary_expression;
		public Token op;
		public Unary_expressionContext u;
		public Unary_expressionContext unary_expression() {
			return getRuleContext(Unary_expressionContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode MULOP() { return getToken(C2105013Parser.MULOP, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	}

	public final TermContext term() throws RecognitionException {
		return term(0);
	}

	private TermContext term(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TermContext _localctx = new TermContext(_ctx, _parentState);
		TermContext _prevctx = _localctx;
		int _startState = 44;
		enterRecursionRule(_localctx, 44, RULE_term, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(442);
			((TermContext)_localctx).unary_expression = unary_expression();
			 
			        ((TermContext)_localctx).isConstFloat =  ((TermContext)_localctx).unary_expression.isConstFloat;
			        ((TermContext)_localctx).isArray =  ((TermContext)_localctx).unary_expression.isArray;
			        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": term : unary_expression\n" + (((TermContext)_localctx).unary_expression!=null?_input.getText(((TermContext)_localctx).unary_expression.start,((TermContext)_localctx).unary_expression.stop):null) + "\n"); 
			}
			_ctx.stop = _input.LT(-1);
			setState(452);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TermContext(_parentctx, _parentState);
					_localctx.t1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_term);
					setState(445);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(446);
					((TermContext)_localctx).op = match(MULOP);
					setState(447);
					((TermContext)_localctx).u = ((TermContext)_localctx).unary_expression = unary_expression();
					 
					                  ((TermContext)_localctx).isConstFloat =  ((TermContext)_localctx).unary_expression.isConstFloat;
					                  ((TermContext)_localctx).isArray =  ((TermContext)_localctx).unary_expression.isArray;
					                  // if MULOP is modulus then number of terms can only be two

					                  writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": term : term MULOP unary_expression\n" + _localctx.getText() + "\n"); 
					                  if ((((TermContext)_localctx).op!=null?((TermContext)_localctx).op.getText():null).equals("%")) {

					                      //modulus by zero check
					                      if ((((TermContext)_localctx).u!=null?_input.getText(((TermContext)_localctx).u.start,((TermContext)_localctx).u.stop):null).equals("0")) {
					                            writeIntoErrorFile("Error at line " + _localctx.start.getLine() + 
					                                             ": Modulus by Zero");
					                            errorCount++;
					                      }

					                      SymbolInfo symbolinfo1 = symbolTable.lookup((((TermContext)_localctx).t1!=null?_input.getText(((TermContext)_localctx).t1.start,((TermContext)_localctx).t1.stop):null), true);
					                      SymbolInfo symbolinfo2 = symbolTable.lookup((((TermContext)_localctx).u!=null?_input.getText(((TermContext)_localctx).u.start,((TermContext)_localctx).u.stop):null), true);

					                      boolean isFloat1 = symbolinfo1 == null && (((TermContext)_localctx).t1!=null?_input.getText(((TermContext)_localctx).t1.start,((TermContext)_localctx).t1.stop):null).matches(".*\\..*");
					                      boolean isFloat2 = symbolinfo2 == null && (((TermContext)_localctx).u!=null?_input.getText(((TermContext)_localctx).u.start,((TermContext)_localctx).u.stop):null).matches(".*\\..*");

					                      if (symbolinfo1 != null) isFloat1 = symbolinfo1.getDataType().equals("float");
					                      if (symbolinfo2 != null) isFloat2 = symbolinfo2.getDataType().equals("float");

					                      // if ((((TermContext)_localctx).t1!=null?_input.getText(((TermContext)_localctx).t1.start,((TermContext)_localctx).t1.stop):null).matches(".*\\..*") || (((TermContext)_localctx).u!=null?_input.getText(((TermContext)_localctx).u.start,((TermContext)_localctx).u.stop):null).matches(".*\\..*")) {

					                      if ( isFloat1 || isFloat2 )
					                      {
					                      
					                          // if either of the operands is a float then error
					                          writeIntoErrorFile("Error at Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + 
					                                           ": Non-integer operands for modulus operator");
					                          errorCount++;
					                      }
					                  }

					                  
					                  writeIntoAssemblyLogFile("POP CX " + "; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()));
					                  writeIntoAssemblyLogFile("POP AX\n");

					                  // Converts word to double-word, does sign extension for DX:AX
					                  writeIntoAssemblyLogFile("CWD\n");

					                  if((((TermContext)_localctx).op!=null?((TermContext)_localctx).op.getText():null).equals("*")){
					                      writeIntoAssemblyLogFile("MUL CX\n");
					                  }
					                  else {       // divide or modulus                   
					                      writeIntoAssemblyLogFile("IDIV CX\n"); 
					                  }

					                  if((((TermContext)_localctx).op!=null?((TermContext)_localctx).op.getText():null).equals("%")){
					                      writeIntoAssemblyLogFile("PUSH DX\n");
					                  }else{
					                      writeIntoAssemblyLogFile("PUSH AX\n");
					                  }
					              
					}
					} 
				}
				setState(454);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Unary_expressionContext extends ParserRuleContext {
		public boolean isConstFloat;
		public boolean isArray;
		public Token ADDOP;
		public Unary_expressionContext u;
		public FactorContext factor;
		public TerminalNode ADDOP() { return getToken(C2105013Parser.ADDOP, 0); }
		public Unary_expressionContext unary_expression() {
			return getRuleContext(Unary_expressionContext.class,0);
		}
		public TerminalNode NOT() { return getToken(C2105013Parser.NOT, 0); }
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public Unary_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_expression; }
	}

	public final Unary_expressionContext unary_expression() throws RecognitionException {
		Unary_expressionContext _localctx = new Unary_expressionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_unary_expression);
		try {
			setState(466);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADDOP:
				enterOuterAlt(_localctx, 1);
				{
				setState(455);
				((Unary_expressionContext)_localctx).ADDOP = match(ADDOP);
				setState(456);
				((Unary_expressionContext)_localctx).u = unary_expression();
				 

				        writeIntoAssemblyLogFile("POP AX" +   " ; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n");

				        if((((Unary_expressionContext)_localctx).ADDOP!=null?((Unary_expressionContext)_localctx).ADDOP.getText():null).equals("-")) {
				            writeIntoAssemblyLogFile("NEG AX");
				        }

				        writeIntoAssemblyLogFile("PUSH AX");


				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": unary_expression : ADDOP unary_expression\n" + _localctx.getText() + "\n"); 
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(459);
				match(NOT);
				setState(460);
				unary_expression();
				{ 
				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": unary_expression : NOT unary_expression\n" + _localctx.getText() + "\n"); 
				        String not_label = newLabel();
				        String trueLabel = "NOT_TRUE_" + (not_label);
						String endLabel = "NOT_END_" + (not_label);

						writeIntoAssemblyLogFile("POP AX " +   " ; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n");
						writeIntoAssemblyLogFile("CMP AX, 0");
						writeIntoAssemblyLogFile("MOV AX, 1");
						writeIntoAssemblyLogFile("JE " + endLabel);
						writeIntoAssemblyLogFile("MOV AX, 0");
						writeIntoAssemblyLogFile(endLabel + ":");
						writeIntoAssemblyLogFile("PUSH AX");
				    
				    }
				}
				break;
			case LPAREN:
			case ID:
			case CONST_INT:
			case CONST_FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(463);
				((Unary_expressionContext)_localctx).factor = factor();
				 
				        // ??
				        ((Unary_expressionContext)_localctx).isArray =  ((Unary_expressionContext)_localctx).factor.isArray;
				        ((Unary_expressionContext)_localctx).isConstFloat =  ((Unary_expressionContext)_localctx).factor.isConstFloat;
				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": unary_expression : factor\n" + _localctx.getText() + "\n"); 
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

	@SuppressWarnings("CheckReturnValue")
	public static class FactorContext extends ParserRuleContext {
		public boolean isConstFloat;
		public boolean isArray;
		public VariableContext v;
		public Token id;
		public Argument_listContext arg;
		public VariableContext variable;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(C2105013Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(C2105013Parser.RPAREN, 0); }
		public TerminalNode ID() { return getToken(C2105013Parser.ID, 0); }
		public Argument_listContext argument_list() {
			return getRuleContext(Argument_listContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CONST_INT() { return getToken(C2105013Parser.CONST_INT, 0); }
		public TerminalNode CONST_FLOAT() { return getToken(C2105013Parser.CONST_FLOAT, 0); }
		public TerminalNode INCOP() { return getToken(C2105013Parser.INCOP, 0); }
		public TerminalNode DECOP() { return getToken(C2105013Parser.DECOP, 0); }
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_factor);
		try {
			setState(494);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(468);
				((FactorContext)_localctx).v = variable();
				  
				    // variable may be either array or normal CONST_INT

				    int arrIndex = -1;
				    String arrName = "";
				    if ((((FactorContext)_localctx).v!=null?_input.getText(((FactorContext)_localctx).v.start,((FactorContext)_localctx).v.stop):null).contains("[")) { // this is an array
				        int start = (((FactorContext)_localctx).v!=null?_input.getText(((FactorContext)_localctx).v.start,((FactorContext)_localctx).v.stop):null).indexOf('['), end = (((FactorContext)_localctx).v!=null?_input.getText(((FactorContext)_localctx).v.start,((FactorContext)_localctx).v.stop):null).indexOf(']');
				        arrIndex = Integer.parseInt((((FactorContext)_localctx).v!=null?_input.getText(((FactorContext)_localctx).v.start,((FactorContext)_localctx).v.stop):null).substring(start + 1, end));
				        arrName = (((FactorContext)_localctx).v!=null?_input.getText(((FactorContext)_localctx).v.start,((FactorContext)_localctx).v.stop):null).substring(0, start);
				    }

				    SymbolInfo symbolInfo;
				    if (!(((FactorContext)_localctx).v!=null?_input.getText(((FactorContext)_localctx).v.start,((FactorContext)_localctx).v.stop):null).contains("["))  symbolInfo = symbolTable.lookup((((FactorContext)_localctx).v!=null?_input.getText(((FactorContext)_localctx).v.start,((FactorContext)_localctx).v.stop):null));
				    else symbolInfo = symbolTable.lookup(arrName);

				    if (symbolInfo.isGlobal()) {
				        if ((((FactorContext)_localctx).v!=null?_input.getText(((FactorContext)_localctx).v.start,((FactorContext)_localctx).v.stop):null).contains("[")) {
				        // global array
				            writeIntoAssemblyLogFile( "MOV AX, [" + arrName + " + " + 2*arrIndex + "] "  + " ; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n" );
				            writeIntoAssemblyLogFile( "PUSH AX\n" );
				        }

				        else {
				        // global variable
				            writeIntoAssemblyLogFile( "MOV AX, " + _localctx.getText() + "\n " + "; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) );   
				            writeIntoAssemblyLogFile( "PUSH AX\n" );
				        }
				    }
				    
				    else { // local variable or array
				        if (!(((FactorContext)_localctx).v!=null?_input.getText(((FactorContext)_localctx).v.start,((FactorContext)_localctx).v.stop):null).contains("[")) { // local variable
				            if (!symbolInfo.isFunctionParam()) writeIntoAssemblyLogFile( "MOV AX, [BP-" + symbolInfo.getStackOffset() + "]\n "  + " ; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n"); 
				            else writeIntoAssemblyLogFile("MOV AX, [BP+" + symbolInfo.getStackOffset() + "]\n"  + " ; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n");
				        }  

				        // else local array
				        else writeIntoAssemblyLogFile( "MOV AX, [BP-" + (symbolInfo.getStackOffset() + 2*arrIndex) + "]\n "  + " ; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n");   
				    
				        writeIntoAssemblyLogFile( "PUSH AX\n" );
				    }
				      
				    writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": factor : variable\n" + _localctx.getText() + "\n"); 
				    
				    if (((FactorContext)_localctx).v.isArray) {
				        ((FactorContext)_localctx).isArray =  true;
				        SymbolInfo info = symbolTable.lookup(((FactorContext)_localctx).v.getText());
				            if (info != null && !info.isArray()) {
				                writeIntoErrorFile("Error at Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + 
				                                 ": '" + ((FactorContext)_localctx).v.getText() + "' is not an array");
				                errorCount++;
				            }
				    }

				    else ((FactorContext)_localctx).isArray =  false;

				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(471);
				((FactorContext)_localctx).id = match(ID);
				setState(472);
				match(LPAREN);
				setState(473);
				((FactorContext)_localctx).arg = argument_list();
				setState(474);
				match(RPAREN);

				    // function call

				        int size = ((FactorContext)_localctx).arg.argNames.size();

				        if (size >= 2) {
				            invertTopStackElements(size);
				        }

				        writeIntoAssemblyLogFile("CALL " + (((FactorContext)_localctx).id!=null?((FactorContext)_localctx).id.getText():null)  + " ; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n" );
				        writeIntoAssemblyLogFile("PUSH AX\n");


				        SymbolInfo funcInfo = symbolTable.lookup((((FactorContext)_localctx).id!=null?((FactorContext)_localctx).id.getText():null), true);

				        
				          if (funcInfo == null && !(((FactorContext)_localctx).id!=null?((FactorContext)_localctx).id.getText():null).equals("main")) {
				              writeIntoErrorFile("Error at line " + ((FactorContext)_localctx).id.getLine() + 
				                               ": Undefined function " + (((FactorContext)_localctx).id!=null?((FactorContext)_localctx).id.getText():null));
				              errorCount++;
				          }
				        
				        if (funcInfo != null && funcInfo.isFunction()) {
				            // Check parameter count
				            if (funcInfo.getFunctionInfo().getParameterCount() != ((FactorContext)_localctx).arg.argTypes.size()) {
				                writeIntoErrorFile("Error at Line " + ((FactorContext)_localctx).id.getLine() + 
				                    ": Argument count mismatch for function '" + (((FactorContext)_localctx).id!=null?((FactorContext)_localctx).id.getText():null) + "'");
				                errorCount++;
				            } else {
				                // Check each parameter type
				                for (int i = 0; i < funcInfo.getFunctionInfo().getParameterCount(); i++) {
				                    // SymbolInfo expected = funcInfo.getFunctionInfo().getParameterTypes().get(i);
				                    String expected = funcInfo.getFunctionInfo().getParameterTypes().get(i);
				                    String actualName = ((FactorContext)_localctx).arg.argNames.get(i);
				                    String actual = ((FactorContext)_localctx).arg.argTypes.get(i);

				                    SymbolInfo actualInfo = symbolTable.lookup(actualName, true);
				                    
				                    // Check if passing array to non-array parameter
				                    if (actualInfo != null && actualInfo.isArray() && !expected.equalsIgnoreCase("array")) {
				                        // writeIntoErrorFile("Error at Line " + ((FactorContext)_localctx).id.getLine() + 
				                        //     ": Cannot pass array to non-array parameter"  + 
				                        //     " of function '" + (((FactorContext)_localctx).id!=null?((FactorContext)_localctx).id.getText():null) + "' or vice versa");
				                        writeIntoErrorFile("Error at Line " + ((FactorContext)_localctx).id.getLine() + ": Type mismatch - " + actualName + " is an array");
				                        errorCount++;
				                    }
				                    // Check basic type compatibility
				                    else if (!expected.equalsIgnoreCase(actual)) {
				                        // 1th argument mismatch in function func
				                        writeIntoErrorFile("Error at Line " + ((FactorContext)_localctx).id.getLine() + ": " + (i+1)
				                        + "th argument mismatch in function "
				                             + (((FactorContext)_localctx).id!=null?((FactorContext)_localctx).id.getText():null));

				 
				                        errorCount++;
				                    }
				                }
				            }
				        }
				      



				    //
				      writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": factor : ID LPAREN argument_list RPAREN\n" + _localctx.getText() + "\n"); 
				    
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(477);
				match(LPAREN);
				setState(478);
				expression();
				setState(479);
				match(RPAREN);
				 { writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": factor : LPAREN expression RPAREN\n" + _localctx.getText() + "\n"); } 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(482);
				match(CONST_INT);
				 
				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": factor : CONST_INT\n" + _localctx.getText() + "\n"); 
				        writeIntoAssemblyLogFile( "MOV AX, " + _localctx.getText() + "\n " + "; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()));   
				        writeIntoAssemblyLogFile( "PUSH AX\n" );   
				    
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(484);
				match(CONST_FLOAT);
				 { 
				        ((FactorContext)_localctx).isConstFloat =  true;
				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": factor : CONST_FLOAT\n" + _localctx.getText() + "\n"); } 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(486);
				((FactorContext)_localctx).variable = variable();
				setState(487);
				match(INCOP);
				 { 
				        int arrIndex = -1;
				        String arrName = "";
				        if ((((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null).contains("[")) { // this is an array
				            int start = (((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null).indexOf('['), end = (((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null).indexOf(']');
				            arrIndex = Integer.parseInt((((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null).substring(start + 1, end));
				            arrName = (((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null).substring(0, start);
				        }

				        SymbolInfo symbolInfo;
				        if (!(((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null).contains("["))  symbolInfo = symbolTable.lookup((((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null));
				        else symbolInfo = symbolTable.lookup(arrName);

				        // SymbolInfo symbolInfo = symbolTable.lookup((((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null));


				        if (symbolInfo.isGlobal() && !(((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null).contains("[")) {
				            writeIntoAssemblyLogFile("MOV AX, " + (((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null)  + " ; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n");
				            writeIntoAssemblyLogFile("PUSH AX\n");
				            writeIntoAssemblyLogFile("INC AX\n");
				            writeIntoAssemblyLogFile("MOV " + (((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null) + ", AX\n");
				            // writeIntoAssemblyLogFile("INC " + (((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null) + "\n" + "Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n") ;
				        }

				        else if (!symbolInfo.isGlobal() && !(((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null).contains("[")) {
				            // local variable
				            int stkoffset = symbolInfo.getStackOffset();
				            // writeIntoAssemblyLogFile("INC WORD PTR [BP-" + stkoffset + "]\n" + "; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()));
				            writeIntoAssemblyLogFile("MOV AX, " + "[BP-" + stkoffset + "]"  + " ; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n");
				            writeIntoAssemblyLogFile("PUSH AX\n");
				            writeIntoAssemblyLogFile("INC AX\n");
				            writeIntoAssemblyLogFile("MOV " + "[BP-" + stkoffset + "]" + ", AX\n");
				        }

				        else if (symbolInfo.isGlobal() && (((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null).contains("["))
				        {
				            // global array
				            writeIntoAssemblyLogFile( "MOV AX, [" + arrName + " + " + 2*arrIndex + "] "  + " ; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n");
				            writeIntoAssemblyLogFile("PUSH AX\n");
				            writeIntoAssemblyLogFile("INC AX\n");
				            writeIntoAssemblyLogFile("MOV WORD PTR [" + arrName + " + " + 2*arrIndex + "]" + ", AX\n");
				        }

				        else if(!symbolInfo.isGlobal() && (((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null).contains("["))
				        {
				            // local array
				            writeIntoAssemblyLogFile( "MOV AX, [BP-" + (symbolInfo.getStackOffset() + 2*arrIndex) + "] "  + " ; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n" );   
				            writeIntoAssemblyLogFile("PUSH AX\n");
				            writeIntoAssemblyLogFile("INC AX\n");
				            writeIntoAssemblyLogFile("MOV " + "[BP-" + (symbolInfo.getStackOffset() + 2*arrIndex) + "]" + ", AX\n");
				        }

				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": factor : variable INCOP\n" + _localctx.getText() + "\n"); 
				    } 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(490);
				((FactorContext)_localctx).variable = variable();
				setState(491);
				match(DECOP);
				 { 
				        // SymbolInfo symbolInfo = symbolTable.lookup((((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null));
				        // if (symbolInfo.isGlobal()) {
				        //     // writeIntoAssemblyLogFile("DEC " + (((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null) + "\n");
				        //     writeIntoAssemblyLogFile("MOV AX, " + (((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null) + "\n");
				        //     writeIntoAssemblyLogFile("PUSH AX\n");
				        //     writeIntoAssemblyLogFile("DEC AX\n");
				        //     writeIntoAssemblyLogFile("MOV " + (((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null) + ", AX\n");
				        // }

				        // else {
				        //     // local variable
				        //     int stkoffset = symbolInfo.getStackOffset();
				        //     // writeIntoAssemblyLogFile("DEC WORD PTR [BP-" + stkoffset + "]\n");
				        //     writeIntoAssemblyLogFile("MOV AX, " + "[BP-" + stkoffset + "]\n");
				        //     writeIntoAssemblyLogFile("PUSH AX\n");
				        //     writeIntoAssemblyLogFile("DEC AX\n");
				        //     writeIntoAssemblyLogFile("MOV " + "[BP-" + stkoffset + "]" + ", AX\n");
				        // }

				        int arrIndex = -1;
				        String arrName = "";
				        if ((((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null).contains("[")) { // this is an array
				            int start = (((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null).indexOf('['), end = (((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null).indexOf(']');
				            arrIndex = Integer.parseInt((((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null).substring(start + 1, end));
				            arrName = (((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null).substring(0, start);
				        }

				        SymbolInfo symbolInfo;
				        if (!(((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null).contains("["))  symbolInfo = symbolTable.lookup((((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null));
				        else symbolInfo = symbolTable.lookup(arrName);

				        // SymbolInfo symbolInfo = symbolTable.lookup((((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null));


				        if (symbolInfo.isGlobal() && !(((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null).contains("[")) {
				            writeIntoAssemblyLogFile("MOV AX, " + (((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null)  + " ; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n");
				            writeIntoAssemblyLogFile("PUSH AX\n");
				            writeIntoAssemblyLogFile("DEC AX\n");
				            writeIntoAssemblyLogFile("MOV " + (((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null) + ", AX\n");
				            // writeIntoAssemblyLogFile("INC " + (((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null) + "\n" + "Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n") ;
				        }

				        else if (!symbolInfo.isGlobal() && !(((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null).contains("[")) {
				            // local variable
				            int stkoffset = symbolInfo.getStackOffset();
				            // writeIntoAssemblyLogFile("INC WORD PTR [BP-" + stkoffset + "]\n" + "; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()));
				            writeIntoAssemblyLogFile("MOV AX, " + "[BP-" + stkoffset + "]"  + " ; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n");
				            writeIntoAssemblyLogFile("PUSH AX\n");
				            writeIntoAssemblyLogFile("DEC AX\n");
				            writeIntoAssemblyLogFile("MOV " + "[BP-" + stkoffset + "]" + ", AX\n");
				        }

				        else if (symbolInfo.isGlobal() && (((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null).contains("["))
				        {
				            // global array
				            writeIntoAssemblyLogFile( "MOV AX, [" + arrName + " + " + 2*arrIndex + "] "  + " ; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n" );
				            writeIntoAssemblyLogFile("PUSH AX\n");
				            writeIntoAssemblyLogFile("DEC AX\n");
				            writeIntoAssemblyLogFile("MOV WORD PTR [" + arrName + " + " + 2*arrIndex + "]" + ", AX\n");
				        }

				        else if(!symbolInfo.isGlobal() && (((FactorContext)_localctx).variable!=null?_input.getText(((FactorContext)_localctx).variable.start,((FactorContext)_localctx).variable.stop):null).contains("["))
				        {
				            // local array
				            writeIntoAssemblyLogFile( "MOV AX, [BP-" + (symbolInfo.getStackOffset() + 2*arrIndex) + "] "  + " ; Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + "\n" );   
				            writeIntoAssemblyLogFile("PUSH AX\n");
				            writeIntoAssemblyLogFile("DEC AX\n");
				            writeIntoAssemblyLogFile("MOV " + "[BP-" + (symbolInfo.getStackOffset() + 2*arrIndex) + "]" + ", AX\n");
				        }



				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": factor : variable DECOP\n" + _localctx.getText() + "\n"); } 
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

	@SuppressWarnings("CheckReturnValue")
	public static class Argument_listContext extends ParserRuleContext {
		public List <String> argNames;
		public List<String> argTypes;
		public ArgumentsContext arg;
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public Argument_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument_list; }
	}

	public final Argument_listContext argument_list() throws RecognitionException {
		Argument_listContext _localctx = new Argument_listContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_argument_list);

		    ((Argument_listContext)_localctx).argNames =  new ArrayList<>();
		    ((Argument_listContext)_localctx).argTypes =  new ArrayList<>();

		try {
			setState(500);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
			case ADDOP:
			case NOT:
			case ID:
			case CONST_INT:
			case CONST_FLOAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(496);
				((Argument_listContext)_localctx).arg = arguments();
				 { 
				        ((Argument_listContext)_localctx).argNames =  ((Argument_listContext)_localctx).arg.argNames;
				        ((Argument_listContext)_localctx).argTypes =  ((Argument_listContext)_localctx).arg.argTypes;
				        writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": argument_list : arguments\n" + _localctx.getText() + "\n");
				    } 
				}
				break;
			case RPAREN:
				enterOuterAlt(_localctx, 2);
				{
				 { { writeIntoParserLogFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + ": argument_list : \n" + _localctx.getText() + "\n"); } } 
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentsContext extends ParserRuleContext {
		public List<String> argNames;
		public List<String> argTypes;
		public ArgumentContext a1;
		public ArgumentContext an;
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(C2105013Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(C2105013Parser.COMMA, i);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_arguments);

		    ((ArgumentsContext)_localctx).argNames =  new ArrayList<>();
		    ((ArgumentsContext)_localctx).argTypes =  new ArrayList<>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(502);
			((ArgumentsContext)_localctx).a1 = argument();
			 _localctx.argNames.add(((ArgumentsContext)_localctx).a1.name); _localctx.argTypes.add(((ArgumentsContext)_localctx).a1.type); 
			setState(510);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(504);
				match(COMMA);
				setState(505);
				((ArgumentsContext)_localctx).an = argument();
				 _localctx.argNames.add(((ArgumentsContext)_localctx).an.name); _localctx.argTypes.add(((ArgumentsContext)_localctx).an.type); 
				}
				}
				setState(512);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentContext extends ParserRuleContext {
		public String name;
		public String type;
		public ExpressionContext e;
		public VariableContext v;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_argument);
		try {
			setState(519);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(513);
				((ArgumentContext)_localctx).e = expression();
				 
				        ((ArgumentContext)_localctx).name =  (((ArgumentContext)_localctx).e!=null?_input.getText(((ArgumentContext)_localctx).e.start,((ArgumentContext)_localctx).e.stop):null);
				        // Determine type from expression
				        if ((((ArgumentContext)_localctx).e!=null?_input.getText(((ArgumentContext)_localctx).e.start,((ArgumentContext)_localctx).e.stop):null).matches(".*\\..*")) {
				            ((ArgumentContext)_localctx).type =  "float";
				        } else {
				            ((ArgumentContext)_localctx).type =  "int";
				        }
				      
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(516);
				((ArgumentContext)_localctx).v = variable();

				        ((ArgumentContext)_localctx).name =  (((ArgumentContext)_localctx).v!=null?_input.getText(((ArgumentContext)_localctx).v.start,((ArgumentContext)_localctx).v.stop):null);
				        SymbolInfo info = symbolTable.lookup((((ArgumentContext)_localctx).v!=null?_input.getText(((ArgumentContext)_localctx).v.start,((ArgumentContext)_localctx).v.stop):null), true);
				        if (info != null) {
				            ((ArgumentContext)_localctx).type =  info.getDataType();
				            if (info.isArray()) {
				                // writeIntoErrorFile("Line " + (_localctx.stop == null ? _localctx.start.getLine() : _localctx.stop.getLine()) + 
				                //     ": Cannot pass array '" + (((ArgumentContext)_localctx).v!=null?_input.getText(((ArgumentContext)_localctx).v.start,((ArgumentContext)_localctx).v.stop):null) + "' as parameter");
				                // errorCount++;
				            }
				        } else {
				            ((ArgumentContext)_localctx).type =  "unknown";
				        }
				      
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

	@SuppressWarnings("CheckReturnValue")
	public static class Expression_fragmentContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(C2105013Parser.ID, 0); }
		public TerminalNode CONST_INT() { return getToken(C2105013Parser.CONST_INT, 0); }
		public TerminalNode CONST_FLOAT() { return getToken(C2105013Parser.CONST_FLOAT, 0); }
		public TerminalNode ADDOP() { return getToken(C2105013Parser.ADDOP, 0); }
		public TerminalNode MULOP() { return getToken(C2105013Parser.MULOP, 0); }
		public TerminalNode LPAREN() { return getToken(C2105013Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(C2105013Parser.RPAREN, 0); }
		public TerminalNode INCOP() { return getToken(C2105013Parser.INCOP, 0); }
		public TerminalNode DECOP() { return getToken(C2105013Parser.DECOP, 0); }
		public TerminalNode UNRECOGNIZED() { return getToken(C2105013Parser.UNRECOGNIZED, 0); }
		public Expression_fragmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_fragment; }
	}

	public final Expression_fragmentContext expression_fragment() throws RecognitionException {
		Expression_fragmentContext _localctx = new Expression_fragmentContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_expression_fragment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(521);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 64667877376L) != 0)) ) {
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
		case 1:
			return program_sempred((ProgramContext)_localctx, predIndex);
		case 14:
			return statements_sempred((StatementsContext)_localctx, predIndex);
		case 21:
			return simple_expression_sempred((Simple_expressionContext)_localctx, predIndex);
		case 22:
			return term_sempred((TermContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean program_sempred(ProgramContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean statements_sempred(StatementsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean simple_expression_sempred(Simple_expressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean term_sempred(TermContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001#\u020c\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0005\u0001G\b\u0001\n\u0001\f\u0001J\t\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002U\b\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0003\u0003f\b\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004\u0080\b\u0004"+
		"\n\u0004\f\u0004\u0083\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004\u0089\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0005\u0005\u0095\b\u0005\n\u0005\f\u0005\u0098\t\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005"+
		"\u00a0\b\u0005\n\u0005\f\u0005\u00a3\t\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0005\u0005\u00a9\b\u0005\n\u0005\f\u0005\u00ac\t\u0005"+
		"\u0001\u0005\u0005\u0005\u00af\b\u0005\n\u0005\f\u0005\u00b2\t\u0005\u0003"+
		"\u0005\u00b4\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003"+
		"\u0006\u00c0\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003"+
		"\u0007\u00d2\b\u0007\u0001\b\u0001\b\u0001\b\u0005\b\u00d7\b\b\n\b\f\b"+
		"\u00da\t\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u00e0\b\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u00e8\b\t\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0003\n\u00f0\b\n\u0001\u000b\u0001\u000b\u0005"+
		"\u000b\u00f4\b\u000b\n\u000b\f\u000b\u00f7\t\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003"+
		"\f\u0103\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u0111\b\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0005\u000e\u011b\b\u000e\n\u000e\f\u000e\u011e\t\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0003\u000f\u0163\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0003\u0010\u016b\b\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0003\u0011\u0175\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u017e\b\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012"+
		"\u018c\b\u0012\n\u0012\f\u0012\u018f\t\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0003\u0012\u0194\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0003\u0013\u01a0\b\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u01aa"+
		"\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u01b5\b\u0015\n"+
		"\u0015\f\u0015\u01b8\t\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0005"+
		"\u0016\u01c3\b\u0016\n\u0016\f\u0016\u01c6\t\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u01d3\b\u0017\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0003\u0018\u01ef\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0003\u0019\u01f5\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0005\u001a\u01fd\b\u001a\n\u001a"+
		"\f\u001a\u0200\t\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0003\u001b\u0208\b\u001b\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0000\u0004\u0002\u001c*,\u001d\u0000\u0002\u0004\u0006\b"+
		"\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02"+
		"468\u0000\u0003\u0001\u0000\u0017\u001d\u0001\u0000\u0017\u001f\u0004"+
		"\u0000\u000f\u0010\u0017\u0017\u0019\u001b #\u022a\u0000:\u0001\u0000"+
		"\u0000\u0000\u0002>\u0001\u0000\u0000\u0000\u0004T\u0001\u0000\u0000\u0000"+
		"\u0006e\u0001\u0000\u0000\u0000\b\u0088\u0001\u0000\u0000\u0000\n\u00b3"+
		"\u0001\u0000\u0000\u0000\f\u00bf\u0001\u0000\u0000\u0000\u000e\u00d1\u0001"+
		"\u0000\u0000\u0000\u0010\u00df\u0001\u0000\u0000\u0000\u0012\u00e7\u0001"+
		"\u0000\u0000\u0000\u0014\u00ef\u0001\u0000\u0000\u0000\u0016\u00f1\u0001"+
		"\u0000\u0000\u0000\u0018\u0102\u0001\u0000\u0000\u0000\u001a\u0110\u0001"+
		"\u0000\u0000\u0000\u001c\u0112\u0001\u0000\u0000\u0000\u001e\u0162\u0001"+
		"\u0000\u0000\u0000 \u016a\u0001\u0000\u0000\u0000\"\u0174\u0001\u0000"+
		"\u0000\u0000$\u0193\u0001\u0000\u0000\u0000&\u019f\u0001\u0000\u0000\u0000"+
		"(\u01a9\u0001\u0000\u0000\u0000*\u01ab\u0001\u0000\u0000\u0000,\u01b9"+
		"\u0001\u0000\u0000\u0000.\u01d2\u0001\u0000\u0000\u00000\u01ee\u0001\u0000"+
		"\u0000\u00002\u01f4\u0001\u0000\u0000\u00004\u01f6\u0001\u0000\u0000\u0000"+
		"6\u0207\u0001\u0000\u0000\u00008\u0209\u0001\u0000\u0000\u0000:;\u0006"+
		"\u0000\uffff\uffff\u0000;<\u0003\u0002\u0001\u0000<=\u0006\u0000\uffff"+
		"\uffff\u0000=\u0001\u0001\u0000\u0000\u0000>?\u0006\u0001\uffff\uffff"+
		"\u0000?@\u0003\u0004\u0002\u0000@A\u0006\u0001\uffff\uffff\u0000AH\u0001"+
		"\u0000\u0000\u0000BC\n\u0002\u0000\u0000CD\u0003\u0004\u0002\u0000DE\u0006"+
		"\u0001\uffff\uffff\u0000EG\u0001\u0000\u0000\u0000FB\u0001\u0000\u0000"+
		"\u0000GJ\u0001\u0000\u0000\u0000HF\u0001\u0000\u0000\u0000HI\u0001\u0000"+
		"\u0000\u0000I\u0003\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000\u0000"+
		"KL\u0003\u000e\u0007\u0000LM\u0006\u0002\uffff\uffff\u0000MU\u0001\u0000"+
		"\u0000\u0000NO\u0003\u0006\u0003\u0000OP\u0006\u0002\uffff\uffff\u0000"+
		"PU\u0001\u0000\u0000\u0000QR\u0003\b\u0004\u0000RS\u0006\u0002\uffff\uffff"+
		"\u0000SU\u0001\u0000\u0000\u0000TK\u0001\u0000\u0000\u0000TN\u0001\u0000"+
		"\u0000\u0000TQ\u0001\u0000\u0000\u0000U\u0005\u0001\u0000\u0000\u0000"+
		"VW\u0003\u0012\t\u0000WX\u0005 \u0000\u0000XY\u0005\u000f\u0000\u0000"+
		"YZ\u0003\n\u0005\u0000Z[\u0005\u0010\u0000\u0000[\\\u0005\u0015\u0000"+
		"\u0000\\]\u0006\u0003\uffff\uffff\u0000]f\u0001\u0000\u0000\u0000^_\u0003"+
		"\u0012\t\u0000_`\u0005 \u0000\u0000`a\u0005\u000f\u0000\u0000ab\u0005"+
		"\u0010\u0000\u0000bc\u0005\u0015\u0000\u0000cd\u0006\u0003\uffff\uffff"+
		"\u0000df\u0001\u0000\u0000\u0000eV\u0001\u0000\u0000\u0000e^\u0001\u0000"+
		"\u0000\u0000f\u0007\u0001\u0000\u0000\u0000gh\u0003\u0012\t\u0000hi\u0005"+
		" \u0000\u0000ij\u0005\u000f\u0000\u0000jk\u0003\n\u0005\u0000kl\u0005"+
		"\u0010\u0000\u0000lm\u0006\u0004\uffff\uffff\u0000mn\u0005\u0011\u0000"+
		"\u0000no\u0006\u0004\uffff\uffff\u0000op\u0003\u001c\u000e\u0000pq\u0005"+
		"\u0012\u0000\u0000qr\u0006\u0004\uffff\uffff\u0000r\u0089\u0001\u0000"+
		"\u0000\u0000st\u0003\u0012\t\u0000tu\u0005 \u0000\u0000uv\u0005\u000f"+
		"\u0000\u0000vw\u0005\u0010\u0000\u0000wx\u0006\u0004\uffff\uffff\u0000"+
		"xy\u0003\f\u0006\u0000yz\u0006\u0004\uffff\uffff\u0000z\u0089\u0001\u0000"+
		"\u0000\u0000{|\u0003\u0012\t\u0000|}\u0005 \u0000\u0000}\u0081\u0005\u000f"+
		"\u0000\u0000~\u0080\u00038\u001c\u0000\u007f~\u0001\u0000\u0000\u0000"+
		"\u0080\u0083\u0001\u0000\u0000\u0000\u0081\u007f\u0001\u0000\u0000\u0000"+
		"\u0081\u0082\u0001\u0000\u0000\u0000\u0082\u0084\u0001\u0000\u0000\u0000"+
		"\u0083\u0081\u0001\u0000\u0000\u0000\u0084\u0085\u0005\u0010\u0000\u0000"+
		"\u0085\u0086\u0003\f\u0006\u0000\u0086\u0087\u0006\u0004\uffff\uffff\u0000"+
		"\u0087\u0089\u0001\u0000\u0000\u0000\u0088g\u0001\u0000\u0000\u0000\u0088"+
		"s\u0001\u0000\u0000\u0000\u0088{\u0001\u0000\u0000\u0000\u0089\t\u0001"+
		"\u0000\u0000\u0000\u008a\u008b\u0003\u0014\n\u0000\u008b\u008c\u0006\u0005"+
		"\uffff\uffff\u0000\u008c\u008d\u0005 \u0000\u0000\u008d\u0096\u0006\u0005"+
		"\uffff\uffff\u0000\u008e\u008f\u0005\u0016\u0000\u0000\u008f\u0090\u0003"+
		"\u0014\n\u0000\u0090\u0091\u0006\u0005\uffff\uffff\u0000\u0091\u0092\u0005"+
		" \u0000\u0000\u0092\u0093\u0006\u0005\uffff\uffff\u0000\u0093\u0095\u0001"+
		"\u0000\u0000\u0000\u0094\u008e\u0001\u0000\u0000\u0000\u0095\u0098\u0001"+
		"\u0000\u0000\u0000\u0096\u0094\u0001\u0000\u0000\u0000\u0096\u0097\u0001"+
		"\u0000\u0000\u0000\u0097\u00b4\u0001\u0000\u0000\u0000\u0098\u0096\u0001"+
		"\u0000\u0000\u0000\u0099\u009a\u0003\u0014\n\u0000\u009a\u00a1\u0006\u0005"+
		"\uffff\uffff\u0000\u009b\u009c\u0005\u0016\u0000\u0000\u009c\u009d\u0003"+
		"\u0014\n\u0000\u009d\u009e\u0006\u0005\uffff\uffff\u0000\u009e\u00a0\u0001"+
		"\u0000\u0000\u0000\u009f\u009b\u0001\u0000\u0000\u0000\u00a0\u00a3\u0001"+
		"\u0000\u0000\u0000\u00a1\u009f\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001"+
		"\u0000\u0000\u0000\u00a2\u00b4\u0001\u0000\u0000\u0000\u00a3\u00a1\u0001"+
		"\u0000\u0000\u0000\u00a4\u00a5\u0003\u0014\n\u0000\u00a5\u00b0\u0006\u0005"+
		"\uffff\uffff\u0000\u00a6\u00aa\u0007\u0000\u0000\u0000\u00a7\u00a9\u0003"+
		"\u0014\n\u0000\u00a8\u00a7\u0001\u0000\u0000\u0000\u00a9\u00ac\u0001\u0000"+
		"\u0000\u0000\u00aa\u00a8\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000"+
		"\u0000\u0000\u00ab\u00ad\u0001\u0000\u0000\u0000\u00ac\u00aa\u0001\u0000"+
		"\u0000\u0000\u00ad\u00af\u0006\u0005\uffff\uffff\u0000\u00ae\u00a6\u0001"+
		"\u0000\u0000\u0000\u00af\u00b2\u0001\u0000\u0000\u0000\u00b0\u00ae\u0001"+
		"\u0000\u0000\u0000\u00b0\u00b1\u0001\u0000\u0000\u0000\u00b1\u00b4\u0001"+
		"\u0000\u0000\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000\u00b3\u008a\u0001"+
		"\u0000\u0000\u0000\u00b3\u0099\u0001\u0000\u0000\u0000\u00b3\u00a4\u0001"+
		"\u0000\u0000\u0000\u00b4\u000b\u0001\u0000\u0000\u0000\u00b5\u00b6\u0005"+
		"\u0011\u0000\u0000\u00b6\u00b7\u0006\u0006\uffff\uffff\u0000\u00b7\u00b8"+
		"\u0003\u001c\u000e\u0000\u00b8\u00b9\u0005\u0012\u0000\u0000\u00b9\u00ba"+
		"\u0006\u0006\uffff\uffff\u0000\u00ba\u00c0\u0001\u0000\u0000\u0000\u00bb"+
		"\u00bc\u0005\u0011\u0000\u0000\u00bc\u00bd\u0006\u0006\uffff\uffff\u0000"+
		"\u00bd\u00be\u0005\u0012\u0000\u0000\u00be\u00c0\u0006\u0006\uffff\uffff"+
		"\u0000\u00bf\u00b5\u0001\u0000\u0000\u0000\u00bf\u00bb\u0001\u0000\u0000"+
		"\u0000\u00c0\r\u0001\u0000\u0000\u0000\u00c1\u00c2\u0003\u0012\t\u0000"+
		"\u00c2\u00c3\u0003\u0016\u000b\u0000\u00c3\u00c4\u0005\u0015\u0000\u0000"+
		"\u00c4\u00c5\u0006\u0007\uffff\uffff\u0000\u00c5\u00d2\u0001\u0000\u0000"+
		"\u0000\u00c6\u00c7\u0003\u0012\t\u0000\u00c7\u00c8\u0003\u0010\b\u0000"+
		"\u00c8\u00c9\u0005\u0015\u0000\u0000\u00c9\u00ca\u0006\u0007\uffff\uffff"+
		"\u0000\u00ca\u00d2\u0001\u0000\u0000\u0000\u00cb\u00cc\u0005#\u0000\u0000"+
		"\u00cc\u00d2\u0006\u0007\uffff\uffff\u0000\u00cd\u00d2\u0003\u0012\t\u0000"+
		"\u00ce\u00cf\u0003\u0012\t\u0000\u00cf\u00d0\u0006\u0007\uffff\uffff\u0000"+
		"\u00d0\u00d2\u0001\u0000\u0000\u0000\u00d1\u00c1\u0001\u0000\u0000\u0000"+
		"\u00d1\u00c6\u0001\u0000\u0000\u0000\u00d1\u00cb\u0001\u0000\u0000\u0000"+
		"\u00d1\u00cd\u0001\u0000\u0000\u0000\u00d1\u00ce\u0001\u0000\u0000\u0000"+
		"\u00d2\u000f\u0001\u0000\u0000\u0000\u00d3\u00d8\u0005 \u0000\u0000\u00d4"+
		"\u00d5\u0005\u0016\u0000\u0000\u00d5\u00d7\u0005 \u0000\u0000\u00d6\u00d4"+
		"\u0001\u0000\u0000\u0000\u00d7\u00da\u0001\u0000\u0000\u0000\u00d8\u00d6"+
		"\u0001\u0000\u0000\u0000\u00d8\u00d9\u0001\u0000\u0000\u0000\u00d9\u00e0"+
		"\u0001\u0000\u0000\u0000\u00da\u00d8\u0001\u0000\u0000\u0000\u00db\u00dc"+
		"\u0005 \u0000\u0000\u00dc\u00dd\u0005\u0017\u0000\u0000\u00dd\u00de\u0005"+
		" \u0000\u0000\u00de\u00e0\u0006\b\uffff\uffff\u0000\u00df\u00d3\u0001"+
		"\u0000\u0000\u0000\u00df\u00db\u0001\u0000\u0000\u0000\u00e0\u0011\u0001"+
		"\u0000\u0000\u0000\u00e1\u00e2\u0005\f\u0000\u0000\u00e2\u00e8\u0006\t"+
		"\uffff\uffff\u0000\u00e3\u00e4\u0005\r\u0000\u0000\u00e4\u00e8\u0006\t"+
		"\uffff\uffff\u0000\u00e5\u00e6\u0005\u000e\u0000\u0000\u00e6\u00e8\u0006"+
		"\t\uffff\uffff\u0000\u00e7\u00e1\u0001\u0000\u0000\u0000\u00e7\u00e3\u0001"+
		"\u0000\u0000\u0000\u00e7\u00e5\u0001\u0000\u0000\u0000\u00e8\u0013\u0001"+
		"\u0000\u0000\u0000\u00e9\u00ea\u0005\f\u0000\u0000\u00ea\u00f0\u0006\n"+
		"\uffff\uffff\u0000\u00eb\u00ec\u0005\r\u0000\u0000\u00ec\u00f0\u0006\n"+
		"\uffff\uffff\u0000\u00ed\u00ee\u0005\u000e\u0000\u0000\u00ee\u00f0\u0006"+
		"\n\uffff\uffff\u0000\u00ef\u00e9\u0001\u0000\u0000\u0000\u00ef\u00eb\u0001"+
		"\u0000\u0000\u0000\u00ef\u00ed\u0001\u0000\u0000\u0000\u00f0\u0015\u0001"+
		"\u0000\u0000\u0000\u00f1\u00f5\u0003\u001a\r\u0000\u00f2\u00f4\u0003\u0018"+
		"\f\u0000\u00f3\u00f2\u0001\u0000\u0000\u0000\u00f4\u00f7\u0001\u0000\u0000"+
		"\u0000\u00f5\u00f3\u0001\u0000\u0000\u0000\u00f5\u00f6\u0001\u0000\u0000"+
		"\u0000\u00f6\u00f8\u0001\u0000\u0000\u0000\u00f7\u00f5\u0001\u0000\u0000"+
		"\u0000\u00f8\u00f9\u0006\u000b\uffff\uffff\u0000\u00f9\u0017\u0001\u0000"+
		"\u0000\u0000\u00fa\u00fb\u0005\u0016\u0000\u0000\u00fb\u00fc\u0003\u001a"+
		"\r\u0000\u00fc\u00fd\u0006\f\uffff\uffff\u0000\u00fd\u0103\u0001\u0000"+
		"\u0000\u0000\u00fe\u00ff\u0007\u0001\u0000\u0000\u00ff\u0100\u0003\u001a"+
		"\r\u0000\u0100\u0101\u0006\f\uffff\uffff\u0000\u0101\u0103\u0001\u0000"+
		"\u0000\u0000\u0102\u00fa\u0001\u0000\u0000\u0000\u0102\u00fe\u0001\u0000"+
		"\u0000\u0000\u0103\u0019\u0001\u0000\u0000\u0000\u0104\u0105\u0005 \u0000"+
		"\u0000\u0105\u0111\u0006\r\uffff\uffff\u0000\u0106\u0107\u0005 \u0000"+
		"\u0000\u0107\u0108\u0005\u0013\u0000\u0000\u0108\u0109\u0005!\u0000\u0000"+
		"\u0109\u010a\u0005\u0014\u0000\u0000\u010a\u0111\u0006\r\uffff\uffff\u0000"+
		"\u010b\u010c\u0005 \u0000\u0000\u010c\u010d\u0005\u0013\u0000\u0000\u010d"+
		"\u010e\u0005\"\u0000\u0000\u010e\u010f\u0005\u0014\u0000\u0000\u010f\u0111"+
		"\u0006\r\uffff\uffff\u0000\u0110\u0104\u0001\u0000\u0000\u0000\u0110\u0106"+
		"\u0001\u0000\u0000\u0000\u0110\u010b\u0001\u0000\u0000\u0000\u0111\u001b"+
		"\u0001\u0000\u0000\u0000\u0112\u0113\u0006\u000e\uffff\uffff\u0000\u0113"+
		"\u0114\u0003\u001e\u000f\u0000\u0114\u0115\u0006\u000e\uffff\uffff\u0000"+
		"\u0115\u011c\u0001\u0000\u0000\u0000\u0116\u0117\n\u0001\u0000\u0000\u0117"+
		"\u0118\u0003\u001e\u000f\u0000\u0118\u0119\u0006\u000e\uffff\uffff\u0000"+
		"\u0119\u011b\u0001\u0000\u0000\u0000\u011a\u0116\u0001\u0000\u0000\u0000"+
		"\u011b\u011e\u0001\u0000\u0000\u0000\u011c\u011a\u0001\u0000\u0000\u0000"+
		"\u011c\u011d\u0001\u0000\u0000\u0000\u011d\u001d\u0001\u0000\u0000\u0000"+
		"\u011e\u011c\u0001\u0000\u0000\u0000\u011f\u0120\u0003\u000e\u0007\u0000"+
		"\u0120\u0121\u0006\u000f\uffff\uffff\u0000\u0121\u0163\u0001\u0000\u0000"+
		"\u0000\u0122\u0123\u0003 \u0010\u0000\u0123\u0124\u0006\u000f\uffff\uffff"+
		"\u0000\u0124\u0163\u0001\u0000\u0000\u0000\u0125\u0126\u0003\f\u0006\u0000"+
		"\u0126\u0127\u0006\u000f\uffff\uffff\u0000\u0127\u0163\u0001\u0000\u0000"+
		"\u0000\u0128\u0129\u0005\u0007\u0000\u0000\u0129\u012a\u0005\u000f\u0000"+
		"\u0000\u012a\u012b\u0003 \u0010\u0000\u012b\u012c\u0006\u000f\uffff\uffff"+
		"\u0000\u012c\u012d\u0003 \u0010\u0000\u012d\u012e\u0006\u000f\uffff\uffff"+
		"\u0000\u012e\u012f\u0003$\u0012\u0000\u012f\u0130\u0006\u000f\uffff\uffff"+
		"\u0000\u0130\u0131\u0005\u0010\u0000\u0000\u0131\u0132\u0003\u001e\u000f"+
		"\u0000\u0132\u0133\u0006\u000f\uffff\uffff\u0000\u0133\u0163\u0001\u0000"+
		"\u0000\u0000\u0134\u0135\u0005\u0005\u0000\u0000\u0135\u0136\u0005\u000f"+
		"\u0000\u0000\u0136\u0137\u0003$\u0012\u0000\u0137\u0138\u0006\u000f\uffff"+
		"\uffff\u0000\u0138\u0139\u0005\u0010\u0000\u0000\u0139\u013a\u0003\u001e"+
		"\u000f\u0000\u013a\u013b\u0006\u000f\uffff\uffff\u0000\u013b\u0163\u0001"+
		"\u0000\u0000\u0000\u013c\u013d\u0005\u0005\u0000\u0000\u013d\u013e\u0005"+
		"\u000f\u0000\u0000\u013e\u013f\u0003$\u0012\u0000\u013f\u0140\u0006\u000f"+
		"\uffff\uffff\u0000\u0140\u0141\u0005\u0010\u0000\u0000\u0141\u0142\u0003"+
		"\u001e\u000f\u0000\u0142\u0143\u0006\u000f\uffff\uffff\u0000\u0143\u0144"+
		"\u0005\u0006\u0000\u0000\u0144\u0145\u0003\u001e\u000f\u0000\u0145\u0146"+
		"\u0006\u000f\uffff\uffff\u0000\u0146\u0147\u0006\u000f\uffff\uffff\u0000"+
		"\u0147\u0163\u0001\u0000\u0000\u0000\u0148\u0149\u0005\b\u0000\u0000\u0149"+
		"\u014a\u0006\u000f\uffff\uffff\u0000\u014a\u014b\u0005\u000f\u0000\u0000"+
		"\u014b\u014c\u0003$\u0012\u0000\u014c\u014d\u0006\u000f\uffff\uffff\u0000"+
		"\u014d\u014e\u0005\u0010\u0000\u0000\u014e\u014f\u0003\u001e\u000f\u0000"+
		"\u014f\u0150\u0006\u000f\uffff\uffff\u0000\u0150\u0163\u0001\u0000\u0000"+
		"\u0000\u0151\u0152\u0005\t\u0000\u0000\u0152\u0153\u0005\u000f\u0000\u0000"+
		"\u0153\u0154\u0005 \u0000\u0000\u0154\u0155\u0005\u0010\u0000\u0000\u0155"+
		"\u0156\u0005\u0015\u0000\u0000\u0156\u0163\u0006\u000f\uffff\uffff\u0000"+
		"\u0157\u0158\u0005\n\u0000\u0000\u0158\u0159\u0005\u000f\u0000\u0000\u0159"+
		"\u015a\u0005 \u0000\u0000\u015a\u015b\u0005\u0010\u0000\u0000\u015b\u015c"+
		"\u0005\u0015\u0000\u0000\u015c\u0163\u0006\u000f\uffff\uffff\u0000\u015d"+
		"\u015e\u0005\u000b\u0000\u0000\u015e\u015f\u0003$\u0012\u0000\u015f\u0160"+
		"\u0005\u0015\u0000\u0000\u0160\u0161\u0006\u000f\uffff\uffff\u0000\u0161"+
		"\u0163\u0001\u0000\u0000\u0000\u0162\u011f\u0001\u0000\u0000\u0000\u0162"+
		"\u0122\u0001\u0000\u0000\u0000\u0162\u0125\u0001\u0000\u0000\u0000\u0162"+
		"\u0128\u0001\u0000\u0000\u0000\u0162\u0134\u0001\u0000\u0000\u0000\u0162"+
		"\u013c\u0001\u0000\u0000\u0000\u0162\u0148\u0001\u0000\u0000\u0000\u0162"+
		"\u0151\u0001\u0000\u0000\u0000\u0162\u0157\u0001\u0000\u0000\u0000\u0162"+
		"\u015d\u0001\u0000\u0000\u0000\u0163\u001f\u0001\u0000\u0000\u0000\u0164"+
		"\u0165\u0005\u0015\u0000\u0000\u0165\u016b\u0006\u0010\uffff\uffff\u0000"+
		"\u0166\u0167\u0003$\u0012\u0000\u0167\u0168\u0005\u0015\u0000\u0000\u0168"+
		"\u0169\u0006\u0010\uffff\uffff\u0000\u0169\u016b\u0001\u0000\u0000\u0000"+
		"\u016a\u0164\u0001\u0000\u0000\u0000\u016a\u0166\u0001\u0000\u0000\u0000"+
		"\u016b!\u0001\u0000\u0000\u0000\u016c\u016d\u0005 \u0000\u0000\u016d\u0175"+
		"\u0006\u0011\uffff\uffff\u0000\u016e\u016f\u0005 \u0000\u0000\u016f\u0170"+
		"\u0005\u0013\u0000\u0000\u0170\u0171\u0003$\u0012\u0000\u0171\u0172\u0005"+
		"\u0014\u0000\u0000\u0172\u0173\u0006\u0011\uffff\uffff\u0000\u0173\u0175"+
		"\u0001\u0000\u0000\u0000\u0174\u016c\u0001\u0000\u0000\u0000\u0174\u016e"+
		"\u0001\u0000\u0000\u0000\u0175#\u0001\u0000\u0000\u0000\u0176\u0177\u0003"+
		"&\u0013\u0000\u0177\u0178\u0006\u0012\uffff\uffff\u0000\u0178\u0194\u0001"+
		"\u0000\u0000\u0000\u0179\u017d\u0003\"\u0011\u0000\u017a\u017b\u0005\u0013"+
		"\u0000\u0000\u017b\u017c\u0005!\u0000\u0000\u017c\u017e\u0005\u0014\u0000"+
		"\u0000\u017d\u017a\u0001\u0000\u0000\u0000\u017d\u017e\u0001\u0000\u0000"+
		"\u0000\u017e\u017f\u0001\u0000\u0000\u0000\u017f\u0180\u0005\u001f\u0000"+
		"\u0000\u0180\u0181\u0003&\u0013\u0000\u0181\u0182\u0006\u0012\uffff\uffff"+
		"\u0000\u0182\u0194\u0001\u0000\u0000\u0000\u0183\u0184\u0003\"\u0011\u0000"+
		"\u0184\u0185\u0005\u001f\u0000\u0000\u0185\u0186\u0003&\u0013\u0000\u0186"+
		"\u0187\u0006\u0012\uffff\uffff\u0000\u0187\u0194\u0001\u0000\u0000\u0000"+
		"\u0188\u0189\u0003\"\u0011\u0000\u0189\u018d\u0005\u001f\u0000\u0000\u018a"+
		"\u018c\u00038\u001c\u0000\u018b\u018a\u0001\u0000\u0000\u0000\u018c\u018f"+
		"\u0001\u0000\u0000\u0000\u018d\u018b\u0001\u0000\u0000\u0000\u018d\u018e"+
		"\u0001\u0000\u0000\u0000\u018e\u0190\u0001\u0000\u0000\u0000\u018f\u018d"+
		"\u0001\u0000\u0000\u0000\u0190\u0191\u0005\u001f\u0000\u0000\u0191\u0192"+
		"\u0006\u0012\uffff\uffff\u0000\u0192\u0194\u0001\u0000\u0000\u0000\u0193"+
		"\u0176\u0001\u0000\u0000\u0000\u0193\u0179\u0001\u0000\u0000\u0000\u0193"+
		"\u0183\u0001\u0000\u0000\u0000\u0193\u0188\u0001\u0000\u0000\u0000\u0194"+
		"%\u0001\u0000\u0000\u0000\u0195\u0196\u0003(\u0014\u0000\u0196\u0197\u0006"+
		"\u0013\uffff\uffff\u0000\u0197\u01a0\u0001\u0000\u0000\u0000\u0198\u0199"+
		"\u0006\u0013\uffff\uffff\u0000\u0199\u019a\u0003(\u0014\u0000\u019a\u019b"+
		"\u0005\u001e\u0000\u0000\u019b\u019c\u0006\u0013\uffff\uffff\u0000\u019c"+
		"\u019d\u0003(\u0014\u0000\u019d\u019e\u0006\u0013\uffff\uffff\u0000\u019e"+
		"\u01a0\u0001\u0000\u0000\u0000\u019f\u0195\u0001\u0000\u0000\u0000\u019f"+
		"\u0198\u0001\u0000\u0000\u0000\u01a0\'\u0001\u0000\u0000\u0000\u01a1\u01a2"+
		"\u0003*\u0015\u0000\u01a2\u01a3\u0006\u0014\uffff\uffff\u0000\u01a3\u01aa"+
		"\u0001\u0000\u0000\u0000\u01a4\u01a5\u0003*\u0015\u0000\u01a5\u01a6\u0005"+
		"\u001d\u0000\u0000\u01a6\u01a7\u0003*\u0015\u0000\u01a7\u01a8\u0006\u0014"+
		"\uffff\uffff\u0000\u01a8\u01aa\u0001\u0000\u0000\u0000\u01a9\u01a1\u0001"+
		"\u0000\u0000\u0000\u01a9\u01a4\u0001\u0000\u0000\u0000\u01aa)\u0001\u0000"+
		"\u0000\u0000\u01ab\u01ac\u0006\u0015\uffff\uffff\u0000\u01ac\u01ad\u0003"+
		",\u0016\u0000\u01ad\u01ae\u0006\u0015\uffff\uffff\u0000\u01ae\u01b6\u0001"+
		"\u0000\u0000\u0000\u01af\u01b0\n\u0001\u0000\u0000\u01b0\u01b1\u0005\u0017"+
		"\u0000\u0000\u01b1\u01b2\u0003,\u0016\u0000\u01b2\u01b3\u0006\u0015\uffff"+
		"\uffff\u0000\u01b3\u01b5\u0001\u0000\u0000\u0000\u01b4\u01af\u0001\u0000"+
		"\u0000\u0000\u01b5\u01b8\u0001\u0000\u0000\u0000\u01b6\u01b4\u0001\u0000"+
		"\u0000\u0000\u01b6\u01b7\u0001\u0000\u0000\u0000\u01b7+\u0001\u0000\u0000"+
		"\u0000\u01b8\u01b6\u0001\u0000\u0000\u0000\u01b9\u01ba\u0006\u0016\uffff"+
		"\uffff\u0000\u01ba\u01bb\u0003.\u0017\u0000\u01bb\u01bc\u0006\u0016\uffff"+
		"\uffff\u0000\u01bc\u01c4\u0001\u0000\u0000\u0000\u01bd\u01be\n\u0001\u0000"+
		"\u0000\u01be\u01bf\u0005\u0019\u0000\u0000\u01bf\u01c0\u0003.\u0017\u0000"+
		"\u01c0\u01c1\u0006\u0016\uffff\uffff\u0000\u01c1\u01c3\u0001\u0000\u0000"+
		"\u0000\u01c2\u01bd\u0001\u0000\u0000\u0000\u01c3\u01c6\u0001\u0000\u0000"+
		"\u0000\u01c4\u01c2\u0001\u0000\u0000\u0000\u01c4\u01c5\u0001\u0000\u0000"+
		"\u0000\u01c5-\u0001\u0000\u0000\u0000\u01c6\u01c4\u0001\u0000\u0000\u0000"+
		"\u01c7\u01c8\u0005\u0017\u0000\u0000\u01c8\u01c9\u0003.\u0017\u0000\u01c9"+
		"\u01ca\u0006\u0017\uffff\uffff\u0000\u01ca\u01d3\u0001\u0000\u0000\u0000"+
		"\u01cb\u01cc\u0005\u001c\u0000\u0000\u01cc\u01cd\u0003.\u0017\u0000\u01cd"+
		"\u01ce\u0006\u0017\uffff\uffff\u0000\u01ce\u01d3\u0001\u0000\u0000\u0000"+
		"\u01cf\u01d0\u00030\u0018\u0000\u01d0\u01d1\u0006\u0017\uffff\uffff\u0000"+
		"\u01d1\u01d3\u0001\u0000\u0000\u0000\u01d2\u01c7\u0001\u0000\u0000\u0000"+
		"\u01d2\u01cb\u0001\u0000\u0000\u0000\u01d2\u01cf\u0001\u0000\u0000\u0000"+
		"\u01d3/\u0001\u0000\u0000\u0000\u01d4\u01d5\u0003\"\u0011\u0000\u01d5"+
		"\u01d6\u0006\u0018\uffff\uffff\u0000\u01d6\u01ef\u0001\u0000\u0000\u0000"+
		"\u01d7\u01d8\u0005 \u0000\u0000\u01d8\u01d9\u0005\u000f\u0000\u0000\u01d9"+
		"\u01da\u00032\u0019\u0000\u01da\u01db\u0005\u0010\u0000\u0000\u01db\u01dc"+
		"\u0006\u0018\uffff\uffff\u0000\u01dc\u01ef\u0001\u0000\u0000\u0000\u01dd"+
		"\u01de\u0005\u000f\u0000\u0000\u01de\u01df\u0003$\u0012\u0000\u01df\u01e0"+
		"\u0005\u0010\u0000\u0000\u01e0\u01e1\u0006\u0018\uffff\uffff\u0000\u01e1"+
		"\u01ef\u0001\u0000\u0000\u0000\u01e2\u01e3\u0005!\u0000\u0000\u01e3\u01ef"+
		"\u0006\u0018\uffff\uffff\u0000\u01e4\u01e5\u0005\"\u0000\u0000\u01e5\u01ef"+
		"\u0006\u0018\uffff\uffff\u0000\u01e6\u01e7\u0003\"\u0011\u0000\u01e7\u01e8"+
		"\u0005\u001a\u0000\u0000\u01e8\u01e9\u0006\u0018\uffff\uffff\u0000\u01e9"+
		"\u01ef\u0001\u0000\u0000\u0000\u01ea\u01eb\u0003\"\u0011\u0000\u01eb\u01ec"+
		"\u0005\u001b\u0000\u0000\u01ec\u01ed\u0006\u0018\uffff\uffff\u0000\u01ed"+
		"\u01ef\u0001\u0000\u0000\u0000\u01ee\u01d4\u0001\u0000\u0000\u0000\u01ee"+
		"\u01d7\u0001\u0000\u0000\u0000\u01ee\u01dd\u0001\u0000\u0000\u0000\u01ee"+
		"\u01e2\u0001\u0000\u0000\u0000\u01ee\u01e4\u0001\u0000\u0000\u0000\u01ee"+
		"\u01e6\u0001\u0000\u0000\u0000\u01ee\u01ea\u0001\u0000\u0000\u0000\u01ef"+
		"1\u0001\u0000\u0000\u0000\u01f0\u01f1\u00034\u001a\u0000\u01f1\u01f2\u0006"+
		"\u0019\uffff\uffff\u0000\u01f2\u01f5\u0001\u0000\u0000\u0000\u01f3\u01f5"+
		"\u0006\u0019\uffff\uffff\u0000\u01f4\u01f0\u0001\u0000\u0000\u0000\u01f4"+
		"\u01f3\u0001\u0000\u0000\u0000\u01f53\u0001\u0000\u0000\u0000\u01f6\u01f7"+
		"\u00036\u001b\u0000\u01f7\u01fe\u0006\u001a\uffff\uffff\u0000\u01f8\u01f9"+
		"\u0005\u0016\u0000\u0000\u01f9\u01fa\u00036\u001b\u0000\u01fa\u01fb\u0006"+
		"\u001a\uffff\uffff\u0000\u01fb\u01fd\u0001\u0000\u0000\u0000\u01fc\u01f8"+
		"\u0001\u0000\u0000\u0000\u01fd\u0200\u0001\u0000\u0000\u0000\u01fe\u01fc"+
		"\u0001\u0000\u0000\u0000\u01fe\u01ff\u0001\u0000\u0000\u0000\u01ff5\u0001"+
		"\u0000\u0000\u0000\u0200\u01fe\u0001\u0000\u0000\u0000\u0201\u0202\u0003"+
		"$\u0012\u0000\u0202\u0203\u0006\u001b\uffff\uffff\u0000\u0203\u0208\u0001"+
		"\u0000\u0000\u0000\u0204\u0205\u0003\"\u0011\u0000\u0205\u0206\u0006\u001b"+
		"\uffff\uffff\u0000\u0206\u0208\u0001\u0000\u0000\u0000\u0207\u0201\u0001"+
		"\u0000\u0000\u0000\u0207\u0204\u0001\u0000\u0000\u0000\u02087\u0001\u0000"+
		"\u0000\u0000\u0209\u020a\u0007\u0002\u0000\u0000\u020a9\u0001\u0000\u0000"+
		"\u0000#HTe\u0081\u0088\u0096\u00a1\u00aa\u00b0\u00b3\u00bf\u00d1\u00d8"+
		"\u00df\u00e7\u00ef\u00f5\u0102\u0110\u011c\u0162\u016a\u0174\u017d\u018d"+
		"\u0193\u019f\u01a9\u01b6\u01c4\u01d2\u01ee\u01f4\u01fe\u0207";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}