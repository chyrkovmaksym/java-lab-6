// Declaration of the Cookie class, which extends the Sweet class
public class Cookie extends Sweet {
    // Private field representing whether the cookie is gluten-free
    private final boolean isGlutenFree;

    // Constructor to initialize the Cookie object with a name, weight, and gluten-free status
    public Cookie(String name, double weight, boolean isGlutenFree) {
        // Call the constructor of the superclass (Sweet) to initialize common properties
        super(name, weight);
        // Assign the provided gluten-free status to the instance variable
        this.isGlutenFree = isGlutenFree;
    }

    // Getter method to retrieve the gluten-free status of the cookie
    public boolean isGlutenFree() {
        return isGlutenFree;
    }
}
