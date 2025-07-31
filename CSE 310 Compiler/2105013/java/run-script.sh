bash clean-script.sh
cd ..
javac SymbolTable.java PeepholeOptimizer.java
mv *.class java/
cd ./java
# antlr4 -v 4.13.2 C2105013Lexer.g4 C2105013Parser.g4
# antlr4 C2105013Lexer.g4 C2105013Parser.g4
java -Xmx500M -cp "/usr/local/lib/antlr-4.13.2-complete.jar:$CLASSPATH" org.antlr.v4.Tool C2105013Lexer.g4 C2105013Parser.g4
javac -cp .:/usr/local/lib/antlr-4.13.2-complete.jar  C2105013*.java Main.java
java -cp .:/usr/local/lib/antlr-4.13.2-complete.jar Main input/test1_i.c
java PeepholeOptimizer output/code.asm output/optimized_code.asm