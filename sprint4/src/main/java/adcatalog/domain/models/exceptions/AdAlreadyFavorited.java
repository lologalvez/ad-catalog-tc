package adcatalog.domain.models.exceptions;

public class AdAlreadyFavorited extends RuntimeException {
    private final String message;

    public AdAlreadyFavorited() {
        this.message = "Ad already favorited by this user";
    }
}
