package games.pong.model;

import java.util.Observable;
import java.util.Random;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Entity;

/**
 *
 * @author Uellington Conceição
 * @since 10/05/2020
 */
public class Ball extends Entity {

    private Rectangle2D rectangle;
    private double dx, dy, speed;
    
    public Ball(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.rectangle = new Rectangle2D(x, y, width, height);
        Random random = new Random();
        this.dx = random.nextGaussian();
        this.dy = random.nextGaussian();
        this.speed = 1.2;
    }

    @Override
    public void tick() {
        this.referencePoint[0] = this.referencePoint[0].add(dx*speed, dy*speed);
        this.setChanged();
        this.notifyObservers(this.referencePoint);
    }

    @Override
    public void render(GraphicsContext graphic) {
        graphic.setFill(Color.YELLOWGREEN);
        graphic.fillOval(this.referencePoint[0].getX(), this.referencePoint[0].getY(), this.width, this.height);
        graphic.save();
    }

    @Override
    public void update(Observable o, Object o1) {
    }

}
