package com.mygdx.game.Nea;

public class BoardEvaluation {
    PieceSquareTables PST=new PieceSquareTables();
    public float BoardValuator (com.mygdx.game.Nea.BitBoards BoardState){
        float BoardStateValue;
        float BoardStateValueBlack=0;
        float BoardStateValueWhite=0;
        BoardStateValueWhite = MaterialPieceValuator(BoardStateValueWhite, 300, BoardState.getWhiteKnight());
        BoardStateValueWhite = MaterialPieceValuator(BoardStateValueWhite, 325, BoardState.getWhiteBishop());
        BoardStateValueWhite = MaterialPieceValuator(BoardStateValueWhite, 500, BoardState.getWhiteRook());
        BoardStateValueWhite = MaterialPieceValuator(BoardStateValueWhite, 900, BoardState.getWhiteQueen());
        if(BoardStateValueWhite<=1400){
            BoardStateValueWhite=PositionalPieceValuator(BoardStateValueWhite,PST.getWhiteKingTableLate(),BoardState.getWhiteKing());
        }
        else{
            BoardStateValueWhite=PositionalPieceValuator(BoardStateValueWhite,PST.getWhiteKingTableEarly(),BoardState.getWhiteKing());
        }
        BoardStateValueWhite = MaterialPieceValuator(BoardStateValueWhite, 100, BoardState.getWhitePawn());

        BoardStateValueBlack = MaterialPieceValuator(BoardStateValueBlack, -300, BoardState.getBlackKnight());
        BoardStateValueBlack = MaterialPieceValuator(BoardStateValueBlack, -325, BoardState.getBlackBishop());
        BoardStateValueBlack = MaterialPieceValuator(BoardStateValueBlack, -500, BoardState.getBlackRook());
        BoardStateValueBlack = MaterialPieceValuator(BoardStateValueBlack, -900, BoardState.getBlackQueen());
        if(BoardStateValueBlack<=-1400){
            BoardStateValueBlack=PositionalPieceValuator(BoardStateValueBlack,PST.getBlackKingTableLate(),BoardState.getBlackKing());
        }
        else{
            BoardStateValueBlack=PositionalPieceValuator(BoardStateValueBlack,PST.getBlackKingTableEarly(),BoardState.getBlackKing());
        }
        BoardStateValueBlack = MaterialPieceValuator(BoardStateValueBlack, -100, BoardState.getBlackPawn());
        BoardStateValue=BoardStateValueBlack+BoardStateValueWhite;

        BoardStateValue=PositionalPieceValuator(BoardStateValue,PST.getWhitePawnTable(),BoardState.getWhitePawn());
        BoardStateValue=PositionalPieceValuator(BoardStateValue,PST.getWhiteRookTable(),BoardState.getWhiteRook());
        BoardStateValue=PositionalPieceValuator(BoardStateValue,PST.getWhiteBishopTable(),BoardState.getWhiteBishop());
        BoardStateValue=PositionalPieceValuator(BoardStateValue,PST.getWhiteKnightTable(),BoardState.getWhiteKnight());
        BoardStateValue=PositionalPieceValuator(BoardStateValue,PST.getWhiteQueenTable(),BoardState.getWhiteQueen());

        BoardStateValue=PositionalPieceValuator(BoardStateValue,PST.getBlackPawnTable(),BoardState.getBlackPawn());
        BoardStateValue=PositionalPieceValuator(BoardStateValue,PST.getBlackRookTable(),BoardState.getBlackRook());
        BoardStateValue=PositionalPieceValuator(BoardStateValue,PST.getBlackBishopTable(),BoardState.getBlackBishop());
        BoardStateValue=PositionalPieceValuator(BoardStateValue,PST.getBlackKnightTable(),BoardState.getBlackKnight());
        BoardStateValue=PositionalPieceValuator(BoardStateValue,PST.getBlackQueenTable(),BoardState.getBlackQueen());
        return BoardStateValue;
    }



    public float MaterialPieceValuator ( float BoardStateMaterialValue, float value, long BitBoard){
        int Bitcount = Long.bitCount(BitBoard);
        BoardStateMaterialValue = BoardStateMaterialValue + (Bitcount * value);
        return BoardStateMaterialValue;
    }



    public float PositionalPieceValuator(float BoardStatePositionalValue,float [] PiecesTable,long BitBoard){
        int range=Long.bitCount(BitBoard);
        for(int i=0;i<range;i++){
            long target=Long.lowestOneBit(BitBoard);
            BitBoard^=target;
            int Index=Long.numberOfTrailingZeros(target);
            BoardStatePositionalValue+=PiecesTable[Index];
        }
        return BoardStatePositionalValue;
    }
}
