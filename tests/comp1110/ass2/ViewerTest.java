package comp1110.ass2;

import comp1110.ass2.Game.Constant;
import comp1110.ass2.gui.Viewer;
import javafx.scene.Group;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertTrue;

public class ViewerTest extends ApplicationTest {

    Viewer test;

    @Override
    public void start(Stage stage) throws Exception{
        test=new Viewer();
        test.start(stage);
        stage.show();
        stage.toFront();
    }

    @Test
    public void Width_HeightTest() {
        assertTrue("Width is"+test.getScene().getWidth()+" excepted: 955",test.getScene().getWidth()==955);
        assertTrue("Height is"+test.getScene().getHeight()+" excepted: 720",test.getScene().getHeight()==720);
    }

    @Test
    public void PiecesTest() throws Exception {
        Group pieces=test.getPiece();
        assertTrue("Size of PiecesSet is "+pieces.getChildren().size()+" excepted: 8",pieces.getChildren().size()==8);
        for (int i = 0; i <pieces.getChildren().size() ; i++) {
            String id=pieces.getChildren().get(i).getId();
            boolean flag=false;
            for (char each:Constant.pieces) {
                if (id.equals(String.valueOf(each))){
                    flag=true;
                    break;
                }
            }
            assertTrue("Pieces "+pieces.getChildren().get(i).getId()+"should be included, but not",flag );
        }
    }

    @Test
    public void PegsTest() throws Exception {
        Group pegs=test.getPeg();
        assertTrue("Size of PegsSet is"+pegs.getChildren().size()+" excepted: 7",pegs.getChildren().size()==7);
        for (int i = 0; i <pegs.getChildren().size() ; i++) {
            String id=pegs.getChildren().get(i).getId();
            boolean flag=false;
            for (char each:Constant.pegs) {
                if (id.equals(String.valueOf(each))){
                    flag=true;
                    break;
                }
            }
            assertTrue("Pegs "+pegs.getChildren().get(i).getId()+"should be included, but not",flag );
        }
    }


}