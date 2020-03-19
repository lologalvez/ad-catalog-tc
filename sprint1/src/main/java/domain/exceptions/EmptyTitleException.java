package domain.exceptions;

public class EmptyTitleException extends RuntimeException {

    private final String message;

    public EmptyTitleException() {
        this.message = "Ad title cannot be empty";
    }
}
