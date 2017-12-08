package View;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import Model.Graph;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class ViewGame {

    private static ViewGame globalView = null;

    static Scene scene;
    static Pane pane = new Pane();

    private ViewGame() {
    }

    public static ViewGame getInstance() {
        if (globalView == null)
            globalView = new ViewGame();
        return globalView;
    }

    public void createGlobalView(Stage stage, Graph g){
    	
        FrameView.drawFrame(stage, scene, pane, (int)Math.sqrt(g.GetSize()),(int)Math.sqrt(g.GetSize()));
        FrameView.drawWall(pane,g, FrameView.WALL_COLOR);
    }
}


