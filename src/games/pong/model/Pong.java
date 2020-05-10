package games.pong.model;

import java.util.LinkedList;
import java.util.List;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Game;
import model.Playable;

/**
 *
 * @author Uellington Conceição
 */
public class Pong extends Game implements EventHandler<KeyEvent> {

    private List<Playable> entities;
    private Player player;
    
    public Pong() {
        this.entities = new LinkedList();
        this.player = new Player(this.WIDTH / 4, this.HEIGHT / 2, 10, 40);
    }

    public void addEntity(Playable entity) {
        this.entities.add(entity);
    }

    public void removeEntity(Playable entity) {
        this.entities.remove(entity);
    }

    @Override
    public void update() {
        player.render(graphic);
        this.entities.forEach((entity) -> {
            entity.update();
            entity.render(this.graphic);
        });
    }

    @Override
    public void handle(KeyEvent event) {
//        System.out.println(event.getCode());
        if (null == event.getCode()) {
            event.consume();
        } else {
            switch (event.getCode()) {
                case UP:
                    player.up();
                    break;
                case DOWN:
                    player.down();
                    break;
                default:
                    event.consume();
                    break;
            }
        }
    }
}
