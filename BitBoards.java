package com.mygdx.game.Nea;

public class BitBoards {
    long WhitePawn;
    long BlackPawn;
    long WhiteRook;
    long BlackRook;
    long WhiteKnight;
    long BlackKnight;
    long WhiteBishop;
    long BlackBishop;
    long WhiteQueen;
    long BlackQueen;
    long WhiteKing;
    long BlackKing;
    long WhitePieces;
    long BlackPieces;
    long BoardState;
    long FirstMove;
    long Promotion;


    public BitBoards(){
       // MiniMaxProof();
        Initialise();

    }

public void MiniMaxProof(){
    Promotion=0b1111111100000000000000000000000000000000000000000000000011111111L;
        BlackQueen=0b00000000000000000000010000000000000000000000000000000000000000000L;
        BlackBishop=0b00000000000000000000000000001000000000000000000000000000000000000L;
        BlackRook=0b0000000000000000000000000000000000000000000001000000000000000000L;
        BlackKing = 0b00000000000000000000100000000000000000000000000000000000000000000L;
        WhitePawn=0b0000000000000000000000000000000000000100000000000000000000000000L;
        WhiteRook=0b000000000000000000000000000000000000000000000000000000000100000000L;
    WhiteKnight=0b0000000000000000000000000000000000000000100000000000000000000010L;
    WhiteKing = 0b000000000000000000000000000000000000000000000000000000000000000001L;
    WhitePieces = WhitePawn | WhiteRook | WhiteKnight | WhiteBishop | WhiteQueen | WhiteKing;
    BlackPieces = BlackPawn | BlackRook | BlackKnight | BlackBishop | BlackQueen | BlackKing;
    BoardState = WhitePieces | BlackPieces;
}
    public void Initialise() {
        //initialising bitboards
        Promotion=0b1111111100000000000000000000000000000000000000000000000011111111L;
        FirstMove=  0b0000000011111111000000000000000000000000000000001111111100000000L;
        WhitePawn = 0b0000000000000000000000000000000000000000000000001111111100000000L;
        BlackPawn = 0b0000000011111111000000000000000000000000000000000000000000000000L;
        WhiteRook = 0b0000000000000000000000000000000000000000000000000000000010000001L;
        BlackRook = 0b1000000100000000000000000000000000000000000000000000000000000000L;
        WhiteKnight = 0b0000000000000000000000000000000000000000000000000000000001000010L;
        BlackKnight = 0b0100001000000000000000000000000000000000000000000000000000000000L;
        WhiteBishop = 0b0000000000000000000000000000000000000000000000000000000000100100L;
        BlackBishop = 0b0010010000000000000000000000000000000000000000000000000000000000L;
        WhiteQueen = 0b0000000000000000000000000000000000000000000000000000000000010000L;
        BlackQueen =0b0001000000000000000000000000000000000000000000000000000000000000L;
        WhiteKing = 0b00000000000000000000000000000000000000000000000000000000000001000L;
        BlackKing = 0b00000100000000000000000000000000000000000000000000000000000000000L;
        WhitePieces = WhitePawn | WhiteRook | WhiteKnight | WhiteBishop | WhiteQueen | WhiteKing;
        BlackPieces = BlackPawn | BlackRook | BlackKnight | BlackBishop | BlackQueen | BlackKing;
        BoardState = WhitePieces | BlackPieces;
    }
    public BitBoards(BitBoards bitboard){
        this.WhitePawn = bitboard.WhitePawn;
        this.BlackPawn = bitboard.BlackPawn;
        this.WhiteRook = bitboard.WhiteRook;
        this.BlackRook = bitboard.BlackRook;
        this.WhiteKnight = bitboard.WhiteKnight;
        this.BlackKnight = bitboard.BlackKnight;
        this.WhiteBishop = bitboard.WhiteBishop;
        this.BlackBishop = bitboard.BlackBishop;
        this.WhiteQueen = bitboard.WhiteQueen;
        this.BlackQueen = bitboard.BlackQueen;
        this.WhiteKing = bitboard.WhiteKing;
        this.BlackKing = bitboard.BlackKing;
        this.WhitePieces = bitboard.WhitePieces;
        this.BlackPieces = bitboard.BlackPieces;
        this.BoardState = bitboard.BoardState;
        this.FirstMove = bitboard.FirstMove;
    }
    public long getFirstMove() {
        return FirstMove;
    }
    public long getPromotion() {
        return Promotion;
    }
    public  long getWhitePawn() {
        return WhitePawn;
    }
    public  void setWhitePawn(long whitepawn) {
        WhitePawn = whitepawn;
    }
    public  long getBlackPawn() {
        return BlackPawn;
    }
    public  void setBlackPawn(long blackPawn) {
        BlackPawn = blackPawn;
    }
    public  long getWhiteRook() {
        return WhiteRook;
    }
    public  void setWhiteRook(long whiteRook) {
        WhiteRook = whiteRook;
    }
    public  long getBlackRook() {
        return BlackRook;
    }
    public  void setBlackRook(long blackRook) {
        BlackRook = blackRook;
    }

    public  long getWhiteKnight() {
        return WhiteKnight;
    }

    public  void setWhiteKnight(long whiteKnight) {
        WhiteKnight = whiteKnight;
    }

    public  long getBlackKnight() {
        return BlackKnight;
    }

    public  void setBlackKnight(long blackKnight) {
        BlackKnight = blackKnight;
    }

    public  long getWhiteBishop() {
        return WhiteBishop;
    }

    public  void setWhiteBishop(long whiteBishop) {
        WhiteBishop = whiteBishop;
    }

    public  long getBlackBishop() {
        return BlackBishop;
    }

    public  void setBlackBishop(long blackBishop) {
        BlackBishop = blackBishop;
    }

    public  long getWhiteQueen() {
        return WhiteQueen;
    }

    public  void setWhiteQueen(long whiteQueen) {
        WhiteQueen = whiteQueen;
    }

    public  long getBlackQueen() {
        return BlackQueen;
    }

    public  void setBlackQueen(long blackQueen) {
        BlackQueen = blackQueen;
    }

    public  long getWhiteKing() {
        return WhiteKing;
    }

    public  void setWhiteKing(long whiteKing) {
        WhiteKing = whiteKing;
    }

    public  long getBlackKing() {
        return BlackKing;
    }

    public  void setBlackKing(long blackKing) {
        BlackKing = blackKing;
    }
    public long getBoardState() {
        return BoardState;
    }

    public long getWhitePieces() {
        return WhitePieces;
    }

    public long getBlackPieces() {
        return BlackPieces;
    }
    public  void UpdateBoardState() {
        WhitePieces = WhitePawn | WhiteRook | WhiteKnight | WhiteBishop | WhiteQueen | WhiteKing;
        BlackPieces = BlackPawn | BlackRook | BlackKnight | BlackBishop | BlackQueen | BlackKing;
        BoardState = WhitePieces | BlackPieces;
    }
}
