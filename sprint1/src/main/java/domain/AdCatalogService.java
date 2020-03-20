package domain;

import domain.catalog.*;
import domain.catalog.serialized.AdCatalogDTO;
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
        AdCatalog adCatalog = new AdCatalog(adCatalogId);
        adCatalogRepository.save(adCatalog);
        return adCatalogId;
    }

    public AdId addAd(String title, String description, AdCatalogId adCatalogId) {
       AdCatalog adCatalog = adCatalogRepository.findById(adCatalogId).orElseThrow(AdCatalogDoesNotExistException::new);

        AdId adId = new AdId(uuidProvider.getUUID());
        Ad ad = Ad.create()
                .withId(adId)
                .withTitle(title)
                .withDescription(description)
                .withPublicationDate(timeService.getDate()).build();

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
        return adCatalog.list();
    }
}
