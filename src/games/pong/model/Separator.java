package games.pong.model;

import java.util.Observable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Entity;

/**
 *
 * @author Uellington Conceição
 */
public class Separator extends Entity {

    public Separator(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(GraphicsContext graphic) {
        graphic.setFill(Color.WHITE);
        graphic.fillRect(
                this.referencePoints[0].getX(),
                this.referencePoints[0].getY(), width, height);
        graphic.setStroke(Color.WHITE);
        graphic.setLineWidth(10);
        graphic.strokeOval(205, 75, 100, 100);
    }

    @Override
    public void update(Observable o, Object o1) {
    }

}
