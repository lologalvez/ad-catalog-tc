package acceptance;

import domain.AdCatalogService;
import domain.catalog.*;
import domain.catalog.serialized.AdCatalogDTO;
import domain.domainservices.timeservice.TimeService;
import domain.domainservices.uuidservice.UUIDProvider;
import infrastructure.InMemoryAdCatalogRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.UUID;

import static org.mockito.Mockito.when;

public class AdCatalogListAdsFeature {

    @Mock TimeService timeService;
    @Mock UUIDProvider uuidProvider;
    private AdCatalogRepository adCatalogRepository;
    private AdCatalogService adCatalogService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        adCatalogRepository = new InMemoryAdCatalogRepository();
        adCatalogService = new AdCatalogService(adCatalogRepository, timeService, uuidProvider);
    }

    @Test
    public void list_all_ads_from_a_given_catalog() {
        when(timeService.getDate()).thenReturn("11/11/2011", "12/11/2011", "13/11/2011");
        when(uuidProvider.getUUID()).thenReturn(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        AdCatalogId adCatalogId = adCatalogService.createCatalog();
        AdId adIdOne = adCatalogService.addAd("Acceptance title 1", "Acceptance Description 1", adCatalogId);
        AdId adIdTwo = adCatalogService.addAd("Acceptance title 2", "Acceptance Description 2", adCatalogId);
        AdId adIdThree = adCatalogService.addAd("Acceptance title 3", "Acceptance Description 3", adCatalogId);

        adCatalogService.removeAd(adIdTwo, adCatalogId);

        AdCatalogDTO adCatalogDTO = adCatalogService.listAds(adCatalogId);
        String expected = "AdCatalogId: " + adCatalogId.serialize().id + "\n" +
        "Ad: {id: " + adIdOne.serialize().id + ", title: Acceptance title 1, description: Acceptance Description 1, publication_date: 11/11/2011}\n" +
        "Ad: {id: " + adIdThree.serialize().id + ", title: Acceptance title 3, description: Acceptance Description 3, publication_date: 13/11/2011}\n";

        Assert.assertEquals(expected, adCatalogDTO.toString());
    }
}

