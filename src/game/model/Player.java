package game.model;

import java.util.Observable;
import java.util.function.Function;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import model.ControllableEntity;

/**
 *
 * @author Uellington Conceição
 * @sice 09/05/2020
 */
public class Player extends ControllableEntity {

    private Rectangle2D rectangle;
    private final double offset;

    public Player(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.rectangle = new Rectangle2D(x, y, width, height);
        this.offset = 5;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(GraphicsContext graphic) {
        graphic.setFill(Color.BLACK);
        graphic.fillRect(this.referencePoints[0].getX(), this.referencePoints[0].getY(), this.width, this.height);
    }

    @Override
    protected Function up() {
        return (Object t) -> {
            if (referencePoints[1].getY() >= offset) {
                for (int i = 0; i < referencePoints.length; i++) {
                    referencePoints[i] = referencePoints[i].subtract(0, offset);
                }
            }
            return t;
        };
    }

    @Override
    protected Function down() {
        return (Object t) -> {
            if (referencePoints[7].getY() <= 250 - offset) {
                for (int i = 0; i < referencePoints.length; i++) {
                    referencePoints[i] = referencePoints[i].add(0, offset);
                }
            }
            return t;
        };
    }

    @Override
    public void update(Observable o, Object o1) {
        Point2D[] ballReferencePoints = (Point2D[]) o1;
        if (ballReferencePoints[4].distance(this.referencePoints[4]) <= this.height) {
            rectangle = new Rectangle2D(this.referencePoints[0].getX(), this.referencePoints[0].getY(), this.width, this.height);
            for (Point2D referencePoint : ballReferencePoints) {
                if (this.rectangle.contains(referencePoint)) {
                    ((Ball) o).collided();
                    break;
                }
            }
        }
    }
}
