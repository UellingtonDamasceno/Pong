package game.pong.ui;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import penguine.game.base.Drawable;
import penguine.game.base.Measurable;

/**
 *
 * @author Uellington Damasceno
 * @since 24/05/2020
 */
public class UserInterface implements Observer {

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
        graphic.setFill(Color.WHITE);
        graphic.setFont(font);

        this.components.forEach(drawable -> drawable.render(graphic));

        graphic.fillRect(limits.getX() / 2,
                0, 10, limits.getY());

        graphic.fillOval(242.5, 112.5, 25, 25);
        graphic.setStroke(Color.WHITE);
        graphic.setLineWidth(10);
        graphic.strokeOval(205, 75, 100, 100);
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
