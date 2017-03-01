package passion.box;

import java.util.ArrayList;
import java.util.List;

import passion.collect.ImmutableList;
import passion.math.CartesianArray;

/**
 * A {@link BoxContainer} implementation based for collecting data, and able to fully
 * operate objects with {@link List}.
 *
 * This should be used for uses of containers to hold data, instead issuing them directly.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class LazyCollectContainer implements BoxContainer {

    private int divisionRate = 9;
    private final List<BoxObject> objects = new ArrayList<>();

    public LazyCollectContainer(List<BoxObject> objects, int divisionRate) {
        this.objects.addAll(objects);
        this.divisionRate = divisionRate;
    }

    public LazyCollectContainer(List<BoxObject> objects) {
        this.objects.addAll(objects);
    }

    public LazyCollectContainer(int divisionRate) {
        this.divisionRate = divisionRate;
    }

    public LazyCollectContainer() {}

    @Override
    public ImmutableList<BoxObject> getObjects() {
        return ImmutableList.constructFrom(objects);
    }

    @Override
    public void set(int index, BoxObject object) {
        objects.set(index, object);
    }

    @Override
    public void set(int x, int y, BoxObject object) {
        objects.set(CartesianArray.computeCartesian(x, y, getDivisionRate()), object);
    }

}
