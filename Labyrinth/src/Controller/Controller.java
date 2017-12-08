package Controller;

import javafx.scene.text.*;

import Model.*;
import View.*;

import com.sun.glass.events.KeyEvent;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;


public class Controller //implements EventHandler<ActionEvent> 
{
	private static Controller instance;
	private View view;
	private Model model;
	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	public Controller () {
		this.view = View.getInstance();
		this.model = new Model();
	}

	public void start(Stage primaryStage) {
		// TODO Auto-generated method stub
		//this.view.start(primaryStage);
		//this.view.start(primaryStage, model.getLabyrinth().getWalls());
		//this.view.start(primaryStage, model.getLabyrinth().getWalls());
		view.createView(primaryStage, model.getLabyrinth());
		//this.view.setOnAction(this);
	}
	

	/*@Override
	public void handle(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
		switch(((Object) e).getKeyCode()) {
		case "UP":Viewframe.mooveCharacter(Viewframe.imageView.getX(),Viewframe.imageView.getX() -2);
		break;
		case "Down":Viewframe.mooveCharacter(Viewframe.imageView.getX(),Viewframe.imageView.getX() +2);
			break;
		case "left":Viewframe.mooveCharacter(Viewframe.imageView.getX()-2,Viewframe.imageView.getX());
		break;
		case "Right":Viewframe.mooveCharacter(Viewframe.imageView.getX() +2,Viewframe.imageView.getX());
		break;
		}
		
		
	   }*/
}

