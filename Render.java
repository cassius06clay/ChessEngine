package com.mygdx.game.Nea;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class Render {
    int bb_index;
    int file;
    int rank;
    BitBoards b;

    public Render() {
        b=new BitBoards();
    }


    public void DrawBoard(ShapeRenderer sr) {
     //   System.out.println("board render");
        sr.setColor(240 / 255f, 217 / 255f, 181 / 255f, 1f);

        sr.rect(240, 40, 720, 720);
        sr.rect(40, 160, 166, 507);
        sr.rect(993, 160, 166, 507);
// outlines

        sr.setColor(180 / 255f, 135 / 255f, 98 / 255f, 1f);
        sr.rect(240, 40, 720, 26.67f);
        sr.rect(240, 733.33f, 720, 26.67f);
        sr.rect(240, 40, 26.67f, 720);
        sr.rect(933.33f, 40, 26.67f, 720);


       // sr.setColor(180 / 255f, 135 / 255f, 98 / 255f, 1f);
        sr.rect(40, 160, 166, 26.67f);
        sr.rect(40, 640, 166, 26.67f);
        sr.rect(40, 160, 26.67f, 507);
        sr.rect(180, 160, 26.67f, 507);
        sr.rect(993, 160, 166, 26.67f);
        sr.rect(993, 640, 166, 26.67f);
        sr.rect(993, 160, 26.67f, 507);
        sr.rect(1133.33f, 160, 26.67f, 507);
// checker pattern loop
        for (int i = 0; i < 8; i++) {
            if (i % 2 != 0) {
                for (int l = 0; l < 4; l++) {
                      sr.rect((266.67f + (166.67f * l)), ((83.33f * i)-16.67f), 83.33f, 83.33f);
                }
            } else {
                for (int j = 0; j < 4; j++) {
                  //  sr.setColor(Color.WHITE);
                    sr.rect((350 + (166.67f * j)), (150 + (83.33f * i)), 83.33f, 83.33f);
                }
            }
        }
        sr.setColor(240 / 255f, 217 / 255f, 181 / 255f, 1f);
        sr.rect(266.67f, 66.67f, 666.67f, 1f);
        sr.rect(266.67f, 733.33f, 666.67f, 1f);
        sr.rect(266.67f, 66.67f, 1, 666.67f);
        sr.rect(933.33f, 66.67f, 1, 666.67f);
    }

    public void CoordFinder(long mask) {
             bb_index = Long.numberOfTrailingZeros(mask);
             file = bb_index>>3;
             rank = bb_index&7;
        }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
