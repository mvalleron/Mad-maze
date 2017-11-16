package Graph;

public class Point implements Comparable<Point>{
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int GetX(){
		return x;
	}
	public int GetY(){
		return y;
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
}
