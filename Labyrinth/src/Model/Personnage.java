package Model;

public class Personnage {
	
	private Point position;
	private int turn;
	private boolean move;
	
	protected String[] tabGauche = new String[3];
	protected String[] tabDroite = new String[3];
	protected String[] tabBas = new String[3];
	protected String[] tabHaut = new String[3];
	
	public Personnage(int x, int y) {
		position = new Point(x,y);
		turn = 0;
		move = false;
	}
	
	public void SetPosition(Point p) {
		if(p != null)
			position = p;
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
	
	public boolean getMove() {
		return move;
	}
	
	public void setMove(boolean b) {
		move = b;
	}
	public String[] getIdImage(){
		if(turn == 0)
			return tabBas;
		else if(turn == 1)
			return tabHaut;
		else if(turn == 2)
			return tabGauche;
		else
			return tabDroite;
	}
}
