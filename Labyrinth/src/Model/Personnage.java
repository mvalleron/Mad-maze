package Model;

import Controller.Controller;

public class Personnage {
	
	private Point position;
	private int turn;
	private boolean direction;
	private boolean move;
	private int cpt = 0;
	
	protected String[] tabGauche = new String[3];
	protected String[] tabDroite = new String[3];
	protected String[] tabBas = new String[3];
	protected String[] tabHaut = new String[3];
	
	public Personnage(int x, int y) {
		position = new Point(x,y);
		turn = 0;
		move = false;
		direction = false;
	}
	
	public void SetPosition(Point p) {
		if(p != null){
			position = p;
			move = true;
		}
	}
	
	public Point GetPosition() {
		return position;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	
	public void setMove() {
		if(move){
			switch(cpt){
			case 0:
				cpt = 2;
				direction = true;
				break;
			case 1:
				cpt = 2;
				direction = false;
				break;
			case 2:
				if(direction)
					cpt = 1;
				else 
					cpt = 0;
				break;
			}
			move = false;
		}
	}
	public String getIdImage(){
		setMove();
		if(turn == 0)
			return tabBas[cpt];
		else if(turn == 1)
			return tabHaut[cpt];
		else if(turn == 2)
			return tabGauche[cpt];
		else
			return tabDroite[cpt];
	}
}
