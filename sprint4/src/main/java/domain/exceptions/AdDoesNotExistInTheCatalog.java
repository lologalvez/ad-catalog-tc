package domain.exceptions;

public class AdDoesNotExistInTheCatalog extends RuntimeException {

    private final String message;

    public AdDoesNotExistInTheCatalog() {
        this.message = "Ad does not exist in the catalogue";
    }
}
