package Main;

import Controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args){
		launch();
	}
	public void start(Stage primaryStage) throws Exception {
		Controller controller = Controller.getInstance();
		controller.start(primaryStage);
	}
}
