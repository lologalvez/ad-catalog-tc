package domain.exceptions;

public class EmptyDescriptionException extends RuntimeException {

    private final String message;

    public EmptyDescriptionException() {
        this.message = "Description cannot be empty";
    }
}
