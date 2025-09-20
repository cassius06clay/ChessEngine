package com.mygdx.game.Nea;

public class PieceSquareTables {
    float[]WhitePawnTable;
    float[]WhiteRookTable;
    float[]WhiteBishopTable;
    float[]WhiteKnightTable;
    float[]WhiteQueenTable;
    float[]WhiteKingTableEarly;
    float[]WhiteKingTableLate;


    float[]BlackPawnTable;
    float[]BlackRookTable;
    float[]BlackBishopTable;
    float[]BlackKnightTable;
    float[]BlackQueenTable;
    float[]BlackKingTable;
    float[]BlackKingTableEarly;
    float[]BlackKingTableLate;

    public PieceSquareTables(){
        WhitePawnTable= new float[]{
            0,  0,  0,  0,  0,  0,  0,  0,
            50, 50, 50, 50, 50, 50, 50, 50,
            10, 10, 20, 30, 30, 20, 10, 10,
            5,  5, 10, 25, 25, 10,  5,  5,
            0,  0,  0, 20, 20,  0,  0,  0,
            5, -5,-10,  0,  0,-10, -5,  5,
            5, 10, 10,-20,-20, 10, 10,  5,
            0,  0,  0,  0,  0,  0,  0,  0
        };

        WhiteRookTable= new float[]{
            0,  0,  0,  0,  0,  0,  0,  0,
            5, 10, 10, 10, 10, 10, 10,  5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            0,  0,  0,  5,  5,  0,  0,  0
        };

        WhiteBishopTable= new float[]{
            -20,-10,-10,-10,-10,-10,-10,-20,
            -10,  0,  0,  0,  0,  0,  0,-10,
            -10,  0,  5, 10, 10,  5,  0,-10,
            -10,  5,  5, 10, 10,  5,  5,-10,
            -10,  0, 10, 10, 10, 10,  0,-10,
            -10, 10, 10, 10, 10, 10, 10,-10,
            -10,  5,  0,  0,  0,  0,  5,-10,
            -20,-10,-10,-10,-10,-10,-10,-20
        };

        WhiteKnightTable= new float[]{
            -50,-40,-30,-30,-30,-30,-40,-50,
            -40,-20,  0,  0,  0,  0,-20,-40,
            -30,  0, 10, 15, 15, 10,  0,-30,
            -30,  5, 15, 20, 20, 15,  5,-30,
            -30,  0, 15, 20, 20, 15,  0,-30,
            -30,  5, 10, 15, 15, 10,  5,-30,
            -40,-20,  0,  5,  5,  0,-20,-40,
            -50,-40,-30,-30,-30,-30,-40,-50
        };

        WhiteQueenTable= new float[]{
            -20,-10,-10, -5, -5,-10,-10,-20,
            -10,  0,  0,  0,  0,  0,  0,-10,
            -10,  0,  5,  5,  5,  5,  0,-10,
            -5,  0,  5,  5,  5,  5,  0, -5,
            0,  0,  5,  5,  5,  5,  0, -5,
            -10,  5,  5,  5,  5,  5,  0,-10,
            -10,  0,  5,  0,  0,  0,  0,-10,
            -20,-10,-10, -5, -5,-10,-10,-20
        };

        WhiteKingTableEarly = new float[]{
            -20, -30, -30, -40, -40, -30, -30, -20,
            -20, -30, -30, -40, -40, -30, -30, -20,
            -20, -30, -30, -40, -40, -30, -30, -20,
            -20, -30, -30, -40, -40, -30, -30, -20,
            -10, -20, -20, -30, -30, -20, -20, -10,
            0, -10, -10, -20, -20, -10, -10,   0,
            5,   5,   0,   0,   0,   0,   5,   5,
            5,  10,   5,   0,   0,   5,  10,   5
        };

        WhiteKingTableLate = new float[]{
            -10, -8, -8, -5, -5, -8, -8, -10,
            -8,   0,  0,  3,  3,  0,  0,  -8,
            -8,   0,  5,  5,  5,  5,  0,  -8,
            -5,   3,  5, 10, 10,  5,  3,  -5,
            -5,   3,  5, 10, 10,  5,  3,  -5,
            -8,   0,  5,  5,  5,  5,  0,  -8,
            -8,   0,  0,  3,  3,  0,  0,  -8,
            -10, -8, -8, -5, -5, -8, -8, -10
        };

        BlackPawnTable= new float[]{
            0,  0,  0,  0,  0,  0,  0,  0,
            -5, -10, -10, 20, 20, -10, -10, -5,
            -5, 5,  10,  0,  0,  10,  5, -5,
            0,0,  0, -20, -20,  0,  0,  0,
            -5,-5, -10, -25, -25, -10, -5, -5,
            -10,-10, -20, -30, -30, -20, -10, -10,
            -50,-50, -50, -50, -50, -50, -50, -50,
            0,  0,  0,  0,  0,  0,  0,  0
            };

        BlackRookTable= new float[]{
            0,  0,  0, -5, -5,  0,  0,  0,
            5,  0,  0,  0,  0,  0,  0,  5,
            5,  0,  0,  0,  0,  0,  0,  5,
            5,  0,  0,  0,  0,  0,  0,  5,
            5,  0,  0,  0,  0,  0,  0,  5,
            5,  0,  0,  0,  0,  0,  0,  5,
            -5,-10,-10,-10,-10,-10,-10,-5,
            0,  0,  0,  0,  0,  0,  0,  0
        };

        BlackBishopTable= new float[]{
            20,  10,  10,  10,  10,  10,  10,  20,
            10,  -5,   0,   0,   0,   0,  -5,  10,
            10, -10, -10, -10, -10, -10, -10,  10,
            10,   0, -10, -10, -10, -10,   0,  10,
            10,  -5,  -5, -10, -10,  -5,  -5,  10,
            10,   0,  -5, -10, -10,  -5,   0,  10,
            10,   0,   0,   0,   0,   0,   0,  10,
            20,  10,  10,  10,  10,  10,  10,  20
        };

        BlackKnightTable= new float[]{
            50,  40,  30,  30,  30,  30,  40,  50,
            40,  20,  0, -5, -5,  0,  20,  40,
            30, -5, -10, -15, -15, -10, -5,  30,
            30,  0, -15, -20, -20, -15,  0,  30,
            30, -5, -15, -20, -20, -15,  5,  30,
            30,  0, -10, -15, -15, -10,  0,  30,
            40,  20,  0,  0,  0,  0,  20,  40,
            50,  40,  30,  30,  30,  30,  40,  50
        };

        BlackQueenTable= new float[]{
            20,  10,  10,  5,   5,  10,  10,  20,
            10,   0,  -5,  0,   0,   0,   0,  10,
            10,  -5,  -5, -5,  -5,  -5,   0,  10,
            5,   0,  -5, -5,  -5,  -5,   0,   5,
            0,   0,  -5, -5,  -5,  -5,   0,   5,
            10,   0,  -5, -5,  -5,  -5,   0,  10,
            10,   0,   0,  0,   0,   0,   0,  10,
            20,  10,  10,  5,   5,  10,  10,  20
        };
        BlackKingTableLate = new float[]{
            10, 8, 8, 5, 5, 8, 8, 10,
            8,   0,  0, -3, -3,  0,  0,   8,
            8,   0, -5, -5, -5, -5,  0,   8,
            5,  -3, -5, -10,-10, -5, -3,   5,
            5,  -3, -5, -10,-10, -5, -3,   5,
            8,   0, -5, -5, -5, -5,  0,   8,
            8,   0,  0, -3, -3,  0,  0,   8,
            10,  8,  8,  5,  5,  8,  8,  10
        };
        BlackKingTableEarly = new float[]{
            -5, -10, -5, 0, 0, -5, -10, -5,
            -5,  -5,  0,  0,  0,  0,  -5,  -5,
            0,  10,  10,  20,  20,  10,  10,  0,
            10,  20,  20,  30,  30,  20,  20,  10,
            20,  30,  30,  40,  40,  30,  30,  20,
            20,  30,  30,  40,  40,  30,  30,  20,
            20,  30,  30,  40,  40,  30,  30,  20,
            20,  30,  30,  40,  40,  30,  30,  20
        };
    }
    public float[] getWhitePawnTable() {
        return WhitePawnTable;
    }

    public float[] getWhiteRookTable() {
        return WhiteRookTable;
    }

    public float[] getWhiteBishopTable() {
        return WhiteBishopTable;
    }

    public float[] getWhiteKnightTable() {
        return WhiteKnightTable;
    }

    public float[] getWhiteQueenTable() {
        return WhiteQueenTable;
    }

    public float[] getBlackPawnTable() {
        return BlackPawnTable;
    }

    public float[] getBlackRookTable() {
        return BlackRookTable;
    }

    public float[] getBlackBishopTable() {
        return BlackBishopTable;
    }

    public float[] getBlackKnightTable() {
        return BlackKnightTable;
    }

    public float[] getBlackQueenTable() {
        return BlackQueenTable;
    }

    public float[] getBlackKingTable() {
        return BlackKingTable;
    }
    public float[] getBlackKingTableLate() {
        return BlackKingTableLate;
    }

    public float[] getBlackKingTableEarly() {
        return BlackKingTableEarly;
    }

    public float[] getWhiteKingTableLate() {
        return WhiteKingTableLate;
    }

    public float[] getWhiteKingTableEarly() {
        return WhiteKingTableEarly;
    }
}
