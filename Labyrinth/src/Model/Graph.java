package Model;

public class Graph implements Comparable<Graph>{
	private static Graph instance = null;
	private Point[] voisin;
	private int w;
	private int h;
	
	public Point GetPoint(int x, int y){
		return voisin[x+y*w];
	}
	
	public int GetSize(){
		return w*h;
	}
	
	//Creer le labyrinth à condition qu'il soit initier.
	public void CreatLabyrinth(){
		while(!estComplet()){
			Point currentCase = PremierLibre();
			while(currentCase.compareTo(voisin[w*h-1]) != 0 && !currentCase.AucunVoisinDispo())
			{
				currentCase = currentCase.RecupererSommet();
			}
		}
	}
	
	//Retourne un sommet seul et le rattache au reste du graph
	private Point PremierLibre() {
		for(int i = 0; i < h*w;i++){
			if(voisin[i].estSeul()){
				if(i!=0)
				{
					voisin[i].rattacherAuReste();
				}
				return voisin[i];
			}
		}
		return null;
	}
	
	//retourne vrai si aucun sommet du graph n est seul
	private boolean estComplet() {
		for(int x = 0;x<h*w;x++){
			if(voisin[x].estSeul()){
				return false;
			}
		}
		return true;
	}
	public Graph(){
		w = 10;
		h = 10;
		voisin = new Point[10*10];
		initGraph();
	}
	public Graph(int w, int h){
		this.w = w;
		this.h = h;
		voisin = new Point[h * w];
		initGraph();
	}
	
	//Initialise le graph en creant tout les sommets et en les connectant entre eux via des arrete tous initialiser a mur
	public void initGraph(){
		for(int y=0; y<h;y++){
			for(int x=0; x<w; x++){
				voisin[x+y*w]= new Point(x,y);
			}
		}
		for(int y=0; y<h;y++){
			for(int x=0; x<w; x++){
				if(x<w-1)
					voisin[x+y*w].SetEst(voisin[x+y*w+1]);
				else
					voisin[x+y*w].SetEst(null);
				if(y<h-1)
					voisin[x+y*w].SetSud(voisin[x+(y+1)*w]);
				else
					voisin[x+y*w].SetSud(null);
				if(x==0)
					voisin[x+y*w].SetOuest(null);
				if(y==0)
					voisin[x+y*w].SetNord(null);
			}
		}
		CreatLabyrinth();
	}
	public Point getPosition(int x,int y){
		return voisin[x+y*w];
	}
	public int compareTo(Graph o) {
		return this.voisin[0].compareTo(o.voisin[0]);
	}
	public String toString(){
		return h + "" + w;
	}
	public static Graph getInstance () {
		if (instance==null)
			instance=new Graph();
		return (instance);
	}
}


