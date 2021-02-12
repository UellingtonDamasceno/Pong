package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Uellington Damasceno
 * @since 21/05/2020
 */
public abstract class ControllableEntity extends Entity {

    protected Map<KeyCode, Consumer> actions;
    protected boolean up, down;
    protected int offset;

    public ControllableEntity(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.up = false;
        this.down = false;
        this.offset = 3;
        this.actions = new HashMap();
    }

    public final void setActionMap(Map<KeyCode, Consumer> acceptableCodes) {
        this.actions = acceptableCodes;
    }
    
    public final void addAction(KeyCode code, Consumer consumer){
        this.actions.put(code, consumer);
    }

    public final Set<KeyCode> getAcceptableCodes() {
        return Collections.unmodifiableSet(actions.keySet());
    }

    protected abstract Consumer up();

    protected abstract Consumer down();

    public final void keyPressed(KeyCode code) {
        this.actions.get(code).accept(code);
    }

    public final void keyReleased(KeyCode code) {
        this.down = false;
        this.up = false;
    }

}
