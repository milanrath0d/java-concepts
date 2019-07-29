import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Add Description
 *
 * @author Milan Rathod
 */
public class Test {

    public static int countPairs(List<Integer> arr, long k) {
        Set<Long> mapping = new HashSet<>();
        Set<String> cache = new HashSet<>();
        for (long element: arr) {
            long temp = k - element;
            if (mapping.contains(temp)) {
                long[] tempArray = new long[] {temp, element};
                Arrays.sort(tempArray);
                cache.add(Arrays.toString(tempArray));
            }
            mapping.add(element);
        }

        return cache.size();
    }

    public static void main(String[] args) {
        System.out.println(countPairs(List.of(1,3,46,1,3,9), 47));
    }

}