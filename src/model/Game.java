package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Uellington Conceição
 * @since 09/05/2020
 */
public abstract class Game {

    protected final double WIDTH = 500;
    protected final double HEIGHT = 200;

    protected int scale;
    protected Canvas canvas;
    protected GraphicsContext graphic;

    public Game() {
        this.canvas = new Canvas(WIDTH, HEIGHT);
        this.graphic = this.canvas.getGraphicsContext2D();
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public abstract void update();

    public final void clear() {
        this.graphic.setFill(Color.WHITE);
        this.graphic.fillRect(0, 0, WIDTH, HEIGHT);
        this.graphic.save();
    }
}
