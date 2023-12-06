import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class SweetSetTest {

    @Test
    public void testDefaultConstructor() {
        SweetSet sweetSet = new SweetSet();
        assertNotNull(sweetSet);
        assertEquals(0, sweetSet.size());
    }

    @Test
    public void testSingleItemConstructor() {
        Sweet sweet = new Sweet("TestSweet", 1.0);
        SweetSet sweetSet = new SweetSet(sweet);

        assertNotNull(sweetSet);
        assertEquals(1, sweetSet.size());
        assertTrue(sweetSet.contains(sweet));
    }

    @Test
    public void testCollectionConstructor() {
        Sweet sweet1 = new Sweet("Sweet1", 1.0);
        Sweet sweet2 = new Sweet("Sweet2", 2.0);
        Sweet sweet3 = new Sweet("Sweet3", 3.0);

        // Create a collection of Sweet objects
        List<Sweet> sweets = Arrays.asList(sweet1, sweet2, sweet3);

        SweetSet sweetSet = new SweetSet(sweets);

        assertNotNull(sweetSet);
        assertEquals(3, sweetSet.size());
        assertTrue(sweetSet.contains(sweet1));
        assertTrue(sweetSet.contains(sweet2));
        assertTrue(sweetSet.contains(sweet3));
    }

    @Test
    public void testCollectionConstructorWithNullElement() {
        Sweet sweet1 = new Sweet("Sweet1", 1.0);

        // Create a collection with a null element
        List<Sweet> sweets = Arrays.asList(sweet1, null);

        // Ensure CustomException is thrown when constructing SweetSet
        assertThrows(CustomException.class, () -> new SweetSet(sweets));
    }
}
