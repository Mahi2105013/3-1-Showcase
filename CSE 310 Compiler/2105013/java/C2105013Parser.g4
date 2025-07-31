parser grammar C2105013Parser;

options {
    tokenVocab = C2105013Lexer;
}

@header {
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;
}

@members {
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

    
}

start
    : {
        try { initAsmWriter(); }
        catch(IOException e) { System.out.println(e); }
    } 

    program
      {

        writeIntoAssemblyLogFile(ending());

        symbolTable.printAllScopeTables(); // console printing
        if (errorCount > 0) System.out.println("THERE ARE ERRORS IN THE INPUT FILE");
        else {
            
        }
        
      }
      
    ;

program
    : program unit
    { { writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": program : program unit\n" + $ctx.getText() + "\n"); } }
    | unit  { 
        { { writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": program : unit : \n" + $ctx.getText() + "\n"); } }
    }
    ;

unit returns [String type, String name_line]
    : var_declaration
      { 
        $type = "var_declaration";
        $name_line = $var_declaration.name_line;
        { { writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": unit : var_declaration\n" + $ctx.getText() + "\n"); } }
      }
    | func_declaration
      { 
        $type = $func_declaration.type;
        $name_line = $func_declaration.name_line;
        { { writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": unit : func_declaration\n" + $ctx.getText() + "\n"); } }
      }
    | func_definition
      { 
        $type = $func_definition.type;
        $name_line = $func_definition.name_line;
        { { writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": unit : func_definition\n" + $ctx.getText() + "\n"); } }
      }
    ;


func_declaration
returns [String type, String name_line]
    : t=type_specifier id=ID LPAREN p=parameter_list RPAREN SEMICOLON
    {
        $type = "func_declaration";
        $name_line = $ctx.getText();
        updateLineNumber($id);
        writeIntoParserLogFile("Line " + $id.getLine() + ": func_declaration : type_specifier ID LPAREN parameter_list RPAREN SEMICOLON\n" + $ctx.getText() + "\n");
        

        insertSuccess = symbolTable.insertFunction($id.text, $t.name_line, $p.paramTypes, $p.paramNames, printMessage);
    }
    | t=type_specifier id=ID LPAREN RPAREN SEMICOLON
    {
        insertSuccess = symbolTable.insertFunction($id.text, $t.name_line, null, null, printMessage);
        // if (insertSuccess) writeIntoParserLogFile("Function declaration: " + $id.text + " at line " + $id.getLine());
        // else writeIntoParserLogFile("NOOOOO!");

        $type = "func_declaration";
        $name_line = $ctx.getText();
        writeIntoParserLogFile("Line " + $id.getLine() + ": func_declaration : type_specifier ID LPAREN RPAREN SEMICOLON\n" + $ctx.getText() + "\n");
    }
    ;

func_definition
returns [String type, String name_line]
    : t=type_specifier id=ID LPAREN p=parameter_list RPAREN
    {  

        currentFunctionName = $id.text.toUpperCase();
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
        SymbolInfo existing = symbolTable.lookup($id.text);

        if (existing != null && !existing.isFunction()) {
            writeIntoErrorFile("Error at line " + $id.getLine() + 
                                 ": Multiple declaration of " + $id.text);
                errorCount++;
        }

        if (existing != null && existing.isFunction()) {
            // Check return type
            if (!existing.getFunctionInfo().getReturnType().equals($t.name_line)) {
                writeIntoErrorFile("Error at line " + $id.getLine() + 
                                 ": Return type mismatch of " + $id.text);
                errorCount++;
            }
            // Check parameter count
            if (existing.getFunctionInfo().getParameterCount() != $p.paramTypes.size()) {
                writeIntoErrorFile("Error at line " + $id.getLine() + 
                                 ": Total number of arguments mismatch with declaration in function " + $id.text);
                errorCount++;
            }
        }



        $type = "func_definition";
        $name_line = $ctx.getText();
        updateLineNumber($id);
        // writeIntoParserLogFile("Line " + $id.getLine() + ": func_definition : type_specifier ID LPAREN parameter_list RPAREN\n" + $ctx.getText() + "\n");

        insertSuccess = symbolTable.insertFunction($id.text, $t.name_line, $p.paramTypes, $p.paramNames, printMessage);
        System.out.println("insert success: " + insertSuccess);

        currentFunctionReturnType = $t.name_line;
        currentFunctionName = $id.text.toUpperCase();
        if (true) {
            isFunction = true;

            for (int i = 0; i < $p.paramTypes.size(); i++) {
                String paramType = $p.paramTypes.get(i);
                String paramName = $p.paramNames.get(i);
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
    }
     LCURL 
     {
        symbolTable.enterScope(printMessage);
        System.out.println("current params is: " + currentFunctionParams);

        if (true) {
            // insert parameters into symbol table
            for (int i = 0; i < currentFunctionParams.size(); i++) {
                System.out.println("We are at line " + $LCURL.getLine() + " and current params is: " + currentFunctionParams);
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
     }
      statements RCURL
     {
        writeIntoParserLogFile ( symbolTable.generateAllScopeTableString() );
        writeIntoParserLogFile("Line " + $RCURL.getLine() + ": func_definition : type_specifier ID LPAREN parameter_list RPAREN compound_statement\n" + $ctx.getText() + "\n");
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
    | t=type_specifier id=ID LPAREN RPAREN
     {

        
        currentFunctionName = $id.text.toUpperCase();
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


        $type = "func_definition";
        $name_line = $ctx.getText();
        currentFunctionReturnType = $t.name_line;
        currentFunctionName = $id.text.toUpperCase();
        // writeIntoParserLogFile("Line " + $id.getLine() + ": func_definition : type_specifier ID LPAREN RPAREN\n" + $ctx.getText() + "\n");
        insertSuccess = symbolTable.insertFunction($id.text, $t.name_line, null, null, printMessage);
        if (insertSuccess) {
            // writeIntoParserLogFile("Function definition: " + $id.text + " at line " + $id.getLine());
            symbolTable.enterScope(printMessage);
        }

        else {
            // writeIntoParserLogFile("FAILED Function definition: " + $id.text + " at line " + $id.getLine());
        }
     }
     compound_statement
     {
        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": func_definition : type_specifier ID LPAREN RPAREN compound_statement\n" + $ctx.getText() + "\n");
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
     |
     t=type_specifier id=ID LPAREN (expression_fragment)* RPAREN
     compound_statement
     {

     }
    ;

// parameter_list
//     : parameter_list COMMA type_specifier ID
//     | parameter_list COMMA type_specifier
//     | type_specifier ID
//     | type_specifier
//     ;

// parameter_list
// returns [List<String> paramTypes, List<String> paramNames]
// @init {
//     $paramTypes = new ArrayList<>();
//     $paramNames = new ArrayList<>();
// }
//     : pl=parameter_list COMMA t=type_specifier_func id=ID
//       {
//         //   $paramTypes.add($t.name_line); // name_line has the type like "type: INT at line 2"
//         //   $paramNames.add($id.getText());
//       }
//     | pl=parameter_list COMMA t=type_specifier_func
//       {
           
//         //   $paramTypes.add($t.name_line);
//         //   $paramNames.add(""); 
//       }
//     | t=type_specifier_func id=ID
//       {
//           $paramTypes.add($t.name_line);
//           $paramNames.add($id.getText());
//       }
//     | t=type_specifier_func
//       {
//           $paramTypes.add($t.name_line);
//           $paramNames.add(""); // no name
//       }
//     ;

parameter_list
returns [List<String> paramTypes, List<String> paramNames]
@init {
    $paramTypes = new ArrayList<>();
    $paramNames = new ArrayList<>();
    Set<String> seenParams = new HashSet<>();
}
    : t=type_specifier_func
        {
            writeIntoParserLogFile("Line " + $t.start.getLine() + ": type_specifier: \n" + $t.name_line.toUpperCase() + "\n" + $t.name_line + "\n");
        }    
     id=ID 
      {
          $paramTypes.add($t.name_line);
          $paramNames.add($id.getText());
        //   if (!seenParams.add($id.text)) {
        //       writeIntoErrorFile("Error at line " + $id.getLine() + 
        //                        ": Multiple declaration of " + $id.text + " in parameter");
        //       errorCount++;
        //   }
          writeIntoParserLogFile("Line " + $t.start.getLine() + ": parameter_list : type_specifier ID\n" + $t.text + " " + $id.text + "\n");
      }
      (COMMA t2=type_specifier_func
        {
            writeIntoParserLogFile("Line " + $t.start.getLine() + ": type_specifier: " + $t.name_line.toUpperCase() + "\n" + $t.name_line + "\n");
        }
       id2=ID
       {
           $paramTypes.add($t2.name_line);
           $paramNames.add($id2.getText());
           writeIntoParserLogFile("Line " + $t2.start.getLine() + ": parameter_list : parameter_list COMMA type_specifier ID\n" + $ctx.getText() + "\n");
           if (!seenParams.add($id.text)) {
              writeIntoErrorFile("Error at line " + $id.getLine() + 
                               ": Multiple declaration of " + $id.text + " in parameter");
              errorCount++;
          }


       }
      )*
    | t=type_specifier_func
      {
          $paramTypes.add($t.name_line);
          $paramNames.add("");
      }
      (COMMA t2=type_specifier_func
       {
           $paramTypes.add($t2.name_line);
           $paramNames.add("");
       }
      )*
    |
    // error
    t=type_specifier_func
    {
          $paramTypes.add($t.name_line);
          $paramNames.add("");
    }

    ( op=(ADDOP|SUBOP|MULOP|INCOP|DECOP|RELOP|NOT) (t2=type_specifier_func)*
       {
           writeIntoErrorFile("Error at line " + $op.getLine() + ": syntax error, unexpected " + $op.getText() + ", expecting RPAREN or COMMA");
        //    $paramTypes.add($t2.name_line);
        //    $paramNames.add("");
       }
    )*

    ;



compound_statement
    : LCURL
    { 
        symbolTable.enterScope(printMessage);
    }
    
    statements RCURL
    {
        { { writeIntoParserLogFile("Line " + $RCURL.getLine() + ": compound_statement : LCURL statements RCURL\n" + $ctx.getText() + "\n"); } }

        writeIntoParserLogFile ( symbolTable.generateAllScopeTableString() );
        symbolTable.exitScope(printMessage);
    }
    | LCURL 
    { 
        symbolTable.enterScope(printMessage);
    }
    RCURL
    {
        { { writeIntoParserLogFile("Line " + $RCURL.getLine() + ": compound_statement : LCURL RCURL\n" + $ctx.getText() + "\n"); } }
        writeIntoParserLogFile ( symbolTable.generateAllScopeTableString() );
        symbolTable.exitScope(printMessage);
    }
    ;

var_declaration
returns [String name_line]
    : t=type_specifier dl=declaration_list sm=SEMICOLON
      {
        if ($t.name_line.equals("void")) {
              writeIntoErrorFile("Error at line " + $t.start.getLine() + 
                               ": Variable type cannot be void");
              errorCount++;
        }

        String[] allTheDeclarations = $dl.name_line.split(",");
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

                writeIntoAssemblyLogFile("\tSUB SP, 2\t; Allocate " + dec + " , Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n");
            }

            else if (!isGlobal && dec.contains("[")) { 
                // this is an array (local)
                int start = dec.indexOf('['), end = dec.indexOf(']');
                varInfo.setArray(Integer.parseInt(dec.substring(start + 1, end)));

                varInfo.setStackOffset(currentStackPointer + 2);
                currentStackPointer += 2*Integer.parseInt(dec.substring(start + 1, end)); 
                writeIntoAssemblyLogFile("\tSUB SP, " + 2*Integer.parseInt(dec.substring(start + 1, end)) + "\t; Allocate " + dec + "\n");
            }

            if (varInfo != null && varInfo.isArray())  varInfo.setDataType($t.text);

            if(varInfo != null) varInfo.setDataType($t.name_line);
        }

        updateLineNumber($sm);
        writeIntoParserLogFile(
            "Line " + currentLine + ": " +
            "var_declaration: type_specifier declaration_list SEMICOLON"

        );
        // writeIntoParserLogFile(
        //     "type_specifier name_line: "
        //     + $t.name_line
        // );

        // print stuff like int x, y, z;
        writeIntoParserLogFile(
            $t.name_line + " " + $dl.name_line + ";\n\n"
        );

        $name_line = $t.name_line + " " + $dl.name_line + ";\n\n";
      }
    | t=type_specifier de=declaration_list_err sm=SEMICOLON
      {
        writeIntoErrorFile(
            "Error at Line "
            + $sm.getLine()
            + " with error name: "
            + $de.error_name
            + " - Syntax error at declaration list of variable declaration"
        );
        // Main.syntaxErrorCount++;
        errorCount++;
      }
      | UNRECOGNIZED
      {
        writeIntoErrorFile(
            "Error at line " + $UNRECOGNIZED.getLine() +
            " Unrecognized character " + $UNRECOGNIZED.text
        );
      }
      | // int x-y,z;
      t=type_specifier 
      | // Error production for incomplete declarations
      t=type_specifier
      {
          writeIntoErrorFile("Error at line " + $t.start.getLine() + ": incomplete variable declaration");
          errorCount++;
      } 
      
    ;

declaration_list_err
    returns [String error_name]
    : id=ID (COMMA id2=ID)*                  
    | id=ID ADDOP id2=ID
    { $error_name = "Error in declaration list"; }
    ;




type_specifier
    returns [String name_line]
    : INT
      {
        // $name_line = "type: INT at line" + $INT.getLine();
        $name_line = "int";
        updateLineNumber($INT);
        writeIntoParserLogFile(
            "Line " + currentLine + " type_specifier : INT\n\n"
        );

        writeIntoParserLogFile(
            "int\n\n"
        );
      }
    | FLOAT
      {
        // $name_line = "type: FLOAT at line" + $FLOAT.getLine();
        $name_line = "float";
        updateLineNumber($FLOAT);
        writeIntoParserLogFile(
            "Line " + currentLine + " type_specifier : FLOAT\n\n"
        );

        writeIntoParserLogFile(
            "float\n\n"
        );
      }
    | VOID
      {
        // $name_line = "type: VOID at line" + $VOID.getLine();
        $name_line = "void";
        updateLineNumber($VOID);
        writeIntoParserLogFile(
            "Line " + currentLine + " type_specifier : VOID\n\n"
        );

        writeIntoParserLogFile(
            "void\n\n"
        );
      }
    ;


type_specifier_func
returns [String name_line]
    : INT { $name_line = "int"; }
    | FLOAT { $name_line = "float"; }
    | VOID { $name_line = "void"; }
    ;


// declaration_list
// returns [String name_line]
//     : declaration_list COMMA ID
//     {   
//         updateLineNumber($ID);
//         writeIntoParserLogFile(
//             "Line " + currentLine +
//             ": declaration_list : declaration_list COMMA ID"
//         );
//         symbolTable.insertIntoCurrentScope($ID.text,
//         "ID", printMessage);

        
//     }
//     | declaration_list COMMA ID LTHIRD CONST_INT RTHIRD
//     {
//         // handle array declaration
//     }
//     | ID
//     {   
//         updateLineNumber($ID);
//         writeIntoParserLogFile(
//             "Line " + currentLine +
//             ": declaration_list : ID"
//         );
//         symbolTable.insertIntoCurrentScope($ID.text,
//         "ID", printMessage);

//         $name_line = $ID.text + "";
        
//     }
//     | ID LTHIRD CONST_INT RTHIRD
//     {
//         // handle array dec 

//         updateLineNumber($ID);
//         writeIntoParserLogFile(
//             "Line " + currentLine +
//             ": declaration_list : ID LTHIRD CONST_INT RTHIRD"
//         );
//         symbolTable.insertIntoCurrentScope($ID.text,
//         "ID", printMessage);

//         $name_line = $ID.text + "";
//     }
//    ;

declaration_list returns [String name_line]
    : first=declaration_item rest+=declaration_tail* {
        // Start with first ID
        $name_line = $first.name;

        // Log first
        if (!$first.name.contains("["))  writeIntoParserLogFile("Line " + currentLine + ": declaration_list : ID\n\n" + $first.name + "\n");
        else writeIntoParserLogFile("Line " + currentLine + ": declaration_list : ID LTHIRD CONST_INT RTHIRD\n\n" + $first.name + "\n");

        // Then for each comma ID, update the name_line and log
        for (var item : $rest) {
            $name_line += "," + item.name;
            writeIntoParserLogFile("Line " + currentLine + ": declaration_list : declaration_list COMMA ID\n\n" + $name_line + "\n");
        }
    }
    ;

declaration_tail returns [String name]
    : COMMA decl=declaration_item { $name = $decl.name; }
    | // Error production for unexpected operators
      op=(ADDOP|SUBOP|MULOP|INCOP|DECOP|RELOP|NOT|LOGICOP|ASSIGNOP) decl=declaration_item
      {
          $name = $decl.name;
          writeIntoErrorFile("Error at line " + $op.getLine() + ": syntax error, unexpected ADDOP, expecting COMMA or SEMICOLON");
          errorCount++;
        //   $name = "";
      }
    ;

declaration_item returns [String name]
    : id=ID {
        updateLineNumber($id);
        boolean success = symbolTable.insertIntoCurrentScope($id.text, "ID", printMessage);
        if (!success) {
            errorCount++;
            writeIntoErrorFile("Error at line " + currentLine + ": Multiple declaration of " + $id.text);
        }
        $name = $id.text;
        // writeIntoParserLogFile("Line " + $id.getLine() + ": declaration_list : ID\n\n" + $id.text + "\n"); 
    }
    | id=ID LTHIRD CONST_INT RTHIRD {
        updateLineNumber($id);
        // symbolTable.insertIntoCurrentScope($id.text, "ID", printMessage);
        boolean success = symbolTable.insertArray($id.text, "ID", Integer.parseInt($CONST_INT.text), printMessage);
        
        // SymbolInfo arr = symbolTable.lookup($id.text, true);
        // arr.setDataType("int");

        if (!success) {
            errorCount++;
            writeIntoErrorFile("Error at line " + currentLine + ": Multiple declaration of " + $id.text);
        }
        // $name = $id.text; 
        $name = $id.text + "[" + $CONST_INT.text + "]";
        // writeIntoParserLogFile("Line " + $id.getLine() + ": declaration_list : ID LTHIRD CONST_INT RTHIRD\n\n" + $id.text + "[" + $CONST_INT.text + "]\n");
    }
    | id=ID LTHIRD CONST_FLOAT RTHIRD {
        updateLineNumber($id);
        errorCount++;
        writeIntoErrorFile("Error at line " + currentLine + ": Expression inside third brackets not an integer");
    }
    ;



statements
    : statement
    { { writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": statements : statement\n" + $ctx.getText() + "\n"); } }
    | statements statement
    { { writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": statements : statements : statement\n" + $ctx.getText() + "\n"); } }
    ;

statement
    : var_declaration
    { writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": statement : var_declaration\n" + $ctx.getText() + "\n"); }
    | expression_statement
    { writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": statement : expression_statement\n" + $ctx.getText() + "\n"); }
    | compound_statement
    { writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": statement : compound_statement\n" + $ctx.getText() + "\n"); }
    
    | 
    FOR LPAREN es1 = expression_statement { 
        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": statement : FOR LPAREN expression_statement expression_statement expression RPAREN statement\n" + $ctx.getText() + "\n"); 

        String loop_label = newLabel();
		String label_start = "FOR_START_" + loop_label;
		String label_end = "FOR_END_" + loop_label;
		String label_body = "FOR_BODY_" + loop_label;
		String label_update = "FOR_UPDATE_" + loop_label;
		writeIntoAssemblyLogFile(label_start + ":"); // start of for loop
	 } es2 = expression_statement {
		writeIntoAssemblyLogFile("POP AX" + " ; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n");
		writeIntoAssemblyLogFile("CMP AX, 0");
		writeIntoAssemblyLogFile("JE " + label_end);  // if condition is false,
		writeIntoAssemblyLogFile("JMP " + label_body); // jump to body if condition is true
		writeIntoAssemblyLogFile(label_update + ":"); // update section starts here

	  } expression {
		writeIntoAssemblyLogFile("JMP " + label_start); // jump back to start of loop
		writeIntoAssemblyLogFile(label_body + ":"); // body of the loop starts here

	  } RPAREN s = statement {
		 writeIntoAssemblyLogFile("JMP " + label_update);
		 writeIntoAssemblyLogFile(label_end + ":");
	}

    // FOR LPAREN 
    // {

    // }
    // expression_statement // first one
    // {
    //     String loopLabel = "FOR_" + newLabel();
    //     String endLabel = "FOR_" + newLabel();
    //     elseLabels.add(endLabel);

    //     writeIntoAssemblyLogFile(loopLabel + ":\n" + "; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()));
    // }
    // expression_statement // second one
    // {
    //     writeIntoAssemblyLogFile("POP AX\n");
    //     writeIntoAssemblyLogFile("CMP AX, 0\n");
    //     writeIntoAssemblyLogFile("JE " + endLabel + "\n");

    //     // execute "expression" (third one) later
    //     tempExpressionLabel = "FOR_" + newLabel();
    //     tempStatementLabel = "FOR_" + newLabel();
    //     writeIntoAssemblyLogFile("JMP " + tempStatementLabel + "\n"); // execute "expression" (third one) later
    //     writeIntoAssemblyLogFile(tempExpressionLabel + ":\n");
    // }
    // expression {
    //     writeIntoAssemblyLogFile("JMP " + loopLabel + "\n");
    // } 
    // RPAREN 
    // {
    //     writeIntoAssemblyLogFile(tempStatementLabel + ":\n");   
    // }
    // statement
    // {
    //     writeIntoAssemblyLogFile("JMP " + tempExpressionLabel + "\n");

    //     // writeIntoAssemblyLogFile("JMP " + loopLabel + "\n");
    //     writeIntoAssemblyLogFile(elseLabels.get(elseLabels.size() - 1) + ":\n");
            
    //     writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": statement : WHILE LPAREN expression RPAREN statement\n" + $ctx.getText() + "\n");
    

    // writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": statement : FOR LPAREN expression_statement expression_statement expression RPAREN statement\n" + $ctx.getText() + "\n"); 
    // }

    | IF LPAREN expression 
    {
        // if without else
        String endLabel = newLabel();
        elseLabels.add(endLabel);
        writeIntoAssemblyLogFile("POP AX" + "; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()));
        writeIntoAssemblyLogFile("CMP AX, 0\n");
        writeIntoAssemblyLogFile("JE " + endLabel + "\n");
    }

    RPAREN statement
    {         
        // if without else continued
        writeIntoAssemblyLogFile(elseLabels.get(elseLabels.size() - 1) + ":\n" + "; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()));
        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": statement : IF LPAREN expression RPAREN statement\n" + $ctx.getText() + "\n"); 
    }
    | IF LPAREN expression
    {
        String elseLabel = newLabel();
        elseLabels.add(elseLabel);
        writeIntoAssemblyLogFile("POP AX" + "; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()));
        writeIntoAssemblyLogFile("CMP AX, 0\n");   
        writeIntoAssemblyLogFile("JE " + elseLabel + "\n");
    }
     RPAREN statement
     {
        String exitLabel = newLabel();
        exitLabels.add(exitLabel);
        writeIntoAssemblyLogFile("JMP " + exitLabel + "\n");
        writeIntoAssemblyLogFile(elseLabels.get(elseLabels.size() - 1) + ":\n");
        elseLabels.remove(elseLabels.size() - 1);
     }
      ELSE statement
      {
        writeIntoAssemblyLogFile(exitLabels.get(exitLabels.size() - 1) + ":\n");
        exitLabels.remove(exitLabels.size() - 1);
      }
    { writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": statement : IF LPAREN expression RPAREN statement ELSE statement\n" + $ctx.getText() + "\n"); }

    | WHILE {
        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": statement : WHILE LPAREN expression RPAREN statement\n" + $ctx.getText() + "\n");

        String loop_label = newLabel();
		String label_start = "WHILE_START_" + loop_label;
		String label_end = "WHILE_END_" + loop_label;
		writeIntoAssemblyLogFile(label_start + ":");        // start of while loop
	  } LPAREN expression {
		writeIntoAssemblyLogFile("POP AX"  + " ; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n");
		writeIntoAssemblyLogFile("CMP AX, 0\n");
		writeIntoAssemblyLogFile("JE " + label_end + "\n");  // if condition is false, exit loop

	 } RPAREN S=statement {
		writeIntoAssemblyLogFile("JMP " + label_start + "\n"); // jump back to start of loop
		writeIntoAssemblyLogFile(label_end + ":");          // end of while loop
	}
    // WHILE LPAREN {
    //     String loopLabel = "WHILE_" +  newLabel();
    //     String endLabel = "WHILE_" +  newLabel();
    //     elseLabels.add(endLabel);

    //     writeIntoAssemblyLogFile(loopLabel + ":\n" + "; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()));
        
    // } 
    // expression
    // {
    //     writeIntoAssemblyLogFile("POP AX\n");
    //     writeIntoAssemblyLogFile("CMP AX, 0\n");
    //     writeIntoAssemblyLogFile("JE " + endLabel + "\n");
    // }
    // RPAREN statement
    // {
    //     writeIntoAssemblyLogFile("JMP " + loopLabel + "\n");
    //     writeIntoAssemblyLogFile(elseLabels.get(elseLabels.size() - 1) + ":\n");
            
    //     writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": statement : WHILE LPAREN expression RPAREN statement\n" + $ctx.getText() + "\n");
    // }
    | PRINTLN LPAREN ID RPAREN SEMICOLON
    { 
            writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": statement : PRINTLN LPAREN ID RPAREN SEMICOLON\n" + $ctx.getText() + "\n"); 
            String var = $ID.text;

            SymbolInfo symbolInfo = symbolTable.lookup(var);

            

            if (symbolInfo.isGlobal()) {
                writeIntoAssemblyLogFile("MOV AX, " + var + "\t; Line " + $ID.getLine() + "\n");
            } else { // local variable
                int offset = symbolInfo.getStackOffset();
                writeIntoAssemblyLogFile("MOV AX, [BP" + (offset >= 0 ? "-" : "") + offset + "]\t; Line " + $ID.getLine() + "\n");
            }
            writeIntoAssemblyLogFile("CALL print_output\n" + "; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()));
            writeIntoAssemblyLogFile("CALL new_line\n");
    }
    | PRINTF LPAREN ID RPAREN SEMICOLON
    { 
        // check if id is declared
        if (symbolTable.lookup($ID.text) == null) {
            writeIntoErrorFile("Error at Line " + $ID.getLine() + 
                             ": Undeclared variable '" + $ID.text + "'");
        }
        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": statement : PRINTF LPAREN ID RPAREN SEMICOLON\n" + $ctx.getText() + "\n"); 
    }
    | RETURN expression SEMICOLON
    { 
        

        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": statement : RETURN expression SEMICOLON\n" + $ctx.getText() + "\n"); 
        
        if (currentFunctionReturnType != null && currentFunctionReturnType.equals("void")) {
              writeIntoErrorFile("Error at line " + $ctx.start.getLine() + 
                               ": Cannot return value from function " + currentFunctionName + " with void return type");
              errorCount++;
        }

        writeIntoAssemblyLogFile("POP AX");
        if(!currentFunctionName.equals("MAIN")) writeIntoAssemblyLogFile("JMP " + currentFunctionName + "_EXIT_LABEL" + "\n");
        // writeIntoAssemblyLogFile("RET 4"); // HARDCODED

    }
    ;

expression_statement
    : SEMICOLON
    { writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": expression_statement : SEMICOLON\n;\n"); }
    | expression SEMICOLON
    { writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": expression_statement : expression SEMICOLON\n" + $ctx.getText() + "\n"); }
    ;

variable
returns [boolean isArray]
    : ID
    { 
        $isArray = false;
        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": variable : ID\n" + $ctx.getText() + "\n"); 

        // if (symbolTable.lookup($ID.text) == null) {
        //     writeIntoErrorFile("Error at Line " + $ID.getLine() + 
        //                      ": Undeclared variable '" + $ID.text + "'");
        //     errorCount++;
        // }

    }
    | id=ID LTHIRD e=expression RTHIRD
    { 
        SymbolInfo info = symbolTable.lookup($id.text);
          if (info != null && !info.isArray()) {
              writeIntoErrorFile("Error at line " + $id.getLine() + 
                               ": " + $id.text + " not an array");
              errorCount++;
          }

        if (info != null && info.isArray()) {
            // Check array bounds if constant index
            if ($e.text.matches("\\d+")) {
                int index = Integer.parseInt($e.text);
                int arraySize = info.getArraySize();
                if (index >= arraySize) {
                    writeIntoErrorFile("Error at line " + $ctx.start.getLine() + 
                                     ": Array index " + index + " out of bounds for size " + arraySize);
                    errorCount++;
                }
            }
        }

        $isArray = true;
        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": variable : ID LTHIRD expression RTHIRD\n" + $ctx.getText() + "\n"); 
        if ($e.text.matches(".*\\..*")) { // Check for float index
            writeIntoErrorFile("Error at Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + 
                             ": Expression inside third brackets not an integer");
            errorCount++;
        }
    }
    ;

// expression
//     : logic_expression
//     | variable ASSIGNOP logic_expression
//     ;

expression
    : logic_expression
      { writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": expression : logic_expression\n" + $logic_expression.text + "\n"); }
    | v=variable (LTHIRD CONST_INT RTHIRD)? ASSIGNOP e=logic_expression
      { 
        SymbolInfo var = symbolTable.lookup($v.text);
        writeIntoAssemblyLogFile("POP AX" + "; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()));

        
        if ($ctx.LTHIRD() != null) {
            int index = Integer.parseInt($ctx.CONST_INT().getText());

            // this is an array
            if (var.isGlobal()) {
                // global array
                writeIntoAssemblyLogFile("MOV WORD PTR [" + $v.text + " + " + 2*index + "]" + ", AX\n");
            }

            else {
                // local array
                int stkoff = var.getStackOffset();

                writeIntoAssemblyLogFile("MOV [BP-" + (stkoff + index*2) + "], AX\n");
            }
        }
      

        if (var.isGlobal() && $ctx.LTHIRD() == null) writeIntoAssemblyLogFile("MOV " + var.getName() + ", AX\n");
        else if ((!var.isGlobal() && $ctx.LTHIRD() == null)) {
            int stkoff = var.getStackOffset();
            if (!var.isFunctionParam())  writeIntoAssemblyLogFile("MOV [BP-" + stkoff + "], AX\n");
            else writeIntoAssemblyLogFile("MOV [BP+" + stkoff + "], AX\n");
        }

        String rawVar = $v.text.replaceAll("\\[.*\\]", ""); // Removes anything inside [...]
        if (symbolTable.lookup(rawVar) == null) {
            writeIntoErrorFile("Error at Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + 
                             ": Undeclared variable '" + $v.text + "'");
            errorCount++;
        }


        // handle type mismatches
        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": expression : variable ASSIGNOP logic_expression\n" + $ctx.getText() + "\n"); 
        boolean vIsArray; 
        vIsArray = (symbolTable.lookup($v.text, true) != null && symbolTable.lookup($v.text, true).isArray());
        
        if ($e.isArray && vIsArray) {
                  writeIntoErrorFile("Error at line " + $ctx.start.getLine() + 
                                   ": Type mismatch, " + $v.text + " is not an array");
                  errorCount++;
        }


        SymbolInfo varInfo = symbolTable.lookup($v.ctx.getText());
        if (varInfo != null) {
            // Check if assigning float to int
            if (varInfo.getDataType().equals("int") && $e.text.matches(".*\\d+\\.\\d+.*") && !$e.text.contains("%")) {
                writeIntoErrorFile("Error at Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + 
                                 ": Type mismatch : cannot assign float to int");
                errorCount++;
            }
        }


        // int c[4]; c[3] = 2.7; -> error!
        if (varInfo != null) {
            // Check if assigning to array element
            if (vIsArray) {
                // For array assignments, check if assigning float to int array
                if (varInfo.getDataType().contains("int") && $e.isConstFloat) {
                    writeIntoErrorFile("Error at line " + $ctx.start.getLine() + 
                                     ": Cannot assign float value to int array");
                    errorCount++;
                }
            }
        }
      }
      |
      v=variable ASSIGNOP e=logic_expression
      {
            SymbolInfo var = symbolTable.lookup($v.text);

            writeIntoAssemblyLogFile("POP AX " + "; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()));
            if (var.isGlobal()) writeIntoAssemblyLogFile("MOV " + var.getName() + ", AX\n");
            else {
                int stkoff = var.getStackOffset();
                if (!var.isFunctionParam()) writeIntoAssemblyLogFile("MOV [BP-" + stkoff + "], AX\n");
                else writeIntoAssemblyLogFile("MOV [BP+" + stkoff + "], AX\n");
            }

            boolean vIsArray; 
            vIsArray = (symbolTable.lookup($v.text, true) != null && symbolTable.lookup($v.text, true).isArray());

            if(vIsArray && !$e.isArray && !$v.text.contains("[")) {
                writeIntoErrorFile("Error at Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + 
                                 ": Type mismatch : " + $v.text + " is an array");
            } 

      }
      | // Error production for malformed assignments
      v=variable ASSIGNOP junk+=expression_fragment* ASSIGNOP
      {
          writeIntoErrorFile("Error at line " + $ASSIGNOP.getLine() + ": syntax error, unexpected ASSIGNOP");
          errorCount++;
      }
    ;

logic_expression
returns [boolean isArray, boolean isConstFloat]
    : rel_expression
    { 
        $isArray = $rel_expression.isArray;
        $isConstFloat = $rel_expression.isConstFloat;
        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": logic_expression : rel_expression\n" + $rel_expression.text + "\n"); 
    }
    | { 
        // Declare labels at the start 
        String trueLabel = newLabel();
        String falseLabel = newLabel();
        String endLabel = newLabel();
      }
      r1=rel_expression LOGICOP 
      {
        if ($LOGICOP.text.equals("&&")) {
            // For AND: if first is false, jump to false (short-circuit)
            writeIntoAssemblyLogFile("POP AX " + "; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()));
            writeIntoAssemblyLogFile("CMP AX, 0\n");
            writeIntoAssemblyLogFile("JE " + falseLabel + "\n");
        }
        else if ($LOGICOP.text.equals("||")) {
            // For OR: if first is true, jump to true (short-circuit)
            writeIntoAssemblyLogFile("POP AX "  + " ; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n");
            writeIntoAssemblyLogFile("CMP AX, 1\n");
            writeIntoAssemblyLogFile("JE " + trueLabel + "\n");
        }
      }
      r2=rel_expression
      { 
        $isArray = $r2.isArray;
        $isConstFloat = $r2.isConstFloat;
        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": logic_expression : rel_expression LOGICOP rel_expression\n" + $ctx.getText() + "\n"); 
        
        // Evaluate second operand
        writeIntoAssemblyLogFile("POP AX " + "; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()));
        writeIntoAssemblyLogFile("CMP AX, 0\n");
        
        if ($LOGICOP.text.equals("&&")) {
            // For AND: if second is false, jump to false, else to true
            writeIntoAssemblyLogFile("JE " + falseLabel + "\n");
            writeIntoAssemblyLogFile("JMP " + trueLabel + "\n");
        }
        else if ($LOGICOP.text.equals("||")) {
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
    ;

rel_expression
returns [boolean isArray, boolean isConstFloat]
    : simple_expression
    { 
        $isConstFloat = $simple_expression.isConstFloat;
        $isArray = $simple_expression.isArray;
        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": rel_expression : simple_expression\n" + $simple_expression.text + "\n"); }
    | simple_expression RELOP simple_expression
    { 
        $isConstFloat = $simple_expression.isConstFloat;
        $isArray = $simple_expression.isArray;
        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": rel_expression : simple_expression RELOP simple_expression\n" + $ctx.getText() + "\n"); 

        String relop = ""; 
        if ($RELOP.text.equals("<=")) { relop = "JLE"; }
        if ($RELOP.text.equals(">=")) { relop = "JGE"; }
        if ($RELOP.text.equals("<")) { relop = "JL"; }
        if ($RELOP.text.equals(">")) { relop = "JG"; }
        if ($RELOP.text.equals("==")) { relop = "JE"; }
        if ($RELOP.text.equals("!=")) { relop = "JNE"; }

        String trueLabel = newLabel();
        String falseLabel = newLabel();

        writeIntoAssemblyLogFile("POP AX " + "; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()));
        writeIntoAssemblyLogFile("POP CX\n");
        writeIntoAssemblyLogFile("CMP CX, AX\n");
        writeIntoAssemblyLogFile(relop + " " + trueLabel + "\n");
        writeIntoAssemblyLogFile("PUSH 0\n");
        writeIntoAssemblyLogFile("JMP " + falseLabel + "\n");
        writeIntoAssemblyLogFile(trueLabel + ":\n");
        writeIntoAssemblyLogFile("PUSH 1\n");
        writeIntoAssemblyLogFile(falseLabel + ":\n");
    }
    ;

simple_expression
returns [boolean isArray, boolean isConstFloat]
    : term
    { 
        $isArray = $term.isArray;
        $isConstFloat = $term.isConstFloat;
        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": simple_expression : term\n" + $term.text + "\n"); }
    | simple_expression ADDOP term
    { 
        $isArray = $term.isArray;
        $isConstFloat = $term.isConstFloat;
        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": simple_expression : simple_expression ADDOP term\n" + $ctx.getText() + "\n"); 

        writeIntoAssemblyLogFile("POP AX " + "; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()));
        writeIntoAssemblyLogFile("POP CX\n");

        if($ADDOP.text.equals("+")) {
            writeIntoAssemblyLogFile("ADD CX, AX\n");
        } 
        else{
            writeIntoAssemblyLogFile("SUB CX, AX\n");
        }

        writeIntoAssemblyLogFile("PUSH CX\n");
    }
    ;

term
returns [boolean isArray, boolean isConstFloat]
    : unary_expression
    { 
        $isConstFloat = $unary_expression.isConstFloat;
        $isArray = $unary_expression.isArray;
        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": term : unary_expression\n" + $unary_expression.text + "\n"); }
    | t1=term op=MULOP u=unary_expression
    { 
        $isConstFloat = $unary_expression.isConstFloat;
        $isArray = $unary_expression.isArray;
        // if MULOP is modulus then number of terms can only be two

        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": term : term MULOP unary_expression\n" + $ctx.getText() + "\n"); 
        if ($op.text.equals("%")) {

            //modulus by zero check
            if ($u.text.equals("0")) {
                  writeIntoErrorFile("Error at line " + $ctx.start.getLine() + 
                                   ": Modulus by Zero");
                  errorCount++;
            }

            SymbolInfo symbolinfo1 = symbolTable.lookup($t1.text, true);
            SymbolInfo symbolinfo2 = symbolTable.lookup($u.text, true);

            boolean isFloat1 = symbolinfo1 == null && $t1.text.matches(".*\\..*");
            boolean isFloat2 = symbolinfo2 == null && $u.text.matches(".*\\..*");

            if (symbolinfo1 != null) isFloat1 = symbolinfo1.getDataType().equals("float");
            if (symbolinfo2 != null) isFloat2 = symbolinfo2.getDataType().equals("float");

            // if ($t1.text.matches(".*\\..*") || $u.text.matches(".*\\..*")) {

            if ( isFloat1 || isFloat2 )
            {
            
                // if either of the operands is a float then error
                writeIntoErrorFile("Error at Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + 
                                 ": Non-integer operands for modulus operator");
                errorCount++;
            }
        }

        
        writeIntoAssemblyLogFile("POP CX " + "; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()));
        writeIntoAssemblyLogFile("POP AX\n");

        // Converts word to double-word, does sign extension for DX:AX
        writeIntoAssemblyLogFile("CWD\n");

        if($op.text.equals("*")){
            writeIntoAssemblyLogFile("MUL CX\n");
        }
        else {       // divide or modulus                   
            writeIntoAssemblyLogFile("IDIV CX\n"); 
        }

        if($op.text.equals("%")){
            writeIntoAssemblyLogFile("PUSH DX\n");
        }else{
            writeIntoAssemblyLogFile("PUSH AX\n");
        }
    }
    ;

unary_expression
returns [boolean isConstFloat, boolean isArray]
    : ADDOP u=unary_expression
    { 

        writeIntoAssemblyLogFile("POP AX" +   " ; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n");

        if($ADDOP.text.equals("-")) {
            writeIntoAssemblyLogFile("NEG AX");
        }

        writeIntoAssemblyLogFile("PUSH AX");


        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": unary_expression : ADDOP unary_expression\n" + $ctx.getText() + "\n"); }
    | NOT unary_expression
    {{ 
        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": unary_expression : NOT unary_expression\n" + $ctx.getText() + "\n"); 
        String not_label = newLabel();
        String trueLabel = "NOT_TRUE_" + (not_label);
		String endLabel = "NOT_END_" + (not_label);

		writeIntoAssemblyLogFile("POP AX " +   " ; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n");
		writeIntoAssemblyLogFile("CMP AX, 0");
		writeIntoAssemblyLogFile("MOV AX, 1");
		writeIntoAssemblyLogFile("JE " + endLabel);
		writeIntoAssemblyLogFile("MOV AX, 0");
		writeIntoAssemblyLogFile(endLabel + ":");
		writeIntoAssemblyLogFile("PUSH AX");
    
    }}
    | factor
    { 
        // ??
        $isArray = $factor.isArray;
        $isConstFloat = $factor.isConstFloat;
        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": unary_expression : factor\n" + $ctx.getText() + "\n"); }
    ;

factor
returns [boolean isConstFloat, boolean isArray]
    : v=variable
    {  
    // variable may be either array or normal CONST_INT

    int arrIndex = -1;
    String arrName = "";
    if ($v.text.contains("[")) { // this is an array
        int start = $v.text.indexOf('['), end = $v.text.indexOf(']');
        arrIndex = Integer.parseInt($v.text.substring(start + 1, end));
        arrName = $v.text.substring(0, start);
    }

    SymbolInfo symbolInfo;
    if (!$v.text.contains("["))  symbolInfo = symbolTable.lookup($v.text);
    else symbolInfo = symbolTable.lookup(arrName);

    if (symbolInfo.isGlobal()) {
        if ($v.text.contains("[")) {
        // global array
            writeIntoAssemblyLogFile( "MOV AX, [" + arrName + " + " + 2*arrIndex + "] "  + " ; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n" );
            writeIntoAssemblyLogFile( "PUSH AX\n" );
        }

        else {
        // global variable
            writeIntoAssemblyLogFile( "MOV AX, " + $ctx.getText() + "\n " + "; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) );   
            writeIntoAssemblyLogFile( "PUSH AX\n" );
        }
    }
    
    else { // local variable or array
        if (!$v.text.contains("[")) { // local variable
            if (!symbolInfo.isFunctionParam()) writeIntoAssemblyLogFile( "MOV AX, [BP-" + symbolInfo.getStackOffset() + "]\n "  + " ; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n"); 
            else writeIntoAssemblyLogFile("MOV AX, [BP+" + symbolInfo.getStackOffset() + "]\n"  + " ; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n");
        }  

        // else local array
        else writeIntoAssemblyLogFile( "MOV AX, [BP-" + (symbolInfo.getStackOffset() + 2*arrIndex) + "]\n "  + " ; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n");   
    
        writeIntoAssemblyLogFile( "PUSH AX\n" );
    }
      
    writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": factor : variable\n" + $ctx.getText() + "\n"); 
    
    if ($v.isArray) {
        $isArray = true;
        SymbolInfo info = symbolTable.lookup($v.ctx.getText());
            if (info != null && !info.isArray()) {
                writeIntoErrorFile("Error at Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + 
                                 ": '" + $v.ctx.getText() + "' is not an array");
                errorCount++;
            }
    }

    else $isArray = false;

    } 
    | id=ID LPAREN arg=argument_list RPAREN
    {
    // function call

        int size = $arg.argNames.size();

        if (size >= 2) {
            invertTopStackElements(size);
        }

        writeIntoAssemblyLogFile("CALL " + $id.text  + " ; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n" );
        writeIntoAssemblyLogFile("PUSH AX\n");


        SymbolInfo funcInfo = symbolTable.lookup($id.text, true);

        
          if (funcInfo == null && !$id.text.equals("main")) {
              writeIntoErrorFile("Error at line " + $id.getLine() + 
                               ": Undefined function " + $id.text);
              errorCount++;
          }
        
        if (funcInfo != null && funcInfo.isFunction()) {
            // Check parameter count
            if (funcInfo.getFunctionInfo().getParameterCount() != $arg.argTypes.size()) {
                writeIntoErrorFile("Error at Line " + $id.getLine() + 
                    ": Argument count mismatch for function '" + $id.text + "'");
                errorCount++;
            } else {
                // Check each parameter type
                for (int i = 0; i < funcInfo.getFunctionInfo().getParameterCount(); i++) {
                    // SymbolInfo expected = funcInfo.getFunctionInfo().getParameterTypes().get(i);
                    String expected = funcInfo.getFunctionInfo().getParameterTypes().get(i);
                    String actualName = $arg.argNames.get(i);
                    String actual = $arg.argTypes.get(i);

                    SymbolInfo actualInfo = symbolTable.lookup(actualName, true);
                    
                    // Check if passing array to non-array parameter
                    if (actualInfo != null && actualInfo.isArray() && !expected.equalsIgnoreCase("array")) {
                        // writeIntoErrorFile("Error at Line " + $id.getLine() + 
                        //     ": Cannot pass array to non-array parameter"  + 
                        //     " of function '" + $id.text + "' or vice versa");
                        writeIntoErrorFile("Error at Line " + $id.getLine() + ": Type mismatch - " + actualName + " is an array");
                        errorCount++;
                    }
                    // Check basic type compatibility
                    else if (!expected.equalsIgnoreCase(actual)) {
                        // 1th argument mismatch in function func
                        writeIntoErrorFile("Error at Line " + $id.getLine() + ": " + (i+1)
                        + "th argument mismatch in function "
                             + $id.text);

 
                        errorCount++;
                    }
                }
            }
        }
      



    //
      writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": factor : ID LPAREN argument_list RPAREN\n" + $ctx.getText() + "\n"); 
    } 
    | LPAREN expression RPAREN
    { { writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": factor : LPAREN expression RPAREN\n" + $ctx.getText() + "\n"); } }
    | CONST_INT
    { 
        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": factor : CONST_INT\n" + $ctx.getText() + "\n"); 
        writeIntoAssemblyLogFile( "MOV AX, " + $ctx.getText() + "\n " + "; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()));   
        writeIntoAssemblyLogFile( "PUSH AX\n" );   
    } 
    | CONST_FLOAT
    { { 
        $isConstFloat = true;
        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": factor : CONST_FLOAT\n" + $ctx.getText() + "\n"); } }
    | variable INCOP
    { { 
        int arrIndex = -1;
        String arrName = "";
        if ($variable.text.contains("[")) { // this is an array
            int start = $variable.text.indexOf('['), end = $variable.text.indexOf(']');
            arrIndex = Integer.parseInt($variable.text.substring(start + 1, end));
            arrName = $variable.text.substring(0, start);
        }

        SymbolInfo symbolInfo;
        if (!$variable.text.contains("["))  symbolInfo = symbolTable.lookup($variable.text);
        else symbolInfo = symbolTable.lookup(arrName);

        // SymbolInfo symbolInfo = symbolTable.lookup($variable.text);


        if (symbolInfo.isGlobal() && !$variable.text.contains("[")) {
            writeIntoAssemblyLogFile("MOV AX, " + $variable.text  + " ; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n");
            writeIntoAssemblyLogFile("PUSH AX\n");
            writeIntoAssemblyLogFile("INC AX\n");
            writeIntoAssemblyLogFile("MOV " + $variable.text + ", AX\n");
            // writeIntoAssemblyLogFile("INC " + $variable.text + "\n" + "Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n") ;
        }

        else if (!symbolInfo.isGlobal() && !$variable.text.contains("[")) {
            // local variable
            int stkoffset = symbolInfo.getStackOffset();
            // writeIntoAssemblyLogFile("INC WORD PTR [BP-" + stkoffset + "]\n" + "; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()));
            writeIntoAssemblyLogFile("MOV AX, " + "[BP-" + stkoffset + "]"  + " ; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n");
            writeIntoAssemblyLogFile("PUSH AX\n");
            writeIntoAssemblyLogFile("INC AX\n");
            writeIntoAssemblyLogFile("MOV " + "[BP-" + stkoffset + "]" + ", AX\n");
        }

        else if (symbolInfo.isGlobal() && $variable.text.contains("["))
        {
            // global array
            writeIntoAssemblyLogFile( "MOV AX, [" + arrName + " + " + 2*arrIndex + "] "  + " ; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n");
            writeIntoAssemblyLogFile("PUSH AX\n");
            writeIntoAssemblyLogFile("INC AX\n");
            writeIntoAssemblyLogFile("MOV WORD PTR [" + arrName + " + " + 2*arrIndex + "]" + ", AX\n");
        }

        else if(!symbolInfo.isGlobal() && $variable.text.contains("["))
        {
            // local array
            writeIntoAssemblyLogFile( "MOV AX, [BP-" + (symbolInfo.getStackOffset() + 2*arrIndex) + "] "  + " ; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n" );   
            writeIntoAssemblyLogFile("PUSH AX\n");
            writeIntoAssemblyLogFile("INC AX\n");
            writeIntoAssemblyLogFile("MOV " + "[BP-" + (symbolInfo.getStackOffset() + 2*arrIndex) + "]" + ", AX\n");
        }

        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": factor : variable INCOP\n" + $ctx.getText() + "\n"); 
    } }
    | variable DECOP
    { { 
        // SymbolInfo symbolInfo = symbolTable.lookup($variable.text);
        // if (symbolInfo.isGlobal()) {
        //     // writeIntoAssemblyLogFile("DEC " + $variable.text + "\n");
        //     writeIntoAssemblyLogFile("MOV AX, " + $variable.text + "\n");
        //     writeIntoAssemblyLogFile("PUSH AX\n");
        //     writeIntoAssemblyLogFile("DEC AX\n");
        //     writeIntoAssemblyLogFile("MOV " + $variable.text + ", AX\n");
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
        if ($variable.text.contains("[")) { // this is an array
            int start = $variable.text.indexOf('['), end = $variable.text.indexOf(']');
            arrIndex = Integer.parseInt($variable.text.substring(start + 1, end));
            arrName = $variable.text.substring(0, start);
        }

        SymbolInfo symbolInfo;
        if (!$variable.text.contains("["))  symbolInfo = symbolTable.lookup($variable.text);
        else symbolInfo = symbolTable.lookup(arrName);

        // SymbolInfo symbolInfo = symbolTable.lookup($variable.text);


        if (symbolInfo.isGlobal() && !$variable.text.contains("[")) {
            writeIntoAssemblyLogFile("MOV AX, " + $variable.text  + " ; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n");
            writeIntoAssemblyLogFile("PUSH AX\n");
            writeIntoAssemblyLogFile("DEC AX\n");
            writeIntoAssemblyLogFile("MOV " + $variable.text + ", AX\n");
            // writeIntoAssemblyLogFile("INC " + $variable.text + "\n" + "Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n") ;
        }

        else if (!symbolInfo.isGlobal() && !$variable.text.contains("[")) {
            // local variable
            int stkoffset = symbolInfo.getStackOffset();
            // writeIntoAssemblyLogFile("INC WORD PTR [BP-" + stkoffset + "]\n" + "; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()));
            writeIntoAssemblyLogFile("MOV AX, " + "[BP-" + stkoffset + "]"  + " ; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n");
            writeIntoAssemblyLogFile("PUSH AX\n");
            writeIntoAssemblyLogFile("DEC AX\n");
            writeIntoAssemblyLogFile("MOV " + "[BP-" + stkoffset + "]" + ", AX\n");
        }

        else if (symbolInfo.isGlobal() && $variable.text.contains("["))
        {
            // global array
            writeIntoAssemblyLogFile( "MOV AX, [" + arrName + " + " + 2*arrIndex + "] "  + " ; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n" );
            writeIntoAssemblyLogFile("PUSH AX\n");
            writeIntoAssemblyLogFile("DEC AX\n");
            writeIntoAssemblyLogFile("MOV WORD PTR [" + arrName + " + " + 2*arrIndex + "]" + ", AX\n");
        }

        else if(!symbolInfo.isGlobal() && $variable.text.contains("["))
        {
            // local array
            writeIntoAssemblyLogFile( "MOV AX, [BP-" + (symbolInfo.getStackOffset() + 2*arrIndex) + "] "  + " ; Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + "\n" );   
            writeIntoAssemblyLogFile("PUSH AX\n");
            writeIntoAssemblyLogFile("DEC AX\n");
            writeIntoAssemblyLogFile("MOV " + "[BP-" + (symbolInfo.getStackOffset() + 2*arrIndex) + "]" + ", AX\n");
        }



        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": factor : variable DECOP\n" + $ctx.getText() + "\n"); } }
    ;

argument_list 
returns [List <String> argNames, List<String> argTypes]
@init {
    $argNames = new ArrayList<>();
    $argTypes = new ArrayList<>();
}
    : arg=arguments
    { { 
        $argNames = $arg.argNames;
        $argTypes = $arg.argTypes;
        writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": argument_list : arguments\n" + $ctx.getText() + "\n");
    } }
    | /* empty */
    { { { writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": argument_list : \n" + $ctx.getText() + "\n"); } } }
    ;



// arguments
//     : arguments COMMA logic_expression
//     { { writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": arguments : arguments COMMA logic_expression\n" + $ctx.getText() + "\n"); } }
//     | logic_expression
//     { { writeIntoParserLogFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + ": arguments : logic_expression\n" + $ctx.getText() + "\n"); } }
//     ;

arguments returns [List<String> argNames, List<String> argTypes]
@init {
    $argNames = new ArrayList<>();
    $argTypes = new ArrayList<>();
}
    : a1=argument { $argNames.add($a1.name); $argTypes.add($a1.type); }
      (COMMA an=argument { $argNames.add($an.name); $argTypes.add($an.type); })*
    ;
    
argument returns [String name, String type]
    : e=expression 
      { 
        $name = $e.text;
        // Determine type from expression
        if ($e.text.matches(".*\\..*")) {
            $type = "float";
        } else {
            $type = "int";
        }
      }
    | v=variable
      {
        $name = $v.text;
        SymbolInfo info = symbolTable.lookup($v.text, true);
        if (info != null) {
            $type = info.getDataType();
            if (info.isArray()) {
                // writeIntoErrorFile("Line " + ($ctx.stop == null ? $ctx.start.getLine() : $ctx.stop.getLine()) + 
                //     ": Cannot pass array '" + $v.text + "' as parameter");
                // errorCount++;
            }
        } else {
            $type = "unknown";
        }
      }
    ;


expression_fragment
    : ID
    | CONST_INT
    | CONST_FLOAT
    | ADDOP
    | MULOP
    | LPAREN
    | RPAREN
    | INCOP
    | DECOP
    | UNRECOGNIZED
    ;