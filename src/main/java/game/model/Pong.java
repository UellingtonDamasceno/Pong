package game.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.input.KeyCode;
import model.Game;
import model.Entity;
import model.KeyListener;

/**
 *
 * @author Uellington Conceição
 */
public class Pong extends Game implements Observer {

    private List<Entity> entities;
    private KeyListener keyHandle;

    private final Player player;
    private final Player player2;
    private final Ball ball;

    public Pong(String name) {
        super(name);
        this.player = new Player(10, this.HEIGHT / 2 - 20, 10, 40);
        this.player2 = new Player(this.WIDTH - 20, this.HEIGHT / 2 - 20, 10, 40);
        this.ball = new Ball(this.WIDTH / 2, this.HEIGHT / 2, 10, 10);

    }

    public void initialize() {
        player.addAction(KeyCode.W, player.up());
        player.addAction(KeyCode.S, player.down());

        player2.addAction(KeyCode.UP, player2.up());
        player2.addAction(KeyCode.DOWN, player2.down());

        this.ball.addObserver(player);
        this.ball.addObserver(player2);

        this.ball.addObserver(this);

        this.entities = new LinkedList();
        this.entities.add(player);
        this.entities.add(player2);
        this.entities.add(ball);

        this.keyHandle = new KeyListener(this.entities);
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    @Override
    public KeyListener getKeyListener() {
        return this.keyHandle;
    }

    @Override
    public void update() {
        this.userInterface.render(graphic);
        this.entities.forEach((entity) -> {
            entity.tick();
            entity.render(graphic);
        });
    }

    @Override
    public void update(Observable o, Object arg) {
    }

}
