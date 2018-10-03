package comp1110.ass2.gui;

/**
 * Create by Stella 2018-10-04
 *This class is to support Menu.class.
 * Last modify: 2018-10-04 06:03:00 by Stella
 */

import javafx.beans.binding.Bindings;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


    public class MenuItem extends Pane{
        private Text text;
        private Effect shadow = new DropShadow(5, Color.GRAY);
        private Effect blur = new BoxBlur(1,1,3);

        public MenuItem(String name){
            Polygon bg = new Polygon(
                    0,0,
                    200,0,
                    215, 15,
                    200,30,
                    0,30
            );

            bg.setStroke(Color.color(1, 1, 1, 0.85));
            bg.setEffect(new GaussianBlur());
            bg.fillProperty().bind(
                    Bindings.when(pressedProperty()).then(Color.color(0, 0, 0, 0.85)).otherwise(Color.color(0, 0, 0, 0.4))
            );

            text = new Text(name);
            text.setTranslateX(5);
            text.setTranslateY(20);
            text.setFont(Font.loadFont(Menu.class.getResource("assets/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 14));
            text.setFill(Color.WHITE);
            text.effectProperty().bind(
                    Bindings.when(hoverProperty())
                            .then(blur)
                            .otherwise(shadow)
            );
            getChildren().addAll(bg, text);
        }

        public void setOnAction (Runnable action){
            setOnMouseClicked(e->action.run());
        }
    }

