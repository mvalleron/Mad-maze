package View;



import Model.Graph;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class ViewGame {

    private static ViewGame globalView = null;

    static Scene scene;
    static Pane pane = new Pane();
    static Stage stage;
    static FrameView frame;

    private ViewGame() {
    }

    public static ViewGame getInstance() {
        if (globalView == null)
            globalView = new ViewGame();
        return globalView;
    }

    public void createGlobalView(Stage stage, Graph g){
    	frame = FrameView.getInstance();
    	frame.drawFrame(stage,scene, pane, (int)Math.sqrt(g.GetSize()),(int)Math.sqrt(g.GetSize()),g);
    }

	public void raffraichir(Graph g) {
		frame.rafraichir(g);
	}
}


