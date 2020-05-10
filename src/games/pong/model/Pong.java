package games.pong.model;

import java.util.LinkedList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.Ball;
import model.Game;
import model.Entity;

/**
 *
 * @author Uellington Conceição
 */
public class Pong extends Game implements EventHandler<KeyEvent> {

    private List<Entity> entities;
    private Player player;
    private Ball ball;

    public Pong() {
        this.player = new Player(this.WIDTH / 4, this.HEIGHT / 2, 10, 40);
        this.ball = new Ball(this.WIDTH / 2, this.HEIGHT / 2, 10, 10);
        
        this.ball.addObserver(player);
        
        this.entities = new LinkedList();
        this.entities.add(player);
        this.entities.add(ball);
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    @Override
    public void update() {
        this.entities.forEach((entity) -> {
            entity.tick();
            entity.render(this.graphic);
        });
    }

    @Override
    public void handle(KeyEvent event) {
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
