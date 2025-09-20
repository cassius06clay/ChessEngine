package com.mygdx.game.Nea;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.Nea.Render;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;

public class ChessMain extends ApplicationAdapter {
    Texture WPawnTexture,BPawnTexture,WRookTexture,BRookTexture,WBishopTexture,BBishopTexture,WKnightTexture,BKnightTexture,WKingTexture,BKingTexture,WQueenTexture,BQueenTexture,BoardTexture,HomeBackground,HomeButton,Highlight;
    ShapeRenderer sr;
    SpriteBatch b;
    BitmapFont font;
    BitBoards BoardState;
    Render Render;
    Position Position;
    ChessAI AI;
    LegalMoveChecker lmc;
    BoardStateManipulator bsm;
    MoveGeneration mg;
    GameState gs;
    //PsuedoChecker PsuedoChecker;
    private boolean holding;
    private int CurrentPieceIndex;
    private int NewPieceIndex;
    private boolean WhiteTurn;
    private int PieceType;//dk
    private long CurrentTileBitValue;
    private long SelectedMove;
    private long CurrentBoard;
    boolean Bot;
    boolean IsAgainstPlayer;
    boolean PvE;
    boolean Home;
    int Depth;
    float ScreenHeight=800;
    float ScreenWidth=1200;
    float TileDiameter=83.33f;
    private final int King = 0;
    private final int Pawn = 1;
    private final int Rook = 2;
    private final int Bishop = 3;
    private final int Knight = 4;
    private final int Queen = 5;
    @Override
    public void create() {
    WPawnTexture= new Texture("WhitePawn.png");
    BPawnTexture= new Texture("BlackPawn.png");
    WRookTexture= new Texture("WhiteRook.png");
    BRookTexture= new Texture("BlackRook.png");
    WBishopTexture= new Texture("WhiteBishop.png");
    BBishopTexture= new Texture("BlackBishop.png");
    WKnightTexture= new Texture("WhiteKnight.png");
    BKnightTexture= new Texture("BlackKnight.png");
    WKingTexture= new Texture("WhiteKing.png");
    BKingTexture= new Texture("BlackKing.png");
    WQueenTexture= new Texture("WhiteQueen.png");
    BQueenTexture= new Texture("BlackQueen.png");
    BoardTexture=new Texture("Board.png");
    HomeBackground=new Texture("BlankBackground.png");
    HomeButton=new Texture("Home.png");
    Highlight=new Texture("Highlight.png");

    font=new BitmapFont();
    sr=new ShapeRenderer();
    b=new SpriteBatch();
    Render =new Render();
    BoardState =new BitBoards();
    AI=new ChessAI();
    lmc=new LegalMoveChecker();
    bsm=new BoardStateManipulator();
    mg=new MoveGeneration();
    gs=new GameState();
 //   Pieces =new Pieces();
   // PsuedoChecker =new PsuedoChecker();
    WhiteTurn=true;
    Position=new Position();
    Bot=true;
    IsAgainstPlayer=false;
    PvE=false;
    Home=true;
    font.setColor(Color.WHITE);
    Depth=3;
    }
    @Override
    public void render() {
        if(Home) {
            Home();
        }
        else if(IsAgainstPlayer){
            Inputcheck();
            Gamedraw();
        }
        else if(PvE){
            PvE();
            Gamedraw();
        }
/*

        BotTest();
        Gamedraw();*/
    }

    public void Home() {
        ScreenUtils.clear(Color.WHITE);
        b.begin();
        b.draw(HomeBackground, 0, 0, ScreenWidth, ScreenHeight);
        font.setColor(Color.ORANGE);
        font.getData().setScale(10f);
        font.draw(b,"CHESS",350,600);
        font.getData().setScale(4f);
        font.draw(b,"Player",350,340);
        font.draw(b,"MiniMax",700,340);
        font.draw(b,"1",680,200);
        font.draw(b,"2",755,200);
        font.draw(b,"3",830,200);
        font.draw(b,"4",905,200);
        font.draw(b, "EXIT", 1000, 720);
        b.end();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.BROWN);

        sr.rect(250,200,300,5);
        sr.rect(250,395,300,5);
        sr.rect(250,200,5,200);
        sr.rect(550,200,5,200);

        sr.rect(650,200,300,5);
        sr.rect(650,395,300,5);
        sr.rect(650,200,5,200);
        sr.rect(950,200,5,200);

        sr.rect(650,150,300,5);
        sr.rect(650,150,5,50);
        sr.rect(950,150,5,50);
        sr.rect(800,150,5,50);
        sr.rect(725,150,5,50);
        sr.rect(875,150,5,50);
        sr.setColor(Color.BLACK);

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if(Gdx.input.getX()>250&&Gdx.input.getX()<551){
                if(ScreenHeight-Gdx.input.getY()>199&&ScreenHeight-Gdx.input.getY()<401){
                    IsAgainstPlayer=true;
                    PvE=false;
                    Home=false;
                }
            }
            else if(Gdx.input.getX()>650&&Gdx.input.getX()<951){
                if(ScreenHeight-Gdx.input.getY()>199&&ScreenHeight-Gdx.input.getY()<401){
                    PvE=true;
                    IsAgainstPlayer=false;
                    Home=false;
                }
                else if(ScreenHeight-Gdx.input.getY()>149&&ScreenHeight-Gdx.input.getY()<201){
                    if(Gdx.input.getX()>650&&Gdx.input.getX()<726){
                        Depth=1;
                        sr.rect(650,150,75,50);

                    }
                    else if(Gdx.input.getX()>650&&Gdx.input.getX()<801){
                        Depth=2;
                        sr.rect(725,150,75,50);
                    }
                    else if(Gdx.input.getX()>650&&Gdx.input.getX()<876){
                        Depth=3;
                        sr.rect(800,150,75,50);
                    }
                    else{
                        Depth=4;
                        sr.rect(875,150,75,50);
                    }
                }
            }
             if(Gdx.input.getX()>1000&&Gdx.input.getX()<1120){
                if(ScreenHeight-Gdx.input.getY()>670&&ScreenHeight-Gdx.input.getY()<710){
                    Gdx.app.exit();
                }
            }
        }
        sr.end();
    }
    public void PvE(){
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (Gdx.input.getX() > 39 && Gdx.input.getX() < 141) {
                if (ScreenHeight - Gdx.input.getY() > 659 && ScreenHeight - Gdx.input.getY() < 761) {
                    Home = true;
                    PvE = false;
                    holding = false;
                    WhiteTurn = true;
                    BoardState = new BitBoards();
                }
            }
            if(Gdx.input.getX()>1000&&Gdx.input.getX()<1120){
                if(ScreenHeight-Gdx.input.getY()>670&&ScreenHeight-Gdx.input.getY()<710){
                    Gdx.app.exit();
                }
            }
        }
        if (WhiteTurn) {
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                int MouseX = Gdx.input.getX();
                int MouseY = Gdx.input.getY();
                if (MouseX > 267&&MouseX<930) {
                    if (MouseY > 67&&MouseY<730) {
                        System.out.println(MouseX);
                        if (!holding) {
                            CurrentPieceIndex = Position.IndexFinder(MouseX, MouseY, 267, 67, 83);
                            CurrentTileBitValue = 1L << CurrentPieceIndex;
                            PieceSelector();

                        } else {//holding a piece
                            NewPieceIndex = Position.IndexFinder(Gdx.input.getX(), Gdx.input.getY(), 267, 67, 83);
                            SelectedMove = 1L << NewPieceIndex;
                            if (lmc.LegalMove(CurrentTileBitValue, SelectedMove, PieceType, WhiteTurn, BoardState)) {
                                bsm.MovementType(BoardState, WhiteTurn, SelectedMove, CurrentTileBitValue, PieceType, BoardState.getPromotion());
                                holding = false;
                                WhiteTurn = !WhiteTurn;
                            } else {
                                holding = false;
                            }
                        }
                    }
                }
            }
        }
        else{
            long StartTime=System.currentTimeMillis();
            Object[] values=AI.MiniMax(BoardState,gs,false,Depth,Depth,Float.MIN_VALUE,Float.MAX_VALUE);
            long EndTime=System.currentTimeMillis();
            System.out.println("Minimax Run Time was "+((float)(EndTime-StartTime)/1000)+"s");
            System.out.println("The best evaluation for "+WhiteTurn+" was "+values[0]);
            if ((long)values[1]!=0L&&(long)values[2] != 0) {
                if (!holding) {
                    CurrentTileBitValue = (long) values[1];
                    PieceSelector();
                }
            }
            if(holding){//holding a piece
                SelectedMove = (long) values[2];
                if (lmc.LegalMove(CurrentTileBitValue, SelectedMove, PieceType, WhiteTurn,BoardState)) {
                    bsm.MovementType(BoardState,WhiteTurn,SelectedMove,CurrentTileBitValue,PieceType,BoardState.getPromotion());
                    holding=false;
                    WhiteTurn=!WhiteTurn;
                }
                else{
                    holding=false;
                }
            }
        }
    }
    public void BotTest() {
        long StartTime = System.currentTimeMillis();
        Object[] values = AI.MiniMax(BoardState,gs, WhiteTurn, Depth, Depth, Float.MIN_VALUE, Float.MAX_VALUE);
        long EndTime = System.currentTimeMillis();
        //System.out.println("Minimax Run Time was " + ((float) (EndTime - StartTime) / 1000) + "s and on " + WhiteTurn + " decided to a " + PieceType + " from " + Long.numberOfTrailingZeros((Long) values[1]) + " Too " + Long.numberOfTrailingZeros((Long) values[2]));
        if ((long)values[1]!=0L&&(long)values[2] != 0){
            if (!holding) {
                CurrentTileBitValue = (long) values[1];
                PieceSelector();
            }
        if (holding) {//holding a piece
            SelectedMove = (long) values[2];
            if (lmc.LegalMove(CurrentTileBitValue, SelectedMove, PieceType, WhiteTurn, BoardState)) {
                bsm.MovementType(BoardState, WhiteTurn, SelectedMove, CurrentTileBitValue, PieceType, BoardState.getPromotion());
                holding = false;
                WhiteTurn = !WhiteTurn;
            } else {
                holding = false;

            }
        }
    }
        else{

        }

    }
    public void PieceSelector(){
        if (WhiteTurn) {
            if ((CurrentTileBitValue & BoardState.getWhitePieces()) != 0) {
                if ((CurrentTileBitValue & BoardState.getWhitePawn()) != 0) {
                    PieceType = 1;
                    CurrentBoard = BoardState.getWhitePawn();
                    holding = true;
                } else if ((CurrentTileBitValue & BoardState.getWhiteQueen()) != 0) {
                    PieceType = 5;
                    CurrentBoard = BoardState.getWhiteQueen();
                    holding = true;
                } else if ((CurrentTileBitValue & BoardState.getWhiteKnight()) != 0) {
                    PieceType = 4;
                    CurrentBoard = BoardState.getWhiteKnight();
                    holding = true;
                } else if ((CurrentTileBitValue & BoardState.getWhiteRook()) != 0) {
                    PieceType = 2;
                    CurrentBoard = BoardState.getWhiteRook();
                    holding = true;
                } else if ((CurrentTileBitValue & BoardState.getWhiteBishop()) != 0) {
                    PieceType = 3;
                    CurrentBoard = BoardState.getWhiteBishop();
                    holding = true;
                } else if ((CurrentTileBitValue & BoardState.getWhiteKing()) != 0) {//if its a king selected#
                    PieceType = 0;
                    CurrentBoard = BoardState.getWhiteKing();
                    holding = true;
                }
            }
        } else {//blacks turn
            if ((CurrentTileBitValue & BoardState.getBlackPieces()) != 0) {//checks your grabbing your piece
                if ((CurrentTileBitValue & BoardState.getBlackPawn()) != 0) {
                    PieceType = 1;
                    CurrentBoard = BoardState.getBlackPawn();
                    holding = true;
                } else if ((CurrentTileBitValue & BoardState.getBlackQueen()) != 0) {
                    PieceType = 5;
                    CurrentBoard = BoardState.getBlackQueen();
                    holding = true;
                    //   ("black queen");
                } else if ((CurrentTileBitValue & BoardState.getBlackKnight()) != 0) {
                    PieceType = 4;
                    CurrentBoard = BoardState.getBlackKnight();
                    holding = true;
                } else if ((CurrentTileBitValue & BoardState.getBlackRook()) != 0) {
                    PieceType = 2;
                    CurrentBoard = BoardState.getBlackRook();
                    holding = true;
                } else if ((CurrentTileBitValue & BoardState.getBlackBishop()) != 0) {
                    PieceType = 3;
                    CurrentBoard = BoardState.getBlackBishop();
                    holding = true;
                } else if ((CurrentTileBitValue & BoardState.getBlackKing()) != 0) {//if its a king selected
                    PieceType = 0;
                    CurrentBoard = BoardState.getBlackKing();
                    holding = true;
                }
            }
        }
    }
    public void Inputcheck() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {

            if (Gdx.input.getX() > 39 && Gdx.input.getX() < 141) {
                if (ScreenHeight - Gdx.input.getY() > 659 && ScreenHeight - Gdx.input.getY() < 761) {
                    Home = true;
                    IsAgainstPlayer = false;
                    holding = false;
                    WhiteTurn = true;
                    BoardState = new BitBoards();
                }
            } else if (Gdx.input.getX() > 1000 && Gdx.input.getX() < 1120) {
                if (ScreenHeight - Gdx.input.getY() > 670 && ScreenHeight - Gdx.input.getY() < 710) {
                    Gdx.app.exit();
                }
            } else if (Gdx.input.getX() > 267 && Gdx.input.getX() < 930) {
                if (Gdx.input.getY() > 67 && Gdx.input.getY() < 730) {
                    if (!holding) {
                        CurrentPieceIndex = Position.IndexFinder(Gdx.input.getX(), Gdx.input.getY(), 267, 67, 83);
                        CurrentTileBitValue = 1L << CurrentPieceIndex;
                        PieceSelector();

                    } else {//holding a piece
                        NewPieceIndex = Position.IndexFinder(Gdx.input.getX(), Gdx.input.getY(), 267, 67, 83);
                        SelectedMove = 1L << NewPieceIndex;
                        if (lmc.LegalMove(CurrentTileBitValue, SelectedMove, PieceType, WhiteTurn, BoardState)) {
                            bsm.MovementType(BoardState, WhiteTurn, SelectedMove, CurrentTileBitValue, PieceType, BoardState.getPromotion());
                            holding = false;
                            WhiteTurn = !WhiteTurn;
                            gs.TerminalStates(WhiteTurn, BoardState);

                        } else {
                            System.out.println("Illegal");
                            holding = false;
                        }
                    }
                }
            }
        }
    }
    public void Gamedraw() {
        if (Gdx.input.isButtonJustPressed(Input.Keys.B)) {
            Bot = true;
        }
        if (Gdx.input.isButtonJustPressed(Input.Keys.P)) {
            Bot = false;
        }
        b.begin();
        b.draw(BoardTexture, 0, 0, ScreenWidth, ScreenHeight);
        b.draw(HomeButton, 40, 660, 100, 100);
        font.setColor(Color.ORANGE);
        font.getData().setScale(4f);
        font.draw(b, "EXIT", 1000, 720);
        BoardPrinter(BoardState.getWhitePawn(), WPawnTexture);
        BoardPrinter(BoardState.getBlackPawn(), BPawnTexture);
        BoardPrinter(BoardState.getWhiteRook(), WRookTexture);
        BoardPrinter(BoardState.getBlackRook(), BRookTexture);
        BoardPrinter(BoardState.getWhiteBishop(), WBishopTexture);
        BoardPrinter(BoardState.getBlackBishop(), BBishopTexture);
        BoardPrinter(BoardState.getWhiteKnight(), WKnightTexture);
        BoardPrinter(BoardState.getBlackKnight(), BKnightTexture);
        BoardPrinter(BoardState.getWhiteKing(), WKingTexture);
        BoardPrinter(BoardState.getBlackKing(), BKingTexture);
        BoardPrinter(BoardState.getWhiteQueen(), WQueenTexture);
        BoardPrinter(BoardState.getBlackQueen(), BQueenTexture);
        b.end();
        if(WhiteTurn){
            if(holding){
                ArrayList<Object[]> TempPieceList = new ArrayList<>();
                b.begin();
                if (PieceType==Pawn) {
                   b.draw(WPawnTexture,Gdx.input.getX(),ScreenHeight-Gdx.input.getY(),40,40);
                   mg.MoveGenerator(TempPieceList,CurrentTileBitValue,Pawn,true,BoardState);
                    if(!TempPieceList.isEmpty()) {
                        Object[] PieceData = TempPieceList.get(0);
                        BoardPrinter((Long) PieceData[0], Highlight);
                    }
                }
                else if (PieceType==Bishop) {
                    b.draw(WBishopTexture,Gdx.input.getX(),ScreenHeight-Gdx.input.getY(),40,40);
                    mg.MoveGenerator(TempPieceList,CurrentTileBitValue,Bishop,true,BoardState);
                    if(!TempPieceList.isEmpty()) {
                        Object[] PieceData = TempPieceList.get(0);
                        BoardPrinter((Long) PieceData[0], Highlight);
                    }
                }
                else if (PieceType==Rook) {
                    b.draw(WRookTexture,Gdx.input.getX(),ScreenHeight-Gdx.input.getY(),40,40);
                    mg.MoveGenerator(TempPieceList,CurrentTileBitValue,Rook,true,BoardState);
                    if(!TempPieceList.isEmpty()) {
                        Object[] PieceData = TempPieceList.get(0);
                        BoardPrinter((Long) PieceData[0], Highlight);
                    }
                }
                else if (PieceType==Knight) {
                    b.draw(WKnightTexture,Gdx.input.getX(),ScreenHeight-Gdx.input.getY(),40,40);
                    mg.MoveGenerator(TempPieceList,CurrentTileBitValue,Knight,true,BoardState);
                    if(!TempPieceList.isEmpty()) {
                        Object[] PieceData = TempPieceList.get(0);
                        BoardPrinter((Long) PieceData[0], Highlight);
                    }
                }
                else if (PieceType==Queen) {
                    b.draw(WQueenTexture,Gdx.input.getX(),ScreenHeight-Gdx.input.getY(),40,40);
                    mg.MoveGenerator(TempPieceList,CurrentTileBitValue,Queen,true,BoardState);
                    if(!TempPieceList.isEmpty()) {
                        Object[] PieceData = TempPieceList.get(0);
                        BoardPrinter((Long) PieceData[0], Highlight);
                    }
                }
                else if (PieceType==King) {
                    b.draw(WKingTexture,Gdx.input.getX(),ScreenHeight-Gdx.input.getY(),40,40);
                    mg.MoveGenerator(TempPieceList,CurrentTileBitValue,King,true,BoardState);
                    if(!TempPieceList.isEmpty()) {
                        Object[] PieceData = TempPieceList.get(0);
                        BoardPrinter((Long) PieceData[0], Highlight);
                    }
                }
                b.end();
            }
        }
        else{
            if(holding){
                ArrayList<Object[]> TempPieceList = new ArrayList<>();
                b.begin();
                if (PieceType==Pawn) {
                    b.draw(BPawnTexture,Gdx.input.getX(),ScreenHeight-Gdx.input.getY(),40,40);
                    mg.MoveGenerator(TempPieceList,CurrentTileBitValue,Pawn,false,BoardState);
                    if(!TempPieceList.isEmpty()) {
                        Object[] PieceData = TempPieceList.get(0);
                        BoardPrinter((Long) PieceData[0], Highlight);
                    }
                }
                else if (PieceType==Bishop) {
                    b.draw(BBishopTexture,Gdx.input.getX(),ScreenHeight-Gdx.input.getY(),40,40);
                    mg.MoveGenerator(TempPieceList,CurrentTileBitValue,Bishop,false,BoardState);
                    if(!TempPieceList.isEmpty()) {
                        Object[] PieceData = TempPieceList.get(0);
                        BoardPrinter((Long) PieceData[0], Highlight);
                    }
                }
                else if (PieceType==Rook) {
                    b.draw(BRookTexture,Gdx.input.getX(),ScreenHeight-Gdx.input.getY(),40,40);
                    mg.MoveGenerator(TempPieceList,CurrentTileBitValue,Rook,false,BoardState);
                    if(!TempPieceList.isEmpty()) {
                        Object[] PieceData = TempPieceList.get(0);
                        BoardPrinter((Long) PieceData[0], Highlight);
                    }
                }
                else if (PieceType==Knight) {
                    b.draw(BKnightTexture,Gdx.input.getX(),ScreenHeight-Gdx.input.getY(),40,40);
                    mg.MoveGenerator(TempPieceList,CurrentTileBitValue,Knight,false,BoardState);
                    if(!TempPieceList.isEmpty()) {
                        Object[] PieceData = TempPieceList.get(0);
                        BoardPrinter((Long) PieceData[0], Highlight);
                    }
                }

                else if (PieceType==Queen) {
                    b.draw(BQueenTexture,Gdx.input.getX(),ScreenHeight-Gdx.input.getY(),40,40);
                    mg.MoveGenerator(TempPieceList,CurrentTileBitValue,Queen,false,BoardState);
                    if(!TempPieceList.isEmpty()) {
                        Object[] PieceData = TempPieceList.get(0);
                        BoardPrinter((Long) PieceData[0], Highlight);
                    }
                }
                else if (PieceType==King) {
                    b.draw(BKingTexture,Gdx.input.getX(),ScreenHeight-Gdx.input.getY(),40,40);
                    mg.MoveGenerator(TempPieceList,CurrentTileBitValue,King,false,BoardState);
                    if(!TempPieceList.isEmpty()) {
                    Object[] PieceData = TempPieceList.get(0);
                    BoardPrinter((Long) PieceData[0], Highlight);
                    }
                }
                b.end();
            }
        }
        if(gs.TerminalStates(WhiteTurn,BoardState)==1){
            b.begin();
            font.getData().setScale(5f);
            font.draw(b,"CHECKMATE",370,500);
            if(WhiteTurn){
                font.draw(b,"BLACK WINS",370,400);
            }
            else{
                font.draw(b,"WHITE WINS",370,400);
            }
            b.end();
        }
        else if(gs.TerminalStates(WhiteTurn,BoardState)==2){
            b.begin();
            font.getData().setScale(5f);
            font.draw(b,"STALEMATE",400,450);
            b.end();
        }
    }

    public void BoardPrinter(long Board, Texture texture) {
        long PrintCount=Long.bitCount(Board);
            for (int i = 0; i < PrintCount; i++) {
                long CurrentPrint=Long.lowestOneBit(Board);
                Board^=CurrentPrint;
                   Render.CoordFinder(CurrentPrint);
                   b.draw(texture,267 + (TileDiameter * (7-Render.getRank())),67f + (Render.getFile() * TileDiameter),TileDiameter,TileDiameter);
      }
    }
}

