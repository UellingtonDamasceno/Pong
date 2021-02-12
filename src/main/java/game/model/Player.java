package game.model;

import java.util.Observable;
import java.util.Observer;
import java.util.function.Consumer;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.ControllableEntity;

/**
 *
 * @author Uellington Conceição
 * @sice 09/05/2020
 */
public class Player extends ControllableEntity implements Observer {

    private int points;
    
    public Player(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.points = 0;
    }
    
    public int getPoint(){
        return this.points;
    }

    public void addPoints(int points){
        this.points += points;
    }
    
    @Override
    public void tick() {
        if (this.up && referencePoints[1].getY() >= offset) {
            this.setPosition(this.referencePoints[0].getX(), this.referencePoints[0].getY() - this.offset);
        } else if (this.down && referencePoints[7].getY() <= 250 - offset) {
            this.setPosition(this.referencePoints[0].getX(), this.referencePoints[0].getY() + this.offset);
        } else {

        }
    }

    @Override
    public void render(GraphicsContext graphic) {
        graphic.setFill(Color.WHITE);
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
