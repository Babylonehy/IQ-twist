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
import javafx.scene.media.AudioClip;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
 * Last modify: 2018-08-21 15:28:53 by Sean
 */

public class Menu extends Application {

    private static final int WIDTH  = 750;
    private static final int HEIGHT = 500;

    private AudioClip loop;

    private List<Pair<String,Runnable>> menuData = Arrays.asList(


            new Pair<String,Runnable>("Exit to Desktop", Platform::exit),
            new Pair<String,Runnable>("Start", (Runnable) new Board()),
            new Pair<String,Runnable>("Game Features", (Runnable) new Features()),
            new Pair<String,Runnable>("Author Information", (Runnable) new Information())
            );


    private Pane root = new Pane();
    //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/Pane.html
    private VBox menuBox = new VBox(-5);
    //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/VBox.html
    private Line line;


    private Parent createContent() {
        addBackground();
        addTitle();
        double lineX = WIDTH / 2 - 100;
        double lineY = HEIGHT / 2 + 120;
        addLine(lineX, lineY);
        addMenu(lineX + 5, lineY );
        startAnimation();
        return root;

    }

    /**
     * Add Background for a Pane
     */
    private void addBackground(){
        ImageView imageView = new ImageView(new Image(getClass().getResource("assets/background2.jpg").toExternalForm()));
        imageView.setFitWidth(WIDTH);
        Image image = new Image("assets/background2.jpg");
        imageView.setImage(image);
        Scene scene = new Scene(root,400,400);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        imageView.setFitHeight(HEIGHT);
        root.getChildren().add(imageView);
    }


    private void addTitle() {
        Title title = new Title("IQ - TWIST");
        title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(HEIGHT / 5.5);
        root.getChildren().add(title);
    }

    private void addLine(double x, double y) {
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
    private void setUpBGM(){

            AudioClip ac;
            ac = new AudioClip(new File("assets/pianoboy.mp3").toURI().toString());
            ac.play();
            ac.setCycleCount(1000);
        }


    private void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1), line);
        st.setToY(1);
        st.setOnFinished(e -> {
            for (int i = 0; i < menuBox.getChildren().size(); i++) {
                Node n = menuBox.getChildren().get(i);
                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.20), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
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
            item.setOnAction((EventHandler) data.getValue());
            line.setTranslateX(-300);
            Rectangle clip = new Rectangle(300, 30);
            clip.translateXProperty().bind(line.translateXProperty().negate());
            clip.setClip(clip);
            menuBox.getChildren().addAll((Collection) item);
        });
        root.getChildren().add(menuBox);
    }

   // private void setUpHandlers(Scene scene){

    //}

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Welcome to IQ TWIST!");
        primaryStage.setScene(scene);
        //setUpHandlers(scene);
        setUpBGM();
        primaryStage.show();
    }
}
