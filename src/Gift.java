import java.util.ArrayList;
import java.util.Comparator;

// Declaration of the Gift class
public class Gift {
    // Private field representing a collection of sweets in the gift
    private final ArrayList<Sweet> sweets;

    // Constructor to initialize the Gift object with an empty list of sweets
    public Gift() {
        sweets = new ArrayList<>();
    }

    // Method to add a sweet to the gift
    public void addSweet(Sweet sweet) throws Exception {
        try {
            sweets.add(sweet);
        } catch (Exception e) {
            // Handle exceptions that might occur during adding a sweet
            throw new Exception("Error adding sweet to the gift: " + e.getMessage());
        }
    }

    // Method to calculate the total weight of all sweets in the gift
    public double calculateWeight() throws Exception {
        try {
            double totalWeight = 0;
            for (Sweet sweet : sweets) {
                totalWeight += sweet.getWeight();
            }
            return totalWeight;
        } catch (Exception e) {
            // Handle exceptions that might occur during weight calculation
            throw new Exception("Error calculating total weight of the gift: " + e.getMessage());
        }
    }

    // Method to sort the sweets in the gift using a provided comparator
    public void sortSweetsBy(Comparator<Sweet> comparator) throws Exception {
        try {
            sweets.sort(comparator);
        } catch (Exception e) {
            // Handle exceptions that might occur during sorting
            throw new Exception("Error sorting sweets in the gift: " + e.getMessage());
        }
    }

    // Method to find a candy with chocolate content within a specified range
    public Sweet findInRangeOfChocolate(double minContent, double maxContent) throws Exception {
        try {
            // Check if minContent is greater than maxContent
            if (minContent > maxContent) {
                throw new Exception("Error: Min content cannot be greater than max content.");
            }

            for (Sweet sweet : sweets) {
                // Check if the sweet is an instance of Candy
                if (sweet instanceof Candy candy) {
                    // Check if the chocolate content is within the specified range
                    if (candy.getChocolateContent() >= minContent && candy.getChocolateContent() <= maxContent) {
                        return candy;
                    }
                }
            }
            return null; // Return null if no candy is found within the specified range
        } catch (Exception e) {
            // Handle exceptions that might occur during searching for candy
            throw new Exception("Error finding candy in the gift: " + e.getMessage());
        }
    }


    // Getter method to retrieve the list of sweets in the gift
    public ArrayList<Sweet> getSweets() {
        return sweets;
    }
}
