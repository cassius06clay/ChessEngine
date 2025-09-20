package com.mygdx.game.Nea;

public class Position {
    int mouseFile;
    int mouseRank;
    int mouseIndex;

    public int IndexFinder(int mouseX, int mouseY, int mouseDiffX, int mouseDiffY, int Width) {
        mouseFile =(7-((mouseX - mouseDiffX) / Width));
        mouseRank =(7-((mouseY - mouseDiffY) / Width));
        mouseIndex = (mouseRank * 8) + mouseFile;

        return mouseIndex;
    }
    public int File(long PieceValue) {
        return Math.abs(Long.numberOfTrailingZeros(PieceValue) % 8);
    }

    public int Rank(long PieceValue) {
        return (Long.numberOfTrailingZeros(PieceValue) / 8);
    }
}
