package model;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Uellington Conceição
 * @since 10/05/2020
 */
public class Ball extends Entity {

    private Rectangle2D rectangle;

    public Ball(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.rectangle = new Rectangle2D(x, y, width, height);
    }

    @Override
    public void tick() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(GraphicsContext graphic) {
        graphic.setFill(Color.YELLOWGREEN);
        graphic.fillOval(this.referencePoint[0].getX(), this.referencePoint[0].getY(), this.width, this.height);
        graphic.save();
//        this.showReferencePoints(graphic);
    }

}
