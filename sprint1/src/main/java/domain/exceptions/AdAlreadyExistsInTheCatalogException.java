package domain.exceptions;

public class AdAlreadyExistsInTheCatalogException extends RuntimeException {

    private final String message;

    public AdAlreadyExistsInTheCatalogException() {
        this.message = "The add already exists in the catalog";
    }

}
