package passion.collect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * Abstract class for collections, but for Lists!
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public abstract class ImmutableList<T> extends ImmutableCollection<T> implements List<T> {

    @Override
    @Deprecated
    public void sort(Comparator<? super T> c) {

    }

    @Override
    @Deprecated
    public T remove(int index) {
        return this.get(index);
    }

    @Override
    @Deprecated
    public void add(int index, T element) {

    }

    public static <T> ImmutableList<T> constructFrom(List<T> ts) {
        return new LazyImmutableList<T>(ts);
    }

    public static <T> ImmutableList<T> constructFrom(T... ts) {
        List<T> ts1 = new ArrayList<T>();
        for (T t : ts) {
            ts1.add(t);
        }
        return constructFrom(ts1);
    }

    @Override
    @Deprecated
    public boolean addAll(int index, Collection<? extends T> newElements) {
        return false;
    }


    @Override
    @Deprecated
    public void replaceAll(UnaryOperator<T> operator) {

    }

    @Override
    @Deprecated
    public T set(int index, T element) {
        return element;
    }

}
