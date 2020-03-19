package domain.catalog;

import domain.exceptions.AdAlreadyExistsInTheCatalogException;
import domain.exceptions.AdDoesNotExistInTheCatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdCatalog {

    private final List<Ad> ads;
    private AdCatalogId adCatalogId;

    public AdCatalog(AdCatalogId adCatalogId) {
        this.adCatalogId = adCatalogId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdCatalog adCatalog = (AdCatalog) o;

        if (!Objects.equals(ads, adCatalog.ads)) return false;
        return Objects.equals(adCatalogId, adCatalog.adCatalogId);
    }

    @Override
    public int hashCode() {
        int result = ads != null ? ads.hashCode() : 0;
        result = 31 * result + (adCatalogId != null ? adCatalogId.hashCode() : 0);
        return result;
    }
}
