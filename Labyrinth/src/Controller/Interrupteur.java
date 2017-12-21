package Controller;

import Model.Arrete;
import Model.Point;

public class Interrupteur {
	public enum Action {
		ouvrir,
		fermer;
	}
	private Point position;
	private Arrete porte;
	private Action actionPorte;
	public Interrupteur(Point position,Arrete porte,Action actionPorte) {
		this.position = position;
		this.porte = porte;
		this.actionPorte = actionPorte;
	}
	public Action GetAction() {
		return actionPorte;
	}
	public Point GetPosition() {
		return position;
	}
	public void interrupteurActionner() {
		if(actionPorte == Action.ouvrir)
			porte.setType(Matiere.corridor);
		else
			porte.setType(Matiere.mur);
	}
}
