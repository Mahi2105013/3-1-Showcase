# Chain Reaction
Chain Reaction is a deterministic, turn-based, perfect-information game for two players. Each player places colored orbs on an m × n board; when a cell reaches its critical mass (its number of orthogonal neighbors), it “explodes,” distributing orbs to adjacent cells and possibly triggering cascades; and converting opponent’s orbs in the process. It was originally developed by Buddy-Matt Entertainment for Android.

**Language:** Java

**GUI:** Java Swing

<p align="center">
<img width="500" alt="Chain React" src="https://github.com/user-attachments/assets/6c882768-5d56-4660-8a2d-adca71f4aacc" />
</p>

## Algorithm used
The AI uses a Minimax algorithm with alpha-beta pruning, allowing the search depth to be adjusted for performance.

## Demonstration Link
https://youtu.be/qEmrUk-pan0?si=_vaW7PmBAvwV0wTh

## Rules of the Game
1. The gameplay takes place on an m×n board. The most commonly used size of the board is 9x6 (9 rows, 6 columns).
2. For each cell in the board, we define a critical mass. The critical mass is equal to the number of orthogonally adjacent cells. That would be 4 for usual cells, 3 for cells in the edge and 2 for cells in the corner.
3. All cells are initially empty. The Red and the Blue player take turns to place "orbs" of their corresponding colors. The Red player can only place an (red) orb in an empty cell or a cell which already contains one or more red orbs. When two or more orbs are placed in the same cell, they stack up.
4. When a cell is loaded with a number of orbs equal to its critical mass, the stack immediately explodes. As a result of the explosion, to each of the orthogonally adjacent cells, an orb is added and the initial cell loses as many orbs as its critical mass. The explosions might result in overloading of an adjacent cell and the chain reaction of explosion continues until every cell is stable.
5. When a red cell explodes and there are blue cells around, the blue cells are converted to red and the other rules of explosions still follow. The same rule is applicable for other colors.
6. The winner is the one who eliminates all orbs of the other player.

## Game play
The game offers three distinct modes, each catering to different types of players and testing strategies:

1. **Human VS Human**
   * Two human players (one red and one blue player) compete on the same board, taking turns to place orbs.
   * This mode allows players to directly test their skills against friends or colleagues, strategize in real time, and explore chain reactions without AI intervention.

2. **Human VS AI**
   * A single human player competes against an AI-controlled opponent.
   * The AI uses a *custom heuristic* designed to evaluate the board and make strategic decisions.
   * This mode provides a challenging experience for the player, letting them test strategies against a smart, reactive opponent.

3. **AI VS AI**
   * Two AI players compete against each other using the same custom heuristic.
   * This mode is useful for analyzing AI performance, visualizing chain reactions, and testing the effectiveness of your heuristic.
   * Observing AI vs AI gameplay can help you fine-tune the heuristic and understand how different strategies play out without human intervention.


## The heuristics that were used
1. Orb Count Difference:
This is the most fundamental and often the primary heuristic in many games. It's a direct measure of who is "winning" in terms of pieces on the board. In Chain Reaction, accumulating more orbs (pieces) than your opponent is generally a good thing, as it indicates control and potential for future explosions.
2. Critical mass potential:
This heuristic focuses on offensive potential. In Chain Reaction, the game is won by eliminating the opponent's orbs through chain reactions. Cells that are close to their "critical mass" (the number of orbs needed to explode) are highly valuable because they can trigger these chain reactions, potentially capturing many opponent cells.
3. Board control (number of cells controlled) = number of player cells minus number of opponent cells
4. Threat detection (potential to explode next turn):
This heuristic focuses on defensive considerations and anticipating opponent moves. If an opponent has a cell that is one orb away from exploding, it represents an immediate threat. Ignoring such threats can lead to massive chain reactions from the opponent, potentially losing the game.
5. Corner control:
Corners are strategically very important in Chain Reaction. They have the lowest critical mass (usually 2 orbs), meaning they explode very easily. Controlling corners allows a player to initiate chain reactions with minimal investment. Losing corners means the opponent can easily trigger explosions from those points.
