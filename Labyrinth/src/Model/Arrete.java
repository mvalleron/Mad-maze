package Model;

public class Arrete implements Comparable<Arrete>{
	private Point[] extremite;
	public enum Matiere {
		corridor,
		mur;
	}
	public Arrete(Point a,Point b){
		extremite = new Point[2];
		type = Matiere.mur;
		extremite[0] = a;
		extremite[1] = b;
	}
	public void setType(Matiere t){
		type = t;
	}
	public Matiere getType(){
		return type;
	}
	public Point getPoint1(){
		return extremite[0];
	}
	public Point getPoint2(){
		return extremite[1];
	}
	private Matiere type;
	public int compareTo(Arrete o) {
		if(extremite[0].compareTo(o.extremite[0]) == 0 || extremite[0].compareTo(o.extremite[1]) == 0){
			if(extremite[1].compareTo(o.extremite[1]) == 0 || extremite[1].compareTo(o.extremite[0]) == 0){
				return 0;
			}
			return -1;
		}
		return 1;
	}
	
	//retourne le voisin par rapport a cette arrete du point passé en commentaire, null si il y en a pas 
	public Point Voisin(Point point) {
		if(extremite[0] == null || extremite[1] == null){
			return null;
		}
		if(extremite[0].compareTo(point) == 0){
			return extremite[1];
		}
		return extremite[0];
	}
}
