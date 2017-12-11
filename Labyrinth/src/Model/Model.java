package Model;

public class Model {
	
	private static Graph labyrinth;

	public Model() {
		labyrinth =  Graph.getInstance();
	}

	public static Graph getLabyrinth() {
		return labyrinth;
	}

	public void setLabyrinth(Graph labyrinth) {
		this.labyrinth = labyrinth;
	}

}
