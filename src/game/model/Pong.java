package game.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javafx.scene.input.KeyCode;
import model.Game;
import model.Entity;
import model.KeyHandle;

/**
 *
 * @author Uellington Conceição
 */
public class Pong extends Game {

    private final List<Entity> entities;
    private final KeyHandle keyHandle;
    private final Player player;
    private final Player player2;
    private final Ball ball;

    public Pong(String name) {
        super(name);
        this.player = new Player(10, this.HEIGHT / 2 - 20, 10, 40);
        this.player2 = new Player(this.WIDTH - 20, this.HEIGHT / 2 - 20, 10, 40);
        this.ball = new Ball(this.WIDTH / 2, this.HEIGHT / 2, 10, 10);

        player.addAction(KeyCode.W, player.up());
        player.addAction(KeyCode.S, player.down());

        player2.addAction(KeyCode.UP, player2.up());
        player2.addAction(KeyCode.DOWN, player2.down());
        
        this.ball.addObserver(player2);

        this.entities = new LinkedList();

        this.entities.add(new Separator(WIDTH / 2, 0, 10, this.HEIGHT));
        this.entities.add(player);
        this.entities.add(ball);
        this.entities.add(player2);
        this.keyHandle = new KeyHandle(this.entities);

    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    @Override
    public KeyHandle getKeyHandle() {
        return this.keyHandle;
    }

    @Override
    public void update() {
        this.entities.forEach((entity) -> {
            entity.tick();
            entity.render(graphic);
//            entity.showReferencePoints(graphic);
        });
    }

}
