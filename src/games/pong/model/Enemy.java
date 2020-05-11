package games.pong.model;

import java.util.Observable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Entity;

/**
 *
 * @author Uellington Conceição
 */
public class Enemy extends Entity {

    public Enemy(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void tick() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(GraphicsContext graphic) {
        graphic.setFill(Color.BLACK);
        graphic.fillRect(this.referencePoint[0].getX(), this.referencePoint[0].getY(), this.width, this.height);
        graphic.save();
//        this.showReferencePoints(graphic);
    }

    @Override
    public void update(Observable o, Object o1) {
        Point2D[] ballPoints = (Point2D[]) o1;
    }

}
