package domain.catalog;

import domain.catalog.serialized.AdCatalogDTO;
import domain.catalog.valueobjects.AdCatalogId;
import domain.catalog.valueobjects.AdId;
import domain.catalog.valueobjects.AdPublicationDate;
import domain.exceptions.AdAlreadyExistsInTheCatalogException;
import domain.exceptions.AdDoesNotExistInTheCatalog;

import java.util.*;

public class AdCatalog {

    private final Map<AdId, Ad> ads;
    private final int maxAdLimit;
    private AdCatalogId adCatalogId;

    public AdCatalog(AdCatalogId adCatalogId) {
        this.adCatalogId = adCatalogId;
        this.ads = new LinkedHashMap<>();
        this.maxAdLimit = 100;
    }

    public void add(AdId adId, Ad ad) {
        for (AdId existingAdId : ads.keySet()) {
           if (this.ads.get(existingAdId).hasSameTitleAndDescription(ad)) throw new AdAlreadyExistsInTheCatalogException();
        }
        this.ads.put(adId, ad);
    }

    public void remove(AdId adId) {
        if (this.ads.containsKey(adId) == false) throw new AdDoesNotExistInTheCatalog();
        this.ads.remove(adId);
    }

    public AdCatalogDTO list() {
        return this.serialize();
    }

    private AdCatalogDTO serialize() {
        AdCatalogDTO adCatalogDTO = new AdCatalogDTO();
        adCatalogDTO.adCatalogId = this.adCatalogId.serialize();
        adCatalogDTO.ads = new ArrayList<>();
        for (AdId adId : ads.keySet()) {
            adCatalogDTO.ads.add(ads.get(adId).serialize());
        }
        return adCatalogDTO;
    }

    public void purgeAdsTilDate(AdPublicationDate limitDate) {
        List<AdId> removableAdIds = new ArrayList<>();
        for (AdId adId : ads.keySet()) {
            Ad storedAd = this.ads.get(adId);
            if (storedAd.isOlderThan(limitDate)) removableAdIds.add(adId);
        }
        for (AdId removableAdId : removableAdIds) {
            this.ads.remove(removableAdId);
        }
    }

    public AdCatalogId getCatalogId() {
        return this.adCatalogId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdCatalog adCatalog = (AdCatalog) o;

        if (maxAdLimit != adCatalog.maxAdLimit) return false;
        if (!Objects.equals(ads, adCatalog.ads)) return false;
        return Objects.equals(adCatalogId, adCatalog.adCatalogId);
    }

    @Override
    public int hashCode() {
        int result = ads != null ? ads.hashCode() : 0;
        result = 31 * result + maxAdLimit;
        result = 31 * result + (adCatalogId != null ? adCatalogId.hashCode() : 0);
        return result;
    }
}
