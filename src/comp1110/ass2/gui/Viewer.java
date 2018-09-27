package comp1110.ass2.gui;

import comp1110.ass2.Game.Constant;
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

/**
 * A very simple viewer for piece placements in the twist game.
 * <p>
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
    private static final int BOARD_X = 180;
    private static final double PEG_X = BOARD_X + SQUARE_SIZE * 1.5;

    private static final String URI_BASE = "assets/";
    private static final String BASEBOARD_URI = Viewer.class.getResource(URI_BASE + "board.png").toString();


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
    private final Text wrongInput = new Text("Wrong Input!");

    private Pieces startPieces[] = new Pieces[8];


    /**
     * Create the message to be displayed when the player wrongInput.
     */

    private void makeWrongInput() {
        wrongInput.setFill(Color.RED);
        wrongInput.setCache(true);
        wrongInput.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));
        wrongInput.setLayoutX(30);
        wrongInput.setLayoutY(VIEWER_HEIGHT - 20);
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
     * @param placement A valid placement string
     */
    void makePlacement(String placement) {
        if (isPlacementStringWellFormed(placement)) {
            hidewrongInput();
            System .out.println(placement);
            char[][] decode = decodeTotype_position(placement);
            for (int i = 0; i < decode.length; i++) {
                if (isPeg(decode[i][0])) {
                    int X = BOARD_X + SQUARE_SIZE * (decode[i][1] - '1' + 1);
                    int Y = MARGIN_Y + SQUARE_SIZE * (decode[i][2] - 'A' + 1);
                    int index = getGroupIndex(decode[i][0]);
                    Peg Peg_change = new Peg(decode[i][0], X, Y);
                    peg.getChildren().set(index, Peg_change);
                } else {
                    int X = BOARD_X + SQUARE_SIZE * (decode[i][1] - '1' + 1);
                    int Y = MARGIN_Y + SQUARE_SIZE * (decode[i][2] - 'A' + 1);
                    int Z = decode[i][3] - '0';
                    Pieces Piece_change = new Pieces(decode[i][0], X, Y, Z);
                    piece.getChildren().set(decode[i][0] - 'a', Piece_change);
                }

            }
        } else {
            makeWrongInput();
            showwrongInput();
        }
        // FIXME Task 4: implement the simple placement viewer
    }


    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() throws Exception {
        Label label1 = new Label("Placement:");
        textField = new TextField();
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
        hb.getChildren().addAll(label1, textField, button, button2);
        hb.setSpacing(10);
        hb.setLayoutX(VIEWER_WIDTH / 3.5);
        hb.setLayoutY(VIEWER_HEIGHT - 30);
        controls.getChildren().add(hb);
    }

    /**
     * A inner class that represent nodes including pieces and pegs used in game
     */
    class Nodes extends ImageView {
        int rotation = 0;
        int peg_index = -1;
        char type = 'z';
        double mouse_x, mouse_y;
        int node_width, node_height;

        Nodes() {
            setOnMousePressed(event -> {
                setOpacity(0.7);
                mouse_x = event.getSceneX();
                mouse_y = event.getSceneY();
            });
            setOnMouseDragged(event -> {
                toFront();
                double increasing_x, increasing_y, increased_x, increased_y;
                increasing_x = event.getSceneX() - mouse_x;
                increasing_y = event.getSceneY() - mouse_y;
                increased_x = getLayoutX() + increasing_x;
                increased_y = getLayoutY() + increasing_y;
                setLayoutX(increased_x);
                setLayoutY(increased_y);
                mouse_x = event.getSceneX();
                mouse_y = event.getSceneY();
                event.consume();
            });
            setOnMouseReleased(event -> {
                setOpacity(1);
                System.out.println(this.type+" Flip:"+getScaleY()+" X:"+getLayoutX()+",Y:"+getLayoutY());
                double raw, coloumn;
                coloumn = (getLayoutX() - 240) / 60;
                raw = (getLayoutY() - 120) / 60;
                if (getLayoutX() > 80 && getLayoutX() < 700 && getLayoutY() > 90 && getLayoutY() < 340) {
                    if (true) {
                        setLayoutX(BOARD_X + 58 + SQUARE_SIZE * (int) coloumn);
                        setLayoutY(BOARD_X / 2 + 30 + SQUARE_SIZE * (int) raw);
                        if (this.type == 'a' || this.type == 'b' || this.type == 'c' || this.type == 'd' || this.type == 'f') {
                            if (rotation == 1 || rotation == 3) {
                                setLayoutX(BOARD_X + 28 + SQUARE_SIZE * (int) coloumn);
                                setLayoutY(BOARD_X / 2 + 60 + SQUARE_SIZE * (int) raw);
                            }
                        }
                    }
                } else if (node_width != 0) {
                    for (Pieces each : startPieces) {
                        if (getLayoutX() > mouse_x && getLayoutX() < mouse_x + each.getFitWidth()
                                && getLayoutY() > mouse_y && getLayoutY() < mouse_y + each.getFitHeight()) {
                            if (SQUARE_SIZE * 4 * (type - 'a') + 200 / 100 * SQUARE_SIZE > VIEWER_WIDTH) {
                                setLayoutX(SQUARE_SIZE * 4 * (type - 'a' - 4));
                                setLayoutY(BOAED_FitHeight + SQUARE_SIZE * 3);
                            } else {
                                setLayoutX(SQUARE_SIZE * 4 * (type - 97));
                                setLayoutY(BOAED_FitHeight + SQUARE_SIZE);
                            }
                            if (type >= 'i') {
                                setLayoutY(0);
                                setLayoutX(PEG_X + peg_index * SQUARE_SIZE);
                            }
                        }
                    }
                } else {
                    if (SQUARE_SIZE * 4 * (type - 'a') + 200 / 100 * SQUARE_SIZE > VIEWER_WIDTH) {
                        setLayoutX(SQUARE_SIZE * 4 * (type - 'a' - 4));
                        setLayoutY(BOAED_FitHeight + SQUARE_SIZE * 3);
                    } else {
                        setLayoutX(SQUARE_SIZE * 4 * (type - 97));
                        setLayoutY(BOAED_FitHeight + SQUARE_SIZE);
                    }
                    if (type >= 'i') {
                        setLayoutY(0);
                        setLayoutX(PEG_X + peg_index * SQUARE_SIZE);
                    }
                }

            });



            setOnScroll(event -> {
                double current_rotate = getRotate();
                double rotation = current_rotate + 90 % 360;
                setRotate(rotation);
                this.rotation++;
                System.out.println(type+" Flip:"+getScaleY()+" R:"+getRotate()%360);
                event.consume();
            });

            //Flip
            setOnMouseClicked(event->{
                if (event.getButton().equals( MouseButton.MIDDLE)&& event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
                        setScaleY(-1*getScaleY());
                }
            });

    }
    }

    /**
     * A inner class that represent pieces used in game
     */
    class Pieces extends Nodes {
        char pieces;

        Pieces(){

        }

        Pieces(char pieces) {
            if (pieces >= 'a' && pieces <= 'h') {
                Image img = new Image(Viewer.class.getResource(URI_BASE + pieces + ".png").toString());
                setImage(img);
                this.pieces = pieces;
                this.type = pieces;
                node_width = (int) img.getWidth();
                node_height = (int) img.getHeight();
                setFitHeight((img.getHeight() / 100) * SQUARE_SIZE);
                setFitWidth(img.getWidth() / 100 * SQUARE_SIZE);
                setLayoutX(SQUARE_SIZE * 4 * (pieces - 'a'));

                if (SQUARE_SIZE * 4 * (pieces - 'a') + img.getWidth() / 100 * SQUARE_SIZE > VIEWER_WIDTH) {
                    setLayoutX(SQUARE_SIZE * 4 * (pieces - 'a' - 4));
                    setLayoutY(BOAED_FitHeight + SQUARE_SIZE * 3);
                } else {
                    setLayoutY(BOAED_FitHeight + SQUARE_SIZE);
                }

            } else {
                System.out.println("Bad pieces: \"" + pieces + "\'");
            }
        }

        Pieces(char pieces, int x, int y, int z) {
            if (pieces >= 'a' && pieces <= 'h') {
                Image img = new Image(Viewer.class.getResource(URI_BASE + pieces + ".png").toString());
                setImage(img);

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
                    x = x - (int) (weight - height) / 2;
                    y = y + (int) (weight - height) / 2;
                }
                setLayoutX(x);
                setLayoutY(y);
                System.out.println(getLayoutX() + " " + getLayoutY());
            }
        }
    }

    /**
     * A inner class that represent pegs used in game
     */
    class Peg extends ImageView {
        char peg;
        Peg(char peg, int index) throws IllegalAccessException {
            if (peg >= 'i' && peg <= 'l') {
                setImage(new Image(Viewer.class.getResource(URI_BASE + peg + ".png").toString()));
                this.peg = peg;
//                this.type = peg;
//                this.peg_index = index;
                System.out.println("peg = " + peg + " " + index);
                setFitHeight(SQUARE_SIZE);
                setFitWidth(SQUARE_SIZE);
                setLayoutX(PEG_X + index * SQUARE_SIZE);

            } else {
                throw new IllegalAccessException("Bad peg: \"" + peg + "\'");
            }
        }

        Peg(char peg, int x, int y) {
            if (peg >= 'i' && peg <= 'l') {
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

    private void reset() throws Exception {

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
        }
        int j = 0;
        for (char each : pieces) {
            startPieces[j] = new Pieces(each);
            piece.getChildren().add(startPieces[j++]);
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
        makeStart(pegs, Constant.pieces);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
