package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


import static comp1110.ass2.TwistGame.*;

/**
 * A very simple viewer for piece placements in the twist game.
 *
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
public class Viewer extends Application {

    /* board layout */
    private static final int MARGIN_Y = 60;
    private static final int SQUARE_SIZE = 60;
    private static final int VIEWER_WIDTH = 955;
    private static final int VIEWER_HEIGHT = 720;
    private static final int BOAED_FitWidth = 9 * SQUARE_SIZE;
    private static final int BOAED_FitHeight = 5 * SQUARE_SIZE;
    private static final int BOARD_X = 180 ;
    private static final double PEG_X = BOARD_X+SQUARE_SIZE*1.5;

    private static final String URI_BASE = "assets/";
    private static final String BASEBOARD_URI = Viewer.class.getResource(URI_BASE + "board.png").toString();


    private final Group root = new Group();
    private final Group controls = new Group();
    TextField textField;

    /* board*/
    private final Group board=new Group();

    /* peg */
    private final  Group peg=new Group();

    /* pieces */
    private final  Group piece=new Group();

    /*message*/
    private final Text wrongInput = new Text("Wrong Input!");


    char[] pegs={'i','j','j','k','k','l','l'};
    char[] pieces={'a','b','c','d','e','f','g','h'};
    /**
     * Create the message to be displayed when the player wrongInput.
     */

    private void makeWrongInput() {
        wrongInput.setFill(Color.RED);
        wrongInput.setCache(true);
        wrongInput.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));
        wrongInput.setLayoutX(30);
        wrongInput.setLayoutY(VIEWER_HEIGHT-20);
        wrongInput.setTextAlignment(TextAlignment.CENTER);
        root.getChildren().add(wrongInput);
    }
    /**
     * Show the  wrongInput message
     */
    private void showwrongInput() {
        wrongInput.toFront();
        wrongInput.setOpacity(1);
    }
    /**
     * Hide the wrongInput message
     */
    private void hidewrongInput() {
        wrongInput.toBack();
        wrongInput.setOpacity(0);
    }

//FIXME this method can be more general to print message.....

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement  A valid placement string
     */
    void makePlacement(String placement) {
        if (isPlacementStringWellFormed(placement)){
            hidewrongInput();
            System.out.println(placement);
            char [][] decode=decodeTotype_position(placement);
            for (int i = 0; i <decode.length ; i++) {
                    if (isPeg(decode[i][0])){
                        int X=BOARD_X+SQUARE_SIZE*(decode[i][1]-'1'+1);
                        int Y=MARGIN_Y+SQUARE_SIZE*(decode[i][2]-'A'+1);
                        int index=getGroupIndex(decode[i][0]);
                        Peg Peg_change=new Peg(decode[i][0],X,Y);
                        peg.getChildren().set(index,Peg_change);
                    }

                    else {
                        int X= BOARD_X+SQUARE_SIZE*(decode[i][1]-'1'+1);
                        int Y=MARGIN_Y+SQUARE_SIZE*(decode[i][2]-'A'+1);
                        int Z=decode[i][3]-'0';
                        Pieces Piece_change=new Pieces(decode[i][0],X,Y,Z);
                        piece.getChildren().set(decode[i][0]-'a',Piece_change);
                    }

            }
        }
        else {
            makeWrongInput();
            showwrongInput();
        }
        // FIXME Task 4: implement the simple placement viewer
    }


    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() throws Exception{
        Label label1 = new Label("Placement:");
        textField = new TextField ();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        Button button2 = new Button("Clear");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    reset();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button,button2);
        hb.setSpacing(10);
        hb.setLayoutX(VIEWER_WIDTH/3.5);
        hb.setLayoutY(VIEWER_HEIGHT - 30);
        controls.getChildren().add(hb);
    }

    /**
     * A inner class that represent pieces used in game
     *
     */
    class Pieces extends ImageView{
        char pieces;
        Pieces (char pieces) {
            if (pieces>='a' && pieces<='h'){
                Image img=new Image(Viewer.class.getResource(URI_BASE + pieces + ".png").toString());
                setImage(img);
                this.pieces = pieces;
                setFitHeight((img.getHeight()/100)*SQUARE_SIZE);

                setFitWidth(img.getWidth()/100*SQUARE_SIZE);
                setLayoutX(SQUARE_SIZE*4*(pieces-'a'));

                if (SQUARE_SIZE*4*(pieces-'a')+img.getWidth()/100*SQUARE_SIZE>VIEWER_WIDTH ){
                    setLayoutX(SQUARE_SIZE*4*(pieces-'a'-4));
                    setLayoutY(BOAED_FitHeight+SQUARE_SIZE*3);
                }
                else {
                    setLayoutY(BOAED_FitHeight+SQUARE_SIZE);
                }

            }
            else {
                System.out.println("Bad pieces: \""+pieces+"\'");
            }
        }
        Pieces (char pieces, int x, int y,int z){
            if (pieces>='a' && pieces<='h'){
                Image img=new Image(Viewer.class.getResource(URI_BASE + pieces + ".png").toString());
                setImage(img);
                if (z>=4){
                    setScaleY(-1);
                }
                setRotate((z%4)*90);

                this.pieces = pieces;
                double height=img.getHeight()/100*SQUARE_SIZE;
                double weight=img.getWidth()/100*SQUARE_SIZE;
                setFitHeight(height);
                setFitWidth(weight);
                System.out.println(x+" "+y);
                if ((z%4)%2!=0){
                    x=x-(int) (weight-height)/2;
                    y=y+(int) (weight-height)/2;
                }
                setLayoutX(x);
                setLayoutY(y);
                System.out.println(getLayoutX()+" "+getLayoutY());
            }
        }

    }
    /**
     * A inner class that represent pegs used in game
     *
     */
    class Peg extends ImageView{
        char peg;
        Peg (char peg,int index) throws IllegalAccessException {
            if (peg>='i' && peg<='l'){
                setImage(new Image(Viewer.class.getResource(URI_BASE + peg + ".png").toString()));
                this.peg = peg;
                setFitHeight(SQUARE_SIZE);
                setFitWidth(SQUARE_SIZE);
                setLayoutX(PEG_X+index*SQUARE_SIZE);

            }
            else {
                throw new IllegalAccessException("Bad peg: \""+peg+"\'");
            }
        }

        Peg(char peg, int x, int y){
            if (peg>='i' && peg<='l'){
                setImage(new Image(Viewer.class.getResource(URI_BASE + peg + ".png").toString()));
                this.peg = peg;
                setFitHeight(SQUARE_SIZE);
                setFitWidth(SQUARE_SIZE);
                setLayoutX(x);
                setLayoutY(y);
            }


            }

    }


    /**
     * Set up the group that represents the places that make the board
     */
    private void makeBoard() {
        board.getChildren().clear();

        ImageView baseboard = new ImageView();
        baseboard.setImage(new Image(BASEBOARD_URI));
        baseboard.setFitWidth(BOAED_FitWidth);
        baseboard.setFitHeight(BOAED_FitHeight);
        baseboard.setLayoutX(BOARD_X);
        baseboard.setLayoutY(MARGIN_Y);
        board.getChildren().add(baseboard);

        board.toBack();
    }
    private void reset() throws Exception{

        makeStart(pegs,pieces);
    }
    /**
     * isPegOnBoard or not
     */
    private boolean isPegOnBoard(double x, double y){
        if (x>=BOARD_X+SQUARE_SIZE&&x<=BOARD_X+BOAED_FitWidth&&y>=MARGIN_Y+SQUARE_SIZE&&y<=MARGIN_Y+BOAED_FitHeight){
            return true;
        }
        else {
            return false;
        }
    }

    private int getGroupIndex(char m){
        int index=0;
        index=m-'i';
        switch (m){
            case 'k':
                index=index+1;
                break;
            case 'l':
                index=index+2;
                break;
             default:
                 break;
        }

        double x=peg.getChildren().get(index).getLayoutX();
        double y=peg.getChildren().get(index).getLayoutY();
        if (isPegOnBoard(x,y)){
            return index+1;
        }
        else {
            return index;
        }
    }
    /**
     * Set up the group that represents the places when start
     */
    private void makeStart(char[] pegs,char[] pieces) throws Exception{

        peg.getChildren().clear();
        piece.getChildren().clear();
        int i=0;
        for (char each:pegs) {
            Peg startPeg=new Peg(each,i);
            i++;
            peg.getChildren().add(startPeg);
        }
        for (char each : pieces) {
            Pieces startPieces=new Pieces(each);
            piece.getChildren().add(startPieces);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TwistGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(board);
        root.getChildren().add(controls);
        root.getChildren().add(peg);
        root.getChildren().add(piece);

        makeControls();
        makeBoard();
        makeStart(pegs,pieces);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
