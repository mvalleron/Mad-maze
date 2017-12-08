package Model;

public class Model {
	
	private Graph labyrinth;

	public Model() {
		labyrinth =  Graph.getInstance();
	}

	public Graph getLabyrinth() {
		return labyrinth;
	}

	public void setLabyrinth(Graph labyrinth) {
		this.labyrinth = labyrinth;
	}

}