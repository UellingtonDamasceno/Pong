package games.pong.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Entity;

/**
 *
 * @author Uellington Conceição
 * @sice 09/05/2020
 */
public class Player extends Entity {

    protected Rectangle2D rectangle;

    public Player(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.rectangle = new Rectangle2D(x, y, width, height);
    }

    @Override
    public void tick() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.;
    }

    @Override
    public void render(GraphicsContext graphic) {
        graphic.setFill(Color.BLACK);
        graphic.fillRect(this.referencePoint[0].getX(), this.referencePoint[0].getY(), this.width, this.height);
        graphic.save();
//        this.showReferencePoints(graphic);
    }

    public void up() {
        for (int i = 0; i < referencePoint.length; i++) {
           referencePoint[i] = referencePoint[i].subtract(0, 5);
        }
    }
    
    public void down() {
        for (int i = 0; i < referencePoint.length; i++) {
           referencePoint[i] = referencePoint[i].add(0, 5);
        }
    }
}
