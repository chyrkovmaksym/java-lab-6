import java.util.Comparator;

// Main class containing the program's entry point
public class Main {
    // Main method where the program execution begins
    public static void main(String[] args) {
        try {
            // Obtain a gift by calling the getGift() method
            Gift myGift = getGift();

            // Performing operations on the gift

            // Print the total weight of the gift
            System.out.println("Total weight of the gift: " + myGift.calculateWeight() + " kg");

            // Sort sweets in the gift by weight using a custom comparator (SortByWeight)
            System.out.println("Sorting sweets by weight:");
            myGift.sortSweetsBy(new SortByWeight());
            for (Sweet sweet : myGift.getSweets()) {
                // Print each sweet's name and weight after sorting
                System.out.println(sweet.getName() + " - " + sweet.getWeight() + " kg");
            }

            // Define a range for chocolate content
            double minContent = 0.4;
            double maxContent = 0.8;

            // Find a candy within the specified range of chocolate content
            Sweet foundCandy = myGift.findInRangeOfChocolate(minContent, maxContent);
            if (foundCandy != null) {
                // Print the name of the found candy
                System.out.println("Found candy in the specified range of chocolate content: " + foundCandy.getName());
            } else {
                // Print a message if no candy is found in the specified range
                System.out.println("No candy found in the specified range of chocolate content.");
            }
        } catch (CustomException e) {
            // Handle any unexpected exception that might occur during program execution
            e.printStackTrace();
        }
    }

    // Method to create and return a gift with various sweets
    private static Gift getGift() {
        // Create instances of different sweets (Candy, Cookie, Jelly)
        Candy chocolateCandy = new Candy("Chocolate Candy", 0.2, 0.7);
        Candy caramelCandy = new Candy("Caramel Candy", 0.15, 0.5);
        Cookie glutenFreeCookie = new Cookie("Gluten-Free Cookie", 0.1, true);
        Jelly fruitJelly = new Jelly("Fruit Jelly", 0.08, "Strawberry");

        // Create a gift and add the sweets to it
        Gift myGift = new Gift();
        myGift.addSweet(chocolateCandy);
        myGift.addSweet(caramelCandy);
        myGift.addSweet(glutenFreeCookie);
        myGift.addSweet(fruitJelly);

        // Return the created gift
        return myGift;
    }
}
