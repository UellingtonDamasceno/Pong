package games.pong.model;

import java.util.Observable;
import java.util.Random;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Entity;
import util.Settings.Direction;

/**
 *
 * @author Uellington Conceição
 * @since 10/05/2020
 */
public class Ball extends Entity {

    private Rectangle2D rectangle;
    private double dx, dy, speed, angle;
    private Direction direction;
    private boolean collided;

    public Ball(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.rectangle = new Rectangle2D(x, y, width, height);
        this.drawInitialDirection();
        this.collided = false;
        this.speed = 1.2;
    }

    private void drawInitialDirection() {
        Random drawer = new Random();
        boolean isRight = drawer.nextBoolean();
        boolean isTop = drawer.nextBoolean();

        if (isRight && isTop) {
            //Setor 3
            angle = drawer.nextInt(60);
            this.direction = Direction.EAST;
        } else if (isRight && !isTop) {
            //Sector 0
            angle = drawer.nextInt(60) + 300;
            this.direction = Direction.EAST;
        } else if (!isRight && isTop) {
            //sector 1
            angle = drawer.nextInt(60) + 120;
            this.direction = Direction.WEAST;
        } else {
            //sector 2
            angle = drawer.nextInt(60) + 180;
            this.direction = Direction.WEAST;
        }
        angle = Math.toRadians(angle);
        this.dx = Math.cos(angle);
        this.dy = Math.sin(angle);
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void collided() {
        this.collided = true;
    }

    @Override
    public void tick() {
        double px = this.referencePoint[0].getX();
        double py = this.referencePoint[0].getY();

        double nextPositionY = py + (dy * speed);
        if (nextPositionY + this.height >= 250 || nextPositionY < 0) {
            this.dy *= -1;
        } else if (this.collided) {
            Random drawer = new Random();
            boolean isTop = drawer.nextBoolean();
            if (this.direction == Direction.EAST) {
                if (isTop) {
                    angle = drawer.nextInt(60);
                } else {
                    angle = drawer.nextInt(60) + 300;
                }
            } else {
                if (isTop) {
                    angle = drawer.nextInt(60) + 120;
                } else {
                    angle = drawer.nextInt(60) + 180;
                }
            }
            angle = Math.toRadians(angle);
            this.dx = Math.cos(angle);
            this.dy = Math.sin(angle);
            this.collided = false;
        } else if (px >= 500 - this.width) {
            this.setPosition(this.originPoint);
            this.dx *= -1;
        } else if (px <= 0) {
            this.setPosition(this.originPoint);
            this.dx *= -1;
        }

        this.move(dx * speed, dy * speed);
        this.setChanged();
        this.notifyObservers(this.referencePoint);
    }

    private void move(double x, double y) {
        for (int i = 0; i < this.referencePoint.length; i++) {
            this.referencePoint[i] = this.referencePoint[i].add(x, y);
        }
    }

    @Override
    public void render(GraphicsContext graphic) {
        graphic.setFill(Color.YELLOWGREEN);
        graphic.fillOval(this.referencePoint[0].getX(), this.referencePoint[0].getY(), this.width, this.height);
    }

    @Override
    public void update(Observable o, Object o1) {
    }

}
