package adcatalog.domain.models.exceptions;

public class AdCatalogDoesNotExistException extends RuntimeException {
    private final String message;

    public AdCatalogDoesNotExistException() {
        this.message = "There is no ad catalog matching that ID";
    }

}
