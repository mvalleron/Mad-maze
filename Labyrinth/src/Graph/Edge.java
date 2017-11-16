package Graph;

import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge implements Comparable<Edge> {
	public int compareTo(Edge o) {
		int sourceDiff = ((Vertex)this.getSource()).compareTo((Vertex)o.getSource());
		if(sourceDiff!=0)
			return sourceDiff;
		else
			return ((Vertex)this.getTarget()).compareTo((Vertex)o.getTarget());
	}

}
