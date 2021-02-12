package game.pong.input;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import game.pong.model.ControllableEntity;
import penguine.game.model.Entity;

/**
 *
 * @author Uellington Damasceno
 */
public class KeyListener implements EventHandler<KeyEvent> {

    private Map<KeyCode, List<ControllableEntity>> relationship;

    private KeyCode keyCode;

    public KeyListener(List<Entity> entities) {
        List controllableEntities = this.getAllControllableEntities(entities);
        this.relationship = this.getSubscribers(controllableEntities);
    }

    private List<ControllableEntity> getAllControllableEntities(List<Entity> entities) {
        return entities
                .stream()
                .filter(ControllableEntity.class::isInstance)
                .map(ControllableEntity.class::cast)
                .collect(Collectors.toList());

    }

    private Map<KeyCode, List<ControllableEntity>> getSubscribers(List<ControllableEntity> entities) {
        Map<KeyCode, List<ControllableEntity>> map = new HashMap();
        List<ControllableEntity> subscribers;

        Set<KeyCode> acceptableCodes = entities.stream()
                .map(controllableEntity -> controllableEntity.getAcceptableCodes())
                .flatMap(codes -> codes.stream())
                .collect(Collectors.toSet());

        for (KeyCode acceptableCode : acceptableCodes) {
            subscribers = new LinkedList();
            for (ControllableEntity controllableEntity : entities) {
                if (controllableEntity.getAcceptableCodes().contains(acceptableCode)) {
                    subscribers.add(controllableEntity);
                }
            }
            map.put(acceptableCode, subscribers);
        }
        return map;
    }

    private void keyPressHandle() {
        this.relationship.get(keyCode).forEach(entity -> entity.keyPressed(keyCode));
    }

    private void keyReleasedHandle() {
        this.relationship.get(keyCode).forEach(entity -> entity.keyReleased(keyCode));
    }

    @Override
    public void handle(KeyEvent event) {
        this.keyCode = event.getCode();
        if (this.relationship.keySet().contains(keyCode)) {
            if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                this.keyPressHandle();
            } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
                this.keyReleasedHandle();
            } else {
                event.consume();
            }
        } else {
            event.consume();
        }
    }
}
