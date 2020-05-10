package model;

import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Uellington Conceição
 * @since 09/05/2020
 */
public abstract class Entity extends Observable implements Observer{

    protected Point2D[] referencePoint;
    protected double width, height;

    public Entity(double x, double y, double width, double height) {
        this.width = width;
        this.height = height;

        this.referencePoint = new Point2D[9];

        this.referencePoint[2] = new Point2D((x + this.width), y);//9
        this.referencePoint[5] = new Point2D((x + this.width), y + (this.height / 2));//6
        this.referencePoint[8] = new Point2D((x + this.width), (y + this.height));//3

        this.referencePoint[0] = new Point2D(x, y); //7
        this.referencePoint[3] = new Point2D(x, y + (this.height / 2));//4
        this.referencePoint[6] = new Point2D(x, (y + this.height));//1

        this.referencePoint[1] = new Point2D(x + (this.width / 2), y);//8
        this.referencePoint[4] = new Point2D(x + (this.width / 2), y + (this.height / 2));//5
        this.referencePoint[7] = new Point2D(x + (this.width / 2), (y + this.height));//2
    }

    public abstract void tick();

    public abstract void render(GraphicsContext graphic);

    protected final void showReferencePoints(GraphicsContext graphic) {
        graphic.setFill(Color.RED);
        for (Point2D referencesPoint : referencePoint) {
            graphic.fillOval(referencesPoint.getX() - 2.5, referencesPoint.getY() - 2.5, 5, 5);
            graphic.save();
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
