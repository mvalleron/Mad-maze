package Controller;




import javafx.event.*;
import javafx.scene.input.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.*;
import View.*;



public class Controller implements ActionListener
{
	private static Joueur joueur;
	private static Controller instance = null;
	private static Model model;
	private static AnimationTimer timer;
	private static Point arriver;
	
	private static int tailleLabyrinthe = 10;

	public static Point GetJoueurPosition() {
		return joueur.GetPosition();
	}
	
	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	public Controller () {
		joueur = new Joueur(0,0);
		arriver = new Point(tailleLabyrinthe-1,tailleLabyrinthe-1);
		View.getInstance();
	    model= new Model();
	}

	public static void start(Stage primaryStage) throws Exception {
		ViewGame.getInstance().createGlobalView(primaryStage, model.getLabyrinth());
		primaryStage.show();
		timer = new AnimationTimer(getInstance());
		timer.start();

	}
	public static EventHandler<KeyEvent> eventHandlerButton = new EventHandler<KeyEvent>() {
		public void handle(KeyEvent event) {
			int x =joueur.GetPosition().GetX();
			int y =joueur.GetPosition().GetY();
			if(event.getCode().equals(KeyCode.UP))
				joueur.SetPosition(Model.getLabyrinth().GetPoint(x, y).GetNord());
			else if(event.getCode()==KeyCode.DOWN)
				joueur.SetPosition(Model.getLabyrinth().GetPoint(x, y).GetSud());
			else if(event.getCode()==KeyCode.LEFT)
				joueur.SetPosition(Model.getLabyrinth().GetPoint(x, y).GetOuest());
			else if(event.getCode()==KeyCode.RIGHT)
				joueur.SetPosition(Model.getLabyrinth().GetPoint(x, y).GetEst());
			ViewGame.getInstance().raffraichir(model.getLabyrinth());
			GameOver();	
		}

		private void GameOver() {
			if(joueur.GetPosition().compareTo(arriver) == 0) {
				tailleLabyrinthe++;
				joueur.SetPosition(new Point(0,0));
				arriver = new Point(tailleLabyrinthe-1,tailleLabyrinthe-1);
				model.setLabyrinth(new Graph(tailleLabyrinthe,tailleLabyrinthe));
				ViewGame.getInstance().raffraichir(model.getLabyrinth());
			}
			
		}	
	};
	public void actionPerformed(ActionEvent arg0) {
		//ViewGame.getInstance().raffraichir(model.getLabyrinth());
	}
}

