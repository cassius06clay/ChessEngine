package com.mygdx.game.Nea;
import java.util.ArrayList;

public class GameState {
    ChessAI AI=new ChessAI();
    LegalMoveChecker lmc=new LegalMoveChecker();
    public int TerminalStates(boolean IsWhiteTurn, BitBoards BoardState){
        ArrayList<Object[]> TemporyPieceList =AI.GameGenerator(IsWhiteTurn,BoardState);
        int GameState=0;
        long KingTileBitValue;
        if(TemporyPieceList.isEmpty()){//if there are no legal moves
            if (IsWhiteTurn) {
                KingTileBitValue = BoardState.getWhiteKing();
            } else {
                KingTileBitValue = BoardState.getBlackKing();
            }
            if(lmc.InCheck(BoardState,IsWhiteTurn,KingTileBitValue)){
                GameState=1;
            }
            else{
                GameState=2;
            }
        }
        return GameState;
    }
}
