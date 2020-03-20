package domain;

import domain.catalog.*;
import domain.catalog.serialized.AdCatalogDTO;
import domain.catalog.valueobjects.*;
import domain.exceptions.AdCatalogDoesNotExistException;
import domain.domainservices.timeservice.TimeService;
import domain.domainservices.uuidservice.UUIDProvider;

public class AdCatalogService {

    private final AdCatalogRepository adCatalogRepository;
    private final TimeService timeService;
    private final UUIDProvider uuidProvider;

    public AdCatalogService(AdCatalogRepository adCatalogRepository, TimeService timeService, UUIDProvider uuidProvider) {
        this.adCatalogRepository = adCatalogRepository;
        this.timeService = timeService;
        this.uuidProvider = uuidProvider;
    }

    public AdCatalogId createCatalog() {
        AdCatalogId adCatalogId = new AdCatalogId(uuidProvider.getUUID());
        AdCatalog adCatalog = AdCatalog.create()
                .withId(adCatalogId)
                .withAdStorageLimit(100)
                .withExpirationStrategy(ExpirationStrategy.OLDEST)
                .build();

        adCatalogRepository.save(adCatalog);
        return adCatalogId;
    }

    public AdId addAd(AdTitle title, AdDescription description, AdCatalogId adCatalogId) {
       AdCatalog adCatalog = adCatalogRepository.findById(adCatalogId).orElseThrow(AdCatalogDoesNotExistException::new);

        AdId adId = new AdId(uuidProvider.getUUID());
        Ad ad = Ad.create()
                .withId(adId)
                .withTitle(title)
                .withDescription(description)
                .withPublicationDate(new AdPublicationDate(timeService.getDate()))
                .build();

        adCatalog.add(adId, ad);
        adCatalogRepository.save(adCatalog);
        return adId;
    }

    public void removeAd(AdId adId, AdCatalogId adCatalogId) {
        AdCatalog adCatalog = adCatalogRepository.findById(adCatalogId).orElseThrow(AdCatalogDoesNotExistException::new);
        adCatalog.remove(adId);
        adCatalogRepository.save(adCatalog);
    }

    public AdCatalogDTO listAds(AdCatalogId adCatalogId) {
        AdCatalog adCatalog = adCatalogRepository.findById(adCatalogId).orElseThrow(AdCatalogDoesNotExistException::new);
        return adCatalog.listAds();
    }


}
