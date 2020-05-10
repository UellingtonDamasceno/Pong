package games.pong.model;

import java.util.LinkedList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import model.Game;
import model.Playable;

/**
 *
 * @author Uellington Conceição
 */
public class Pong extends Game {

    private List<Playable> entities;

    public Pong() {
        this.entities = new LinkedList();
    }

    public void addEntity(Playable entity){
        this.entities.add(entity);
    }
    
    public void removeEntity(Playable entity){
        this.entities.remove(entity);
    }

    @Override
    public void update() {
//        this.entities.forEach((entity) -> {
//            entity.update();
//            entity.render(this.graphic);
//        });
    }

}
