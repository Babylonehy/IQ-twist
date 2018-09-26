package comp1110.ass2.gui;

import comp1110.ass2.Game.Constant;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import static comp1110.ass2.Game.Constant.pegs;
import static comp1110.ass2.Game.Constant.pieces;
import static comp1110.ass2.TwistGame.*;

public class Board extends Application {


    /* board layout */
    private static final int MARGIN_Y = 60;
    private static final int SQUARE_SIZE = 60;
    private static final int VIEWER_WIDTH = 933;
    private static final int VIEWER_HEIGHT = 730;
    private static final int BOAED_FitWidth = 9 * SQUARE_SIZE;
    private static final int BOAED_FitHeight = 5 * SQUARE_SIZE;
    private static final int BOARD_X = 180;
    private static final double PEG_X = BOARD_X + SQUARE_SIZE * 1.5;

    private static final String URI_BASE = "assets/";
    private static final String BASEBOARD_URI = Board.class.getResource(URI_BASE + "board.png").toString();


    private final Group root = new Group();
    private final Group controls = new Group();
    TextField textField;

    /* board*/
    private final Group board = new Group();

    /* peg */
    private final Group peg = new Group();

    /* pieces */
    private final Group piece = new Group();

    /*message*/
    private final Text wrongInput = new Text();

    private Pieces startPieces[] = new Pieces[8];

    /* marker for unplaced tiles */
    public static final int NOT_PLACED = 255;
    public static final int Percsion = SQUARE_SIZE;

    public static String BoardStr="";
    public static boolean Finished=false;


    Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
    /**
     * Create the message to be displayed when the player wrongInput.
     */

    private void makeWrongInput(String out,int size) {
        wrongInput.setText(out);
        wrongInput.setFill(Color.RED);
        wrongInput.setCache(true);
        wrongInput.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, size));
        wrongInput.setLayoutX(30);
        wrongInput.setLayoutY(VIEWER_HEIGHT - 20);
        wrongInput.setTextAlignment(TextAlignment.CENTER);
        if (!root.getChildren().contains(wrongInput)){
            root.getChildren().add(wrongInput);
        }
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
     * @param placement A valid placement string
     */
    void makePlacement(String placement) {
        if (isPlacementStringValid(placement)) {
            hidewrongInput();
            System .out.println(placement);
            char[][] decode = decodeTotype_position(placement);

            for (int i = 0; i < decode.length; i++) {
                int x=(decode[i][1] - '1' + 1);
                int y=(decode[i][2] - 'A' + 1);
                if (isPeg(decode[i][0])) {
                    int index = getGroupIndex(decode[i][0]);
                    Peg Peg_change = new Peg(decode[i][0], x, y);
                    peg.getChildren().set(index, Peg_change);
                } else {
                    int Z = decode[i][3] - '0';
                    Pieces Piece_change = new Pieces(decode[i][0], x, y, Z);
                    piece.getChildren().set(decode[i][0] - 'a', Piece_change);
                }

            }
        } else {
            makeWrongInput("Wrong Input!",12);
            showwrongInput();
        }
        // FIXME Task 4: implement the simple placement viewer
    }


    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() throws Exception {
        Label label1 = new Label("Start—Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Start");
        Button button2 = new Button("Reset");
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
        hb.getChildren().addAll(label1, textField, button, button2);
        hb.setSpacing(10);
        hb.setLayoutX(VIEWER_WIDTH / 3.5);
        hb.setLayoutY(VIEWER_HEIGHT - 30);
        controls.getChildren().add(hb);
    }

    /**
     * A inner class that represent nodes including pieces and pegs used in game
     */
    class DraggablePieces extends ImageView {
        int rotation = 0;
        char type ;
        double mouseX, mouseY;
        double width, height;
        double homeX, homeY;
        int status;
        int z=0;
        String position="";

        DraggablePieces(){
            /* event handlers */
            toFront();
            if (Finished==false){

            setOnMousePressed(event -> {// mouse press indicates begin of drag
                toFront();
                if (event.getButton().equals(MouseButton.PRIMARY)){
                    setOpacity(0.7);
                    mouseX = event.getSceneX();
                    mouseY = event.getSceneY();
                    codePieces();
                }
            });

            setOnScroll(event -> {// scroll to change orientation
                if (status==NOT_PLACED){
                    rotate();
                    //System.out.println(getLayoutX()+" "+getLayoutY()+" "+width+" "+height);
                    codePieces();
                    event.consume();
                }
            });

            //Flip
            setOnMouseClicked(event->{
                if (event.getButton().equals( MouseButton.MIDDLE)&& event.getEventType().equals(MouseEvent.MOUSE_CLICKED)&&status==NOT_PLACED){
                    setScaleY(-1*getScaleY());
                    codePieces();
                }
                if (event.getButton().equals( MouseButton.SECONDARY)&& event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
                    snapToHome();
                }
                event.consume();
            });

            setOnMouseDragged(event -> {      // mouse is being dragged
                toFront();
                if (event.getButton().equals(MouseButton.PRIMARY)){
                    double movementX = event.getSceneX() - mouseX;
                    double movementY = event.getSceneY() - mouseY;
                    setLayoutX(getLayoutX() + movementX);
                    setLayoutY(getLayoutY() + movementY);
                    mouseX = event.getSceneX();
                    mouseY = event.getSceneY();
                    event.consume();
                }

            });

            setOnMouseReleased(event -> {     // drag is complete
                setOpacity(1);
                snapToGrid();
            });
            }
        }

        private void rotate(){
                double current_rotate = getRotate();
                double rotation = current_rotate + 90 % 360;
                setRotate(rotation);
                z = (int) ((getRotate() % 360) / 90 + (getScaleY() == 1 ? 0 : 1) * 4);
                if ((z % 4) % 2 == 1) {
                    width = getFitHeight();
                    height = getFitWidth();
                } else {
                    width = getFitWidth();
                    height = getFitHeight();
                }
                this.rotation++;
           // System.out.println(type+" Flip:"+getScaleY()+" R:"+getRotate()%360);
        }

        private  String codePieces(){
           // z= (int) ((getRotate()%360)/90+(getScaleY()==1?0:1)*4);
            String piecesId=type+""+z;
            setId(piecesId);
            System.out.println(piecesId);
            return piecesId;
        }
        /**
         * @return true if the mask is on the board
         */
        private boolean onBoard() {
            double x;
            double y;
            x=getLayoutX();
            y=getLayoutY();
            double max=Math.max(width,height);
            double min=Math.min(width,height);
            if ((z%4)%2!=0){
                x=x+(max - min) / 2.0;
                y=y-(max - min) / 2.0;
            }

//            System.out.println("W:"+width+" H:"+height);
//            System.out.println("TL:"+x+" "+y+PointOnBoard(x,y));
//            System.out.println("TR:"+(x+width)+" "+y+PointOnBoard(x+width,y));
//            System.out.println("DL:"+x+" "+(y+height)+PointOnBoard(x,y+height));
//            System.out.println("DR:"+(x+width)+" "+(y+height)+PointOnBoard(x+width,y+height));

            return PointOnBoard(x,y)&&PointOnBoard(x+width,y+height)
                    &&PointOnBoard(x+width,y)&&PointOnBoard(x,y+height);

        }


        /**
         * @return true if the mask is on the board
         */
        private boolean PointOnBoard(double x,double y) {
            double xmin=BOARD_X+SQUARE_SIZE-2*Percsion;
            double xmax=BOARD_X+BOAED_FitWidth+2*Percsion;
            double ymin=MARGIN_Y+SQUARE_SIZE-2*Percsion;
            double ymax=MARGIN_Y+BOAED_FitHeight+2*Percsion;
            //System.out.println((xmin)+" "+(xmax)+" "+(ymin)+" "+(ymax));
            return x>=xmin && x<=xmax&& y>=ymin && y<=ymax;
        }

        private boolean PiecesOffBoard(){
            double maxwidth=Math.max(height,width);
//            System.out.println("TL:"+!PointOnBoard(getLayoutX(),getLayoutY()));
//            System.out.println("DR:"+!PointOnBoard(getLayoutX()+width,getLayoutY()+height));
//            System.out.println("TR:"+!PointOnBoard(getLayoutX()+width,getLayoutY()));
//            System.out.println("DL+"+!PointOnBoard(getLayoutX(),getLayoutY()+height));
            return !PointOnBoard(getLayoutX(),getLayoutY())&&!PointOnBoard(getLayoutX()+width,getLayoutY()+height)
                    &&!PointOnBoard(getLayoutX()+width,getLayoutY())&&!PointOnBoard(getLayoutX(),getLayoutY()+height);
        }

        /**
         * Snap the tile to the nearest grid position (if it is over the grid)
         */
        private void snapToGrid() {
            if (onBoard()){
              //  System.out.println("On Board!");
                int row, coloumn;
                double x=getLayoutX();
                double y=getLayoutY();
                double max=Math.max(width,height);
                double min=Math.min(width,height);
                if ((z%4)%2!=0){
                    x=x+(max - min) / 2.0;
                    y=y-(max - min) / 2.0;
                }
                coloumn = (int)(x - 240) / 60;
                row = (int)(y - 120) / 60;
                x=BOARD_X + SQUARE_SIZE+SQUARE_SIZE * coloumn;
                y=MARGIN_Y+SQUARE_SIZE+SQUARE_SIZE*row;
               // System.out.println("TopLeft:"+x+" "+y);
                if (row<4 && coloumn<8){
                    //System.out.println(row+" "+coloumn);
                    String placeStep=type+positionToPlaceCode(row*8+coloumn)+z;
                    System.out.println(generateBoardStr(placeStep));
                    if (generateBoardStr(placeStep)){

                        if ((z%4)%2!=0){
                            x=x-(max - min) / 2.0;
                            y=y+(max - min) / 2.0;
                        }
                        setLayoutX(x);
                        setLayoutY(y);
                        status=0;
                        setId(placeStep);
                        checkComplete();
                    }
                    else {
                        snapToHome();
                    }

                }

            }
            else {
                snapToHome();
            }
        }

        /**
         * Snap the mask to its home position (if it is not on the grid)
         */
        private void snapToHome() {
           // System.out.println(homeX+" "+homeY);
            if (!PiecesOffBoard()){
                setLayoutX(homeX);
                setLayoutY(homeY);
                setRotate(0);
                setId(type+""+"0");
                status = NOT_PLACED;
            }
        }

        public int getStatus() {
            return status;
        }

        public String getPosition() {
            return position;
        }
    }


    /**
     * A inner class that represent pieces used in game
     */
    class Pieces extends DraggablePieces {
        char pieces;

        Pieces(char pieces) {
            if (pieces >= 'a' && pieces <= 'h') {
                setId(String.valueOf(pieces));
                Image img = new Image(Viewer.class.getResource(URI_BASE + pieces + ".png").toString());
                setImage(img);
                this.pieces = pieces;
                this.type = pieces;
                setFitHeight((img.getHeight() / 100) * SQUARE_SIZE);
                setFitWidth(img.getWidth() / 100 * SQUARE_SIZE);
                width = getFitWidth();
                height = getFitHeight();
                setLayoutX(SQUARE_SIZE * 4 * (pieces - 'a'));

                if (SQUARE_SIZE * 4 * (pieces - 'a') + img.getWidth() / 100 * SQUARE_SIZE > VIEWER_WIDTH) {
                    setLayoutX(SQUARE_SIZE * 4 * (pieces - 'a' - 4));
                    setLayoutY(BOAED_FitHeight + SQUARE_SIZE * 3.5);
                } else {
                    setLayoutY(BOAED_FitHeight + SQUARE_SIZE*1.5);
                }

            } else {
                System.out.println("Bad pieces: \"" + pieces + "\'");
            }
            homeX=getLayoutX();
            homeY=getLayoutY();
            status=NOT_PLACED;
        }

        Pieces(char pieces, int x, int y, int z) {
            if (pieces >= 'a' && pieces <= 'h') {
                setId(String.valueOf(pieces));
                Image img = new Image(Viewer.class.getResource(URI_BASE + pieces + ".png").toString());
                setImage(img);
                int X = BOARD_X + SQUARE_SIZE * x;
                int Y = MARGIN_Y + SQUARE_SIZE * y;
                x=x-1;
                y=y-1;
                if (z >= 4) {
                    setScaleY(-1);
                }
                setRotate((z % 4) * 90);

                this.pieces = pieces;
                double height = img.getHeight() / 100 * SQUARE_SIZE;
                double weight = img.getWidth() / 100 * SQUARE_SIZE;
                setFitHeight(height);
                setFitWidth(weight);
                if ((z % 4) % 2 != 0) {
                    X = X - (int) (weight - height) / 2;
                    Y = Y + (int) (weight - height) / 2;
                }
                setLayoutX(X);
                setLayoutY(Y);
                setId(pieces+""+positionToPlaceCode(8*y+x)+""+z);
                //System.out.println(getLayoutX() + " " + getLayoutY());
            }
        }
        public char getPieces() {
            return pieces;
        }

    }

    /**
     * A inner class that represent pegs used in game
     */
    class Peg extends ImageView {
        char peg;

        Peg(char peg, int index) throws IllegalAccessException {
            if (peg >= 'i' && peg <= 'l') {
                setId(String.valueOf(peg));
                setImage(new Image(Viewer.class.getResource(URI_BASE + peg + ".png").toString()));
                this.peg = peg;

                setFitHeight(SQUARE_SIZE);
                setFitWidth(SQUARE_SIZE);
                setLayoutX(PEG_X + index * SQUARE_SIZE);
                setId(peg+"0");

            } else {
                throw new IllegalAccessException("Bad peg: \"" + peg + "\'");
            }

        }

        Peg(char peg, int x, int y) {
            if (peg >= 'i' && peg <= 'l') {
                int X = BOARD_X + SQUARE_SIZE * x;
                int Y = MARGIN_Y + SQUARE_SIZE * y;
                setId(String.valueOf(peg));
                setImage(new Image(Viewer.class.getResource(URI_BASE + peg + ".png").toString()));
                this.peg = peg;
                setFitHeight(SQUARE_SIZE);
                setFitWidth(SQUARE_SIZE);
                setLayoutX(X);
                setLayoutY(Y);
                x--;
                y--;
                setId(peg+positionToPlaceCode(8*y+x)+"0");
            }

        }


    }

    public void checkComplete(){
        if (checkString(BoardStr)){
            Finished=true;
            makeWrongInput("Well Done!",48);
            showwrongInput();
        }
    }
    public boolean generateBoardStr(String place){
        String temp="";
        for (Node each:piece.getChildren()) {
            if (each.getId().length()==4){
                temp+=each.getId();
               // System.out.println(each.getId());
            }

        }
        for (Node each:peg.getChildren()) {
            if (each.getId().length()==4){
                temp+=each.getId();
                //System.out.println(each.getId());
            }
        }
        temp+=place;
        if(isPlacementStringValid(temp)){
            BoardStr=temp;
            System.out.println(BoardStr);
            return true;
        }else {
            return false;
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
    }

    private void reset() throws Exception {
        BoardStr="";
        Finished=false;
        makeStart(pegs, pieces);
    }

    /**
     * isPegOnBoard or not
     */
    private boolean isPegOnBoard(double x, double y) {
        if (x >= BOARD_X + SQUARE_SIZE && x <= BOARD_X + BOAED_FitWidth && y >= MARGIN_Y + SQUARE_SIZE && y <= MARGIN_Y + BOAED_FitHeight) {
            return true;
        } else {
            return false;
        }
    }

    private int getGroupIndex(char m) {
        int index = 0;
        index = m - 'i';
        switch (m) {
            case 'k':
                index = index + 1;
                break;
            case 'l':
                index = index + 2;
                break;
            default:
                break;
        }

        double x = peg.getChildren().get(index).getLayoutX();
        double y = peg.getChildren().get(index).getLayoutY();
        if (isPegOnBoard(x, y)) {
            return index + 1;
        } else {
            return index;
        }
    }

    /**
     * Set up the group that represents the places when start
     */
    private void makeStart(char[] pegs, char[] pieces) throws Exception {

        peg.getChildren().clear();
        piece.getChildren().clear();
        int i = 0;
        for (char each : pegs) {
            Peg startPeg = new Peg(each, i);
            i++;
            peg.getChildren().add(startPeg);
            peg.getChildren().get(peg.getChildren().size()-1).setId(String.valueOf(startPeg.getId()));
        }
        int j = 0;
        for (char each : pieces) {
            startPieces[j] = new Pieces(each);
            piece.getChildren().add(startPieces[j]);
            piece.getChildren().get(j).setId(String.valueOf(startPieces[j].getId()));
            j++;
        }

    }
    private void addBackground(){
        ImageView imageView = new ImageView(new Image(getClass().getResource(URI_BASE +"BKG.jpg").toExternalForm()));
        root.getChildren().add(imageView);
        root.toBack();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TwistGame Viewer");
        addBackground();
        root.getChildren().add(board);
        root.getChildren().add(controls);
        root.getChildren().add(peg);
        root.getChildren().add(piece);

        makeControls();
        makeBoard();
        makeStart(pegs, Constant.pieces);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    // FIXME Task 7: Implement a basic playable Twist Game in JavaFX that only allows pieces to be placed in valid places



    // FIXME Task 8: Implement starting placements

    // FIXME Task 10: Implement hints

    // FIXME Task 11: Generate interesting starting placements

}
