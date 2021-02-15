package game.pong.model;

import game.pong.util.PointUtils;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import penguine.game.model.Entity;
import game.pong.util.Settings.Direction;
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
        this.dx = x;
        this.dy = y;
        this.drawer = new Random();
        this.speed = 2;
        this.collided = false;
        this.limits = limits;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void collided() {
        this.collided = true;
    }

    public void drawInitialDirection() {
        this.move(origin.getX(), origin.getY());
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
        double py = (dy * speed);
        double nextPositionY = y + py;
        if ((nextPositionY + this.height) >= this.limits.getHeight() || nextPositionY < this.limits.getY()) {
            this.dy *= -1;
        } else if (this.collided) {
            this.direction = this.direction.getOpposite();
            this.currentAngle = this.draw(this.direction);
            this.calculeAndUpdateDirectionsVector(currentAngle);
            this.collided = false;
            this.initialState = false;
            this.count = 0;
        }

        this.move((x + (dx * speed)), nextPositionY);
        this.count++;
        if ((this.initialState && count*speed >= 125) || (!this.initialState && count*speed >= 270)) {
            this.setChanged();
            this.notifyObservers(PointUtils.getReferencePoints(this));
        }
    }

    @Override
    public void renderHook(GraphicsContext graphic) {
        graphic.setFill(Paint.valueOf("#08FB09"));
        graphic.fillOval(this.x, this.y, this.width, this.height);
    }

}
