// Declaration of the Jelly class, which extends the Sweet class
public class Jelly extends Sweet {
    // Private field representing the flavor of the jelly
    private final String flavor;

    // Constructor to initialize the Jelly object with a name, weight, and flavor
    public Jelly(String name, double weight, String flavor) {
        // Call the constructor of the superclass (Sweet) to initialize common properties
        super(name, weight);
        // Assign the provided flavor to the instance variable
        this.flavor = flavor;
    }

    // Getter method to retrieve the flavor of the jelly
    public String getFlavor() {
        return flavor;
    }
}
