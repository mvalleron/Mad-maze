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
import Controller.Interrupteur.Action;
import Model.Arrete.Matiere;


public class Controller implements ActionListener
{
	private static Joueur joueur;
	private static Controller instance = null;
	private static Model model;
	private static AnimationTimer timer;
	private static Point arriver;
	private static CarteDistance carte;
	private static Monstre[] monstres;
	private static Interrupteur[] portes;
	private static int nbPorte = 1;
	
	private static int nbMonstre = 1;
	private static int tailleLabyrinthe = 10;
	private static int lenteurMonstre = 30;

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
	    initMonstre();
	    initPorte();
	}

	private static void initMonstre() {
		int i = 0;
		monstres = new Monstre[nbMonstre];
		while(i < nbMonstre) {
			monstres[i] =  new Monstre((int)(Math.random()*(tailleLabyrinthe-1)),(int)(Math.random()*(tailleLabyrinthe-1)));
			if(monstres[i].GetPosition().compareTo(joueur.GetPosition())==0)
				i--;
			if(monstres[i].GetPosition().compareTo(arriver)==0)
				i--;
			monstres[i].SetLenteur(lenteurMonstre);
			i++;
		}
		
	}
	private static void initPorte() {
		int i = 0;
		portes = new Interrupteur[nbPorte*2];
		while(i<nbPorte*2) {
			carte = new CarteDistance();
			Arrete porte;
			do {
				porte = model.getLabyrinth().GetPorte();
			}while(porte.getPoint1().compareTo(joueur.GetPosition()) == 0 || porte.getPoint2().compareTo(joueur.GetPosition()) == 0 || porte.getPoint1().compareTo(arriver) == 0 || porte.getPoint2().compareTo(arriver) == 0);
			Point interrupteurOuverture;
			do {
				interrupteurOuverture = new Point((int)(Math.random()*(tailleLabyrinthe-1)),(int)(Math.random()*(tailleLabyrinthe-1)));
			}while(carte.tab[interrupteurOuverture.GetX()][interrupteurOuverture.GetY()]>carte.tab[porte.getPoint1().GetX()][porte.getPoint1().GetY()] || carte.tab[interrupteurOuverture.GetX()][interrupteurOuverture.GetY()]>carte.tab[porte.getPoint2().GetX()][porte.getPoint2().GetY()] || carte.tab[interrupteurOuverture.GetX()][interrupteurOuverture.GetY()] == -1);
			Point interrupteurFermeture;
			do {
				interrupteurFermeture = new Point((int)(Math.random()*(tailleLabyrinthe-1)),(int)(Math.random()*(tailleLabyrinthe-1)));
			}while(carte.tab[interrupteurFermeture.GetX()][interrupteurFermeture.GetY()]<carte.tab[porte.getPoint1().GetX()][porte.getPoint1().GetY()] || carte.tab[interrupteurFermeture.GetX()][interrupteurFermeture.GetY()]<carte.tab[porte.getPoint2().GetX()][porte.getPoint2().GetY()] || carte.tab[interrupteurFermeture.GetX()][interrupteurFermeture.GetY()] == -1);
			portes[i] = new Interrupteur(interrupteurOuverture,porte,Action.ouvrir);
			i++;
			portes[i] = new Interrupteur(interrupteurFermeture,porte,Action.fermer);
			i++;
			porte.setType(Matiere.mur);
		}
		
	}
	public static Action GetTypeInterrupteur(int i) {
		return portes[i].GetAction();
	}
	public static int getNbMonster() {
		return nbMonstre;
	}
	public static int getNbInterrupteur() {
		return nbPorte*2;
	}
	public static Point positionMonstre(int i) {
		return monstres[i].GetPosition();
	}
	public static Point positionInterrupteur(int i) {
		return portes[i].GetPosition();
	}
	public static void start(Stage primaryStage) throws Exception {
		ViewGame.getInstance().createGlobalView(primaryStage, model.getLabyrinth());
		ViewGame.getInstance().raffraichir(model.getLabyrinth());
		primaryStage.show();
		timer = new AnimationTimer(getInstance());
		timer.start();
	}
	private static void GameOver() {
		for(int i = 0; i < nbMonstre; i++) {
			if(joueur.GetPosition().compareTo(monstres[i].GetPosition()) == 0) {
				joueur.SetPosition(new Point(0,0));
				arriver = new Point(tailleLabyrinthe-1,tailleLabyrinthe-1);
				model.setLabyrinth(new Graph(tailleLabyrinthe,tailleLabyrinthe));
				initMonstre();
				initPorte();
				break;
			}
		}
		if(joueur.GetPosition().compareTo(arriver) == 0) {
			tailleLabyrinthe++;
			joueur.SetPosition(new Point(0,0));
			arriver = new Point(tailleLabyrinthe-1,tailleLabyrinthe-1);
			model.setLabyrinth(new Graph(tailleLabyrinthe,tailleLabyrinthe));
			nbMonstre++;
			nbPorte++;
			lenteurMonstre --;
			initMonstre();
			initPorte();
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
			for(int i = 0; i<nbPorte*2;i++){
				if(portes[i].GetPosition().compareTo(joueur.GetPosition()) == 0)
					portes[i].interrupteurActionner();
			}
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
                            		GameOver();	
                            }
                        });   
                    }
                },1,1,TimeUnit.MILLISECONDS);	
	}
}


