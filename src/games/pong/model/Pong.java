package games.pong.model;

import java.util.LinkedList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.Game;
import model.Entity;

/**
 *
 * @author Uellington Conceição
 */
public class Pong extends Game implements EventHandler<KeyEvent> {

    private List<Entity> entities;
    private final Separator separator;
    private final Player player;
    private final Player player2;
    private final Ball ball;

    public Pong(String name) {
        super(name);
        this.separator = new Separator(WIDTH/2, 0, 10, this.HEIGHT);
        this.player = new Player(10, this.HEIGHT / 2 - 20, 10, 40);
        this.player2 = new Player(this.WIDTH - 20, this.HEIGHT / 2 - 20, 10, 40);
        this.ball = new Ball(this.WIDTH / 2, this.HEIGHT / 2, 10, 10);

        this.ball.addObserver(player);
        this.ball.addObserver(player2);

        this.entities = new LinkedList();
        
        this.entities.add(separator);
        this.entities.add(player);
        this.entities.add(ball);
        this.entities.add(player2);
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
            entity.render(graphic);
//            entity.showReferencePoints(graphic);
        });
    }

    @Override
    public void handle(KeyEvent event) {
        if (null == event.getCode()) {
            event.consume();
        } else {
            switch (event.getCode()) {
                case UP:
                    player2.up();
                    break;
                case DOWN:
                    player2.down();
                    break;
                case W:
                    player.up();
                   break;
                case S:
                    player.down();
                    break;
                default:
                    event.consume();
                    break;
            }
        }
    }
}
