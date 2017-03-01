package passion.box;

import java.util.function.Consumer;

/**
 * Represents a object in a {@link BoxContainer} which can be acted upon.
 * A listener can be in place, when the object is issued on.
 *
 * {@link CALLED_ELEMENT} is a the type of the element required to call this box object.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class ActionBoxObject<CALLED_ELEMENT> {

    private final Consumer<CALLED_ELEMENT> predicate;

    public ActionBoxObject(Consumer<CALLED_ELEMENT> predicate) {
        this.predicate = predicate;
    }

    public void call(CALLED_ELEMENT element) {
        predicate.accept(element);
    }

}
