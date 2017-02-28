package passion.collect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            partitions.add(list.subList(i, Math.min(algebraN, i + partitionSize)));
        }
        return partitions;
    }

    @Deprecated
    public static void main(String... args) {
        List<Integer> oriList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<List<Integer>> partitioned = partition(oriList, 5);
        for (List<Integer> list : partitioned) {
            System.out.println("NEW LIST");
            list.forEach(System.out::println);
        }
    }

}
