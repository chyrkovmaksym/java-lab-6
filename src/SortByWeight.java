import java.util.Comparator;

/**
 * A comparator class for sorting Sweets objects based on their weight in ascending order.
 */
public class SortByWeight implements  Comparator<Sweet>{
    @Override
    public int compare(Sweet a, Sweet b) {
        // Using Double.compare to compare double values (weight)
        return Double.compare(a.getWeight(), b.getWeight());
    }
}
