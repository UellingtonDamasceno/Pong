package game.pong.model;

import java.util.Observable;
import java.util.Observer;
import java.util.function.Consumer;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import penguine.game.base.Measurable;

/**
 *
 * @author Uellington Conceição
 */
public class Player extends ControllableEntity implements Observer {

    private SimpleIntegerProperty points;
    private Measurable limits;

    public Player(double x, double y, double width, double height, Measurable limits) {
        super(x, y, width, height);
        this.points = new SimpleIntegerProperty(0);
        this.limits = limits;
    }

    public SimpleIntegerProperty getPoint() {
        return this.points;
    }

    public void addPoints(int point) {
        int pontuation = this.points.get();
        pontuation += point;
        this.points.set(pontuation);
    }

    @Override
    public void update() {
        if (this.up && referencePoints[1].getY() >= offset) {
            this.setPosition(this.referencePoints[0].getX(), this.referencePoints[0].getY() - this.offset);
        } else if (this.down && referencePoints[7].getY() <= this.limits.getY() - offset) {
            this.setPosition(this.referencePoints[0].getX(), this.referencePoints[0].getY() + this.offset);
        } else {

        }
    }

    @Override
    protected void renderHook(GraphicsContext graphic) {
        graphic.setFill(Paint.valueOf("#08FB09"));
        graphic.fillRect(this.referencePoints[0].getX(), this.referencePoints[0].getY(), this.width, this.height);
    }

    @Override
    protected Consumer up() {
        return (Object t) -> {
            this.up = true;
            this.down = false;
        };
    }

    @Override
    protected Consumer down() {
        return (Object t) -> {
            this.down = true;
            this.up = false;
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
