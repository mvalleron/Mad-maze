package View;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Model.Graph;

public class View  {

	private static View instance;
	static Scene scene;
    static Pane pane ;

	public View() {		
	}
	public static View getInstance() {
		if (instance == null) {
			instance = new View();
		}
		return instance;
	}
	public void createView(Stage primaryStage , Graph g){
		System.out.println("ok");
		scene= new Scene (pane , 350,350);
		primaryStage.setTitle("Mad Maze");
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
		//pane= new Pane();
	    //Viewframe.drawFrame(primaryStage, scene, pane, 10,10);
	 }
}
	
	

	