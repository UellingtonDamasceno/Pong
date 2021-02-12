package game.ui;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Drawable;

/**
 *
 * @author Uellington Damasceno
 * @since 24/05/2020
 */
public class UserInterface implements Observer {

    private Font font;
    private final double width, height;
    private List<Drawable> components;

    public UserInterface(double width, double height) {
        this.width = width;
        this.height = height;
        this.components = new ArrayList();
        Path path = Paths.get("src", "main", "java", "game", "ui", "fonts", "Imagine.ttf");
        this.font = Font.loadFont(path.toUri().toString(), 15);
    }

    public void addChildren(Drawable drawable) {
        this.components.add(drawable);
    }

    public void render(GraphicsContext graphic) {
        graphic.setFill(Color.WHITE);
        graphic.setFont(font);
        graphic.fillText("Player 1", 100, 25);
        graphic.fillText("Player 2", 350, 25);
        graphic.fillRect(width / 2,
                0, 10, height);
        graphic.fillOval(242.5, 112.5, 25, 25);
        graphic.setStroke(Color.WHITE);
        graphic.setLineWidth(10);
        graphic.strokeOval(205, 75, 100, 100);
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
