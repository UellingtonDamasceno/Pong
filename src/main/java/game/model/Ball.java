package game.model;

import java.util.Random;
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

    private final Random drawer;
    private double dx, dy, currentAngle;
    private final double speed;
    private Direction direction;
    private boolean collided, initialState;
    private int count;

    public Ball(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.drawer = new Random();
        this.speed = 1.5;
        this.collided = false;
        this.drawInitialDirection();
    }

    private void drawInitialDirection() {
        this.setPosition(this.originPoint);
        boolean isRight = drawer.nextBoolean();
        boolean isTop = drawer.nextBoolean();
        this.direction = (isRight) ? Direction.EAST : Direction.WEAST;
        this.currentAngle = draw(direction, isTop);
        this.calculeAndUpdateDirectionsVector(currentAngle);
        this.initialState = true;
        this.count = 0;
    }

    private double draw(Direction direction) {
        return this.draw(direction, this.drawer.nextBoolean());
    }

    private double draw(Direction direction, boolean isTop) {
        return (direction == Direction.EAST)
                ? (isTop)
                        ? drawer.nextInt(60)
                        : drawer.nextInt(60) + 300
                : (isTop)
                        ? drawer.nextInt(60) + 120
                        : drawer.nextInt(60) + 180;

    }

    private void calculeAndUpdateDirectionsVector(double angle) {
        angle = Math.toRadians(angle);
        this.dx = Math.cos(angle);
        this.dy = Math.sin(angle);
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void collided() {
        this.collided = true;
    }

    @Override
    public void tick() {
        double px = this.referencePoints[0].getX();
        double py = this.referencePoints[0].getY();

        double nextPositionY = py + (dy * speed);
        if (nextPositionY + this.height >= 250 || nextPositionY < 0) {
            this.dy *= -1;
        } else if (this.collided) {
            this.direction = this.direction.getOpposite();
            this.currentAngle = this.draw(this.direction);
            this.calculeAndUpdateDirectionsVector(currentAngle);
            this.collided = false;
            this.initialState = false;
            this.count = 0;
        } else if (px >= 500 - this.width) {
            this.drawInitialDirection();

        } else if (px <= 0) {
            this.drawInitialDirection();
        }

        this.move(dx * speed, dy * speed);
        this.count++;
        if ((this.initialState && count >= 125) || (!this.initialState && count >= 270)) {
            this.setChanged();
            this.notifyObservers(this.referencePoints);
        }
    }

    private void move(double x, double y) {
        for (int i = 0; i < this.referencePoints.length; i++) {
            this.referencePoints[i] = this.referencePoints[i].add(x, y);
        }
    }

    @Override
    public void render(GraphicsContext graphic) {
        graphic.setFill(Color.CHARTREUSE);
        graphic.fillOval(this.referencePoints[0].getX(), this.referencePoints[0].getY(), this.width, this.height);
    }

}