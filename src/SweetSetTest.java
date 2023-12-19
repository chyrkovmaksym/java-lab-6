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
        Jelly sweet1 = new Jelly("Sweet1", 1.0, "apple");
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

    @Test
    public void testSizeMethod() {
        SweetSet sweetSet = new SweetSet();
        assertEquals(0, sweetSet.size());

        Sweet sweet1 = new Sweet("Chocolate", 12.3);
        sweetSet.add(sweet1);
        assertEquals(1, sweetSet.size());

        Sweet sweet2 = new Sweet("Candy", 3.5);
        sweetSet.add(sweet2);
        assertEquals(2, sweetSet.size());

        sweetSet.remove(sweet1);
        assertEquals(1, sweetSet.size());

        sweetSet.clear();
        assertEquals(0, sweetSet.size());
    }

    @Test
    public void testIterator() {
        SweetSet sweetSet = new SweetSet();

        Candy chocolateCandy = new Candy("Chocolate Candy", 0.2, 0.7);
        Candy caramelCandy = new Candy("Caramel Candy", 0.15, 0.5);
        Cookie glutenFreeCookie = new Cookie("Gluten-Free Cookie", 0.1, true);
        Jelly fruitJelly = new Jelly("Fruit Jelly", 0.08, "Strawberry");

        sweetSet.addAll(Set.of(chocolateCandy, caramelCandy, glutenFreeCookie, fruitJelly));

        for(Sweet sweet : sweetSet) {
            assertNotNull(sweet);
            assertNotNull(sweet.getName());
            assertTrue(sweet.getWeight() > 0);
        }

        Iterator<Sweet> iterator = sweetSet.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }

        assertThrows(CustomException.class, () -> iterator.next());

    }

    @Test
    public void testToArray() {
        SweetSet sweetSet = new SweetSet();

        Candy chocolateCandy = new Candy("Chocolate Candy", 0.2, 0.7);
        Cookie glutenFreeCookie = new Cookie("Gluten-Free Cookie", 0.1, true);

        sweetSet.add(chocolateCandy);
        sweetSet.add(glutenFreeCookie);

        Sweet[] array = sweetSet.toArray();
        assertNotNull(array);
        assertEquals(2, array.length);
        assertTrue(Arrays.asList(array).contains(chocolateCandy));
        assertTrue(Arrays.asList(array).contains(glutenFreeCookie));
    }

    @Test
    public void testToArrayWithProvidedArray() {
        SweetSet sweetSet = new SweetSet();

        Candy chocolateCandy = new Candy("Chocolate Candy", 0.2, 0.7);
        Cookie glutenFreeCookie = new Cookie("Gluten-Free Cookie", 0.1, true);

        sweetSet.add(chocolateCandy);
        sweetSet.add(glutenFreeCookie);

        Sweet[] providedArray = new Sweet[3];

        Sweet[] resultArray = sweetSet.toArray(providedArray);
        assertNotNull(resultArray);
        assertSame(providedArray, resultArray); // Check that the provided array is the same as the returned array
        assertEquals(3, resultArray.length); // Check that the size of the array is correct
        assertTrue(Arrays.asList(resultArray).contains(chocolateCandy));
        assertTrue(Arrays.asList(resultArray).contains(glutenFreeCookie));
        assertNull(resultArray[2]); // Check that the unused element in the provided array is set to null
    }

    @Test
    public void testToArrayResize() {
        SweetSet sweetSet = new SweetSet();

        Candy chocolateCandy = new Candy("Chocolate Candy", 0.2, 0.7);
        Cookie glutenFreeCookie = new Cookie("Gluten-Free Cookie", 0.1, true);

        sweetSet.add(chocolateCandy);
        sweetSet.add(glutenFreeCookie);

        Sweet[] providedArray = new Sweet[1];

        Sweet[] resultArray = sweetSet.toArray(providedArray);
        assertNotNull(resultArray);
        assertNotSame(providedArray, resultArray); // Check that a new array is created
        assertEquals(2, resultArray.length); // Check that the size of the array is correct
        assertTrue(Arrays.asList(resultArray).contains(chocolateCandy));
        assertTrue(Arrays.asList(resultArray).contains(glutenFreeCookie));
    }

    @Test
    public void testIsEmpty() {
        SweetSet emptySet = new SweetSet();
        assertTrue(emptySet.isEmpty());

        SweetSet nonEmptySet = new SweetSet();
        nonEmptySet.add(new Candy("Chocolate Candy", 0.2, 0.7));
        assertFalse(nonEmptySet.isEmpty());

        nonEmptySet.clear();
        assertTrue(nonEmptySet.isEmpty());
    }

    @Test
    public void testRemoveAll() {
        SweetSet sweetSet = new SweetSet();

        Candy chocolateCandy = new Candy("Chocolate Candy", 0.2, 0.7);
        Candy caramelCandy = new Candy("Caramel Candy", 0.15, 0.5);
        Cookie glutenFreeCookie = new Cookie("Gluten-Free Cookie", 0.1, true);
        Jelly fruitJelly = new Jelly("Fruit Jelly", 0.08, "Strawberry");

        sweetSet.addAll(Arrays.asList(chocolateCandy, caramelCandy, glutenFreeCookie, fruitJelly));

        Collection<Sweet> sweetsToRemove = new HashSet<>(Arrays.asList(chocolateCandy, glutenFreeCookie));

        assertTrue(sweetSet.removeAll(sweetsToRemove));
        assertFalse(sweetSet.contains(chocolateCandy));
        assertTrue(sweetSet.contains(caramelCandy));
        assertFalse(sweetSet.contains(glutenFreeCookie));
        assertTrue(sweetSet.contains(fruitJelly));
        assertEquals(2, sweetSet.size());
    }

    @Test
    public void testRetainAll() {
        SweetSet sweetSet = new SweetSet();

        Candy chocolateCandy = new Candy("Chocolate Candy", 0.2, 0.7);
        Candy caramelCandy = new Candy("Caramel Candy", 0.15, 0.5);
        Cookie glutenFreeCookie = new Cookie("Gluten-Free Cookie", 0.1, true);
        Jelly fruitJelly = new Jelly("Fruit Jelly", 0.08, "Strawberry");

        sweetSet.addAll(Arrays.asList(chocolateCandy, caramelCandy, glutenFreeCookie, fruitJelly));

        Collection<Sweet> sweetsToRetain = new HashSet<>(Arrays.asList(chocolateCandy, glutenFreeCookie));

        assertTrue(sweetSet.retainAll(sweetsToRetain));
        assertTrue(sweetSet.contains(chocolateCandy));
        assertFalse(sweetSet.contains(caramelCandy));
        assertTrue(sweetSet.contains(glutenFreeCookie));
        assertFalse(sweetSet.contains(fruitJelly));
        assertEquals(2, sweetSet.size());
    }

    @Test
    public void testContainsAll() {
        SweetSet sweetSet = new SweetSet();

        Candy chocolateCandy = new Candy("Chocolate Candy", 0.2, 0.7);
        Candy caramelCandy = new Candy("Caramel Candy", 0.15, 0.5);
        Cookie glutenFreeCookie = new Cookie("Gluten-Free Cookie", 0.1, true);
        Jelly fruitJelly = new Jelly("Fruit Jelly", 0.08, "Strawberry");

        sweetSet.addAll(Arrays.asList(chocolateCandy, caramelCandy, glutenFreeCookie, fruitJelly));

        Collection<Sweet> allSweets = Arrays.asList(chocolateCandy, caramelCandy, glutenFreeCookie, fruitJelly);

        Cookie glutenCookie = new Cookie("Gluten Cookie", 0.1, true);

        Collection<Sweet> someSweets = Arrays.asList(chocolateCandy, glutenFreeCookie, glutenCookie);

        assertTrue(sweetSet.containsAll(allSweets));
        assertFalse(sweetSet.containsAll(someSweets));
    }

    @Test
    public void testRemoveElementNotFound() {
        SweetSet sweetSet = new SweetSet();

        Candy chocolateCandy = new Candy("Chocolate Candy", 0.2, 0.7);
        Candy caramelCandy = new Candy("Caramel Candy", 0.15, 0.5);

        sweetSet.addAll(Arrays.asList(chocolateCandy, caramelCandy));

        assertFalse(sweetSet.remove(new Candy("Mint Candy", 0.18, 0.6)));

        assertTrue(sweetSet.contains(chocolateCandy));
        assertTrue(sweetSet.contains(caramelCandy));
    }

    @Test
    public void testAdd() {
        SweetSet sweetSet = new SweetSet();

        Candy chocolateCandy = new Candy("Chocolate Candy", 0.2, 0.7);
        Candy caramelCandy = new Candy("Caramel Candy", 0.15, 0.5);
        Cookie glutenFreeCookie = new Cookie("Gluten-Free Cookie", 0.1, true);

        assertTrue(sweetSet.add(chocolateCandy));
        assertEquals(1, sweetSet.size());

        assertTrue(sweetSet.add(caramelCandy));
        assertEquals(2, sweetSet.size());

        assertFalse(sweetSet.add(chocolateCandy));
        assertEquals(2, sweetSet.size()); // Size should not change

        assertTrue(sweetSet.add(glutenFreeCookie));
        assertEquals(3, sweetSet.size());

        assertTrue(sweetSet.contains(chocolateCandy));
        assertTrue(sweetSet.contains(caramelCandy));
        assertTrue(sweetSet.contains(glutenFreeCookie));
    }

    @Test
    public void testAddWithArrayResize() {
        SweetSet sweetSet = new SweetSet();

        for (int i = 0; i < 15; i++) {
            Sweet sweet = new Sweet("Sweet ",  (i + 1));
            sweetSet.add(sweet);
        }

        Candy chocolateCandy = new Candy("Chocolate Candy", 0.2, 0.7);

        assertTrue(sweetSet.add(chocolateCandy));
    }

}
