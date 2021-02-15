package game.pong.model;

import game.pong.input.KeyListener;
import game.pong.ui.UserInterface;
import game.pong.ui.components.UIText;
import game.pong.util.PointUtils;
import game.pong.util.Settings.Direction;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import penguine.game.model.Game;
import penguine.game.model.Entity;

/**
 *
 * @author Uellington Conceição
 */
public class Pong extends Game implements Observer {

    private List<Entity> entities;
    private KeyListener keyHandle;

    private UserInterface userInterface;
    private final Player player;
    private final Player player2;
    private final Ball ball;

    public Pong(String name) {
        super(name, 500, 250);
        this.player = new Player(10, this.HEIGHT / 2 - 20, 10, 40, this);
        this.player2 = new Player(this.WIDHT - 20, this.HEIGHT / 2 - 20, 10, 40, this);
        this.ball = new Ball(this.WIDHT / 2, this.HEIGHT / 2, 10, 10, this);
    }

    public void initialize() {
        player.addAction(KeyCode.W, player.up());
        player.addAction(KeyCode.S, player.down());

        player2.addAction(KeyCode.UP, player2.up());
        player2.addAction(KeyCode.DOWN, player2.down());

        this.ball.addObserver(player);
        this.ball.addObserver(player2);

        this.ball.addObserver(this);

        this.ball.drawInitialDirection();
        
        this.entities = new LinkedList();
        this.entities.add(player);
        this.entities.add(player2);
        this.entities.add(ball);

        this.keyHandle = new KeyListener(this.entities);
        this.userInterface = new UserInterface(this);

        UIText playerOnePoint = new UIText("0", 100, 25, Color.WHITE);
        UIText playerTwoPoint = new UIText("0", 350, 25, Color.WHITE);

        this.player.getPoint().addListener((ov, t, t1) -> {
            playerOnePoint.setText(t1.toString());
        });
        this.player2.getPoint().addListener((ov, t, t1) -> {
            playerTwoPoint.setText(t1.toString());
        });

        this.userInterface.addComponent(playerOnePoint);
        this.userInterface.addComponent(playerTwoPoint);
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    public KeyListener getKeyListener() {
        return this.keyHandle;
    }

    @Override
    public void update() {
        this.userInterface.render(graphic);
        this.entities.forEach((entity) -> {
            entity.update();
            entity.render(graphic);
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Ball) {
            double ballCurrentX = (this.ball.getDirection() == Direction.EAST)
                    ? PointUtils.getReferencePointByIndex(ball, 5).getX()
                    : PointUtils.getReferencePointByIndex(ball, 3).getX();
            if (ballCurrentX >= this.getWidth()) {
                this.player.addPoints(1);
                this.ball.drawInitialDirection();
            } else if (ballCurrentX <= this.getX()) {
                this.player2.addPoints(1);
                this.ball.drawInitialDirection();
            }
        }
    }

}
