package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Uellington Damasceno
 * @since 21/05/2020
 */
public abstract class ControllableEntity extends Entity {

    protected Map<KeyCode, Function> actions;

    public ControllableEntity(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public final void setActionMap(Map<KeyCode, Function> acceptableCodes) {
        this.actions = acceptableCodes;
    }

    public final Set<KeyCode> getAcceptableCodes() {
        return Collections.unmodifiableSet(actions.keySet());
    }

    protected abstract Function up();

    protected abstract Function down();

    public final Object handle(KeyCode code) {
        return this.actions.get(code).apply(this);
    }

}
