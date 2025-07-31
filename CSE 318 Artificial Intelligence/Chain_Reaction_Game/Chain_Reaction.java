import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.BasicButtonUI;

public class Chain_Reaction {
    
    // Game constants
    private static int ROWS = 9;
    private static int COLS = 6;
    private static final char RED = 'R';
    private static final char BLUE = 'B';
    private static final char EMPTY = '0';
    private static final String GAME_FILE = "gamestate.txt";
    private int mode;
    
    // Game state
    private Cell[][] board;
    private char currentPlayer;
    private int numberOfMoves;
    private boolean gameOver;
    private char winner;
    private long timeToCompleteGame;
    private int redOrbs; // count of red orbs
    private int blueOrbs; // count of blue orbs
    private int heuristic1;
    private int heuristic2;
    private List<Integer> heuristic_list = new ArrayList<>(); // for random agent vs ai
    // "Ai Move:" : "Human Move:"
    private String player1;
    private String player2;

    
    // AI settings
    private int searchDepth = 3; // Default depth for minimax
    private boolean useAlphaBeta = true;
    
    public Chain_Reaction(int rows, int cols, int searchDepth) {
        initializeGame(rows, cols, searchDepth);
        setPlayers("Human Move:", "Ai Move:");
    }

    public void setHeuristicList(List <Integer> heuristics) {
        this.heuristic_list = heuristics;
    }

    public void setPlayers (String p1, String p2) {
        player1 = p1;
        player2 = p2;
    }
    
    private void initializeGame(int rows, int cols, int searchDepth) {
        ROWS = rows;
        COLS = cols;
        this.searchDepth = searchDepth;
        numberOfMoves = 0;
        board = new Cell[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
        currentPlayer = RED; // Red goes first
        gameOver = false;
        winner = EMPTY;
        redOrbs = 0;
        blueOrbs = 0;
        timeToCompleteGame = 0;
    }

    private void setHeuristic(int h1, int h2) {
        // 1 -> orb count difference
        // 2 -> Critical mass potential
        // 3 -> Board control (number of cells controlled)
        // 4 -> Threat detection (potential to explode next turn)
        // 5 -> Corner control
        heuristic1 = h1;
        heuristic2 = h2;
    }

    private void setHeuristic(int h1) {
        heuristic1 = h1;
    }
    
    // Cell class representing each cell on the board
    private class Cell {
        int count;
        char color;
        int i;
        int j;

        Cell(int i, int j) {
            this.i = i;
            this.j = j;
            reset();
        }
        
        void set(char color, int count) {
            this.color = color;
            this.count = count;
        }
        
        void reset() {
            count = 0;
            color = EMPTY;
        }
        
        boolean isEmpty() {
            return color == EMPTY;
        }
        
        int getCriticalMass() {
            if ((i == 0 || i == ROWS - 1) && (j == 0 || j == COLS - 1)) {
                return 2; // Corner
            } else if (i == 0 || i == ROWS - 1 || j == 0 || j == COLS - 1) {
                return 3; // Edge
            } else {
                return 4; // Middle
            }
        }
    }
    
    // Main game loop
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Chain Reaction Game");
        System.out.println("1. Human vs Human");
        System.out.println("2. Human vs AI");
        System.out.println("3. AI vs AI");
        System.out.println("4. Random-move agent vs AI");
        System.out.print("Choose game mode: ");
        int mode;
        boolean validModeInt;
        
        do {
            mode = scanner.nextInt();
            validModeInt = ((mode >= 1 && mode <= 4));
            if (!validModeInt) System.out.println("Please select a valid mode (1-4): ");
        } while (!validModeInt);




        if (mode != 1) {
            System.out.println("\nFive heuristics are available for the AI:");
            System.out.println("1 -> orb count difference\r\n" + 
                        "2 -> Critical mass potential\r\n" + 
                        "3 -> Board control (number of cells controlled)\r\n" + 
                        "4 -> Threat detection (potential to explode next turn)\r\n" + 
                        "5 -> Corner control");
            System.out.println("\nSelect first heuristic:");

            int heuristic;
            boolean validHeuristicInt;
            
            do {
                heuristic = scanner.nextInt();
                validHeuristicInt = ((heuristic >= 1 && heuristic <= 5));
                if (!validHeuristicInt) System.out.println("Please select a valid heuristic (1-5): ");
            } while (!validHeuristicInt);
            
            setHeuristic(heuristic);

            if (mode == 3) { // AI vs AI mode, so two heuristics are needed
                System.out.println("Select second heuristic:");
                int secondHeuristic;
                boolean validsecondHeuristicInt;
            
                do {
                    secondHeuristic = scanner.nextInt();
                    validsecondHeuristicInt = ((secondHeuristic >= 1 && secondHeuristic <= 5));
                    if (!validsecondHeuristicInt) System.out.println("Please select a valid secondHeuristic (1-5): ");
                } while (!validsecondHeuristicInt);

                setHeuristic(heuristic, secondHeuristic);
            }
        }
   
        if (mode == 1) {
            setPlayers("Human-1 Move:", "Human-2 Move:");
            this.mode = mode;
            humanVsHuman();
        } else if (mode == 2) {
            setPlayers("Human Move:", "Ai Move:");
            this.mode = mode;
            humanVsAI();
        } else if (mode == 3) {
            setPlayers("Ai-1 Move:", "Ai-2 Move:");
            this.mode = mode;
            aiVsAi();
        } else if (mode == 4) {
            setPlayers("Random Agent Move:", "Ai Move:");
            this.mode = mode;
            randomMoveAgentVsAi();
        }

        scanner.close();
    }
    
    private void humanVsHuman() {
        setPlayers("Human-1 Move:", "Human-2 Move:");
        Scanner scanner = new Scanner(System.in);
        
        while (!gameOver) {
            printBoard();
            System.out.println("Current player: " + (currentPlayer == RED ? "Red" : "Blue"));
            
            int row, col;
            do {
                System.out.print("Enter row (0-" + (ROWS-1) + "): ");
                row = scanner.nextInt();
                System.out.print("Enter column (0-" + (COLS-1) + "): ");
                col = scanner.nextInt();
            } while (!isValidMove(row, col, currentPlayer));
            
            makeMove(row, col, currentPlayer, true);
            switchPlayer();
        }
        
        printResult();
        scanner.close();
    }
    
    private void humanVsAI() {
        setPlayers("Human Move:", "Ai Move:");
        Scanner scanner = new Scanner(System.in);
        long startTimeOfGame = System.currentTimeMillis();
        
        while (!gameOver) {
            printBoard();
            
            if (currentPlayer == RED) { // Human player (Red)
                System.out.println("Your turn (Red)");
                
                int row, col;
                do {
                    System.out.print("Enter row (0-" + (ROWS-1) + "): ");
                    row = scanner.nextInt();
                    System.out.print("Enter column (0-" + (COLS-1) + "): ");
                    col = scanner.nextInt();
                } while (!isValidMove(row, col, currentPlayer));
                
                makeMove(row, col, currentPlayer, true);
            } else { // AI player (Blue)
                System.out.println("AI is thinking...");
                long startTime = System.currentTimeMillis();
                
                int[] bestMove = findBestMoveWithTimeout(heuristic1);
                makeMove(bestMove[0], bestMove[1], currentPlayer, true);
                
                long endTime = System.currentTimeMillis();
                System.out.println("AI moved to (" + bestMove[0] + ", " + bestMove[1] + ") in " + 
                                   (endTime - startTime) + "ms");
            }
            
            switchPlayer();
        }

        long endTimeOfGame = System.currentTimeMillis();
        timeToCompleteGame = endTimeOfGame - startTimeOfGame;
        
        printResult();
        System.out.println("Time taken: " + timeToCompleteGame/1000.0 + " seconds with heuristic " + heuristic1);
        scanner.close();
    }

    private void randomMoveAgentVsAi() {
        setPlayers("Random agent Move:", "Ai Move:");
        Scanner scanner = new Scanner(System.in);
        long startTimeOfGame = System.currentTimeMillis();
        
        while (!gameOver) {
            printBoard();
            
            if (currentPlayer == RED) { // Random move agent
                
                int row, col;
                Random random = new Random();
                do {
                    row = random.nextInt(0, ROWS - 1);
                    col = random.nextInt(0, COLS - 1);
                } while (!isValidMove(row, col, currentPlayer));
                
                makeMove(row, col, currentPlayer, true);
            } else { // AI player (Blue)
                System.out.println("AI is thinking...");
                long startTime = System.currentTimeMillis();
                
                int[] bestMove = findBestMoveWithTimeout(heuristic1);
                makeMove(bestMove[0], bestMove[1], currentPlayer, true);
                
                long endTime = System.currentTimeMillis();
                System.out.println("AI moved to (" + bestMove[0] + ", " + bestMove[1] + ") in " + 
                                   (endTime - startTime) + "ms");
            }
            
            switchPlayer();
        }
        
        long endTimeOfGame = System.currentTimeMillis();
        timeToCompleteGame = endTimeOfGame - startTimeOfGame;

        printResult();
        scanner.close();
    }
    
    private void aiVsAi() {
        setPlayers("Ai-1 Move:", "Ai-2 Move:");
        System.out.println("AI vs AI mode");
        System.out.println("Red AI vs Blue AI");

        long startTimeOfGame = System.currentTimeMillis();
        
        while (!gameOver) {
            printBoard();
            System.out.println("Current player: " + (currentPlayer == RED ? "Red AI" : "Blue AI"));
            
            long startTime = System.currentTimeMillis();
            int[] bestMove;
            if (currentPlayer == RED) bestMove = findBestMoveWithTimeout(heuristic1);
            else bestMove = findBestMoveWithTimeout(heuristic2);
            makeMove(bestMove[0], bestMove[1], currentPlayer, true);
            
            long endTime = System.currentTimeMillis();
            System.out.println((currentPlayer == RED ? "Red AI" : "Blue AI") + " with heuristic " + (currentPlayer == RED ? heuristic1 : heuristic2) + " moved to (" + 
                             bestMove[0] + ", " + bestMove[1] + ") in " + (endTime - startTime) + "ms");
            
            try {
                TimeUnit.SECONDS.sleep(1); // Pause for visibility
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            switchPlayer();
        }
        
        long endTimeOfGame = System.currentTimeMillis();
        timeToCompleteGame = endTimeOfGame - startTimeOfGame;
        printResult();
    }
    
    private void printBoard() {
        System.out.println("\nCurrent Board:");
        System.out.println("Red orbs: " + redOrbs + " | Blue orbs: " + blueOrbs);
        
        // Print column numbers
        System.out.print("   ");
        for (int j = 0; j < COLS; j++) {
            System.out.print(j + "  ");
        }
        System.out.println();
        
        for (int i = 0; i < ROWS; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < COLS; j++) {
                Cell cell = board[i][j];
                if (cell.isEmpty()) {
                    System.out.print(" . ");
                } else {
                    String orb = cell.count + "" + cell.color;
                    System.out.print(orb + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
    private void printResult() {
        printBoard();
        if (winner == RED) {
            System.out.println("Red player wins!");
        } else if (winner == BLUE) {
            System.out.println("Blue player wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }
    
    private boolean isValidMove(int row, int col, char player) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
            System.out.println("Invalid position. Try again.");
            return false;
        }
        
        Cell cell = board[row][col];
        if (cell.isEmpty() || cell.color == player) {
            return true;
        }
        
        System.out.println("Can't place orb on opponent's cell. Try again.");
        return false;
    }
    
    private void makeMove(int row, int col, char player, boolean wontBacktrackLater) {
        if (numberOfMoves != 0 && wontBacktrackLater) {
            // loadGameState();
        }

        Cell cell = board[row][col];
        
        if (cell.isEmpty()) {
            cell.set(player, 1);
            if (player == RED) redOrbs++;
            else blueOrbs++;
        } else {
            cell.count++;
            if (player == RED) redOrbs++;
            else blueOrbs++;
        }

        if (wontBacktrackLater) { // will not backtrack later
            numberOfMoves++;
            System.out.println("Number of moves: " + numberOfMoves);
        }
        
        // Check for explosion
        if (cell.count >= cell.getCriticalMass()) {
            explode(row, col);
        }

        if (wontBacktrackLater) {
            if(player == RED) {
                // saveGameState(player1);
                // try {
                //     Thread.sleep(2000);
                // } catch (InterruptedException e) {
                //     e.printStackTrace();
                // }
            }
            else {
                // saveGameState(player2);
                if (mode == 3 || mode == 4) {
                    // try {
                    //    Thread.sleep(2000);
                    // } catch (InterruptedException e) {
                    //     e.printStackTrace();
                    // }
                }
            }
        }
        
        
        // Check for game over
        checkGameOver();
        
    }

private void explode(int startRow, int startCol) {
    // System.out.println("EXPLOSION!");
    Queue<int[]> explosionQueue = new LinkedList<>();
    explosionQueue.add(new int[]{startRow, startCol, 0});
    
    while (!explosionQueue.isEmpty()) {
        int[] current = explosionQueue.poll();
        if (current[2] > 100) continue; // dont allow more than 100 nested explosions
        int row = current[0], col = current[1];
        Cell cell = board[row][col];
        
        // Skip if cell no longer meets explosion criteria
        if (cell.count < cell.getCriticalMass() || cell.isEmpty()) continue;
        
        char color = cell.color;
        int criticalMass = cell.getCriticalMass();
        
        // Process current explosion
        cell.count -= criticalMass;
        if (cell.count == 0) {
            cell.reset();
        }
        
        // Update global orb counts
        if (color == RED) redOrbs -= criticalMass;
        else blueOrbs -= criticalMass;
        
        // Process adjacent cells
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            
            if (newRow >= 0 && newRow < ROWS && newCol >= 0 && newCol < COLS) {
                Cell adjacent = board[newRow][newCol];
                
                // Convert opponent's orbs
                if (!adjacent.isEmpty() && adjacent.color != color) {
                    if (color == RED) {
                        redOrbs += adjacent.count;
                        blueOrbs -= adjacent.count;
                    } else {
                        blueOrbs += adjacent.count;
                        redOrbs -= adjacent.count;
                    }
                    adjacent.color = color;
                }
                
                // Add orb
                if (adjacent.isEmpty()) {
                    adjacent.set(color, 1);
                    if (color == RED) redOrbs++;
                    else blueOrbs++;
                } else {
                    adjacent.count++;
                    if (color == RED) redOrbs++;
                    else blueOrbs++;
                }
                
                // Schedule next explosion if reached critical mass
                if (adjacent.count >= adjacent.getCriticalMass()) {
                    explosionQueue.add(new int[]{newRow, newCol, current[2] + 1});
                }
            }
        }
    }
}
    
    private void switchPlayer() {
        currentPlayer = (currentPlayer == RED) ? BLUE : RED;
    }
    
    private void checkGameOver() {
        if(numberOfMoves <= 2) return;
        if (redOrbs == 0) {
            gameOver = true;
            winner = BLUE;
        } else if (blueOrbs == 0) {
            gameOver = true;
            winner = RED;
        }
    }
    
    // AI methods
    private int[] findBestMoveWithTimeout(int heuristic) {
        final AtomicReference<int[]> bestMove = new AtomicReference<>(new int[]{0, 0});
        final Thread calculationThread = new Thread(() -> {
            bestMove.set(findBestMove(heuristic));
        });
    
        calculationThread.start();
        try {
            calculationThread.join(15000); // 15s timeout
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    
        if (calculationThread.isAlive()) {
            calculationThread.interrupt();
            // random valid move bcos AI timed out!
            System.out.println("AI timed out! Heuristic " + heuristic + "is ongoing. Selecting a random move.");
            List<int[]> moves = generatePossibleMoves(currentPlayer);
            return moves.get(new Random().nextInt(moves.size()));
        }
        return bestMove.get();
    }

    private int[] findBestMove(int heuristic) {
        int bestValue = Integer.MIN_VALUE;
        
        List<int[]> possibleMoves = generatePossibleMoves(currentPlayer);
        List<int[]> bestMoves = new ArrayList<>();
        // Random rng = new Random(System.nanoTime() + Thread.currentThread().getId());
        Random rng = new Random(System.nanoTime() ^ Thread.currentThread().hashCode());
        
        for (int[] move : possibleMoves) {
            // Simulate the move
            Cell[][] tempBoard = copyBoard();
            int tempRedOrbs = redOrbs;
            int tempBlueOrbs = blueOrbs;
            char tempCurrentPlayer = currentPlayer;
            
            makeMove(move[0], move[1], currentPlayer, false);
            
            // Evaluate the move
            int moveValue;
            if (useAlphaBeta) {
                moveValue = alphaBeta(searchDepth, Integer.MIN_VALUE, Integer.MAX_VALUE, false, heuristic);
            } else {
                moveValue = minimax(searchDepth, false, heuristic);
            }
            
            // Undo the move (backtracking)
            restoreBoard(tempBoard);
            tempBoard = null;
            redOrbs = tempRedOrbs;
            blueOrbs = tempBlueOrbs;
            currentPlayer = tempCurrentPlayer;
            gameOver = false;
            winner = EMPTY;

            if (moveValue > bestValue) {
                bestValue = moveValue;
                bestMoves.clear();
                bestMoves.add(move);
            } else if (moveValue == bestValue) {
                bestMoves.add(move);
            }
        }
        
        // return bestMove;
        if (!bestMoves.isEmpty()) {
            return bestMoves.get(rng.nextInt(bestMoves.size())); // may have multiple best moves, select one randomly
        }

        // Fallback (shouldn't happen)
        return new int[]{-1, -1};
    }
    
    private List<int[]> generatePossibleMoves(char player) {
        List<int[]> moves = new ArrayList<>();
        
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j].isEmpty() || board[i][j].color == player) {
                    moves.add(new int[]{i, j});
                }
            }
        }
        
        return moves;
    }
    
    private int minimax(int depth, boolean isMaximizing, int heuristic) {
        if (depth == 0 || gameOver) {
            // return evaluate();
            return evaluate(heuristic);
            // return evaluate(heuristic_list);
        }
        
        char player = isMaximizing ? currentPlayer : (currentPlayer == RED ? BLUE : RED);
        List<int[]> possibleMoves = generatePossibleMoves(player);
        
        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (int[] move : possibleMoves) {
                // Simulate move
                Cell[][] tempBoard = copyBoard();
                int tempRedOrbs = redOrbs;
                int tempBlueOrbs = blueOrbs;
                char tempCurrentPlayer = currentPlayer;
                
                makeMove(move[0], move[1], player, false);
                
                int eval = minimax(depth - 1, false, heuristic);
                
                // Undo move
                restoreBoard(tempBoard);
                tempBoard = null;
                redOrbs = tempRedOrbs;
                blueOrbs = tempBlueOrbs;
                currentPlayer = tempCurrentPlayer;
                gameOver = false;
                winner = EMPTY;
                
                maxEval = Math.max(maxEval, eval);
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int[] move : possibleMoves) {
                // Simulate move
                Cell[][] tempBoard = copyBoard();
                int tempRedOrbs = redOrbs;
                int tempBlueOrbs = blueOrbs;
                char tempCurrentPlayer = currentPlayer;
                
                makeMove(move[0], move[1], player, false);
                
                int eval = minimax(depth - 1, true, heuristic);
                
                // Undo move
                restoreBoard(tempBoard);
                tempBoard = null;
                redOrbs = tempRedOrbs;
                blueOrbs = tempBlueOrbs;
                currentPlayer = tempCurrentPlayer;
                gameOver = false;
                winner = EMPTY;
                
                minEval = Math.min(minEval, eval);
            }
            return minEval;
        }
    }
    
    private int alphaBeta(int depth, int alpha, int beta, boolean isMaximizing, int heuristic) {
        if (depth == 0 || gameOver) {
            // return evaluate();
            return evaluate(heuristic);
            // return evaluate(heuristic_list);
        }
        
        char player = isMaximizing ? currentPlayer : (currentPlayer == RED ? BLUE : RED);
        List<int[]> possibleMoves = generatePossibleMoves(player);
        
        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (int[] move : possibleMoves) {
                // Simulate move
                Cell[][] tempBoard = copyBoard();
                int tempRedOrbs = redOrbs;
                int tempBlueOrbs = blueOrbs;
                char tempCurrentPlayer = currentPlayer;
                
                makeMove(move[0], move[1], player, false);
                
                int eval = alphaBeta(depth - 1, alpha, beta, false, heuristic);
                
                // Undo move
                restoreBoard(tempBoard);
                tempBoard = null;
                redOrbs = tempRedOrbs;
                blueOrbs = tempBlueOrbs;
                currentPlayer = tempCurrentPlayer;
                gameOver = false;
                winner = EMPTY;
                
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int[] move : possibleMoves) {
                // Simulate move
                Cell[][] tempBoard = copyBoard();
                int tempRedOrbs = redOrbs;
                int tempBlueOrbs = blueOrbs;
                char tempCurrentPlayer = currentPlayer;
                
                makeMove(move[0], move[1], player, false);
                
                int eval = alphaBeta(depth - 1, alpha, beta, true, heuristic);
                
                // Undo move
                restoreBoard(tempBoard);
                tempBoard = null;                
                redOrbs = tempRedOrbs;
                blueOrbs = tempBlueOrbs;
                currentPlayer = tempCurrentPlayer;
                gameOver = false;
                winner = EMPTY;
                
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            return minEval;
        }
    }
    
    // Evaluation heuristics
    private int evaluate(int heuristic) { // used for heuristic A vs heuristic B
        int score = 0;
        
        // Heuristic 1: Orb count difference
        if (heuristic == 1) score += (currentPlayer == RED) ? (redOrbs - blueOrbs) : (blueOrbs - redOrbs);
        
        // Heuristic 2: Critical mass potential
        if (heuristic == 2) score += calculateCriticalMassPotential();
        
        // Heuristic 3: Board control (number of cells controlled)
        if (heuristic == 3) score += calculateBoardControl();
        
        // Heuristic 4: Threat detection (potential to explode next turn)
        if (heuristic == 4) score += calculateThreatScore();
        
        // Heuristic 5: Corner control
        if (heuristic == 5) score += calculateCornerControl();
        
        return score;
    }

    private int evaluate() {
        int score = 0;
        
        // Heuristic 1: Orb count difference
        score += (currentPlayer == RED) ? (redOrbs - blueOrbs) : (blueOrbs - redOrbs);
        
        // Heuristic 2: Critical mass potential
        score += calculateCriticalMassPotential();
        
        // Heuristic 3: Board control (number of cells controlled)
        score += calculateBoardControl();
        
        // Heuristic 4: Threat detection (potential to explode next turn)
        score += calculateThreatScore();
        
        // Heuristic 5: Corner control
        score += calculateCornerControl();
        
        return score;
    }
    
    private int calculateCriticalMassPotential() {
        int score = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                Cell cell = board[i][j];
                if (!cell.isEmpty() && cell.color == currentPlayer) {
                    int remaining = cell.getCriticalMass() - cell.count;
                    if (remaining == 1) {
                        score += 5; // About to explode
                    } else if (remaining == 2) {
                        score += 2; // Potential to explode soon
                    }
                }
            }
        }
        return 5*score;
    }
    
    private int calculateBoardControl() {
        int playerCells = 0;
        int opponentCells = 0;
        
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (!board[i][j].isEmpty()) {
                    if (board[i][j].color == currentPlayer) {
                        playerCells++;
                    } else {
                        opponentCells++;
                    }
                }
            }
        }
        
        return (playerCells - opponentCells) * 3;
    }
    
    private int calculateThreatScore() {
        int threatScore = 0;
        
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                Cell cell = board[i][j];
                if (!cell.isEmpty() && cell.color != currentPlayer) {
                    if (cell.count == cell.getCriticalMass() - 1) {
                        threatScore -= 10; // Opponent can explode this cell next move
                    }
                }
            }
        }
        
        return 3*threatScore;
    }
    
    private int calculateCornerControl() {
        int score = 0;
        int[][] corners = {{0, 0}, {0, COLS-1}, {ROWS-1, 0}, {ROWS-1, COLS-1}};
        
        for (int[] corner : corners) {
            Cell cell = board[corner[0]][corner[1]];
            if (!cell.isEmpty()) {
                if (cell.color == currentPlayer) {
                    score += 5;
                } else {
                    score -= 5;
                }
            }
        }
        
        return score;
    }
    
    // Utility methods
    private Cell[][] copyBoard() {
        Cell[][] copy = new Cell[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                copy[i][j] = new Cell(i, j);
                copy[i][j].count = board[i][j].count;
                copy[i][j].color = board[i][j].color;
            }
        }
        return copy;
    }
    
    private void restoreBoard(Cell[][] savedBoard) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j].count = savedBoard[i][j].count;
                board[i][j].color = savedBoard[i][j].color;
            }
        }
    }

    public static void main(String[] args) {

        // Console version
        // int rows = 9, cols = 6, searchDepth = 3;
        // Chain_Reaction game = new Chain_Reaction(rows, cols, searchDepth);
        // game.playGame();
        // game.humanVsAI();
        // game.aiVsAi();


        // Launch the GUI version
        SwingUtilities.invokeLater(() -> {
            new ChainReactionGUI().setVisible(true);
        });
    }

    // GUI Implementation
    static class ChainReactionGUI extends JFrame {
        private static final Color BACKGROUND_COLOR = new Color(45, 45, 60);
        private static final Color BOARD_COLOR = new Color(30, 30, 40);
        private static final Color CELL_COLOR = new Color(60, 60, 80);
        private static final Color GRID_COLOR = new Color(100, 100, 120);
        private static final Color RED_ORB_COLOR = new Color(255, 80, 80);
        private static final Color BLUE_ORB_COLOR = new Color(80, 150, 255);
        private static final Color HIGHLIGHT_COLOR = new Color(100, 255, 100, 100);
        
        private Chain_Reaction game;
        private JPanel boardPanel;
        private JLabel statusLabel;
        private JLabel redOrbsLabel;
        private JLabel blueOrbsLabel;
        private int cellSize = 70;
        private int hoverRow = -1, hoverCol = -1;
        private boolean gameActive = false;

        private int lastMoveRow = -1;
        private int lastMoveCol = -1;
        private static final Color LAST_MOVE_HIGHLIGHT = new Color(255, 255, 100, 120); // glowing yellow

        public ChainReactionGUI() {
            createUI();
        }

        private void createUI() {
            setTitle("Chain Reaction - Beautiful Edition");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(670, 760);
            // setSize(COLS*cellSize, ROWS*cellSize);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());
            getContentPane().setBackground(BACKGROUND_COLOR);

            // Header panel
            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setBackground(BACKGROUND_COLOR);
            headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
            
            JLabel titleLabel = new JLabel("CHAIN REACTION");
            titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
            titleLabel.setForeground(new Color(220, 220, 255));
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            headerPanel.add(titleLabel, BorderLayout.CENTER);
            
            add(headerPanel, BorderLayout.NORTH);

            // Center panel for board and info
            JPanel centerPanel = new JPanel(new BorderLayout(20, 20));
            centerPanel.setBackground(BACKGROUND_COLOR);
            centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            // Board panel
            boardPanel = new BoardPanel();
            boardPanel.setBackground(BOARD_COLOR);
            boardPanel.setBorder(BorderFactory.createLineBorder(GRID_COLOR, 2));
            centerPanel.add(boardPanel, BorderLayout.CENTER);
            
            // Info panel
            JPanel infoPanel = new JPanel();
            infoPanel.setBackground(BACKGROUND_COLOR);
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            
            statusLabel = new JLabel("Configure game to start");
            statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
            statusLabel.setForeground(new Color(200, 200, 255));
            statusLabel.setAlignmentX(CENTER_ALIGNMENT);
            
            redOrbsLabel = new JLabel("Red Orbs: 0");
            redOrbsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            redOrbsLabel.setForeground(RED_ORB_COLOR);
            redOrbsLabel.setAlignmentX(CENTER_ALIGNMENT);
            
            blueOrbsLabel = new JLabel("Blue Orbs: 0");
            blueOrbsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            blueOrbsLabel.setForeground(BLUE_ORB_COLOR);
            blueOrbsLabel.setAlignmentX(CENTER_ALIGNMENT);
            
            infoPanel.add(statusLabel);
            infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            infoPanel.add(redOrbsLabel);
            infoPanel.add(blueOrbsLabel);
            
            centerPanel.add(infoPanel, BorderLayout.EAST);
            add(centerPanel, BorderLayout.CENTER);
            
            // Control panel
            JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
            controlPanel.setBackground(BACKGROUND_COLOR);
            controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
            
            JButton configButton = createStyledButton("Configure/Start Game");
            configButton.addActionListener(e -> showConfigDialog());
            controlPanel.add(configButton);
            
            JButton restartButton = createStyledButton("Restart Game");
            restartButton.addActionListener(e -> restartGame());
            controlPanel.add(restartButton);
            
            add(controlPanel, BorderLayout.SOUTH);
            
            // Setup board panel listener
            boardPanel.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    if (!gameActive || game == null || game.gameOver) return;
                    
                    int row = e.getY() / cellSize;
                    int col = e.getX() / cellSize;
                    
                    if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
                        hoverRow = -1;
                        hoverCol = -1;
                    } else {
                        hoverRow = row;
                        hoverCol = col;
                    }
                    boardPanel.repaint();
                }
            });
            
            boardPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!gameActive || game == null || game.gameOver) return;
                    
                    int col = e.getX() / cellSize;
                    int row = e.getY() / cellSize;
                    
                    if (row < 0 || row >= ROWS || col < 0 || col >= COLS) return;
                    
                    if (game.isValidMove(row, col, game.currentPlayer)) {
                        makeHumanMove(row, col);
                    } else {
                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    hoverRow = -1;
                    hoverCol = -1;
                    boardPanel.repaint();
                }
            });
        }

        private JButton createStyledButton(String text) {
            JButton button = new JButton(text);
            button.setFont(new Font("Segoe UI", Font.BOLD, 14));
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(80, 80, 120));
            button.setUI(new BasicButtonUI());
            button.setFocusPainted(false);
            // button.setBorder(BorderFactory.createCompoundBorder(
            //     new LineBorder(new Color(120, 120, 160), 
            //     new EmptyBorder(8, 20, 8, 20)
            // ));
            
            button.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(new Color(100, 100, 150));
                }

                public void mouseExited(MouseEvent e) {
                    button.setBackground(new Color(80, 80, 120));
                }
            });
            
            return button;
        }

        private void showConfigDialog() {
            JDialog configDialog = new JDialog(this, "Game Configuration", true);
            configDialog.setSize(500, 400);
            configDialog.setLocationRelativeTo(this);
            configDialog.getContentPane().setBackground(BACKGROUND_COLOR);
            configDialog.setLayout(new BorderLayout());
            
            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(BACKGROUND_COLOR);
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
            
            JLabel titleLabel = new JLabel("Game Settings");
            titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
            titleLabel.setForeground(new Color(220, 220, 255));
            titleLabel.setAlignmentX(CENTER_ALIGNMENT);
            mainPanel.add(titleLabel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
            
            // Game mode selection
            JPanel modePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
            modePanel.setBackground(BACKGROUND_COLOR);
            modePanel.setAlignmentX(CENTER_ALIGNMENT);
            
            JLabel modeLabel = new JLabel("Game Mode:");
            modeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            modeLabel.setForeground(Color.WHITE);
            modePanel.add(modeLabel);
            
            // String[] modes = {"Human vs Human", "Human vs AI", "AI vs AI", "Random Agent vs AI"};
            String[] modes = {"Human vs Human", "Human vs AI", "AI vs AI"};
            JComboBox<String> modeCombo = new JComboBox<>(modes);
            styleComboBox(modeCombo);
            modePanel.add(modeCombo);
            
            mainPanel.add(modePanel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            
            // Heuristic selection
            JPanel heuristicPanel = new JPanel();
            heuristicPanel.setBackground(BACKGROUND_COLOR);
            heuristicPanel.setLayout(new BoxLayout(heuristicPanel, BoxLayout.Y_AXIS));
            heuristicPanel.setAlignmentX(CENTER_ALIGNMENT);
            
            JLabel heuristicLabel = new JLabel("AI Heuristics:");
            heuristicLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            heuristicLabel.setForeground(Color.WHITE);
            heuristicLabel.setAlignmentX(CENTER_ALIGNMENT);
            heuristicPanel.add(heuristicLabel);
            heuristicPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            
            JPanel heuristicSubPanel = new JPanel(new GridLayout(0, 2, 10, 10));
            heuristicSubPanel.setBackground(BACKGROUND_COLOR);
            heuristicSubPanel.setAlignmentX(CENTER_ALIGNMENT);
            
            JLabel ai1Label = new JLabel("AI 1 Heuristic:");
            ai1Label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            ai1Label.setForeground(Color.WHITE);
            heuristicSubPanel.add(ai1Label);
            
            String[] heuristics = {"Orb Count", "Critical Mass", "Board Control", "Threat Detection", "Corner Control"};
            JComboBox<String> heuristic1Combo = new JComboBox<>(heuristics);
            styleComboBox(heuristic1Combo);
            heuristicSubPanel.add(heuristic1Combo);
            
            JLabel ai2Label = new JLabel("AI 2 Heuristic (only for AI vs AI):");
            ai2Label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            ai2Label.setForeground(Color.WHITE);
            heuristicSubPanel.add(ai2Label);
            
            JComboBox<String> heuristic2Combo = new JComboBox<>(heuristics);
            styleComboBox(heuristic2Combo);
            heuristicSubPanel.add(heuristic2Combo);
            
            heuristicPanel.add(heuristicSubPanel);
            mainPanel.add(heuristicPanel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
            
            // Start button
            JButton startButton = createStyledButton("Start Game");
            startButton.addActionListener(e -> {
                int mode = modeCombo.getSelectedIndex() + 1;
                int h1 = heuristic1Combo.getSelectedIndex() + 1;
                int h2 = heuristic2Combo.getSelectedIndex() + 1;
                
                configDialog.dispose();
                startGame(mode, h1, h2);
            });
            startButton.setAlignmentX(CENTER_ALIGNMENT);
            mainPanel.add(startButton);
            
            configDialog.add(mainPanel, BorderLayout.CENTER);
            configDialog.setVisible(true);
        }
        
        private void styleComboBox(JComboBox<String> combo) {
            combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            combo.setBackground(new Color(70, 70, 100));
            combo.setForeground(Color.WHITE);
            combo.setBorder(new EmptyBorder(5, 10, 5, 10));
            combo.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                                                              boolean isSelected, boolean cellHasFocus) {
                    Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    setBackground(isSelected ? new Color(100, 100, 150) : new Color(70, 70, 100));
                    setForeground(Color.WHITE);
                    setBorder(new EmptyBorder(5, 10, 5, 10));
                    return c;
                }
            });
        }

        private void startGame(int mode, int heuristic1, int heuristic2) {
            int rows = 9, cols = 6, searchDepth = 4;
            game = new Chain_Reaction(rows, cols, searchDepth);
            game.mode = mode;
            
            if (mode == 3) { // AI vs AI
                game.setHeuristic(heuristic1, heuristic2);
                game.setPlayers("Ai-1 Move:", "Ai-2 Move:");
            } else if (mode == 2 || mode == 4) { // Human vs AI or Random vs AI
                game.setHeuristic(heuristic1);
                if (mode == 2) {
                    game.setPlayers("Human Move:", "Ai Move:");
                } else {
                    game.setPlayers("Random Agent Move:", "Ai Move:");
                }
            } else if (mode == 1) { // Human vs Human
                game.setPlayers("Human-1 Move:", "Human-2 Move:");
            }
            
            gameActive = true;
            updateStatus();
            boardPanel.repaint();
            
            // Start AI if needed
            if (mode != 1 && game.currentPlayer == BLUE) {
                makeAIMove();
            }

            if (mode == 3 && game.currentPlayer == RED) {
                makeAIMove();
            }
        }

        private void restartGame() {
            if (game == null) return;
            int mode = game.mode;
            int h1 = game.heuristic1;
            int h2 = game.mode == 3 ? game.heuristic2 : 1;
            startGame(mode, h1, h2);
        }

        private void makeHumanMove(int row, int col) {
            game.makeMove(row, col, game.currentPlayer, true);
            lastMoveRow = row;
            lastMoveCol = col;

            boardPanel.repaint();
            updateStatus();
            
            if (game.gameOver) {
                showGameOver();
            } else {
                game.switchPlayer();
                updateStatus();
                
                // Trigger AI move if needed
                if ((game.mode == 2 && game.currentPlayer == BLUE) || 
                    (game.mode == 4 && game.currentPlayer == BLUE) || 
                    (game.mode == 3)) {
                    makeAIMove();
                }
            }
        }

        private void makeAIMove() {
            new SwingWorker<int[], Void>() {
                @Override
                protected int[] doInBackground() {
                    statusLabel.setText("AI is thinking...");
                    int heuristic;
                    if (game.mode == 3) {
                        heuristic = (game.currentPlayer == RED) ? game.heuristic1 : game.heuristic2;
                    } else {
                        heuristic = game.heuristic1;
                    }

                    Chain_Reaction duplicateGame = game;
                    int[] arr = duplicateGame.findBestMoveWithTimeout(heuristic);
                    return arr;
                }

                @Override
                protected void done() {
                    try {
                        int[] move = get();
                        lastMoveRow = move[0];
                        lastMoveCol = move[1];
                        game.makeMove(move[0], move[1], game.currentPlayer, true);

                        boardPanel.repaint();
                        updateStatus();
                        
                        if (game.gameOver) {
                            showGameOver();
                        } else {
                            game.switchPlayer();
                            updateStatus();
                            
                            // Continue AI moves if needed
                            if (game.mode == 3 || 
                                (game.mode == 2 && game.currentPlayer == BLUE) || 
                                (game.mode == 4 && game.currentPlayer == BLUE)) {
                                makeAIMove();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.execute();
        }

        private void updateStatus() {
            if (game == null) return;
            
            redOrbsLabel.setText("Red Orbs: " + game.redOrbs);
            blueOrbsLabel.setText("Blue Orbs: " + game.blueOrbs);
            
            if (game.gameOver) {
                statusLabel.setText(game.winner == RED ? "Red Player Wins!" : 
                                  game.winner == BLUE ? "Blue Player Wins!" : "Game Over!");
            } else {
                String player;
                if (game.mode == 1) {
                    player = (game.currentPlayer == RED) ? "Player 1 (Red)" : "Player 2 (Blue)";
                } else if (game.mode == 2) {
                    player = (game.currentPlayer == RED) ? "Your Turn (Red)" : "AI Thinking...";
                } else if (game.mode == 3) {
                    player = (game.currentPlayer == RED) ? "AI 1 (Red) Thinking..." : "AI 2 (Blue) Thinking...";
                } else {
                    player = (game.currentPlayer == RED) ? "Random Agent (Red)" : "AI (Blue) Thinking...";
                }
                statusLabel.setText("Current Player: " + player);
            }
        }

        private void showGameOver() {
            String message = game.winner == RED ? "Red player wins!" : 
                            game.winner == BLUE ? "Blue player wins!" : "It's a draw!";
            
            JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }

        // Custom board panel with beautiful rendering
        class BoardPanel extends JPanel {
    public BoardPanel() {
        new Timer(100, e -> repaint()).start(); // repaints every 100ms
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeCells();
                repaint();
            }
        });
    }

    private void resizeCells() {
        int width = getWidth();
        int height = getHeight();
        cellSize = Math.min(width / COLS, height / ROWS);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COLS * cellSize, ROWS * cellSize);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw grid background
        g2d.setColor(CELL_COLOR);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw grid lines
        g2d.setColor(GRID_COLOR);
        for (int i = 0; i <= ROWS; i++) {
            g2d.drawLine(0, i * cellSize, COLS * cellSize, i * cellSize);
        }
        for (int j = 0; j <= COLS; j++) {
            g2d.drawLine(j * cellSize, 0, j * cellSize, ROWS * cellSize);
        }

        if (game == null) return;

        // Draw orbs
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                Cell cell = game.board[i][j];
                if (!cell.isEmpty()) {
                    drawOrb(g2d, j, i, cell.count, cell.color);
                }
            }
        }

        // Highlight last move
        if (lastMoveRow >= 0 && lastMoveCol >= 0) {
            g2d.setColor(LAST_MOVE_HIGHLIGHT);
            g2d.fillRect(lastMoveCol * cellSize, lastMoveRow * cellSize, cellSize, cellSize);
         
        }


        // Draw hover effect
        if (hoverRow >= 0 && hoverCol >= 0 && game.isValidMove(hoverRow, hoverCol, game.currentPlayer)) {
            g2d.setColor(HIGHLIGHT_COLOR);
            g2d.fillRect(hoverCol * cellSize, hoverRow * cellSize, cellSize, cellSize);

            int previewCount = game.board[hoverRow][hoverCol].isEmpty()
                ? 1 : game.board[hoverRow][hoverCol].count + 1;
            drawOrb(g2d, hoverCol, hoverRow, previewCount, game.currentPlayer);
        }
    }

    private void drawOrb(Graphics2D g2d, int col, int row, int count, char color) {
    int x = col * cellSize + cellSize / 2;
    int y = row * cellSize + cellSize / 2;
    int radius = (int) (cellSize * 0.35);

    boolean isLastMove = (row == lastMoveRow && col == lastMoveCol);

    // Glowing background if it's the last moved cell
    if (isLastMove) {
        RadialGradientPaint glow = new RadialGradientPaint(
            new Point(x, y),
            radius * 1.5f,
            new float[]{0f, 1f},
            new Color[]{new Color(255, 255, 150, 180), new Color(255, 255, 150, 0)}
        );
        g2d.setPaint(glow);
        g2d.fillOval(x - radius * 2, y - radius * 2, radius * 4, radius * 4);
    }

    Color baseColor = (color == RED) ? RED_ORB_COLOR : BLUE_ORB_COLOR;
    Color lightColor = baseColor.brighter().brighter();
    Color darkColor = baseColor.darker().darker();

    GradientPaint gradient = new GradientPaint(
        x - radius, y - radius, lightColor,
        x + radius, y + radius, darkColor
    );

    g2d.setPaint(gradient);
    g2d.fillOval(x - radius, y - radius, radius * 2, radius * 2);

    g2d.setStroke(new BasicStroke(2));
    g2d.setColor(baseColor.darker());
    g2d.drawOval(x - radius, y - radius, radius * 2, radius * 2);

    g2d.setColor(Color.WHITE);
    g2d.setFont(new Font("Segoe UI", Font.BOLD, 16));
    String countStr = String.valueOf(count);
    FontMetrics fm = g2d.getFontMetrics();
    int textX = x - fm.stringWidth(countStr) / 2;
    int textY = y - fm.getHeight() / 2 + fm.getAscent();
    g2d.drawString(countStr, textX, textY);
}

}

    }
}