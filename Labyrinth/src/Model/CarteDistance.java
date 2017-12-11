package Model;

import Controller.Controller;
import Controller.Joueur;

public class CarteDistance {
	public int[][] tab;
	Point cible;
	public CarteDistance() {
		cible = Controller.GetJoueurPosition();
		Graph g = Model.getLabyrinth();
		tab = new int[(int)(Math.sqrt(g.GetSize()))][(int)(Math.sqrt(g.GetSize()))];
		Point p = g.getPosition(cible.GetX(), cible.GetY());
		for(int x = 0; x< (int)(Math.sqrt(g.GetSize()));x++)
			for(int y = 0; y< (int)(Math.sqrt(g.GetSize()));y++)
				tab[x][y] = -1;
		PointSuivant(p,0);
	}
	public void PointSuivant(Point actuelle,int valeur) {
		if(tab[actuelle.GetX()][actuelle.GetY()] == -1) {
			tab[actuelle.GetX()][actuelle.GetY()] =1+valeur++;
			if(actuelle.GetEst() != null)
				PointSuivant(actuelle.GetEst(),valeur);
			if(actuelle.GetNord() != null)
				PointSuivant(actuelle.GetNord(),valeur);
			if(actuelle.GetOuest() != null)
				PointSuivant(actuelle.GetOuest(),valeur);
			if(actuelle.GetSud() != null)
				PointSuivant(actuelle.GetSud() ,valeur);
		}
	}
}