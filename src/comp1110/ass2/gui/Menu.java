package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.Scene;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Create by Sean 2018-08-21
 *This class is used to create a menu for interaction with players.
 * Maybe it will have more than one menu to provide more interaction with player
 *
 * Last modify: 2018-10-04 06:03:00 by Stella
 */

public class Menu extends Application {
    private static final int WIDTH  = 833;
    private static final int HEIGHT = 600;
    private static final String URI_BASE = "assets/";
    private Pane root = new Pane();
    private VBox menuBox = new VBox(-5);
    private List<Pair<String,Runnable>> menuData = Arrays.asList(
            new Pair<String,Runnable>("Start",new Board()),
            new Pair<String,Runnable>("Impression",new otherInformation()),
            new Pair<String,Runnable>("Author Information",new Information()),
            new Pair<String,Runnable>("Exit to Desktop", Platform::exit));
    private Line line;

    private Parent createContent() {
        addBackground();
        double lineX = WIDTH / 2 - 100;
        double lineY = HEIGHT / 2.6 + 120;
        addMenu(lineX + 5, lineY );
        return root;
    }
    /**
     * Add Background for a Pane
     */
    private void addBackground(){
        ImageView imageView = new ImageView(new Image(getClass().getResource(URI_BASE + "background1.jpg").toExternalForm()));
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);
        root.getChildren().add(imageView);


    }


    private void addTitle() {
        Title title = new Title("IQ - STEPS");
        title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(HEIGHT / 5.5);

        root.getChildren().add(title);

    }

   /** private void addLine(double x, double y) {
        line = new Line(x, y, x, y + 150);
        line.setStrokeWidth(3);
        line.setStroke(Color.color(1, 1, 1, 0.75));
        line.setEffect(new DropShadow(5, Color.BLACK));
        line.setScaleY(0);

        root.getChildren().add(line);
    }
    /**
     * Set up the background music loop (to play when the 'M' key is pressed)
     * Need Exception
     */
    private void setUpBGM() {

        try {
            InputStream in = new FileInputStream(URI_BASE+"Pianoboy.mp3");
            AudioStream as = new AudioStream(in);
            AudioData data = as.getData();
            ContinuousAudioDataStream loop= new ContinuousAudioDataStream (data);
            AudioPlayer.player.start(loop);
        } catch (IOException e) {
            System.out.print("FileNotFoundException ");
        }
    }

    private void startAnimation() {

    }

    ///**
     //*  Create handlers for key press and release events
    // *  such as q means quit; m means play music or stop playing and so on.
    // * @param scene A Target UI
     //*/



    private void addMenu(double x, double y) {
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        menuData.forEach(data -> {
            MenuItem item = new MenuItem(data.getKey());

            Rectangle clip = new Rectangle(300, 30);

        });
        root.getChildren().addAll();
    }

   // private void setUpHandlers(Scene scene){

    //}

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("IQ Puzzle Game");
        Group root = new Group();
        Scene scene = new Scene(root,WIDTH,HEIGHT);
        primaryStage.setScene(scene);
        addBackground();
        //Button btn = new Button("START");
        setUpBGM();
        this.setOpaque(false);
        root.getChildren().addAll();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private boolean setOpaque(boolean b) {
        return b;
    }


    public static void main(String[] args) {
        launch(args);
    }
}