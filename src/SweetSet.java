import java.util.*;

/**
 * Represents a set of Sweet objects with dynamic resizing capabilities.
 * The implementation is based on an array, and it provides standard set
 * operations like add, remove, contains, etc.
 */
public class SweetSet implements Set<Sweet>{
    // Constants
    private static final int INITIAL_CAPACITY = 15;
    private static final double GROWTH_FACTOR = 1.3;

    // Fields
    private Sweet[] elements;
    private int size;

    /**
     * Constructs an empty SweetSet with an initial capacity.
     */
    public SweetSet(){
        this.elements = new Sweet[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Constructs a SweetSet with a single initial element.
     *
     * @param firstItem the initial element to add to the set.
     */
    public SweetSet(Sweet firstItem) {
        this();
        this.add(firstItem);
    }

    /**
     * Constructs a SweetSet from a collection of Sweet objects.
     *
     * @param items the collection of Sweet objects to add to the set.
     */
    public SweetSet(Collection<Sweet> items){
        this();
        this.addAll(items);
    }

    /**
     * Returns the number of elements in the set.
     *
     * @return the number of elements in the set.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Checks if the set is empty.
     *
     * @return true if the set is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Checks if the set contains a specific element.
     *
     * @param o the element to check for.
     * @return true if the set contains the element, false otherwise.
     */
    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, elements[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an iterator over the elements in the set.
     *
     * @return an iterator over the elements in the set.
     */
    @Override
    public Iterator<Sweet> iterator() {
        return new Iterator<Sweet>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Sweet next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (Sweet) elements[currentIndex++];
            }
        };
    }

    /**
     * Returns an array containing all the elements in the set.
     *
     * @return an array containing all the elements in the set.
     */
    @Override
    public Sweet[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    /**
     * Returns an array containing all the elements in the set; the runtime type
     * of the returned array is that of the specified array.
     *
     * @param array the array into which the elements of the set are to be stored.
     * @return an array containing the elements of the set.
     */
    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            // If the provided array is too small, create a new one with the correct type and size
            array = Arrays.copyOf(array, size);
        } else if (array.length > size) {
            // If the provided array is larger, set the first unused element to null
            array[size] = null;
        }

        // Manually copy the elements into the provided array
        for (int i = 0; i < size; i++) {
            array[i] = (T) elements[i];
        }

        return array;
    }

    /**
     * Adds a Sweet element to the set.
     *
     * @param sweet the Sweet element to add.
     * @return true if the element was added successfully, false if it already
     *         exists in the set.
     */
    @Override
    public boolean add(Sweet sweet) {
        if (!contains(sweet)) {
            if (size == elements.length) {
                // If the array is full, resize it
                int newCapacity = (int) (elements.length * GROWTH_FACTOR);
                elements = Arrays.copyOf(elements, newCapacity);
            }
            elements[size++] = sweet;
            return true; // Element added successfully
        }
        return false; // Element already exists in the set
    }

    /**
     * Removes a specific element from the set.
     *
     * @param o the element to remove.
     * @return true if the element was removed successfully, false if it was not
     *         found in the set.
     */
    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, elements[i])) {
                // Shift elements to remove the found element using a for loop
                for (int j = i; j < size - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                elements[--size] = null; // Clear the last element
                return true; // Element removed successfully
            }
        }
        return false; // Element not found in the set
    }

    /**
     * Checks if the set contains all elements in the specified collection.
     *
     * @param collection the collection to check for elements.
     * @return true if the set contains all elements of the collection, false
     *         otherwise.
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object o : collection) {
            if (!contains(o)) {
                return false; // At least one element is not in the set
            }
        }
        return true; // All elements from the collection are in the set
    }

    /**
     * Adds all elements from the specified collection to the set.
     *
     * @param collection the collection containing elements to add.
     * @return true if at least one element was added to the set, false otherwise.
     */
    @Override
    public boolean addAll(Collection<? extends Sweet> collection) {
        boolean modified = false;

        for (Sweet sweet : collection) {
            if (add(sweet)) {
                modified = true; // At least one element was added
            }
        }

        return modified;
    }

    /**
     * Retains only the elements in the set that are contained in the specified
     * collection.
     *
     * @param collection the collection containing elements to retain.
     * @return true if the set was modified as a result of this operation, false
     *         otherwise.
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        Objects.requireNonNull(collection);

        int originalSize = size;
        Sweet[] newArray = new Sweet[size];
        int newSize = 0;

        for (int i = 0; i < size; i++) {
            if (collection.contains(elements[i])) {
                newArray[newSize++] = elements[i];
            }
        }

        // Update the internal array with the new array
        elements = Arrays.copyOf(newArray, newSize);

        // Update the size
        size = newSize;

        return newSize < originalSize;
    }

    /**
     * Removes all elements in the set that are contained in the specified
     * collection.
     *
     * @param collection the collection containing elements to remove.
     * @return true if the set was modified as a result of this operation, false
     *         otherwise.
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        Objects.requireNonNull(collection);

        int originalSize = size;

        for (int i = 0; i < size; i++) {
            if (collection.contains(elements[i])) {
                // Use the existing remove method to remove the element
                remove(elements[i]);
                i--; // Adjust the index to recheck the current position
            }
        }

        return size < originalSize;
    }

    /**
     * Removes all elements from the set, leaving it empty.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }
}
