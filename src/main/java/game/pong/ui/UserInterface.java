package game.pong.ui;

import game.pong.util.PointUtils;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import penguine.game.base.Drawable;
import penguine.game.base.Measurable;

/**
 *
 * @author Uellington Damasceno
 * @since 24/05/2020
 */
public class UserInterface {

    private Font font;
    private Measurable limits;
    private List<Drawable> components;

    public UserInterface(Measurable limits) {
        this.components = new ArrayList();
        this.limits = limits;
        Path path = Paths.get("src", "main", "java", "game", "pong", "ui", "fonts", "Imagine.ttf");
        this.font = Font.loadFont(path.toUri().toString(), 15);
    }

    public void addComponent(Drawable drawable) {
        this.components.add(drawable);
    }

    public void render(GraphicsContext graphic) {
        Paint color = Paint.valueOf("#08FB09");
        graphic.setFill(color);
        graphic.setStroke(color);

        double centerLine = 5;
        graphic.fillRect((limits.getWidth() / 2) - centerLine / 2,
                0, centerLine, limits.getHeight());

        double circleSize = 20;
        Point2D centerPoint = PointUtils.alignCenter(limits, circleSize);

        graphic.fillOval(centerPoint.getX(), centerPoint.getY(), circleSize, circleSize);

        graphic.strokeRect(limits.getX(), limits.getY(), limits.getWidth(), limits.getHeight());

        graphic.setLineWidth(5);
        circleSize = 100;
        centerPoint = PointUtils.alignCenter(limits, circleSize);
        graphic.strokeOval(centerPoint.getX(), centerPoint.getY(), circleSize, circleSize);

        graphic.setFont(font);
        this.components.forEach(drawable -> drawable.render(graphic));
    }

}
