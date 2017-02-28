package passion.collect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Lazy use of {@link ImmutableList}, but still effective.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class LazyImmutableList<T> extends ImmutableList<T> {

    private final List<T> list;

    public LazyImmutableList(List<T> list) {
        this.list = list;
    }

    public static void main(String... args) {
        List<AtomicBoolean> concurrentList = new ArrayList<>();
        concurrentList.add(new AtomicBoolean(false));
        ImmutableList<AtomicBoolean> immutableList = ImmutableList.constructFrom(concurrentList);
        immutableList.get(0).set(true);
        immutableList.add(new AtomicBoolean(true));
        System.out.println("(" + concurrentList.get(0).get()  + ") size, " + immutableList.size()
                + ", secondary size, " + concurrentList.size());
        assert concurrentList.get(0).get();
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }
}
