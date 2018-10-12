package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class otherInformation extends Application implements Runnable  {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
    @Override

    public void run() {
        try {
            new otherInformation().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
