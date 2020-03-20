package domain.catalog;

import domain.catalog.serialized.AdCatalogDTO;
import domain.catalog.serialized.AdDTO;
import domain.catalog.valueobjects.AdCatalogId;
import domain.catalog.valueobjects.AdId;
import domain.catalog.valueobjects.AdPublicationDate;
import domain.exceptions.AdAlreadyExistsInTheCatalogException;
import domain.exceptions.AdDoesNotExistInTheCatalog;

import java.util.*;

public class AdCatalog {

    private final Map<AdId, Ad> ads;
    private final int maxAdLimit;
    private final ExpirationStrategy expirationStrategy;
    private AdCatalogId adCatalogId;

    private AdCatalog(int maxAdLimit, ExpirationStrategy expirationStrategy, AdCatalogId id) {
        this.maxAdLimit = maxAdLimit;
        this.expirationStrategy = expirationStrategy;
        this.adCatalogId = id;
        this.ads = new LinkedHashMap<>();
    }

    public static AdCatalogBuilder create() {
        return new AdCatalogBuilder();
    }

    public void add(AdId adId, Ad ad) {
        for (AdId existingAdId : ads.keySet()) {
           if (this.ads.get(existingAdId).hasSameTitleAndDescription(ad)) throw new AdAlreadyExistsInTheCatalogException();
        }

        if (this.ads.size() == this.maxAdLimit && !this.ads.isEmpty()) {
            AdId adIdToBeRemoved = this.findAdToBeExpired();
            this.ads.remove(adIdToBeRemoved);
        }
        this.ads.put(adId, ad);
    }

    private AdId findAdToBeExpired() {
        List<Map.Entry<AdId, Ad>> adsList = new LinkedList<>(this.ads.entrySet());
        if (this.expirationStrategy == ExpirationStrategy.OLDEST) Collections.sort(adsList, Comparator.comparing(entry -> entry.getValue().serialize().publicationDate.date));
        if (this.expirationStrategy == ExpirationStrategy.LESS_ACCESSED) Collections.sort(adsList, Comparator.comparing(entry -> entry.getValue().serialize().visits));
        return adsList.get(0).getKey();
    }

    public void remove(AdId adId) {
        if (this.ads.containsKey(adId) == false) throw new AdDoesNotExistInTheCatalog();
        this.ads.remove(adId);
    }

    public AdCatalogDTO listAds() {
        return this.serialize();
    }

    public AdCatalogDTO serialize() {
        AdCatalogDTO adCatalogDTO = new AdCatalogDTO();
        adCatalogDTO.adCatalogId = this.adCatalogId.serialize();
        adCatalogDTO.ads = new ArrayList<>();
        for (AdId adId : ads.keySet()) {
            adCatalogDTO.ads.add(ads.get(adId).serialize());
        }
        return adCatalogDTO;
    }

    public void purgeAdsOlderThanDate(AdPublicationDate limitDate) {
        List<AdId> removableAdIds = new ArrayList<>();
        for (AdId adId : ads.keySet()) {
            Ad storedAd = this.ads.get(adId);
            if (storedAd.isOlderThan(limitDate)) removableAdIds.add(adId);
        }
        for (AdId removableAdId : removableAdIds) {
            this.ads.remove(removableAdId);
        }
    }

    public AdDTO findAdById(AdId adId) {
        if (this.ads.containsKey(adId) == false ) throw new AdDoesNotExistInTheCatalog();
        this.ads.get(adId).incrementVisits();
        return this.ads.get(adId).serialize();
    }

    public AdCatalogId getCatalogId() {
        return this.adCatalogId;
    }


    public static class AdCatalogBuilder {

        private int maxAdStorageLimit;
        private ExpirationStrategy expirationStrategy;
        private AdCatalogId id;

        public AdCatalogBuilder withAdStorageLimit(int maxAdStorageLimit) {
            this.maxAdStorageLimit = maxAdStorageLimit;
            return this;
        }

        public AdCatalogBuilder withExpirationStrategy(ExpirationStrategy expirationStrategy) {
            this.expirationStrategy = expirationStrategy;
            return this;
        }

        public AdCatalogBuilder withId(AdCatalogId adCatalogId) {
            this.id = adCatalogId;
            return this;
        }

        public AdCatalog build() {
            return new AdCatalog(this.maxAdStorageLimit, this.expirationStrategy, this.id);
        }
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
