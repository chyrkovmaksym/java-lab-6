// Declaration of the Candy class, which extends the Sweet class
public class Candy extends Sweet {
    // Private field representing the chocolate content of the candy
    private final double chocolateContent;

    // Constructor to initialize the Candy object with a name, weight, and chocolate content
    public Candy(String name, double weight, double chocolateContent) {
        // Call the constructor of the superclass (Sweet) to initialize common properties
        super(name, weight);
        // Assign the provided chocolate content to the instance variable
        this.chocolateContent = chocolateContent;
    }

    // Getter method to retrieve the chocolate content of the candy
    public double getChocolateContent() {
        return chocolateContent;
    }
}
