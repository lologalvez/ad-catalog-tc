package adcatalog.acceptance;

import adcatalog.domain.services.AdCatalogService;
import adcatalog.domain.models.dto.AdCatalogDTO;
import adcatalog.domain.repositories.AdCatalogRepository;
import adcatalog.domain.models.valueobjects.AdCatalogId;
import adcatalog.domain.models.valueobjects.AdDescription;
import adcatalog.domain.models.valueobjects.AdId;
import adcatalog.domain.models.valueobjects.AdTitle;
import adcatalog.domain.services.timeservice.TimeService;
import adcatalog.domain.services.uuidservice.UUIDProvider;
import adcatalog.infrastructure.repositories.InMemoryAdCatalogRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        when(timeService.getDate()).thenReturn(LocalDate.parse("20/03/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        when(uuidProvider.getUUID()).thenReturn(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        AdCatalogId adCatalogId = adCatalogService.createCatalog();
        AdId adIdOne = adCatalogService.addAd(new AdTitle("Acceptance title 1"), new AdDescription("Acceptance Description 1"), adCatalogId);
        AdId adIdTwo = adCatalogService.addAd(new AdTitle("Acceptance title 2"), new AdDescription("Acceptance Description 2"), adCatalogId);
        AdId adIdThree = adCatalogService.addAd(new AdTitle("Acceptance title 3"), new AdDescription("Acceptance Description 3"), adCatalogId);

        adCatalogService.removeAd(adIdTwo, adCatalogId);

        AdCatalogDTO adCatalogDTO = adCatalogService.listAds(adCatalogId);
        String expected = "AdCatalogId: " + adCatalogId.serialize().id + "\n" +
        "Ad: {id: " + adIdOne.serialize().id + ", title: Acceptance title 1, description: Acceptance Description 1, publication_date: 2020-03-20}\n" +
        "Ad: {id: " + adIdThree.serialize().id + ", title: Acceptance title 3, description: Acceptance Description 3, publication_date: 2020-03-20}\n";

        Assert.assertEquals(expected, adCatalogDTO.toString());
    }
}

