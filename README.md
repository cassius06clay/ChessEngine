# ChessEngine
A Java-based chess engine built using bitboards, Minimax search, and a material/positional
 evaluation function. It can be played as a complete game between two players, but the main
 technical focus is the AI, which searches up to depth 4 with adjustable difficulty.
 Features
 • **Bitboard move generation** covering all standard pieces.
 • **Minimax search** with adjustable depth (1–4 levels).
 • **Evaluation function** combining static material values and piece-square tables.
 • **Check, checkmate, and stalemate detection** with game-over screens.
 • **Move highlighting**: when a player selects a piece, only legal moves are shown.
 • **Full game logic**: supports both player-vs-player and player-vs-AI play.
 • **Simple, easy-to-use UI** with clear controls (piece-following cursor, home/exit buttons).
 • **Efficient performance**: depth 4 search completes in a few seconds without pruning.
 Planned Improvements
 • Add missing chess rules (castling, en passant, underpromotion).
 • Optimise AI with **Alpha-Beta pruning**.
 • Explore **transposition tables** for faster repeated state lookups. 
 • Further refinement of player-side logic and general polish.
 Technical Notes
 • Language: Java.
 • UI built using **LibGDX**.
 • This repository is code-focused; it does not include executables.
 Purpose
 This project is primarily a portfolio piece to demonstrate coding ability in Java, algorithmic design,
 and problem-solving. It is not intended as a commercial product.
 Usage
 The code is provided for reference and learning purposes only. It should not be copied, sold, or
 modified for distribution
