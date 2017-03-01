package passion.box;

import java.util.Optional;

import passion.collect.ImmutableList;
import passion.collect.ListUtils;

/**
 * Represents a box container.
 *
 * Boxes (i.e. {@link BoxContainer}) are a good idea for standardizing your implementation of a
 * inventory, or a sorted indexed container. Boxes are divided into coordinates, and also
 * allows for indexing using a regular index number.
 *
 * Cartesian coordinates are calculated using {@link #getDivisionRate()}, which converts values
 * in the container into rows and columns. This allows for usage for coordinating. Coordinates
 * start at the index of 0. 0, 0 means the origin.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public interface BoxContainer {

    /**
     * Create a data container, with no method links to any actual implementation.
     * @return
     */
    static BoxContainer createDataContainer() {
        return new LazyCollectContainer();
    }

    /**
     * This rate is used to consider the division of objects into separate mini-groups to allow
     * for cartesian calculation.
     * @return
     */
    default int getDivisionRate() {
        return 9; // 9 is the magic number, since Minecraft supports it.
    }

    /**
     * Retrieves all the objects inside of the box.
     * @return
     */
    ImmutableList<BoxObject> getObjects();

    /**
     * Identifies a box object inside of the container.
     * If the object doesn't exist in the container, this will return -1.
     * @param object
     * @return the index held by this container
     */
    default int identifyObjectIndex(BoxObject object) {
        for (int i = 0; i < getObjects().size(); i++) {
            BoxObject loopObj = getObjects().get(i);
            if (loopObj.equals(object)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Sets a object in the container. This will automatically replace the object, before.
     * @param index
     * @param object
     */
    void set(int index, BoxObject object);

    /**
     * Sets the object in the container using cartes
     * @param x
     * @param y
     * @param object
     */
    void set(int x, int y, BoxObject object);

    /**
     * Retrieves a object from the container. May be not present!
     * @param index
     * @return
     */
    default Optional<BoxObject> get(int index) {
        return Optional.ofNullable(getObjects().get(index));
    }

    /**
     * Retrieves a object from the container, using cartesian coordinates. May not be present!
     * @param x
     * @param y
     * @return
     */
    default Optional<BoxObject> get(int x, int y) {
        return ListUtils.cartesianSelection(x, y, getDivisionRate(), getObjects());
    }

}
