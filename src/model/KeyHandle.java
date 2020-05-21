package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Uellington Damasceno
 * @since 20/05/2020
 */
public class KeyHandle implements EventHandler<KeyEvent> {

    private Map<KeyCode, List<ControllableEntity>> relationship;
    private List<ControllableEntity> controllableEntities;

    private KeyCode keyCode;

    public KeyHandle(List<Entity> entities) {
        this.controllableEntities = this.getAllControllableEntities(entities);
        this.relationship = this.getSubscribers(this.controllableEntities);
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
        this.relationship.get(keyCode).forEach(entity -> entity.handle(keyCode));
    }

    private void keyReleasedHandle() {
//        this.relationship.get(keyCode).forEach(entity -> entity.handle(keyCode));
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
