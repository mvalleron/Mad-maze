package Controller;

import Model.CarteDistance;
import Model.Point;

public class Monstre {
	private Point position;
	public Monstre(int x,int y) {
		position = new Point(x,y);
	}
	public void SetPosition(Point p) {
		if(p != null)
			position = p;
	}
	public Point GetPosition() {
		return position;
	}
	public void NextPoint() {
		CarteDistance carte = Controller.getCarte();
		Point selection = Model.Model.getLabyrinth().getPosition(position.GetX(),position.GetY());
		Point tmp = selection.GetEst();
		if(tmp != null && carte.tab[tmp.GetX()][tmp.GetY()] < carte.tab[selection.GetX()][selection.GetY()]) 
			selection = tmp;
		tmp = selection.GetNord();
		if(tmp != null && carte.tab[tmp.GetX()][tmp.GetY()] < carte.tab[selection.GetX()][selection.GetY()]) 
			selection = tmp;
		tmp = selection.GetOuest();
		if(tmp != null && carte.tab[tmp.GetX()][tmp.GetY()] < carte.tab[selection.GetX()][selection.GetY()]) 
			selection = tmp;
		tmp = selection.GetSud() ;
		if(tmp != null && carte.tab[tmp.GetX()][tmp.GetY()] < carte.tab[selection.GetX()][selection.GetY()]) 
			selection = tmp;
		position = new Point(selection.GetX(),selection.GetY());
	}
}
