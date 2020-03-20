package adcatalog.domain.models.exceptions;

public class SameTitleAndDescriptionException extends RuntimeException {

    private final String message;

    public SameTitleAndDescriptionException() {
        this.message = "Title and description cannot have same content";
    }
}
