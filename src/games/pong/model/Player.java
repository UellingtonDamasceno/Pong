package games.pong.model;

import java.util.Observable;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Entity;

/**
 *
 * @author Uellington Conceição
 * @sice 09/05/2020
 */
public class Player extends Entity {

    public Player(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(GraphicsContext graphic) {
        graphic.setFill(Color.BLACK);
        graphic.fillRect(this.referencePoint[0].getX(), this.referencePoint[0].getY(), this.width, this.height);
    }

    public void up() {
        for (int i = 0; i < referencePoint.length; i++) {
            referencePoint[i] = referencePoint[i].subtract(0, 5);
        }
    }

    public void down() {
        for (int i = 0; i < referencePoint.length; i++) {
            referencePoint[i] = referencePoint[i].add(0, 5);
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        Point2D[] referencePoints = (Point2D[]) o1;
        Rectangle2D rectangle = new Rectangle2D(this.referencePoint[0].getX(), this.referencePoint[0].getY(), this.width, this.height);
        for (Point2D referencePoint : referencePoints) {
            if (rectangle.contains(referencePoint)) {
                Ball ball = (Ball) o;
                ball.setDirection(ball.getDirection().getOpposite());
                ball.collided();
            }
        }
    }
}
