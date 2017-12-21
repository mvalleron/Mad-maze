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
//import Controller.Interrupteur.Action;
import Model.Arrete.Matiere;

public class Controller implements ActionListener
{
	private static Joueur joueur;
	private static Controller instance = null;
	private static Model model;
	private static AnimationTimer timer;
	private static Point arrivee;
	private static CarteDistance carte;
	private static Point[] coins;
	//private static boolean demarrage;
	
	private static Monstre[] monstres;
	private static int nbMonstre = 1;
	private static int vitesseMonstre = 15;
	
	private static Interrupteur[] portes;
	private static int nbPorte = 1;
	
	private static int tailleLabyrinthe = 10;
	private static int nbcoins = 3;
	
	private static Score score;
	private static int niveau = 0;
	
	public static int getScore(){
		return score.GetScore();
	}
	
	//private static int turn = 0;
	//private static boolean move = false;
	
	public static int getNbCoins() {
		return nbcoins;
	}
	
	
	public static int getTurn(int i) {
		if(i==nbMonstre)
			return joueur.getTurn();
		else
			return monstres[i].getTurn();
	}

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
		score = new Score();
		joueur = new Joueur(0,0);
		arrivee = new Point(tailleLabyrinthe-1,tailleLabyrinthe-1);
		initCoins();
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
			if(monstres[i].GetPosition().compareTo(arrivee)==0)
				i--;
			monstres[i].SetVitesse(vitesseMonstre);
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
				porte = Model.getLabyrinth().GetPorte();
			}while(porte.getPoint1().compareTo(joueur.GetPosition()) == 0 || porte.getPoint2().compareTo(joueur.GetPosition()) == 0 || porte.getPoint1().compareTo(arrivee) == 0 || porte.getPoint2().compareTo(arrivee) == 0);
			Point interrupteurOuverture;
			do {
				interrupteurOuverture = new Point((int)(Math.random()*(tailleLabyrinthe-1)),(int)(Math.random()*(tailleLabyrinthe-1)));
			}while(carte.tab[interrupteurOuverture.GetX()][interrupteurOuverture.GetY()]>carte.tab[porte.getPoint1().GetX()][porte.getPoint1().GetY()] || carte.tab[interrupteurOuverture.GetX()][interrupteurOuverture.GetY()]>carte.tab[porte.getPoint2().GetX()][porte.getPoint2().GetY()] || carte.tab[interrupteurOuverture.GetX()][interrupteurOuverture.GetY()] == -1);
			Point interrupteurFermeture;
			do {
				interrupteurFermeture = new Point((int)(Math.random()*(tailleLabyrinthe-1)),(int)(Math.random()*(tailleLabyrinthe-1)));
			}while(carte.tab[interrupteurFermeture.GetX()][interrupteurFermeture.GetY()]<carte.tab[porte.getPoint1().GetX()][porte.getPoint1().GetY()] || carte.tab[interrupteurFermeture.GetX()][interrupteurFermeture.GetY()]<carte.tab[porte.getPoint2().GetX()][porte.getPoint2().GetY()] || carte.tab[interrupteurFermeture.GetX()][interrupteurFermeture.GetY()] == -1);
			portes[i] = new Interrupteur(interrupteurOuverture,porte,Interrupteur.Action.ouvrir);
			i++;
			portes[i] = new Interrupteur(interrupteurFermeture,porte,Interrupteur.Action.fermer);
			i++;
			porte.setType(Matiere.mur);
		}
		
	}
	
	public static Interrupteur.Action GetTypeInterrupteur(int i) {
		return portes[i].GetAction();
	}
	
	public static int getNbMonster() {
		return nbMonstre;
	}
	
	public static int getNbInterrupteur() {
		return nbPorte*2;
	}
	
	public static Point positionMonstre(int i) {
		if(i == nbMonstre)
			return joueur.GetPosition();
		return monstres[i].GetPosition();
	}
	
	public static Point positionInterrupteur(int i) {
		return portes[i].GetPosition();
}

	public static void start(Stage primaryStage) throws Exception {
		View.getInstance().createGlobalView(primaryStage, Model.getLabyrinth());
		View.getInstance().raffraichir(Model.getLabyrinth());
		primaryStage.show();
		timer = new AnimationTimer(getInstance());
		timer.start();
	}
	private static void GameOver() {
		for(int i = 0; i < nbMonstre; i++) {
			if(joueur.GetPosition().compareTo(monstres[i].GetPosition()) == 0) {
				joueur.SetPosition(new Point(0,0));
				arrivee = new Point(tailleLabyrinthe-1,tailleLabyrinthe-1);
				model.setLabyrinth(new Graph(tailleLabyrinthe,tailleLabyrinthe));
				initMonstre();
				initPorte();
				score.PerdreScore();
				break;
			}
}
		if(joueur.GetPosition().compareTo(arrivee) == 0) {
			score.AjouterScore(nbcoins*100);
			niveau++;
			tailleLabyrinthe++;
			joueur.SetPosition(new Point(0,0));
			arrivee = new Point(tailleLabyrinthe-1,tailleLabyrinthe-1);
			model.setLabyrinth(new Graph(tailleLabyrinthe,tailleLabyrinthe));
			initMonstre();
			initPorte();
			nbcoins++;
			initCoins();
		}
	}
	
	private static void initCoins() {
		coins = new Point[nbcoins];
		
		int i = 0;
		while(i!=nbcoins) {
			coins[i] = new Point((int)(Math.random()*(tailleLabyrinthe-1)),(int)(Math.random()*(tailleLabyrinthe-1)));
			if(coins[i].compareTo(joueur.GetPosition()) == 0) {
				i--;
			}
			if(coins[i].compareTo(arrivee) == 0) {
				i--;
			}
			for(int j = i-1; j>=0; j--) {
				if(coins[i].compareTo(coins[j]) == 0) {
						i--;
				}
			}
			i ++;
		}
		
	}

	private static void AttraperPiece() {
		for(int i = 0; i<nbcoins; i++) {
			if(joueur.GetPosition().compareTo(getCoin(i)) == 0) {
				coins[i] = new Point(-1,-1);
				score.AjouterScore(100);
			}
		}
	}	
	
	public static EventHandler<KeyEvent> eventHandlerButton = new EventHandler<KeyEvent>() {
		public void handle(KeyEvent event) {
				
			int x =joueur.GetPosition().GetX();
			int y =joueur.GetPosition().GetY();
			if(event.getCode().equals(KeyCode.UP)) {
				//turn = 1;
				joueur.setTurn(1);
				joueur.SetPosition(Model.getLabyrinth().GetPoint(x, y).GetNord());
			}
			else if(event.getCode()==KeyCode.DOWN) {
				//turn = 0;
				joueur.setTurn(0);
				joueur.SetPosition(Model.getLabyrinth().GetPoint(x, y).GetSud());
			}
			else if(event.getCode()==KeyCode.LEFT) {
				//turn = 2;
				joueur.setTurn(2);
				joueur.SetPosition(Model.getLabyrinth().GetPoint(x, y).GetOuest());
			}
			else if(event.getCode()==KeyCode.RIGHT) {
				//turn = 3;
				joueur.setTurn(3);
				joueur.SetPosition(Model.getLabyrinth().GetPoint(x, y).GetEst());
			} 
			GameOver();
			AttraperPiece();
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
                            	View.getInstance().raffraichir(model.getLabyrinth());
                            	GameOver();
                            }
                        });   
                    }
                },1,1,TimeUnit.MILLISECONDS);	
	}

	public static Point getCoin(int i) {
		return coins[i];
	}

	public static String getIdImage(int i) {
		if(i == nbMonstre)
			return joueur.getIdImage();
		return monstres[i].getIdImage();
	}
}

