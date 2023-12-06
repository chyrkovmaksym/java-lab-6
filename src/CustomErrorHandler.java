import java.util.Collection;

public class CustomErrorHandler {
    void validateObjectNotNull(Object o) {
        if (o == null) {
            throw new CustomException("Element cannot be null.");
        }
    }

    void validateBounds(double min, double max) {
        if (max < min) {
            throw new CustomException("Min content cannot be greater than max content.");
        }
    }

    void validateEmptyCollection(Collection<?> collection) {
        if(collection.isEmpty()){
            throw new CustomException("The collection is empty.");
        }
    }
}