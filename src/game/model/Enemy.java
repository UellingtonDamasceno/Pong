package game.model;

import java.util.Observable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Uellington Conceição
 */
public class Enemy extends Player {

    private Point2D[] ballReferencePoints;

    public Enemy(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void tick() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Observable o, Object o1) {
        this.ballReferencePoints = (Point2D[]) o1;
    }

}
