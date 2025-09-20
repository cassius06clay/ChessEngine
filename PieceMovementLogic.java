package com.mygdx.game.Nea;
public class PieceMovementLogic {


    public boolean PawnLogic(BitBoards BoardState, boolean IsWhiteTurn, int FileDiff, int CurrentRank, int RankDiff, long CurrentTileBitValue, long SelectedTileBitValue, boolean IsValidMove) {
        if (IsWhiteTurn) {
            if (Math.abs(FileDiff) > 1) {
                IsValidMove = false;
            } else if (RankDiff == 1&&FileDiff==0) {//forwards 1
                if (CurrentTileBitValue << 8 != SelectedTileBitValue) {
                    IsValidMove = false;
                }
                else {
                    if (((CurrentTileBitValue << 8) & BoardState.getBlackPieces()) != 0) {
                        IsValidMove = false;
                    }
                }
            }
            else if (RankDiff == 1&&Math.abs(FileDiff)==1) {//capturing
                boolean CaptureLeft = (((CurrentTileBitValue << 9) & BoardState.getBlackPieces()) != 0);
                boolean CaptureRight = (((CurrentTileBitValue << 7) & BoardState.getBlackPieces()) != 0);
                if(((CurrentTileBitValue << 9) & SelectedTileBitValue)!= 0){
                    if(!CaptureLeft){
                        IsValidMove=false;
                    }
                }
                if(((CurrentTileBitValue << 7) & SelectedTileBitValue)!= 0){
                    if(!CaptureRight){
                        IsValidMove=false;
                    }

                }
            }
            else if (RankDiff == 2) {
                if(FileDiff!=0){
                    IsValidMove=false;
                }
                else if (CurrentRank != 1) {
                    IsValidMove = false;
                }
                else {
                    if (((CurrentTileBitValue << 8) & BoardState.getBoardState()) != 0 || ((CurrentTileBitValue << 16) & BoardState.getBoardState()) != 0) {
                        IsValidMove = false;
                    }
                }
            } else {
                IsValidMove = false;
            }
        }
        else {
            if (Math.abs(FileDiff) > 1) {
                IsValidMove = false;
            }
            else if (RankDiff == -1&&FileDiff==0) {//forwards 1
                if (CurrentTileBitValue >>> 8 != SelectedTileBitValue) {
                    IsValidMove = false;
                }
                else {
                    if (((CurrentTileBitValue >>> 8) & BoardState.getWhitePieces()) != 0) {
                        IsValidMove = false;
                    }
                }
            }
            else if (RankDiff == -1&&Math.abs(FileDiff)==1) {//capturing
                boolean CaptureLeft = (((CurrentTileBitValue >>> 9) & BoardState.getWhitePieces()) != 0);
                boolean CaptureRight = (((CurrentTileBitValue >>> 7) & BoardState.getWhitePieces()) != 0);
                if(((CurrentTileBitValue >>> 9) & SelectedTileBitValue)!= 0){
                    if(!CaptureLeft){
                        IsValidMove=false;
                    }
                }
                if(((CurrentTileBitValue >>> 7) & SelectedTileBitValue)!= 0){
                    if(!CaptureRight){
                        IsValidMove=false;
                    }

                }
            }
            else if (RankDiff == -2) {
                if(FileDiff!=0){
                    IsValidMove=false;
                }
                else if (CurrentRank != 6) {
                    IsValidMove = false;
                }
                else {
                    if (((CurrentTileBitValue >>> 8) & BoardState.getBoardState()) != 0 || ((CurrentTileBitValue >>> 16) & BoardState.getBoardState()) != 0) {
                        IsValidMove = false;
                    }
                }
            } else {
                IsValidMove = false;
            }
        }
        return IsValidMove;
    }

    public boolean RookLogic(BitBoards BoardState, int FileDiff, int RankDiff, long CurrentTileBitValue, long SelectedTileBitValue, boolean IsValidMove) {
        if(CurrentTileBitValue==SelectedTileBitValue){
            IsValidMove=false;
        }
        if (RankDiff > 0) {
            for (int i = 1; i < RankDiff; i++) {//up collision
                if (((CurrentTileBitValue << Math.abs(8 * i)) & BoardState.getBoardState()) != 0) {
                    IsValidMove = false;
                    break;
                }
            }
        } else if (RankDiff < 0) {
            for (int j = -1; j > RankDiff; j--) {//down collision
                if (((CurrentTileBitValue >>> Math.abs(8 * j)) & BoardState.getBoardState()) != 0) {
                    IsValidMove = false;
                    break;
                }
            }
        } else if (FileDiff > 0) {
            for (int k = 1; k < FileDiff; k++) {//left collision
                if (((CurrentTileBitValue << k) & BoardState.getBoardState()) != 0) {
                    IsValidMove = false;
                    break;
                }
            }
        } else if (FileDiff < 0) {
            for (int l = -1; l > FileDiff; l--) {//right shift
                if (((CurrentTileBitValue >>> Math.abs(l)) & BoardState.getBoardState()) != 0) {
                    IsValidMove = false;
                    l = FileDiff;
                }
            }
        }

        if (CurrentTileBitValue << Math.abs(8 * RankDiff) != SelectedTileBitValue) {//up n
            if (CurrentTileBitValue >>> Math.abs(8 * RankDiff) != SelectedTileBitValue) {//down n
                if (RankDiff != 0) {
                    IsValidMove = false;
                }
            }
        }
        return IsValidMove;
    }

    public boolean BishopLogic(BitBoards BoardState, int FileDiff, int RankDiff, long CurrentTileBitValue, long SelectedTileBitValue, boolean IsValidMove) {
        if (RankDiff > 0) {
            if (FileDiff > 0) {
                for (int i = 1; i < RankDiff; i++) {
                    if (((CurrentTileBitValue << Math.abs(9 * i)) & BoardState.getBoardState()) != 0) {
                        IsValidMove = false;
                        break;
                    }
                }
            }
            if (FileDiff < 0) {
                for (int j = 1; j < RankDiff; j++) {
                    if (((CurrentTileBitValue << Math.abs(7 * j)) & BoardState.getBoardState()) != 0) {
                        IsValidMove = false;
                        break;
                    }
                }
            }
        }
        if (RankDiff < 0) {
            if (FileDiff < 0) {
                for (int i = -1; i > RankDiff; i--) {
                    if (((CurrentTileBitValue >>> Math.abs(9 * i)) & BoardState.getBoardState()) != 0) {
                        IsValidMove = false;
                        break;
                    }
                }
            }
            if (FileDiff > 0) {
                for (int j = -1; j > RankDiff; j--) {
                    if (((CurrentTileBitValue >>> Math.abs(7 * j)) & BoardState.getBoardState()) != 0) {
                        IsValidMove = false;
                        break;
                    }
                }
            }
        }
        if (CurrentTileBitValue << Math.abs(9 * RankDiff) != SelectedTileBitValue) {//up left n
            if (CurrentTileBitValue << Math.abs(7 * RankDiff) != SelectedTileBitValue) {//up right n
                if (CurrentTileBitValue >>> Math.abs(7 * RankDiff) != SelectedTileBitValue) {//bottom left n
                    if (CurrentTileBitValue >>> Math.abs(9 * RankDiff) != SelectedTileBitValue) {//bottom right n
                        IsValidMove = false;
                    }
                }
            }
        }
        return IsValidMove;
    }

    public boolean KnightLogic(int FileDiff, int RankDiff, long CurrentTileBitValue, long SelectedTileBitValue, boolean IsValidMove) {
        if (Math.abs(RankDiff) > 2 || Math.abs(FileDiff) > 2) {
            IsValidMove = false;
        }
        if (CurrentTileBitValue << 10 != SelectedTileBitValue) {//-2i+j
            if (CurrentTileBitValue << 17 != SelectedTileBitValue) {//-i+2j
                if (CurrentTileBitValue >>> 10 != SelectedTileBitValue) {//2i-j
                    if (CurrentTileBitValue >>> 17 != SelectedTileBitValue) {//i-2j
                        if (CurrentTileBitValue << 15 != SelectedTileBitValue) {//i+2j
                            if (CurrentTileBitValue << 6 != SelectedTileBitValue) {//2i+j
                                if (CurrentTileBitValue >>> 15 != SelectedTileBitValue) {//-i-2j
                                    if (CurrentTileBitValue >>> 6 != SelectedTileBitValue) {//-2i-j
                                        IsValidMove = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return IsValidMove;
    }

    public boolean QueenLogic(BitBoards BoardState, int FileDiff, int RankDiff, long CurrentTileBitValue, long SelectedTileBitValue, boolean IsValidMove) {

        if (RankDiff != 0 & FileDiff != 0) {
            if (RankDiff > 0) {
                if (FileDiff > 0) {
                    for (int i = 1; i < RankDiff; i++) {
                        if (((CurrentTileBitValue << Math.abs(9 * i)) & BoardState.getBoardState()) != 0) {
                            IsValidMove = false;
                            break;
                        }
                    }
                }
                if (FileDiff < 0) {
                    for (int j = 1; j < RankDiff; j++) {
                        if (((CurrentTileBitValue << Math.abs(7 * j)) & BoardState.getBoardState()) != 0) {
                            IsValidMove = false;
                            break;
                        }
                    }
                }
            }
            if (RankDiff < 0) {
                if (FileDiff < 0) {
                    for (int i = -1; i > RankDiff; i--) {
                        if (((CurrentTileBitValue >>> Math.abs(9 * i)) & BoardState.getBoardState()) != 0) {
                            IsValidMove = false;
                            break;
                        }
                    }
                }
                if (FileDiff > 0) {
                    for (int j = -1; j > RankDiff; j--) {
                        if (((CurrentTileBitValue >>> Math.abs(7 * j)) & BoardState.getBoardState()) != 0) {
                            IsValidMove = false;
                            break;
                        }
                    }
                }
            }
        } else {
            if (RankDiff > 0) {
                for (int i = 1; i < RankDiff; i++) {//up collision
                    if (((CurrentTileBitValue << Math.abs(8 * i)) & BoardState.getBoardState()) != 0) {
                        IsValidMove = false;
                        break;
                    }
                }
            } else if (RankDiff < 0) {
                for (int j = -1; j > RankDiff; j--) {//down collision
                    if (((CurrentTileBitValue >>> Math.abs(8 * j)) & BoardState.getBoardState()) != 0) {
                        IsValidMove = false;
                        break;
                    }
                }
            } else if (FileDiff > 0) {
                for (int k = 1; k < FileDiff; k++) {//left collision
                    if (((CurrentTileBitValue << k) & BoardState.getBoardState()) != 0) {
                        IsValidMove = false;
                        break;
                    }
                }
            } else if (FileDiff < 0) {
                for (int l = -1; l > FileDiff; l--) {//right shift
                    if (((CurrentTileBitValue >>> Math.abs(l)) & BoardState.getBoardState()) != 0) {
                        IsValidMove = false;
                        break;
                    }
                }
            }
        }
        if (CurrentTileBitValue << Math.abs(9 * RankDiff) != SelectedTileBitValue) {//up left n
            if (CurrentTileBitValue << Math.abs(7 * RankDiff) != SelectedTileBitValue) {//up right n
                if (CurrentTileBitValue >>> Math.abs(7 * RankDiff) != SelectedTileBitValue) {//bottom left n
                    if (CurrentTileBitValue >>> Math.abs(9 * RankDiff) != SelectedTileBitValue) {//bottom right n
                        if (CurrentTileBitValue << Math.abs(8 * RankDiff) != SelectedTileBitValue) {//up n
                            if (CurrentTileBitValue >>> Math.abs(8 * RankDiff) != SelectedTileBitValue) {//down n
                                if (RankDiff != 0) {
                                    IsValidMove = false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return IsValidMove;
    }

    public boolean KingLogic(int FileDiff, int RankDiff, long CurrentTileBitValue, long SelectedTileBitValue, boolean IsValidMove) {

        if (Math.abs(RankDiff) > 2 || Math.abs(FileDiff) > 2) {
            IsValidMove = false;
        }
        if (CurrentTileBitValue << 8 != SelectedTileBitValue) {//up
            if (CurrentTileBitValue << 1 != SelectedTileBitValue) {//left
                if (CurrentTileBitValue >>> 8 != SelectedTileBitValue) {//down
                    if (CurrentTileBitValue >>> 1 != SelectedTileBitValue) {//right
                        if (CurrentTileBitValue << 9 != SelectedTileBitValue) {//top left
                            if (CurrentTileBitValue << 7 != SelectedTileBitValue) {//top right
                                if (CurrentTileBitValue >>> 7 != SelectedTileBitValue) {//bottom left
                                    if (CurrentTileBitValue >>> 9 != SelectedTileBitValue) {//bottom right
                                        IsValidMove = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return IsValidMove;
    }
}
