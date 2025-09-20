package com.mygdx.game.Nea;
import java.util.ArrayList;

public class ChessAI {

    private final int King = 0;
    private final int Pawn = 1;
    private final int Rook = 2;
    private final int Bishop = 3;
    private final int Knight = 4;
    private final int Queen = 5;

    BoardEvaluation be=new BoardEvaluation();
    BoardStateManipulator bsm=new BoardStateManipulator();
    MoveGeneration mg=new MoveGeneration();

    public Object[] MiniMax(BitBoards BoardState,GameState gs, boolean Maximising, int depth, int Root, float alpha, float beta) {
        com.mygdx.game.Nea.BitBoards TempBoard = new com.mygdx.game.Nea.BitBoards(BoardState);
        long MMPomotionTiles = BoardState.getPromotion();
        int TerminalStateResult=gs.TerminalStates(Maximising,BoardState);
        if (depth == 0||TerminalStateResult!=0) {
            if (TerminalStateResult== 1) {
                if(Maximising){
                    return new Object[]{(long)be.BoardValuator(BoardState)-20000, 0L, 0L};
                }
                else{
                    return new Object[]{(long)be.BoardValuator(BoardState)+20000, 0L, 0L};
                }
            }
            else {
                return new Object[]{(long) be.BoardValuator(BoardState), 0L, 0L};
            }
        }
        ArrayList<Object[]> PieceList = GameGenerator(Maximising, TempBoard);
        if (Maximising) {//while maximise/ is white turn
            long MovingFrom = 0L;
            long MovingToo = 0L;
            long Max = Long.MIN_VALUE;
            for (int i = 0; i < PieceList.size(); i++) {//itterates through all the pieces
                Object[] PieceData = PieceList.get(i);//pulling each pieces data
                long MMmoves = (long) PieceData[0];//possible moves for piece i
                long MMcurrent = (long) PieceData[1];//current position of piece i
                int MMSelectedPieceType = (int) PieceData[2];
                int MMrange = Long.bitCount(MMmoves);//number of moves
                for (int j = 0; j < MMrange; j++) {//runs each move
                    TempBoard = new com.mygdx.game.Nea.BitBoards(BoardState);
                    long MMmove = Long.lowestOneBit(MMmoves);
                    MMmoves ^= MMmove;
                    bsm.MovementType(TempBoard, true, MMmove, MMcurrent, MMSelectedPieceType, MMPomotionTiles);
                    Object[] Results = MiniMax(TempBoard,gs, false, depth - 1, Root, alpha, beta);
                    long Eval = (long) Results[0];
                    if (Max < Eval) {
                        Max = Eval;
                        if (Root == depth) {
                            MovingFrom = MMcurrent;
                            MovingToo = MMmove;
                        }
                    }
                }
            }
            return new Object[]{Max, MovingFrom, MovingToo};
        } else {//minimise
            long MovingFrom = 0L;
            long MovingToo = 0L;
            long Min = Long.MAX_VALUE;
            for (int i = 0; i < PieceList.size(); i++) {
                Object[] PieceData = PieceList.get(i);
                long MMmoves = (long) PieceData[0];
                long MMcurrent = (long) PieceData[1];
                int MMSelectedPieceType = (int) PieceData[2];
                int MMrange = Long.bitCount(MMmoves);
                for (int j = 0; j < MMrange; j++) {
                    long MMmove = Long.lowestOneBit(MMmoves);
                    MMmoves ^= MMmove;
                    TempBoard = new com.mygdx.game.Nea.BitBoards(BoardState);
                    bsm.MovementType(TempBoard, false, MMmove, MMcurrent, MMSelectedPieceType, MMPomotionTiles);
                    Object[] Results = MiniMax(TempBoard,gs, true, depth - 1, Root, alpha, beta);
                    long Eval = (long) Results[0];
                    if (Min > Eval) {
                        Min = Eval;
                        if (Root == depth) {
                            MovingFrom = MMcurrent;
                            MovingToo = MMmove;
                        }
                    }
                }
            }
            return new Object[]{Min, MovingFrom, MovingToo};
        }
    }
    public ArrayList<Object[]> GameGenerator(boolean IsWhiteTurn, com.mygdx.game.Nea.BitBoards BoardState) {
        ArrayList<Object[]> TempPieceList = new ArrayList<>();
        if (IsWhiteTurn) {
            mg.MoveGenerator(TempPieceList, BoardState.getWhitePawn(), Pawn, true, BoardState);
            mg.MoveGenerator(TempPieceList, BoardState.getWhiteRook(), Rook, true, BoardState);
            mg.MoveGenerator(TempPieceList, BoardState.getWhiteKnight(), Knight, true, BoardState);
            mg.MoveGenerator(TempPieceList, BoardState.getWhiteBishop(), Bishop, true, BoardState);
            mg.MoveGenerator(TempPieceList, BoardState.getWhiteQueen(), Queen, true, BoardState);
            mg.MoveGenerator(TempPieceList, BoardState.getWhiteKing(), King, true, BoardState);
        } else {
            mg.MoveGenerator(TempPieceList, BoardState.getBlackPawn(), Pawn, false, BoardState);
            mg.MoveGenerator(TempPieceList, BoardState.getBlackRook(), Rook, false, BoardState);
            mg.MoveGenerator(TempPieceList, BoardState.getBlackKnight(), Knight, false, BoardState);
            mg.MoveGenerator(TempPieceList, BoardState.getBlackBishop(), Bishop, false, BoardState);
            mg.MoveGenerator(TempPieceList, BoardState.getBlackQueen(), Queen, false, BoardState);
            mg.MoveGenerator(TempPieceList, BoardState.getBlackKing(), King, false, BoardState);
        }
        return TempPieceList;
    }
}
