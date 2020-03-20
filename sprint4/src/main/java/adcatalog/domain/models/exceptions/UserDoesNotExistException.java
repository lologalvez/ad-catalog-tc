package adcatalog.domain.models.exceptions;

public class UserDoesNotExistException extends RuntimeException {
    private final String message;

    public UserDoesNotExistException() {
        this.message = "There is not user matching the userId provided";
    }
}
