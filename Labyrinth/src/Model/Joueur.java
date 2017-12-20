package Model;

public class Joueur extends Personnage {
	
	public Joueur(int x, int y) {
		super(x,y);
		super.tabBas[0] = "down1.png";
		super.tabBas[1] = "down2.png";
		super.tabBas[2] = "downneutral.png";
		
		super.tabHaut[0] = "up1.png";
		super.tabHaut[1] = "up2.png";
		super.tabHaut[2] = "upneutral.png";
		
		super.tabDroite[0] = "right1.png";
		super.tabDroite[1] = "right2.png";
		super.tabDroite[2] = "rightneutral.png";
		
		super.tabGauche[0] = "left1.png";
		super.tabGauche[1] = "left2.png";
		super.tabGauche[2] = "leftneutral.png";
	}
}
