package game.pong.model;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import penguine.game.model.Entity;
import game.pong.util.Settings.Direction;
import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;
import penguine.game.base.Measurable;

/**
 *
 * @author Uellington Conceição
 */
public class Ball extends Entity {

    private final Random drawer;
    private double dx, dy, currentAngle;
    private final double speed;
    private Direction direction;
    private boolean collided, initialState;
    private Measurable limits;
    private int count;

    public Ball(double x, double y, double width, double height, Measurable limits) {
        super(x, y, width, height);
        this.drawer = new Random();
        this.speed = 1.5;
        this.collided = false;
        this.limits = limits;
    }

    public Point2D[] getReferencePoints() {
        return this.referencePoints;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void collided() {
        this.collided = true;
    }

    public void drawInitialDirection() {
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

    @Override
    public void update() {
        double py = this.referencePoints[0].getY();

        double nextPositionY = py + (dy * speed);
        if (nextPositionY + this.height >= this.limits.getY() || nextPositionY < this.limits.getMinY()) {
            this.dy *= -1;
        } else if (this.collided) {
            this.direction = this.direction.getOpposite();
            this.currentAngle = this.draw(this.direction);
            this.calculeAndUpdateDirectionsVector(currentAngle);
            this.collided = false;
            this.initialState = false;
            this.count = 0;
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
    public void renderHook(GraphicsContext graphic) {
        graphic.setFill(Paint.valueOf("#08FB09"));
        graphic.fillOval(this.referencePoints[0].getX(), this.referencePoints[0].getY(), this.width, this.height);
    }

}
