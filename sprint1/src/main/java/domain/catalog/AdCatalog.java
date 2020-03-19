package domain.catalog;

import domain.exceptions.AdAlreadyExistsInTheCatalogException;
import domain.exceptions.AdDoesNotExistInTheCatalog;

import java.util.ArrayList;
import java.util.List;

public class AdCatalog {

    private final List<Ad> ads;

    public AdCatalog() {
        this.ads = new ArrayList<>();
    }

    public void add(Ad ad) {
        if (this.ads.contains(ad)) throw new AdAlreadyExistsInTheCatalogException();
        this.ads.add(ad);
    }

    public void remove(Ad ad) {
        if (this.ads.contains(ad) == false) throw new AdDoesNotExistInTheCatalog();
        this.ads.remove(ad);
    }

    public AdCollection list() {
        return new AdCollection(this.ads);
    }
}
