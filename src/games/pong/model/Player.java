package games.pong.model;

import java.awt.event.KeyListener;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Playable;

/**
 *
 * @author Uellington Conceição
 * @sice 09/05/2020
 */
public class Player extends Playable {

    protected Rectangle2D rectangle;

    public Player(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.rectangle = new Rectangle2D(x, y, width, height);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(GraphicsContext graphic) {
        graphic.setFill(Color.BLACK);
        graphic.fillRect(this.point.getX(), this.point.getY(), this.width, this.height);
        graphic.save();
        graphic.setFill(Color.GREY);
        graphic.fillOval(this.point.getX(), this.point.getY(), 5, 5);
        graphic.save();
    }

    public void up() {
        this.point = this.point.subtract(0, 10);
    }

    public void down() {
        this.point = this.point.add(0, 10);
    }
}
