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
        this.drawInitialDirection();
        this.speed = 1.2;
    }

    private void drawInitialDirection() {
        Random drawer = new Random();
        boolean isRight = drawer.nextBoolean();
        boolean isTop = drawer.nextBoolean();
        double angle;
        
        if (isRight && isTop) {
            //Setor 3
            angle = drawer.nextInt(60);
        } else if (isRight && !isTop) {
            //Sector 0
            angle = drawer.nextInt(60) + 300;
        } else if (!isRight && isTop) {
            //sector 1
            angle = drawer.nextInt(60) + 120;
        } else {
            //sector 2
            angle = drawer.nextInt(60) + 180;
        }

        this.dx = Math.cos(Math.toRadians(angle));
        this.dy = Math.sin(Math.toRadians(angle));
    }

    @Override
    public void tick() {
        double px = this.referencePoint[0].getX();
        double py = this.referencePoint[0].getY();

        double nextPositionY = py + (dy * speed);
        if (nextPositionY + this.height >= 250 || nextPositionY < 0) {
            this.dy *= -1;
        }
        if (px >= 500 - this.width || px <= 0) {
            this.dx *= -1;
        }
        this.referencePoint[0] = this.referencePoint[0].add(dx * speed, dy * speed);
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
