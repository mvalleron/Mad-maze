package Controller;

import Model.*;
import View.*;


import javafx.stage.Stage;


public class Controller //implements EventHandler<ActionEvent> 
{
	private static Controller instance = null;
	private static View view;
	private static Model model;
	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	public Controller () {
		view = View.getInstance();
	    model= new Model();
	}

	public static void start(Stage primaryStage) throws Exception {
		ViewGame.getInstance().createGlobalView(primaryStage, model.getLabyrinth());
		primaryStage.show();
	}
}

