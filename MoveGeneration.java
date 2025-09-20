package com.mygdx.game.Nea;
import java.util.ArrayList;

public class MoveGeneration {
    private final int King = 0;
    private final int Pawn = 1;
    private final int Rook = 2;
    private final int Bishop = 3;
    private final int Knight = 4;
    private final int Queen = 5;
    PieceDirectionGenerator pdg=new PieceDirectionGenerator();
    LegalMoveChecker lmc=new com.mygdx.game.Nea.LegalMoveChecker();
    public void MoveGenerator (ArrayList< Object[]> TempPieceList, long BitBoard, int SelectedPieceType, boolean IsWhiteTurn, com.mygdx.game.Nea.BitBoards BoardState){
        long Moves;
        int range = Long.bitCount(BitBoard);
        long PossibleTiles = 0;
        for (int i = 0; i < range; i++) {//number of pieces
            Moves = 0;
            long current = Long.lowestOneBit(BitBoard);
            BitBoard ^= current;
            if(SelectedPieceType==Pawn){
                PossibleTiles=pdg.PawnDirectionGenerator(BoardState,current,IsWhiteTurn);
            }
            else if(SelectedPieceType==Rook){
                PossibleTiles=pdg.RookDirectionGenerator(BoardState,current,IsWhiteTurn);
            }
            else if(SelectedPieceType==Bishop){
                PossibleTiles=pdg.BishopDirectionGenerator(BoardState,current,IsWhiteTurn);
            }
            else if(SelectedPieceType==Knight){
                PossibleTiles=pdg.KnightDirectionGenerator(BoardState,current,IsWhiteTurn);
            }
            else if(SelectedPieceType==Queen){
                PossibleTiles=pdg.QueenDirectionGenerator(BoardState,current,IsWhiteTurn);
            }
            else if(SelectedPieceType==King){
                PossibleTiles=pdg.KingDirectionGenerator(BoardState,current,IsWhiteTurn);
            }

            int range2 = Long.bitCount(PossibleTiles);
            for (int j = 0; j < range2; j++) {
                long target = Long.lowestOneBit(PossibleTiles);
                PossibleTiles ^= target;
                if (lmc.LegalMove(current, target, SelectedPieceType, IsWhiteTurn, BoardState)) {
                    Moves = Moves | target;
                }
            }
            if(Moves!=0){
                Object[] Data = new Object[]{Moves, current, SelectedPieceType, IsWhiteTurn};
                TempPieceList.add(Data);
            }
        }

    }
}
