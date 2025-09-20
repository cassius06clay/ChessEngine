package com.mygdx.game.Nea;
public class LegalMoveChecker {
    private final int King = 0;
    private final int Pawn = 1;
    private final int Rook = 2;
    private final int Bishop = 3;
    private final int Knight = 4;
    private final int Queen = 5;
    BoardStateManipulator bsm=new BoardStateManipulator();
    Position pos=new Position();
    PieceMovementLogic pml=new PieceMovementLogic();

    public boolean LegalMove(long CurrentTileBitValue, long SelectedTileBitValue, int SelectedPieceType, boolean IsWhiteTurn, com.mygdx.game.Nea.BitBoards BoardState) {
        com.mygdx.game.Nea.BitBoards TempBoard;
        boolean IsLegalMove = LegalMoveLogic(CurrentTileBitValue, SelectedTileBitValue, SelectedPieceType, IsWhiteTurn, BoardState);
        long KingTileBitValue;
        if (IsLegalMove) {
            if (IsWhiteTurn) {
                KingTileBitValue = BoardState.getWhiteKing();
            } else {
                KingTileBitValue = BoardState.getBlackKing();
            }
            if (InCheck(BoardState, IsWhiteTurn, KingTileBitValue)) {
                TempBoard = new com.mygdx.game.Nea.BitBoards(BoardState);
                bsm.MovementType(TempBoard, IsWhiteTurn, SelectedTileBitValue, CurrentTileBitValue, SelectedPieceType, TempBoard.Promotion);
                if (IsWhiteTurn) {
                    KingTileBitValue = TempBoard.getWhiteKing();
                } else {
                    KingTileBitValue = TempBoard.getBlackKing();
                }
                if (InCheck(TempBoard, IsWhiteTurn, KingTileBitValue)) {
                    return false;
                }

            } else {
                TempBoard = new com.mygdx.game.Nea.BitBoards(BoardState);
                bsm.MovementType(TempBoard, IsWhiteTurn, SelectedTileBitValue, CurrentTileBitValue, SelectedPieceType, TempBoard.Promotion);
                if (IsWhiteTurn) {
                    KingTileBitValue = TempBoard.getWhiteKing();
                } else {
                    KingTileBitValue = TempBoard.getBlackKing();
                }
                if (InCheck(TempBoard, IsWhiteTurn, KingTileBitValue)) {
                    return false;
                }
            }
        }
        if (IsWhiteTurn) {
            if ((SelectedTileBitValue & BoardState.getWhitePieces()) != 0) {
                return false;
            } else if ((SelectedTileBitValue & BoardState.getBlackKing()) != 0) {
                return false;
            }
        } else {
            if ((SelectedTileBitValue & BoardState.getBlackPieces()) != 0) {
                return false;
            } else if ((SelectedTileBitValue & BoardState.getWhiteKing()) != 0) {
                return false;
            }
        }
        return IsLegalMove;
    }

    public boolean LegalMoveLogic(long CurrentTileBitValue, long SelectedTileBitValue, int SelectedPieceType, boolean IsWhiteTurn, com.mygdx.game.Nea.BitBoards BoardState) {
        boolean IsValidMove = true;
        int CurrentRank = pos.Rank(CurrentTileBitValue);
        int FileDiff = pos.File(SelectedTileBitValue) - pos.File(CurrentTileBitValue);
        int RankDiff = pos.Rank(SelectedTileBitValue) - CurrentRank;

        if (CurrentTileBitValue == SelectedTileBitValue) {
            return false;
        } else {
            if (SelectedPieceType == Pawn) {
                return pml.PawnLogic(BoardState, IsWhiteTurn, FileDiff, CurrentRank, RankDiff, CurrentTileBitValue, SelectedTileBitValue, IsValidMove);
            } else if (SelectedPieceType == Rook) {
                return pml.RookLogic(BoardState, FileDiff, RankDiff, CurrentTileBitValue, SelectedTileBitValue, IsValidMove);
            } else if (SelectedPieceType == Bishop) {
                return pml.BishopLogic(BoardState, FileDiff, RankDiff, CurrentTileBitValue, SelectedTileBitValue, IsValidMove);
            } else if (SelectedPieceType == Knight) {
                return pml.KnightLogic(FileDiff, RankDiff, CurrentTileBitValue, SelectedTileBitValue, IsValidMove);
            } else if (SelectedPieceType == Queen) {
                return pml.QueenLogic(BoardState, FileDiff, RankDiff, CurrentTileBitValue, SelectedTileBitValue, IsValidMove);
            } else if (SelectedPieceType == King) {
                return pml.KingLogic(FileDiff, RankDiff, CurrentTileBitValue, SelectedTileBitValue, IsValidMove);
            }
        }
        return IsValidMove;
    }
    public boolean InCheck(com.mygdx.game.Nea.BitBoards BoardState, boolean IsWhiteTurn, long KingTileBitValue) {
        int KingThreats = 0;
        boolean Check = false;

        if (IsWhiteTurn) {
            KingThreats = InCheckLogic(BoardState, BoardState.getBlackPawn(), KingTileBitValue, false, Pawn, KingThreats);
            KingThreats = InCheckLogic(BoardState, BoardState.getBlackKnight(), KingTileBitValue, true, Knight, KingThreats);
            KingThreats = InCheckLogic(BoardState, BoardState.getBlackBishop(), KingTileBitValue, true, Bishop, KingThreats);
            KingThreats = InCheckLogic(BoardState, BoardState.getBlackRook(), KingTileBitValue, true, Rook, KingThreats);
            KingThreats = InCheckLogic(BoardState, BoardState.getBlackQueen(), KingTileBitValue, true, Queen, KingThreats);
            KingThreats = InCheckLogic(BoardState, BoardState.getBlackKing(), KingTileBitValue, true, King, KingThreats);
        } else {
            KingThreats = InCheckLogic(BoardState, BoardState.getWhitePawn(), KingTileBitValue, true, Pawn, KingThreats);
            KingThreats = InCheckLogic(BoardState, BoardState.getWhiteKnight(), KingTileBitValue, false, Knight, KingThreats);
            KingThreats = InCheckLogic(BoardState, BoardState.getWhiteBishop(), KingTileBitValue, false, Bishop, KingThreats);
            KingThreats = InCheckLogic(BoardState, BoardState.getWhiteRook(), KingTileBitValue, false, Rook, KingThreats);
            KingThreats = InCheckLogic(BoardState, BoardState.getWhiteQueen(), KingTileBitValue, false, Queen, KingThreats);
            KingThreats = InCheckLogic(BoardState, BoardState.getWhiteKing(), KingTileBitValue, false, King, KingThreats);
        }
        if (KingThreats != 0) {
            Check = true;
        }
        return Check;
    }

    public int InCheckLogic(com.mygdx.game.Nea.BitBoards BoardState, long CheckingBoard, long KingTileBitValue, boolean IsWhiteCheckingBlack, int SelectedPieceType, int KingThreats) {
        long WorkCheckingBoard = CheckingBoard;
        int range = Long.bitCount(WorkCheckingBoard);//counts pieces on specific BitBoard
        long current;
        com.mygdx.game.Nea.BitBoards TempBoard = new com.mygdx.game.Nea.BitBoards(BoardState);
        for (int i = 0; i < range; i++) {
            current = Long.lowestOneBit(WorkCheckingBoard);
            WorkCheckingBoard ^= current;
            if (LegalMoveLogic(current, KingTileBitValue, SelectedPieceType, IsWhiteCheckingBlack, TempBoard)) {//if a piece can legally get to the king
                KingThreats++;
            }
        }

        return KingThreats;
    }
}
