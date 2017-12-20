package Controller;

public class Score {
	private int valeur;
	public Score(){
		valeur = 0;
	}
	public void AjouterScore(int valeur){
		this.valeur += valeur;
	}
	public int GetScore(){
		return valeur;
	}
	public void PerdreScore(){
		valeur -= 200;
		if(valeur < 0)
			valeur = 0;
	}
}
