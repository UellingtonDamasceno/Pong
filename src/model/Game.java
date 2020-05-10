package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

/**
 *
 * @author Uellington Conceição
 * @since 09/05/2020
 */
public abstract class Game{
    
    protected final double WIDTH = 400;
    protected final double HEIGHT = 400;
    
    protected int scale;
    protected Canvas canvas;
    protected GraphicsContext graphic;
    
    public Game(){
        this.canvas = new Canvas(WIDTH, HEIGHT);
        this.graphic = this.canvas.getGraphicsContext2D();
        this.graphic.setFont(new Font(Font.getFontNames().get(5), 20));
        this.graphic.fillText("Olá, Pong", 50, 50);
        this.graphic.save();
    }
    
    public Canvas getCanvas(){
        return this.canvas;
    }
    
    public abstract void update();
}
