package Main;

import Controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	static Controller controller;	
	public static void main(String[] args){
		launch();
	}
	public void start(Stage primaryStage) throws Exception {
		Controller.getInstance();
		Controller.start(primaryStage);
	}
	public void stop(){
	    System.exit(0);
    }
}
