package View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class Viewframe {
	
	static final int SPAN = 2; //pixels for a unit
	static final int WALL = 3; //thickness of the walls (in units)
	static final int CELL = 9; //size of the cells (in units)
	public static final Paint WALL_COLOR = Color.BURLYWOOD;

    static Scene scene;
    static Pane pane;
	public static final Paint WALLCOLOR = Color.BLACK;
	public static final Paint SCENECOLOR = Color.BLANCHEDALMOND;
	public static ImageView imageView;
	
	public static void drawFrame(Stage stage, Scene scene, Pane pane,int nbrX, int nbrY) {
		scene = new Scene(pane,((WALL + CELL) * nbrX + WALL) * SPAN,((WALL + CELL) * nbrY + WALL) * SPAN);
		scene.setFill(SCENECOLOR);
		
		Rectangle square;
		stage.setScene(scene);
		
		square = new Rectangle(0, 0,SPAN * (nbrX * (CELL+WALL) + WALL), WALL * SPAN);
		square.setFill(WALLCOLOR);
		pane.getChildren().add(square);
		
		square = new Rectangle(0,SPAN * (nbrY * (CELL+WALL)),SPAN * (nbrX * (CELL+WALL) + WALL), WALL * SPAN);
		square.setFill(WALLCOLOR);
		pane.getChildren().add(square);
		
		square = new Rectangle(SPAN * (nbrX * (CELL+WALL)), 0,WALL * SPAN,SPAN * (nbrY * (CELL + WALL) + WALL));
		square.setFill(WALLCOLOR);
		pane.getChildren().add(square);
		
		square = new Rectangle(0, 0,WALL * SPAN,SPAN * (nbrY * (CELL + WALL) + WALL));
		square.setFill(WALLCOLOR);
		pane.getChildren().add(square);
		
		for (int x=0; x < nbrX-1;++x) {
			int offsetX = ((WALL+CELL) + (WALL+CELL) * x) * SPAN;
			for (int y=0; y < nbrY-1;++y) {
				int offsetY = ((WALL+CELL) + (WALL+CELL) * y) * SPAN;
				square = new Rectangle(offsetX,offsetY,WALL*SPAN,WALL*SPAN);
				square.setFill(WALLCOLOR);
				pane.getChildren().add(square);
			}
		}
	}
	
	public static void drawCharacter(Stage stage, Group root) {
		Image image = new Image(Viewframe.class.getResource("ressource/cat.png").toExternalForm());
		imageView = new ImageView(image);
		root.getChildren().add(imageView);
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(CELL * SPAN * 2);
		imageView.setX(10);
		imageView.setY(10);
	}
	
	public static void mooveCharacter(double x,double y) {
		imageView.setX(x);
		imageView.setY(y);
	}
	
	public static void drawWall (Pane pane, int xs, int ys, int xt, int yt, Paint color) {
        int x = 0, y = 0, xspan = 0, yspan = 0;
        
        if (ys == yt) {
            x = ((WALL + CELL) + (WALL + CELL)*((int) (xs + xt) / 2))*SPAN;
            y = (WALL + ys*(WALL + CELL))*SPAN;
            
            xspan = WALL*SPAN;
            yspan = CELL*SPAN;
            
            Rectangle square = new Rectangle(x, y, xspan, yspan);
            square.setFill(color);
            pane.getChildren().add(square);
        } 
        
        else if (xs == xt) {
            x = (WALL + xs*(WALL + CELL))*SPAN;
            y = ((WALL + CELL) + (WALL + CELL)*((int) (ys + yt) / 2))*SPAN;
            
            xspan = CELL*SPAN;
            yspan = WALL*SPAN;
            
            Rectangle square = new Rectangle(x, y, xspan, yspan);
            square.setFill(color);
            pane.getChildren().add(square);
        }
    }

}
