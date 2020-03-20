package adcatalog.domain.models.exceptions;

public class TitleLongerThanFiftyCharactersException extends RuntimeException {
    private final String message;

    public TitleLongerThanFiftyCharactersException() {
        this.message = "Title cannot be longer than 50 characters";
    }
}
