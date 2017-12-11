package View;




import Model.Graph;
import Model.Point;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class FrameView extends Pane{
    private static FrameView labyrinthView = null;

    static final int SPAN = 2;
    static final int WALL = 3;
    static final int CELL = 9;
    
    public static final Paint WALL_COLOR = Color.BURLYWOOD;

    static Scene scene;
    static Pane pane = new Pane();
    static int sizeX;
    static int sizeY;
    static boolean init;

	private Stage stage;
    
    
    private FrameView() {
    	this.requestLayout();
    }
    
    public static FrameView getInstance() {
        if (labyrinthView == null)
            labyrinthView = new FrameView();
        return labyrinthView;
    }
    public void paint() {
    	getChildren().clear();
    	int nbrX = sizeX;
    	int nbrY = sizeY;
        Rectangle square;

        square = new Rectangle(0, 0,SPAN * (nbrX * (CELL + WALL) + WALL),WALL * SPAN);
        square.setFill(WALL_COLOR);
        getChildren().add(square);

        square = new Rectangle(0, SPAN * (nbrY * (CELL + WALL)),SPAN * (nbrX * (CELL + WALL) + WALL),WALL * SPAN);
        square.setFill(WALL_COLOR);
        getChildren().add(square);

        square = new Rectangle(0, 0, WALL * SPAN,SPAN * (nbrY * (CELL + WALL) + WALL));
        square.setFill(WALL_COLOR);
        getChildren().add(square);

        square = new Rectangle(SPAN * (nbrX * (CELL + WALL)), 0,WALL * SPAN,SPAN * (nbrY * (CELL + WALL) + WALL));
        square.setFill(WALL_COLOR);
        getChildren().add(square);

        for (int x = 0; x < nbrX - 1; ++x) {
            int offsetX = ((WALL + CELL) + (WALL + CELL) * x) * SPAN;
            for (int y = 0; y < nbrY - 1; ++y) {
                int offsetY = ((WALL + CELL) + (WALL + CELL) * y) * SPAN;
                square = new Rectangle(offsetX, offsetY, WALL * SPAN, WALL * SPAN);
                square.setFill(WALL_COLOR);
                getChildren().add(square);

            }
        }
        Image image = new Image("porte.png");
        int xp =(sizeX)*(CELL*SPAN+WALL*SPAN);
        int yp =(sizeY)*(CELL*SPAN+WALL*SPAN);
        square = new Rectangle(xp-(CELL*SPAN+WALL*SPAN),yp-(CELL*SPAN+WALL*SPAN) ,CELL*WALL+SPAN,CELL*WALL+SPAN);
        ImagePattern imagePattern = new ImagePattern(image);
        square.setFill(imagePattern);
        getChildren().add(square);
        for(int i = 0; i<Controller.Controller.getNbMonster();i++){
        	image = new Image("bas1.png");
            xp =Controller.Controller.positionMonstre(i).GetX()*(CELL*SPAN+WALL*SPAN);
            yp =Controller.Controller.positionMonstre(i).GetY()*(CELL*SPAN+WALL*SPAN);
            square = new Rectangle(xp-(CELL*SPAN+WALL*SPAN),yp-(CELL*SPAN+WALL*SPAN) ,CELL*CELL,CELL*CELL);
            imagePattern = new ImagePattern(image);
            square.setFill(imagePattern);
            getChildren().add(square);
    	}
        image = new Image("bas1.png");
        xp =Controller.Controller.GetJoueurPosition().GetX()*(CELL*SPAN+WALL*SPAN);
        yp =Controller.Controller.GetJoueurPosition().GetY()*(CELL*SPAN+WALL*SPAN);
        square = new Rectangle(xp-(CELL*SPAN+WALL*SPAN),yp-(CELL*SPAN+WALL*SPAN) ,CELL*CELL,CELL*CELL);
        imagePattern = new ImagePattern(image);
        square.setFill(imagePattern);
        getChildren().add(square);
        drawWall(this, Model.Model.getLabyrinth(),WALL_COLOR);
    }
    public void rafraichir(Graph g) {
    	sizeX = (int)Math.sqrt(g.GetSize());
    	sizeY = (int)Math.sqrt(g.GetSize());
        int width = ((WALL + CELL) * sizeX + WALL) * SPAN + CELL*SPAN-SPAN;
        int height = ((WALL + CELL) * sizeX + WALL) * SPAN +CELL*SPAN*2+SPAN;
    	stage.setWidth(width);
    	stage.setHeight(height);
    	paint();
    }

    public void drawFrame(Stage stage,Scene scene, Pane pane, int nbrX, int nbrY,Graph g) {
    	this.stage = stage;
    	init = true;
    	sizeX = nbrX;
    	sizeY = nbrY;
        int width = ((WALL + CELL) * nbrX + WALL) * SPAN;
        int height = ((WALL + CELL) * nbrY + WALL) * SPAN;
        scene = new Scene(this, width, height);
        scene.setOnKeyPressed(Controller.Controller.eventHandlerButton);
        stage.setScene(scene);
        paint();
    }

    public void drawWall(Pane pane, Graph g, Paint color) {
        int x = 0, y = 0, xspan = 0, yspan = 0;
        int tmp = (int)Math.sqrt(g.GetSize());
        for(int i = 0; i < g.GetSize();i++){
        	Point p = g.GetPoint(i%tmp, i/tmp);
            xspan = WALL*SPAN;
            yspan = CELL*SPAN;
    		
        	if(p.GetEst() == null){
        		x =((WALL + CELL)*((i+1)%tmp))*SPAN;
        		y=(WALL + ((i+1)/tmp)*(WALL + CELL))*SPAN;
        		Rectangle square = new Rectangle(x, y, xspan, yspan);
        		square.setFill(color);
        		pane.getChildren().add(square);
        	}
        	if(p.GetNord() == null){
        		 x =(WALL + ((i)%tmp)*(WALL + CELL))*SPAN;
        		 y =((WALL + CELL)*((i)/tmp))*SPAN;
        		Rectangle square = new Rectangle(x, y, yspan, xspan);
        		square.setFill(color);
        		pane.getChildren().add(square);
        	}
        	if(p.GetOuest() == null){
        		x=((WALL + CELL)*((i)%tmp))*SPAN;
        		y=(WALL + ((i)/tmp)*(WALL + CELL))*SPAN;
        		Rectangle square = new Rectangle(x, y, xspan, yspan);
        		square.setFill(color);
        		pane.getChildren().add(square);
        	}
        	if(p.GetSud() == null){
        		x=(WALL + ((i+sizeX)%tmp)*(WALL + CELL))*SPAN;
        		y=((WALL + CELL)*((i+sizeX)/tmp))*SPAN;
        		Rectangle square = new Rectangle(x, y, yspan, xspan);
        		square.setFill(color);
        		pane.getChildren().add(square);
        	}
        }
    }

	public static boolean GetInit() {
		return init;
	}
}
