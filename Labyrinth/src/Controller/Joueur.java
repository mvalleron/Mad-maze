package Controller;

import Model.Point;

public class Joueur {
	private Point position;
	public Joueur(int x,int y) {
		position = new Point(x,y);
	}
	public void SetPosition(Point p) {
		if(p != null)
			position = p;
	}
	public Point GetPosition() {
		return position;
	}
}
