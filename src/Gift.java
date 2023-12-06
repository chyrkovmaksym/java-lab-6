import java.util.ArrayList;
import java.util.Comparator;

// Declaration of the Gift class
public class Gift {
    // Private field representing a collection of sweets in the gift
    private final ArrayList<Sweet> sweets;
    private CustomErrorHandler errorHandler;

    // Constructor to initialize the Gift object with an empty list of sweets
    public Gift() {
        sweets = new ArrayList<>();
        this.errorHandler = new CustomErrorHandler();
    }

    // Method to add a sweet to the gift
    public void addSweet(Sweet sweet) {
        sweets.add(sweet);
    }

    // Method to calculate the total weight of all sweets in the gift
    public double calculateWeight() throws CustomException {
        errorHandler.validateEmptyCollection(sweets);

        double totalWeight = 0;
        for (Sweet sweet : sweets) {
            totalWeight += sweet.getWeight();
        }
        return totalWeight;
    }

    // Method to sort the sweets in the gift using a provided comparator
    public void sortSweetsBy(Comparator<Sweet> comparator) {
        sweets.sort(comparator);
    }

    // Method to find a candy with chocolate content within a specified range
    public Sweet findInRangeOfChocolate(double minContent, double maxContent) throws CustomException {
        // Check if minContent is greater than maxContent
        errorHandler.validateBounds(minContent, maxContent);

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
    }


    // Getter method to retrieve the list of sweets in the gift
    public ArrayList<Sweet> getSweets() {
        return sweets;
    }
}
