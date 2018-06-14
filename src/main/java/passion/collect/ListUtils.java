package passion.collect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Represents list utilities.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class ListUtils {

    /**
     * Partitions a list into multiple smaller lists from a partition size.
     * @param list
     * @param partitionSize
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> partition(List<T> list, int partitionSize) {
        List<List<T>> partitions = new ArrayList<>();
        final int algebraN = list.size();
        for (int i = 0; i < algebraN; i += partitionSize) {
            partitions.add(new ArrayList<T>(list.subList(i, Math.min(algebraN, i + partitionSize)
            )));
        }
        return partitions;
    }

    /**
     * Determines whether the specified index is not null in the specified list.
     * @param index
     * @param objects
     * @return
     */
    public static boolean containsIndex(int index, List<?> objects) {
        if (objects.size() >= index) {
            for (int i = 0; i < objects.size(); i++) {
                if (i == index) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Creates a {@link List} from a {@link Enumeration}.
     * @param enumeration
     * @param <T>
     * @return
     */
    public static <T> List<T> createFrom(Enumeration<T> enumeration) {
        List<T> list = new ArrayList<T>();
        while (enumeration.hasMoreElements()) {
            T object = enumeration.nextElement();
            if (object != null) {
                list.add(object);
            }
        }
        return list;
    }

    /**
     * Combines a bunch of lists inside of a large list.
     * @param lists
     * @param <T>
     * @return
     */
    public static <T> List<T> combine(List<List<T>> lists) {
        List<T> majorList = new ArrayList<T>();
        lists.forEach(majorList::addAll);
        return majorList;
    }

    /**
     * Divides a list into separate columns and tables and selects the one that
     * is positioned at the specified X and Y coords.
     * @param x
     * @param y
     * @param newLineRate the rate for dividing each line
     * @param list
     * @param <T>
     * @return
     */
    public static <T> Optional<T> cartesianSelection(int x, int y, int newLineRate, List<T> list) {
        // array: 1, 2, 3, 4, 5, 6
        // divided into:
        // 1 2 3
        // 4 5 6
        List<List<T>> lines = partition(list, newLineRate);
        if (lines.size() >= y) {
            // lines = y
            // this means that we can access the y access for that specified coordinate
            List<T> row = lines.get(y);
            // check if the row contains enough
            if (row.size() >= x) {
                // enough to contain x
                return Optional.of(row.get(x));
            }
        }
        return Optional.empty();
    }

    /**
     * Inverts the values of a list.
     * @param map
     * @param <A>
     * @param <B>
     * @return
     */
    public static <A, B> Map<B, A> invert(Map<A, B> map) {
        Map<B, A> map1 = new HashMap<>();
        map.forEach((k, v) -> map1.put(v, k));
        return map1;
    }

    @Deprecated
    public static void main(String... args) {
        List<Integer> oriList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<List<Integer>> partitioned = partition(oriList, 5);
        for (List<Integer> list : partitioned) {
            System.out.println("NEW LIST");
            list.forEach(System.out::println);
        }
        // second test
        // 1 2 3 4 5
        // 6 7 8 9 10
        // 11
        List<Integer> otherList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        Optional<Integer> possible = cartesianSelection(0, 2, 5, otherList);
        assert possible.isPresent() && possible.get() == 11;
        System.out.println(possible.get());
        Optional<Integer> possible2 = cartesianSelection(5, 11, 5, otherList);
        // test if it will glitch out if its not correct
        System.out.println(possible2.isPresent());
        assert !possible2.isPresent();
        Optional<Integer> get10 = cartesianSelection(4, 1, 5, otherList);
        System.out.println(get10.get());
        assert get10.isPresent() && get10.get() == 10;
        List<Integer> otherList2 = Arrays.asList(0, 1);
        System.out.println(containsIndex(0, otherList2) + " : " + containsIndex(1, otherList2)
                + " : " + containsIndex(2, otherList2));
    }

}
