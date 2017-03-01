package passion.math;

import java.util.Arrays;
import java.util.List;

import passion.collect.ListUtils;

/**
 * Utilities to create/decode cartesian coordinates from/to an array.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class CartesianArray {

    public static int computeCartesian(int x, int y, int divisionRate) {
        return x + (y * divisionRate);
    }

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(0, 100000, 10);
        int cartesian = computeCartesian(0, 1, 2);
        System.out.println(cartesian + ": " + integerList.get(cartesian));
        System.out.println(ListUtils.cartesianSelection(0, 1, 2, integerList));
    }

}
