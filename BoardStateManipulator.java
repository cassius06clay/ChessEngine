package com.mygdx.game.Nea;

public class BoardStateManipulator {
    private final int Pawn = 1;
    private final int Rook = 2;
    private final int Bishop = 3;
    private final int Knight = 4;
    private final int Queen = 5;
    public void MovementType (com.mygdx.game.Nea.BitBoards BoardState, boolean IsWhiteTurn, long SelectedTileBitValue,
                              long CurrentTileBitValue, int SelectedPieceType, long PomotionTiles){
        if ((BoardState.getBoardState() & SelectedTileBitValue) == 0) {
            Move(BoardState, IsWhiteTurn, SelectedTileBitValue, CurrentTileBitValue, SelectedPieceType, PomotionTiles);
        } else {//if occupied
            Capture(BoardState, IsWhiteTurn, CurrentTileBitValue, SelectedTileBitValue, SelectedPieceType, PomotionTiles);
        }
    }
    public void Move (com.mygdx.game.Nea.BitBoards BoardState, boolean IsWhiteTurn, long SelectedTileBitValue, long CurrentTileBitValue, int SelectedPieceType, long PromotionTiles){
        long Update = SelectedTileBitValue | CurrentTileBitValue;
        if ((SelectedTileBitValue & BoardState.getBoardState()) == 0) {
            if (IsWhiteTurn) {
                if ((SelectedTileBitValue & BoardState.getWhitePieces()) == 0) {
                    if (SelectedPieceType == Pawn) {//checks your grabbing your piece
                        if ((SelectedTileBitValue & PromotionTiles) != 0) {
                            BoardState.setWhitePawn(BoardState.getWhitePawn() ^ CurrentTileBitValue);
                            BoardState.setWhiteQueen(BoardState.getWhiteQueen() ^ SelectedTileBitValue);
                            BoardState.UpdateBoardState();
                        } else {
                            BoardState.setWhitePawn(BoardState.getWhitePawn() ^ Update);
                            BoardState.UpdateBoardState();
                        }
                    } else if (SelectedPieceType == Knight) {
                        BoardState.setWhiteKnight(BoardState.getWhiteKnight() ^ Update);
                        BoardState.UpdateBoardState();
                    } else if (SelectedPieceType == Bishop) {
                        BoardState.setWhiteBishop(BoardState.getWhiteBishop() ^ Update);
                        BoardState.UpdateBoardState();
                    } else if (SelectedPieceType == Rook) {
                        BoardState.setWhiteRook(BoardState.getWhiteRook() ^ Update);
                        BoardState.UpdateBoardState();
                    } else if (SelectedPieceType == Queen) {
                        BoardState.setWhiteQueen(BoardState.getWhiteQueen() ^ Update);
                        BoardState.UpdateBoardState();
                    } else {
                        BoardState.setWhiteKing(BoardState.getWhiteKing() ^ Update);
                        BoardState.UpdateBoardState();
                    }
                }
            } else {//blacks turn
                if ((SelectedTileBitValue & BoardState.getBlackPieces()) == 0) {
                    if (SelectedPieceType == Pawn) {
                        if ((SelectedTileBitValue & PromotionTiles) != 0) {
                            BoardState.setBlackPawn(BoardState.getBlackPawn() ^ CurrentTileBitValue);
                            BoardState.setBlackQueen(BoardState.getBlackQueen() ^ SelectedTileBitValue);
                            BoardState.UpdateBoardState();
                        } else {
                            BoardState.setBlackPawn(BoardState.getBlackPawn() ^ Update);
                            BoardState.UpdateBoardState();
                        }
                    } else if (SelectedPieceType == Knight) {
                        BoardState.setBlackKnight(BoardState.getBlackKnight() ^ Update);
                        BoardState.UpdateBoardState();
                    } else if (SelectedPieceType == Bishop) {
                        BoardState.setBlackBishop(BoardState.getBlackBishop() ^ Update);
                        BoardState.UpdateBoardState();
                    } else if (SelectedPieceType == Rook) {
                        BoardState.setBlackRook(BoardState.getBlackRook() ^ Update);
                        BoardState.UpdateBoardState();
                    } else if (SelectedPieceType == Queen) {
                        BoardState.setBlackQueen(BoardState.getBlackQueen() ^ Update);
                        BoardState.UpdateBoardState();
                    } else {//if its a king selected
                        BoardState.setBlackKing(BoardState.getBlackKing() ^ Update);
                        BoardState.UpdateBoardState();
                    }
                }
            }
        }
    }
    public void Capture (com.mygdx.game.Nea.BitBoards BoardState, boolean IsWhiteTurn, long CurrentTileBitValue,
                         long SelectedTileBitValue, int SelectedPieceType, long PromotionTiles){
        long Update = CurrentTileBitValue | SelectedTileBitValue;
        if (IsWhiteTurn) {
            if ((SelectedTileBitValue & BoardState.getBlackPieces()) != 0) {//if capturing
                if ((SelectedTileBitValue & (BoardState.getBlackPawn() | BoardState.getBlackKnight() | BoardState.getBlackBishop())) != 0) {//if capturing pawn knight or bishop
                    if ((SelectedTileBitValue & BoardState.getBlackPawn()) != 0) {//if capturing pawn
                        BoardState.setBlackPawn(BoardState.getBlackPawn() ^ SelectedTileBitValue);
                        if (SelectedPieceType == Pawn) {
                            if ((SelectedTileBitValue & PromotionTiles) != 0) {
                                BoardState.setWhitePawn(BoardState.getWhitePawn() ^ CurrentTileBitValue);
                                BoardState.setWhiteQueen(BoardState.getWhiteQueen() ^ SelectedTileBitValue);
                            } else {
                                BoardState.setWhitePawn(BoardState.getWhitePawn() ^ Update);
                            }
                        } else if (SelectedPieceType == Knight) {
                            BoardState.setWhiteKnight(BoardState.getWhiteKnight() ^ Update);
                        } else if (SelectedPieceType == Bishop) {
                            BoardState.setWhiteBishop(BoardState.getWhiteBishop() ^ Update);
                        } else if (SelectedPieceType == Rook) {
                            BoardState.setWhiteRook(BoardState.getWhiteRook() ^ Update);
                        } else if (SelectedPieceType == Queen) {
                            BoardState.setWhiteQueen(BoardState.getWhiteQueen() ^ Update);
                        } else {
                            BoardState.setWhiteKing(BoardState.getWhiteKing() ^ Update);
                        }
                    } else if ((SelectedTileBitValue & BoardState.getBlackKnight()) != 0) {
                        BoardState.setBlackKnight(BoardState.getBlackKnight() ^ SelectedTileBitValue);
                        if (SelectedPieceType == Pawn) {
                            if ((SelectedTileBitValue & PromotionTiles) != 0) {
                                BoardState.setWhitePawn(BoardState.getWhitePawn() ^ CurrentTileBitValue);
                                BoardState.setWhiteQueen(BoardState.getWhiteQueen() ^ SelectedTileBitValue);
                            } else {
                                BoardState.setWhitePawn(BoardState.getWhitePawn() ^ Update);
                            }
                        } else if (SelectedPieceType == Knight) {
                            BoardState.setWhiteKnight(BoardState.getWhiteKnight() ^ Update);
                        } else if (SelectedPieceType == Bishop) {
                            BoardState.setWhiteBishop(BoardState.getWhiteBishop() ^ Update);
                        } else if (SelectedPieceType == Rook) {
                            BoardState.setWhiteRook(BoardState.getWhiteRook() ^ Update);
                        } else if (SelectedPieceType == Queen) {
                            BoardState.setWhiteQueen(BoardState.getWhiteQueen() ^ Update);
                        } else {
                            BoardState.setWhiteKing(BoardState.getWhiteKing() ^ Update);
                        }
                    } else {
                        BoardState.setBlackBishop(BoardState.getBlackBishop() ^ SelectedTileBitValue);
                        if (SelectedPieceType == Pawn) {
                            if ((SelectedTileBitValue & PromotionTiles) != 0) {
                                BoardState.setWhitePawn(BoardState.getWhitePawn() ^ CurrentTileBitValue);
                                BoardState.setWhiteQueen(BoardState.getWhiteQueen() ^ SelectedTileBitValue);
                            } else {
                                BoardState.setWhitePawn(BoardState.getWhitePawn() ^ Update);
                            }
                        } else if (SelectedPieceType == Knight) {
                            BoardState.setWhiteKnight(BoardState.getWhiteKnight() ^ Update);
                        } else if (SelectedPieceType == Bishop) {
                            BoardState.setWhiteBishop(BoardState.getWhiteBishop() ^ Update);
                        } else if (SelectedPieceType == Rook) {
                            BoardState.setWhiteRook(BoardState.getWhiteRook() ^ Update);
                        } else if (SelectedPieceType == Queen) {
                            BoardState.setWhiteQueen(BoardState.getWhiteQueen() ^ Update);
                        } else {
                            BoardState.setWhiteKing(BoardState.getWhiteKing() ^ Update);
                        }
                    }
                } else {//if capturing black Rook Queen or King
                    if ((SelectedTileBitValue & BoardState.getBlackRook()) != 0) {
                        BoardState.setBlackRook(BoardState.getBlackRook() ^ SelectedTileBitValue);
                        if (SelectedPieceType == Pawn) {
                            if ((SelectedTileBitValue & PromotionTiles) != 0) {
                                BoardState.setWhitePawn(BoardState.getWhitePawn() ^ CurrentTileBitValue);
                                BoardState.setWhiteQueen(BoardState.getWhiteQueen() ^ SelectedTileBitValue);
                            } else {
                                BoardState.setWhitePawn(BoardState.getWhitePawn() ^ Update);
                            }
                        } else if (SelectedPieceType == Knight) {
                            BoardState.setWhiteKnight(BoardState.getWhiteKnight() ^ Update);
                        } else if (SelectedPieceType == Bishop) {
                            BoardState.setWhiteBishop(BoardState.getWhiteBishop() ^ Update);
                        } else if (SelectedPieceType == Rook) {
                            BoardState.setWhiteRook(BoardState.getWhiteRook() ^ Update);
                        } else if (SelectedPieceType == Queen) {
                            BoardState.setWhiteQueen(BoardState.getWhiteQueen() ^ Update);
                        } else {
                            BoardState.setWhiteKing(BoardState.getWhiteKing() ^ Update);
                        }
                    } else if ((SelectedTileBitValue & BoardState.getBlackQueen()) != 0) {
                        BoardState.setBlackQueen(BoardState.getBlackQueen() ^ SelectedTileBitValue);
                        if (SelectedPieceType == Pawn) {
                            if ((SelectedTileBitValue & PromotionTiles) != 0) {
                                BoardState.setWhitePawn(BoardState.getWhitePawn() ^ CurrentTileBitValue);
                                BoardState.setWhiteQueen(BoardState.getWhiteQueen() ^ SelectedTileBitValue);
                            } else {
                                BoardState.setWhitePawn(BoardState.getWhitePawn() ^ Update);
                            }
                        } else if (SelectedPieceType == Knight) {
                            BoardState.setWhiteKnight(BoardState.getWhiteKnight() ^ Update);
                        } else if (SelectedPieceType == Bishop) {
                            BoardState.setWhiteBishop(BoardState.getWhiteBishop() ^ Update);
                        } else if (SelectedPieceType == Rook) {
                            BoardState.setWhiteRook(BoardState.getWhiteRook() ^ Update);
                        } else if (SelectedPieceType == Queen) {
                            BoardState.setWhiteQueen(BoardState.getWhiteQueen() ^ Update);
                        } else {
                            BoardState.setWhiteKing(BoardState.getWhiteKing() ^ Update);
                        }
                    }
                }
            }
        } else {//BlackTurn
            if ((SelectedTileBitValue & BoardState.getWhitePieces()) != 0) {//if capturing
                if ((SelectedTileBitValue & (BoardState.getWhitePawn() | BoardState.getWhiteKnight() | BoardState.getWhiteBishop())) != 0) {//if capturing pawn knight or bishop
                    if ((SelectedTileBitValue & BoardState.getWhitePawn()) != 0) {//if capturing pawn
                        BoardState.setWhitePawn(BoardState.getWhitePawn() ^ SelectedTileBitValue);
                        if (SelectedPieceType == Pawn) {
                            if ((SelectedTileBitValue & PromotionTiles) != 0) {
                                BoardState.setBlackPawn(BoardState.getBlackPawn() ^ CurrentTileBitValue);
                                BoardState.setBlackQueen(BoardState.getBlackQueen() ^ SelectedTileBitValue);
                            } else {
                                BoardState.setBlackPawn(BoardState.getBlackPawn() ^ Update);
                            }
                        } else if (SelectedPieceType == Knight) {
                            BoardState.setBlackKnight(BoardState.getBlackKnight() ^ Update);
                        } else if (SelectedPieceType == Bishop) {
                            BoardState.setBlackBishop(BoardState.getBlackBishop() ^ Update);
                        } else if (SelectedPieceType == Rook) {
                            BoardState.setBlackRook(BoardState.getBlackRook() ^ Update);
                        } else if (SelectedPieceType == Queen) {
                            BoardState.setBlackQueen(BoardState.getBlackQueen() ^ Update);
                        } else {
                            BoardState.setBlackKing(BoardState.getBlackKing() ^ Update);
                        }
                    } else if ((SelectedTileBitValue & BoardState.getWhiteKnight()) != 0) {
                        BoardState.setWhiteKnight(BoardState.getWhiteKnight() ^ SelectedTileBitValue);
                        if (SelectedPieceType == Pawn) {
                            if ((SelectedTileBitValue & PromotionTiles) != 0) {
                                BoardState.setBlackPawn(BoardState.getBlackPawn() ^ CurrentTileBitValue);
                                BoardState.setBlackQueen(BoardState.getBlackQueen() ^ SelectedTileBitValue);
                            } else {
                                BoardState.setBlackPawn(BoardState.getBlackPawn() ^ Update);
                            }
                        } else if (SelectedPieceType == Knight) {
                            BoardState.setBlackKnight(BoardState.getBlackKnight() ^ Update);
                        } else if (SelectedPieceType == Bishop) {
                            BoardState.setBlackBishop(BoardState.getBlackBishop() ^ Update);
                        } else if (SelectedPieceType == Rook) {
                            BoardState.setBlackRook(BoardState.getBlackRook() ^ Update);
                        } else if (SelectedPieceType == Queen) {
                            BoardState.setBlackQueen(BoardState.getBlackQueen() ^ Update);
                        } else {
                            BoardState.setBlackKing(BoardState.getBlackKing() ^ Update);
                        }
                    } else {
                        BoardState.setWhiteBishop(BoardState.getWhiteBishop() ^ SelectedTileBitValue);
                        if (SelectedPieceType == Pawn) {
                            if ((SelectedTileBitValue & PromotionTiles) != 0) {
                                BoardState.setBlackPawn(BoardState.getBlackPawn() ^ CurrentTileBitValue);
                                BoardState.setBlackQueen(BoardState.getBlackQueen() ^ SelectedTileBitValue);
                            } else {
                                BoardState.setBlackPawn(BoardState.getBlackPawn() ^ Update);
                            }
                        } else if (SelectedPieceType == Knight) {
                            BoardState.setBlackKnight(BoardState.getBlackKnight() ^ Update);
                        } else if (SelectedPieceType == Bishop) {
                            BoardState.setBlackBishop(BoardState.getBlackBishop() ^ Update);
                        } else if (SelectedPieceType == Rook) {
                            BoardState.setBlackRook(BoardState.getBlackRook() ^ Update);
                        } else if (SelectedPieceType == Queen) {
                            BoardState.setBlackQueen(BoardState.getBlackQueen() ^ Update);
                        } else {
                            BoardState.setBlackKing(BoardState.getBlackKing() ^ Update);
                        }
                    }
                } else {//if capturing White Rook Queen or King
                    if ((SelectedTileBitValue & BoardState.getWhiteRook()) != 0) {
                        BoardState.setWhiteRook(BoardState.getWhiteRook() ^ SelectedTileBitValue);
                        if (SelectedPieceType == Pawn) {
                            if ((SelectedTileBitValue & PromotionTiles) != 0) {
                                BoardState.setBlackPawn(BoardState.getBlackPawn() ^ CurrentTileBitValue);
                                BoardState.setBlackQueen(BoardState.getBlackQueen() ^ SelectedTileBitValue);
                            } else {
                                BoardState.setBlackPawn(BoardState.getBlackPawn() ^ Update);
                            }
                        } else if (SelectedPieceType == Knight) {
                            BoardState.setBlackKnight(BoardState.getBlackKnight() ^ Update);
                        } else if (SelectedPieceType == Bishop) {
                            BoardState.setBlackBishop(BoardState.getBlackBishop() ^ Update);
                        } else if (SelectedPieceType == Rook) {
                            BoardState.setBlackRook(BoardState.getBlackRook() ^ Update);
                        } else if (SelectedPieceType == Queen) {
                            BoardState.setBlackQueen(BoardState.getBlackQueen() ^ Update);
                        } else {
                            BoardState.setBlackKing(BoardState.getBlackKing() ^ Update);
                        }
                    } else if ((SelectedTileBitValue & BoardState.getWhiteQueen()) != 0) {
                        BoardState.setWhiteQueen(BoardState.getWhiteQueen() ^ SelectedTileBitValue);
                        if (SelectedPieceType == Pawn) {
                            if ((SelectedTileBitValue & PromotionTiles) != 0) {
                                BoardState.setBlackPawn(BoardState.getBlackPawn() ^ CurrentTileBitValue);
                                BoardState.setBlackQueen(BoardState.getBlackQueen() ^ SelectedTileBitValue);
                            } else {
                                BoardState.setBlackPawn(BoardState.getBlackPawn() ^ Update);
                            }
                        } else if (SelectedPieceType == Knight) {
                            BoardState.setBlackKnight(BoardState.getBlackKnight() ^ Update);
                        } else if (SelectedPieceType == Bishop) {
                            BoardState.setBlackBishop(BoardState.getBlackBishop() ^ Update);
                        } else if (SelectedPieceType == Rook) {
                            BoardState.setBlackRook(BoardState.getBlackRook() ^ Update);
                        } else if (SelectedPieceType == Queen) {
                            BoardState.setBlackQueen(BoardState.getBlackQueen() ^ Update);
                        } else {
                            BoardState.setBlackKing(BoardState.getBlackKing() ^ Update);
                        }
                    }
                }
            }
        }
        BoardState.UpdateBoardState();
    }
}
