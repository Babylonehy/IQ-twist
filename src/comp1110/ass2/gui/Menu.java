package comp1110.ass2.gui;

import com.sun.xml.internal.fastinfoset.sax.Features;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.File;

import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.*;
import javafx.util.Duration;
import javafx.util.Pair;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import java.util.Arrays;
import java.util.Collection;
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
    private Pane root = new Pane();
    private AudioClip loop;
    private VBox menuBox = new VBox(-5);
    private List<Pair<String,Runnable>> menuData = Arrays.asList(
            new Pair<String,Runnable>("Start",new Board()),
            new Pair<String,Runnable>("Game Features",new otherInformation()),
            new Pair<String,Runnable>("Author Information",new Information()),
            new Pair<String,Runnable>("Exit to Desktop", Platform::exit));

    private Parent createContent() {
        addBackground();
        addTitle();
        double lineX = WIDTH / 2 - 100;//WIDTH / 2 - 100;
        double lineY = HEIGHT / 2.6 + 120;//HEIGHT / 3 + 50;
        addLine(lineX, lineY);
        addMenu(lineX + 5, lineY );
        startAnimation();
        return root;
    }
    /**
     * Add Background for a Pane
     */
    private void addBackground(){
        ImageView imageView = new ImageView(new Image(getClass().getResource("assets/background1.jpg").toExternalForm()));
        //ImageView imageView = new ImageView(new Image(getClass().getResource("res/iqsteps.png").toExternalForm()));
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);
        root.getChildren().add(imageView);
    }


    private void addTitle() {

    }

    private void addLine(double x, double y) {

    }
    /**
     * Set up the background music loop (to play when the 'M' key is pressed)
     * Need Exception
     */
    private void setUpBGM(){


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
            root.getChildren().addAll(menuBox);
        });
    }

   // private void setUpHandlers(Scene scene){

    //}

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("IQ Puzzle Game");
        Group root = new Group();
        Scene scene = new Scene(root,WIDTH,HEIGHT);
        Button btn = new Button("Start");
        root.getChildren().addAll();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}