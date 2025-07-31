/*
 * javac PeepholeOptimizer.java
 * java PeepholeOptimizer input.asm output.asm
 */

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class PeepholeOptimizer {
    private static final Pattern MOV_PATTERN = Pattern.compile("MOV\\s+([^,]+),\\s*([^\\s;]+)");
    private static final Pattern PUSH_POP_PATTERN = Pattern.compile("(PUSH|POP)\\s+([^\\s;]+)");
    private static final Pattern ARITH_PATTERN = Pattern.compile("(ADD|SUB|MUL|DIV|AND|OR|XOR)\\s+([^,]+),\\s*([^\\s;]+)");
    private static final Pattern LABEL_PATTERN = Pattern.compile("^([A-Za-z_][A-Za-z0-9_]*):");
    private static final Pattern LABEL_PATTERN_COLON_SEPARATED = Pattern.compile("^([A-Za-z_][A-Za-z0-9_]*)\\:");
    private static final Pattern JMP_PATTERN = Pattern.compile("(JMP|JE|JNE|JG|JGE|JL|JLE)\\s+([^\\s;]+)");
    private static final Pattern COMMENT_PATTERN = Pattern.compile(";.*$");

    public static void optimizeAssembly(String inputFile, String outputFile) throws IOException {
        List<String> lines = readAndCleanLines(inputFile);
        List<String> optimized = new ArrayList<>();
        
        // First pass: Remove redundant operations
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) {
                continue;
            }
            
            // Check for redundant MOV
            if (i < lines.size() - 1) {
                Matcher m1 = MOV_PATTERN.matcher(line);
                Matcher m2 = MOV_PATTERN.matcher(lines.get(i+1).trim());
                if (m1.matches() && m2.matches() && 
                    m1.group(2).equals(m2.group(1)) && 
                    m1.group(1).equals(m2.group(2))) {
                    // Found MOV AX, a followed by MOV a, AX
                    optimized.add(lines.get(i));
                    i++; // skip the next line
                    continue;
                }
            }
            
            // Check for PUSH/POP pair
            if (i < lines.size() - 1) {
                Matcher m1 = PUSH_POP_PATTERN.matcher(line);
                Matcher m2 = PUSH_POP_PATTERN.matcher(lines.get(i+1).trim());
                if (m1.matches() && m2.matches() && 
                    m1.group(1).equals("PUSH") && m2.group(1).equals("POP") &&
                    m1.group(2).equals(m2.group(2))) {
                    // Found PUSH AX followed by POP AX
                    i++; // skip both lines
                    continue;
                }
            }
            
            // Check for arithmetic operations with identity values
            Matcher arithMatcher = ARITH_PATTERN.matcher(line);
            if (arithMatcher.matches()) {
                String op = arithMatcher.group(1);
                String operand = arithMatcher.group(3);
                
                if ((op.equals("ADD") || op.equals("SUB")) && operand.equals("0")) {
                    continue; // skip ADD/SUB with 0
                } else if ((op.equals("MUL") || op.equals("DIV")) && operand.equals("1")) {
                    continue; // skip MUL/DIV with 1
                } else if (op.equals("AND") && operand.equals("0")) {
                    optimized.add("MOV " + arithMatcher.group(2) + ", 0");
                    continue;
                } else if (op.equals("OR") && operand.equals("0")) {
                    continue;
                } else if (op.equals("XOR") && arithMatcher.group(2).equals(operand)) {
                    optimized.add("MOV " + arithMatcher.group(2) + ", 0");
                    continue;
                }
            }
            
            optimized.add(lines.get(i));
        }
        
        // Second pass: Remove redundant labels
        Map<String, Integer> labelCount = new HashMap<>();
        List<String> labelPass = new ArrayList<>();
        
        // Count label usage
        for (String line : optimized) {
            Matcher labelMatcher = LABEL_PATTERN.matcher(line.trim());
            if (labelMatcher.matches()) {
                String label = labelMatcher.group(1);
                labelCount.put(label, 0);
            }
            
            Matcher jmpMatcher = JMP_PATTERN.matcher(line.trim());
            if (jmpMatcher.matches()) {
                String label = jmpMatcher.group(2);
                labelCount.put(label, labelCount.getOrDefault(label, 0) + 1);
            }
        }
        
        // In the second pass (label processing), modify it to:
Map<String, String> labelRemap = new HashMap<>(); // Maps removed labels to kept labels
String lastKeptLabel = null;

for (String line : optimized) {
    String trimmed = line.trim();
    Matcher labelMatcher = LABEL_PATTERN.matcher(trimmed);
    
    if (labelMatcher.matches()) {
        String label = labelMatcher.group(1);
        if (labelCount.getOrDefault(label, 0) == 0 && lastKeptLabel != null) {
            // Unused label following another label - mark for remapping
            labelRemap.put(label, lastKeptLabel);
            continue;
        }
        lastKeptLabel = label;
        labelPass.add(line);
    } else {
        lastKeptLabel = null;
        labelPass.add(line);
    }
}

// Then add a pass to update jumps referencing removed labels
for (int i = 0; i < labelPass.size(); i++) {
    String line = labelPass.get(i);
    Matcher jmpMatcher = JMP_PATTERN.matcher(line.trim());
    if (jmpMatcher.matches()) {
        String label = jmpMatcher.group(2);
        if (labelRemap.containsKey(label)) {
            // Update the jump to point to the kept label
            String newLabel = labelRemap.get(label);
            line = line.replace(label, newLabel);
            labelPass.set(i, line);
        }
    }
}
        
        // remove JMP to immediately following label
        for (int i = 0; i < labelPass.size() - 1; i++) {
             String currentLine = labelPass.get(i).trim();
             String nextLine = labelPass.get(i+1).trim();
    
             Matcher jmpMatcher = JMP_PATTERN.matcher(currentLine);
             Matcher labelMatcher = LABEL_PATTERN_COLON_SEPARATED.matcher(nextLine);
    
             if (jmpMatcher.matches() && labelMatcher.matches() && 
                 jmpMatcher.group(2).equals(labelMatcher.group(1))) {
                 // Found JMP to immediately following label - remove both
                 labelPass.remove(i);  // Remove the JMP
                 labelPass.remove(i);  // Now i points to what was i+1 (the label)
                 i--; // Adjust index since we removed two items
             }
        }

        // Third pass: Remove consecutive identical jumps
        List<String> finalPass = new ArrayList<>();
        for (int i = 0; i < labelPass.size(); i++) {
            String line = labelPass.get(i);
            if (i > 0) {
                Matcher jmp1 = JMP_PATTERN.matcher(labelPass.get(i-1).trim());
                Matcher jmp2 = JMP_PATTERN.matcher(line.trim());
                if (jmp1.matches() && jmp2.matches() && 
                    jmp1.group(1).equals(jmp2.group(1)) && 
                    jmp1.group(2).equals(jmp2.group(2))) {
                    // Skip duplicate jump
                    continue;
                }
            }
            finalPass.add(line);
        }
        
        writeLines(outputFile, finalPass);
    }
    
    private static List<String> readAndCleanLines(String filename) throws IOException {
        List<String> cleanedLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Remove comments (both whole line and inline)
                line = COMMENT_PATTERN.matcher(line).replaceAll("").trim();
                // Only add non-empty lines
                if (!line.isEmpty()) {
                    cleanedLines.add(line);
                }
            }
        }
        return cleanedLines;
    }
    
    private static void writeLines(String filename, List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
    
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java PeepholeOptimizer input.asm output.asm");
            return;
        }
        
        try {
            optimizeAssembly(args[0], args[1]);
            System.out.println("Optimization complete.");
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
}