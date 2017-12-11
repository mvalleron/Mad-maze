package Controller;




import javafx.event.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.application.Platform;
import java.util.concurrent.TimeUnit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import Model.*;
import View.*;



public class Controller implements ActionListener
{
	private static Joueur joueur;
	private static Controller instance = null;
	private static Model model;
	private static AnimationTimer timer;
	private static Point arriver;
	private static boolean demarrage;
	private static CarteDistance carte;
	private static Monstre[] monstres;
	
	private static int nbMonstre = 2;
	private static int tailleLabyrinthe = 10;

	public static Point GetJoueurPosition() {
		return joueur.GetPosition();
	}
	public static CarteDistance getCarte() {
		return carte;
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
	    carte = new CarteDistance();
	    monstres = new Monstre[nbMonstre];
	    initMonstre();
	}

	private static void initMonstre() {
		int i = 0;
		while(i < nbMonstre) {
			monstres[i] =  new Monstre((int)(Math.random()*(tailleLabyrinthe-1)),(int)(Math.random()*(tailleLabyrinthe-1)));
			if(monstres[i].GetPosition().compareTo(joueur.GetPosition())==0)
				i--;
			if(monstres[i].GetPosition().compareTo(arriver)==0)
				i--;
			i++;
		}
		
	}
	public static int getNbMonster() {
		return nbMonstre;
	}
	public static Point positionMonstre(int i) {
		return monstres[i].GetPosition();
	}
	public static void start(Stage primaryStage) throws Exception {
		ViewGame.getInstance().createGlobalView(primaryStage, model.getLabyrinth());
		ViewGame.getInstance().raffraichir(model.getLabyrinth());
		primaryStage.show();
		timer = new AnimationTimer(getInstance());
		timer.start();
	}
	private static void GameOver() {
		if(joueur.GetPosition().compareTo(arriver) == 0) {
			tailleLabyrinthe++;
			joueur.SetPosition(new Point(0,0));
			arriver = new Point(tailleLabyrinthe-1,tailleLabyrinthe-1);
			model.setLabyrinth(new Graph(tailleLabyrinthe,tailleLabyrinthe));
			nbMonstre++;
			initMonstre();
		}
		
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
			GameOver();	
			carte = new CarteDistance();
		}	
	};
	public void actionPerformed(ActionEvent arg0) {
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(
				new Runnable(){
                	public void run() {
                    	scheduler.shutdown();
                        Platform.runLater(new Runnable(){
                            public void run() {
                            	for(int i = 0; i<nbMonstre;i++){
                            		monstres[i].NextPoint();
                            	}
                            	ViewGame.getInstance().raffraichir(model.getLabyrinth());
                            }
                        });   
                    }
                },1,1,TimeUnit.MILLISECONDS);	
	}
}

