package Graph;

public class Vertex implements Comparable<Vertex>{
	private Point position;
	public Vertex(){
		position = new Point(0,0);
	}
	public Vertex(Point p){
		position = p;
	}
	public Point getPosition(){
		return position;
	}
	public int compareTo(Vertex o) {
		return this.position.compareTo(o.position);
	}
}
