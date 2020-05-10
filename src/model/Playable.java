package model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Uellington Conceição
 * @since 09/05/2020
 */
public abstract class Playable {

    protected Point2D point;
    protected double width, height;

    public Playable(double x, double y, double width, double height) {
        this(new Point2D(x, y), width, height);
    }
    
    public Playable(Point2D point, double width, double height){
        this.point = point;
        this.width = width;
        this.height = height;
    }
    
    public abstract void update();
    public abstract void render(GraphicsContext graphic);
}
