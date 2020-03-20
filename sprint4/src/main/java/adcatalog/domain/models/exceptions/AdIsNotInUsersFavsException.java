package adcatalog.domain.models.exceptions;

public class AdIsNotInUsersFavsException extends RuntimeException {

    private final String message;

    public AdIsNotInUsersFavsException() {
        this.message = "Ad is not in users favorites";
    }

}
