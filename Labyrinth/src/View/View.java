package View;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class View {
	private static View instance=new View();
	private Button button;
	
	private static Pane pane = new Pane();
	private Scene scene;
	
	private View() {
		
	}
	
	public static View getInstance() {
		return instance;
	}
	
	public void start(Stage primaryStage) throws Exception {
		
        // bouton
        button=new Button("Joueur");
        //player=new Actor(50,50,new Image(getClass().getResource(//Mettre le chemin vers l'image).toExternalForm() ));
        
        // ajout des éléments
        //pane.getChildren().add(player.getImageView();
        
        // scène
        scene = new Scene(pane,350,300);
       
        // stage
        primaryStage.setTitle("Jeu labyrinthe"); 
        primaryStage.setScene(scene); 
        primaryStage.sizeToScene(); 
        //scene.setOnKeyPressed(new PlayerListener());
        primaryStage.show(); 
	}
	



	public Button getButton() {
		return button;
	}

	
	
}