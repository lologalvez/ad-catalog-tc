package domain;

import domain.catalog.*;
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

    public AdCatalogId create() {
        AdCatalogId adCatalogId = new AdCatalogId(uuidProvider.getUUID());
        AdCatalog adCatalog = new AdCatalog(adCatalogId);
        adCatalogRepository.save(adCatalog);
        return adCatalogId;
    }

    public AdId add(String title, String description, AdCatalogId adCatalogId) {
        AdCatalog adCatalog = adCatalogRepository.findById(adCatalogId);
        if (adCatalog == null) throw new AdCatalogDoesNotExistException();

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

    public void remove(AdId adId, AdCatalogId adCatalogId) {
        AdCatalog adCatalog = adCatalogRepository.findById(adCatalogId);
        if (adCatalog == null) throw new AdCatalogDoesNotExistException();
        adCatalog.remove(adId);
        adCatalogRepository.save(adCatalog);

    }



}
