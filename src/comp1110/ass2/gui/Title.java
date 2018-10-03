package comp1110.ass2.gui;

/**
 * Create by Stella 2018-10-04
 *This class is to support Menu.class.
 * Last modify: 2018-10-04 06:03:00 by Stella
 */

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Title extends Pane{
        private Text text;

        public Title (String name){
            String spread = "";
            for (char c : name.toCharArray()){
                spread += c+ "";
            }

            text = new Text(spread);
            text.setFont(Font.loadFont(Menu.class.getResource("assets/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 48));
            text.setFill(Color.GRAY);
            text.setEffect(new DropShadow(30,Color.WHITE));
            getChildren().addAll(text);
        }

        public double getTitleWidth(){
            return text.getLayoutBounds().getWidth();
        }
    }

