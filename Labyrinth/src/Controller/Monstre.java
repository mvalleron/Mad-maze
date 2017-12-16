package Controller;

import Model.CarteDistance;
import Model.Point;

public class Monstre {
	private Point position;
	private int lenteurMonstre = 0;
	private int incrementeur = 0;
	public Monstre(int x,int y) {
		position = new Point(x,y);
	}
	public void SetLenteur(int l) {
		lenteurMonstre = l;
	}
	public void SetPosition(Point p) {
		if(p != null)
			position = p;
	}
	public Point GetPosition() {
		return position;
	}
	public void NextPoint() {
		if(lenteurMonstre <= incrementeur) {
			CarteDistance carte = Controller.getCarte();
			Point selection = Model.Model.getLabyrinth().getPosition(position.GetX(),position.GetY());
			if(selection.GetEst() != null && carte.tab[selection.GetEst().GetX()][selection.GetEst().GetY()] < carte.tab[selection.GetX()][selection.GetY()]) 
				selection = selection.GetEst();
			else if(selection.GetNord() != null && carte.tab[selection.GetNord().GetX()][selection.GetNord().GetY()] < carte.tab[selection.GetX()][selection.GetY()]) 
				selection = selection.GetNord();
			else if(selection.GetOuest() != null && carte.tab[selection.GetOuest().GetX()][selection.GetOuest().GetY()] < carte.tab[selection.GetX()][selection.GetY()]) 
				selection = selection.GetOuest();
			else if(selection.GetSud() != null && carte.tab[selection.GetSud().GetX()][selection.GetSud().GetY()] < carte.tab[selection.GetX()][selection.GetY()]) 
				selection = selection.GetSud();
			position = new Point(selection.GetX(),selection.GetY());
			incrementeur = 0;
		}
		else
			incrementeur++;
	}
}
