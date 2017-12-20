package Model;

import Controller.Controller;

public class Monstre extends Personnage {
	private int vitesseMonstre = 0;
	private int incrementeur = 0;
	
	public Monstre(int x,int y) {
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
	
	public void SetVitesse(int l) {
		vitesseMonstre = l;
	}
	public void NextPoint() {
		if(vitesseMonstre <= incrementeur) {
			CarteDistance carte = Controller.getCarte();
			Point selection = Model.getLabyrinth().getPosition(super.GetPosition().GetX(),super.GetPosition().GetY());
			if(selection.GetEst() != null && carte.tab[selection.GetEst().GetX()][selection.GetEst().GetY()] < carte.tab[selection.GetX()][selection.GetY()]){
				selection = selection.GetEst();
				super.setTurn(3);
			}
			else if(selection.GetNord() != null && carte.tab[selection.GetNord().GetX()][selection.GetNord().GetY()] < carte.tab[selection.GetX()][selection.GetY()]){ 
				selection = selection.GetNord();
				super.setTurn(1);
			}
			else if(selection.GetOuest() != null && carte.tab[selection.GetOuest().GetX()][selection.GetOuest().GetY()] < carte.tab[selection.GetX()][selection.GetY()]){
				selection = selection.GetOuest();
				super.setTurn(2);
			}
			else if(selection.GetSud() != null && carte.tab[selection.GetSud().GetX()][selection.GetSud().GetY()] < carte.tab[selection.GetX()][selection.GetY()]) {
				selection = selection.GetSud();
				super.setTurn(0);
			}
			super.SetPosition(new Point(selection.GetX(),selection.GetY()));
			incrementeur = 0;
		}
		else
			incrementeur++;
}
}
