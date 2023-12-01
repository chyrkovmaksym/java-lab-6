// Declaration of the Sweet class
public class Sweet {
    // Private fields representing the characteristics of a sweet
    private final String name;    // The name of the sweet
    private final double weight;  // The weight of the sweet

    // Constructor to initialize the Sweet object with a name and weight
    public Sweet(String name, double weight) {
        this.name = name;         // Assign the provided name to the instance variable
        this.weight = weight;     // Assign the provided weight to the instance variable
    }

    // Getter method to retrieve the name of the sweet
    public String getName() {
        return name;
    }

    // Getter method to retrieve the weight of the sweet
    public double getWeight() {
        return weight;
    }
}
