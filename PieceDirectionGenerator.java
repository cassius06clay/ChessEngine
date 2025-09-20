package com.mygdx.game.Nea;
public class PieceDirectionGenerator {
    Position pos=new Position();

    public long PawnDirectionGenerator(com.mygdx.game.Nea.BitBoards BoardState, long PawnTileBitValue, boolean IsWhiteTurn) {
        long PossibleMoveTiles;
        if (IsWhiteTurn) {
            PossibleMoveTiles = (PawnTileBitValue << 7) | (PawnTileBitValue << 8) | (PawnTileBitValue << 9);
            if ((BoardState.getFirstMove() & PawnTileBitValue) != 0) {
                PossibleMoveTiles = PossibleMoveTiles | (PawnTileBitValue << 16);

            }
        } else {
            PossibleMoveTiles = (PawnTileBitValue >>> 7) | (PawnTileBitValue >>> 8) | (PawnTileBitValue >>> 9);
            if ((BoardState.getFirstMove() & PawnTileBitValue) != 0) {
                PossibleMoveTiles = PossibleMoveTiles | (PawnTileBitValue >>> 16);
            }
        }
        return PossibleMoveTiles;
    }

    public long RookDirectionGenerator(com.mygdx.game.Nea.BitBoards BoardState, long RookTileBitValue, boolean IsWhiteTurn) {
        long PossibleMoveTiles = 0;
        long BitMask;
        int Rank = pos.Rank(RookTileBitValue);
        int File = pos.File(RookTileBitValue);
        BitMask = RookTileBitValue;
        long OppositeBoard;
        if(IsWhiteTurn){
            OppositeBoard=BoardState.getBlackPieces();
        }
        else{
            OppositeBoard=BoardState.getWhitePieces();
        }

        for (int i = 0; i < 7 - Rank; i++) {//up collision
            BitMask <<= 8;
            if ((BitMask & BoardState.getBoardState()) == 0) {
                PossibleMoveTiles = PossibleMoveTiles | BitMask;
            } else if ((BitMask & OppositeBoard) != 0) {
                PossibleMoveTiles = PossibleMoveTiles | BitMask;
                i=7-Rank;
            } else {
                i=7-Rank;
            }
        }
        BitMask = RookTileBitValue;
        for (int j = 0; j < Rank; j++) {
            BitMask >>>= 8;
            if ((BitMask & BoardState.getBoardState()) == 0) {
                PossibleMoveTiles = PossibleMoveTiles | BitMask;
            } else if ((BitMask & OppositeBoard) != 0) {
                PossibleMoveTiles = PossibleMoveTiles | BitMask;
                j=Rank;
            } else {
                j=Rank;
            }
        }
        BitMask = RookTileBitValue;
        for (int k = 1; k < 8 - File; k++) {//left collision
            BitMask <<= 1;
            if ((BitMask & BoardState.getBoardState()) == 0) {
                PossibleMoveTiles = PossibleMoveTiles | BitMask;
            } else if ((BitMask & OppositeBoard) != 0) {
                PossibleMoveTiles = PossibleMoveTiles | BitMask;
                k=8 - File;
            } else {
                k=8 - File;
            }
        }
        BitMask = RookTileBitValue;
        for (int l = 0; l < File; l++) {
            BitMask >>>= 1;
            if ((BitMask & BoardState.getBoardState()) == 0) {
                PossibleMoveTiles = PossibleMoveTiles | BitMask;
            } else if ((BitMask & OppositeBoard) != 0) {
                PossibleMoveTiles = PossibleMoveTiles | BitMask;
                l=File;
            } else {
                l=File;
            }
        }
        return PossibleMoveTiles;
    }
    public long BishopDirectionGenerator(com.mygdx.game.Nea.BitBoards BoardState, long BishopTileBitValue, boolean IsWhiteTurn) {
        long PossibleMoveTiles = 0;
        long BitMask=BishopTileBitValue;
        int Rank = pos.Rank(BishopTileBitValue);
        int File = pos.File(BishopTileBitValue);
        long OppositeBoard;
        if(IsWhiteTurn){
            OppositeBoard=BoardState.getBlackPieces();
        }
        else{
            OppositeBoard=BoardState.getWhitePieces();
        }
        if(Rank<7) {
            if (File < 7) {
                for (int i = 0; i < 7-Rank; i++) {
                    BitMask <<= 9;
                    if ((BitMask & BoardState.getBoardState()) == 0) {
                        PossibleMoveTiles = PossibleMoveTiles | BitMask;
                    }
                    else if((BitMask & OppositeBoard) != 0) {
                        PossibleMoveTiles = PossibleMoveTiles | BitMask;
                        break;
                    }
                    else{
                        break;
                    }
                }
                BitMask=BishopTileBitValue;
            }
            if (File > 0) {
                for (int j = 0; j < 7-Rank; j++) {
                    BitMask <<= 7;
                    if ((BitMask & BoardState.getBoardState()) == 0) {
                        PossibleMoveTiles = PossibleMoveTiles | BitMask;
                    }
                    else if((BitMask & OppositeBoard) != 0) {
                        PossibleMoveTiles = PossibleMoveTiles | BitMask;
                        break;
                    }
                    else{
                        break;
                    }
                }
                BitMask=BishopTileBitValue;
            }
        }
        if(Rank>0) {
            if(File<7) {
                for (int i = Rank; i > 0; i--) {
                    BitMask >>>= 7;
                    if ((BitMask & BoardState.getBoardState()) == 0) {
                        PossibleMoveTiles = PossibleMoveTiles | BitMask;
                    }
                    else if((BitMask & OppositeBoard) != 0) {
                        PossibleMoveTiles = PossibleMoveTiles | BitMask;
                        break;
                    }
                    else{
                        break;
                    }
                }
                BitMask=BishopTileBitValue;
            }
            if(File>0) {
                for (int j = Rank; j > 0; j--) {
                    BitMask >>>= 9;
                    if ((BitMask & BoardState.getBoardState()) == 0) {
                        PossibleMoveTiles = PossibleMoveTiles | BitMask;
                    }
                    else if((BitMask & OppositeBoard) != 0) {
                        PossibleMoveTiles = PossibleMoveTiles | BitMask;
                        break;
                    }
                    else{
                        break;
                    }
                }
            }
        }

        return PossibleMoveTiles;
    }
    public long QueenDirectionGenerator(com.mygdx.game.Nea.BitBoards BoardState, long QueenTileBitValue, boolean IsWhiteTurn) {
        long PossibleMoveTiles = 0;
        PossibleMoveTiles=PossibleMoveTiles+BishopDirectionGenerator(BoardState,QueenTileBitValue,IsWhiteTurn);
        PossibleMoveTiles=PossibleMoveTiles+RookDirectionGenerator(BoardState,QueenTileBitValue,IsWhiteTurn);
        return PossibleMoveTiles;
    }
    public long KingDirectionGenerator(com.mygdx.game.Nea.BitBoards BoardState, long KingTileBitValue, boolean IsWhiteTurn) {
        long PossibleMoveTiles=0;
        int Rank = pos.Rank(KingTileBitValue);
        int File = pos.File(KingTileBitValue);
        if (Rank < 7) {
            if (File > 0) {
                PossibleMoveTiles = PossibleMoveTiles | (KingTileBitValue << 7) | (KingTileBitValue << 8)|(KingTileBitValue>>> 1);
            }
            else{
                PossibleMoveTiles=(KingTileBitValue << 8);
            }
            if(File<7){
                PossibleMoveTiles=PossibleMoveTiles|(KingTileBitValue << 9)|(KingTileBitValue << 1);
            }
        }
        else{
            PossibleMoveTiles=PossibleMoveTiles|(KingTileBitValue << 1)|(KingTileBitValue >>> 1);
        }
        if(Rank>0){
            if(File>0){
                PossibleMoveTiles=PossibleMoveTiles|(KingTileBitValue >>> 9)|(KingTileBitValue >>> 8);
            }
            else{
                PossibleMoveTiles=PossibleMoveTiles|(KingTileBitValue >>> 8);
            }
            if(File<7){
                PossibleMoveTiles=PossibleMoveTiles|(KingTileBitValue >>> 7);
            }
        }
        return PossibleMoveTiles;
    }
    public long KnightDirectionGenerator(com.mygdx.game.Nea.BitBoards BoardState, long KnightTileBitValue, boolean IsWhiteTurn) {
        long PossibleMoveTiles=0;
        int Rank=pos.Rank(KnightTileBitValue);
        int File=pos.File(KnightTileBitValue);
        if(Rank<6){
            if(File>0){
                PossibleMoveTiles=PossibleMoveTiles|KnightTileBitValue<<15;
            }
            if(File<7){
                PossibleMoveTiles=PossibleMoveTiles|KnightTileBitValue<<17;
            }
        }
        if(Rank>1) {
            if (File > 0) {
                PossibleMoveTiles = PossibleMoveTiles | KnightTileBitValue >>> 17;
            }
            if (File < 7) {
                PossibleMoveTiles = PossibleMoveTiles | KnightTileBitValue >>> 15;
            }
        }
        if(File<6){
            if(Rank<7){
                PossibleMoveTiles=PossibleMoveTiles|KnightTileBitValue<<10;
            }
            if(Rank>0){
                PossibleMoveTiles=PossibleMoveTiles|KnightTileBitValue>>>6;
            }
        }
        if(File>1){
            if(Rank>0){
                PossibleMoveTiles=PossibleMoveTiles|KnightTileBitValue>>>10;
            }
            if(Rank<7){
                PossibleMoveTiles=PossibleMoveTiles|KnightTileBitValue<<6;
            }
        }
        return PossibleMoveTiles;
    }
}
