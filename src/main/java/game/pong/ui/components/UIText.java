package game.pong.ui.components;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import penguine.game.base.Drawable;

/**
 *
 * @author Uellington Damasceno
 */
public class UIText implements Drawable {

    private String text;
    private Point2D point;
    private Paint color;

    public UIText(String text, Point2D point, Paint color) {
        this.text = text;
        this.point = point;
        this.color = color;
    }

    public UIText(String text, double x, double y) {
        this(text, new Point2D(x, y), Color.WHITE);
    }

    public UIText(String text, double x, double y, Paint color) {
        this(text, new Point2D(x, y), color);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void render(GraphicsContext graphic) {
        graphic.setFill(color);
        graphic.fillText(text, point.getX(), point.getY());
    }

}
