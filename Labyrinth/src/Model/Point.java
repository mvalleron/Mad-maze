package Model;

import Model.Arrete.Matiere;

public class Point implements Comparable<Point>{
	private int x;
	private int y;
	private Arrete[] voisin;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		this.voisin = new Arrete[4];
	}
	public int GetX(){
		return x;
	}
	public int GetY(){
		return y;
	}
	public void SetSud(Point p){
		voisin[3] = new Arrete(this,p);
		if(p != null)
			p.voisin[1] = voisin[3];
	}
	public void SetNord(Point p){
		voisin[1] = new Arrete(this,p);
		if(p != null)
			p.voisin[3] = voisin[1];
	}
	public void SetOuest(Point p){
		voisin[0] = new Arrete(this,p);
		if(p != null)
			p.voisin[2] = voisin[0];
	}
	public void SetEst(Point p){
		voisin[2] = new Arrete(this,p);
		if(p != null)
			p.voisin[0] = voisin[2];
	}
	public Point GetSud(){
		if(voisin[3].getType() == Matiere.mur && voisin[3].getPoint1() == null || voisin[3].getPoint2() == null){
			return null;
		}
		if(this.compareTo(voisin[3].getPoint1()) == 0){
			return voisin[3].getPoint2();
		}
		return voisin[3].getPoint1();
	}
	public Point GetNord(){
		if(voisin[1].getType() == Matiere.mur && voisin[1].getPoint1() == null || voisin[1].getPoint2() == null){
			return null;
		}
		if(this.compareTo(voisin[1].getPoint1()) == 0){
			return voisin[1].getPoint2();
		}
		return voisin[1].getPoint1();
	}
	public Point GetOuest(){
		if(voisin[0].getType() == Matiere.mur && voisin[0].getPoint1() == null || voisin[0].getPoint2() == null){
			return null;
		}
		if(this.compareTo(voisin[0].getPoint1()) == 0){
			return voisin[0].getPoint2();
		}
		return voisin[0].getPoint1();
	}
	public Point GetEst(){
		if(voisin[2].getType() == Matiere.mur && voisin[2].getPoint1() == null || voisin[2].getPoint2() == null){
			return null;
		}
		if(this.compareTo(voisin[2].getPoint1()) == 0){
			return voisin[2].getPoint2();
		}
		return voisin[2].getPoint1();
	}
	public float Distance(Point p1,Point p2){
		return (float) (Math.pow(p1.GetX()-p2.GetX(),2)+Math.pow(p1.GetY()-p2.GetY(),2));
	}
	public int compareTo(Point position) {
		if(this.x>position.x){
			return 1;
		}
		else if(this.x < position.x){
			return -1;
		}
		else{
			if(this.y >position.y){
				return 1;
			}
			if(this.y < position.y){
				return -1;
			}
			return 0;
		}
	}
	public boolean estSeul() {
		return GetEst() == null && GetOuest() == null && GetNord() == null && GetSud() == null;
	}
	public void rattacherAuReste() {
		int tmp = (int) (Math.random()*4);
		while(voisin[tmp].getType() == Matiere.corridor && voisin[tmp].Voisin(this) != null && !voisin[tmp].Voisin(this).estSeul()){
			tmp = (int) (Math.random()*4);
		} 
		voisin[tmp].setType(Matiere.corridor); 
	}
	public boolean AucunVoisinDispo() {
		return !voisin[0].Voisin(this).estSeul() && !voisin[1].Voisin(this).estSeul() && !voisin[2].Voisin(this).estSeul() && !voisin[3].Voisin(this).estSeul();
	}
	public Point RecupererSommet() {
		int tmp = (int) (Math.random()*4);
		while(voisin[tmp].Voisin(this) == null && !voisin[tmp].Voisin(this).estSeul()){
			tmp = (int) (Math.random()*4);
		}
		return voisin[tmp].Voisin(this);
	}
}
