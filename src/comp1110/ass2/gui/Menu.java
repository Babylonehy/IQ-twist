package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import comp1110.ass2.gui.Board;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Menu extends Application {
    private static final String URI_BASE = "assets/";
    private static final int VIEWER_WIDTH = 933;
    private static final int VIEWER_HEIGHT = 700;
    Parent root = null;


    private void setFunction(){
        try {
            root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void StartGameClick() throws Exception {
        Board game=new Board();
        Stage gamestage=new Stage();
        game.start(gamestage);
    }

    @FXML
    private void ShowInfo() {
        Parent info = null;
        try {
            info = FXMLLoader.load(getClass().getResource("Info.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Hyperlink link=new Hyperlink("https://www.smartgames.eu/uk/one-player-games/iq-twist");

        Stage infostage=new Stage();
        Scene infoscene = new Scene(info, 300, 215);


        infostage.setTitle("Info");
        infostage.setScene(infoscene);
        infostage.show();

    }
    @FXML private void MoreInfo(){
        try {
            Desktop.getDesktop().browse(new URI("https://www.smartgames.eu/uk/one-player-games/iq-twist"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void ExitClick() {
        Platform.exit();
    }

    @Override
    public void start(Stage primaryStage) {
        setFunction();
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        primaryStage.getIcons().add(new Image(getClass().getResource(URI_BASE + "Icon.jpg").toExternalForm()));

        primaryStage.setTitle("IQ-twist");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
