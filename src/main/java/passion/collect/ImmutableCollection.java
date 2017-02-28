package passion.collect;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * Represents a collection, but immutable. Values cannot be changed!
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public abstract class ImmutableCollection<T> implements Collection<T> {

    @Override
    @Deprecated
    public boolean add(T t) {
        return false;
    }

    @Override
    @Deprecated
    public boolean addAll(Collection<? extends T> collection) {
        return false;
    }

    @Override
    @Deprecated
    public void clear() {

    }

    @Override
    @Deprecated
    public boolean remove(Object object) {
        return false;
    }

    @Override
    @Deprecated
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    @Deprecated
    public boolean removeIf(Predicate<? super T> predicate) {
        return false;
    }

    @Override
    @Deprecated
    public boolean retainAll(Collection<?> objects) {
        return false;
    }

}