import domain.AdCatalogService;
import domain.catalog.*;
import domain.catalog.serialized.AdCatalogDTO;
import domain.catalog.valueobjects.*;
import domain.exceptions.AdCatalogDoesNotExistException;
import domain.domainservices.timeservice.TimeService;
import domain.domainservices.uuidservice.UUIDProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AdCatalogServiceShould {

    @Mock AdCatalogRepository adCatalogRepository;
    @Mock TimeService timeService;
    @Mock UUIDProvider uuidProvider;

    @InjectMocks
    AdCatalogService adCatalogService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create_and_save_a_new_ad_catalog() {
        UUID uuid = UUID.randomUUID();
        AdCatalogId adCatalogId = new AdCatalogId(uuid);
        AdCatalog adCatalog = AdCatalog.create()
                .withId(adCatalogId)
                .withAdStorageLimit(100)
                .withExpirationStrategy(ExpirationStrategy.OLDEST)
                .build();

        when(uuidProvider.getUUID()).thenReturn(uuid);

        adCatalogService.createCatalog();

        verify(adCatalogRepository).save(adCatalog);
    }

    @Test
    public void not_allow_adding_ads_to_unexisting_catalog() {
        UUID uuid = UUID.randomUUID();
        AdCatalogId adCatalogId = new AdCatalogId(uuid);

        Assertions.assertThrows(AdCatalogDoesNotExistException.class,
                () -> adCatalogService.addAd(new AdTitle("Title"), new AdDescription("Description"), adCatalogId));
    }

    @Test
    public void add_ad_to_catalog_and_save_it() {
        UUID uuid = UUID.randomUUID();
        AdCatalogId adCatalogId = new AdCatalogId(uuid);
        AdCatalog adCatalog = AdCatalog.create()
                .withId(adCatalogId)
                .withAdStorageLimit(100)
                .withExpirationStrategy(ExpirationStrategy.OLDEST)
                .build();

        when(adCatalogRepository.findById(adCatalogId)).thenReturn(Optional.ofNullable(adCatalog));
        when(uuidProvider.getUUID()).thenReturn(uuid);
        when(timeService.getDate()).thenReturn(LocalDate.parse("20/03/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        AdId adId = new AdId(uuid);
        Ad ad = Ad.create()
                .withId(adId)
                .withTitle(new AdTitle("Title"))
                .withDescription(new AdDescription("Description"))
                .withPublicationDate(new AdPublicationDate(LocalDate.parse("20/03/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))))
                .build();

        AdId expected = adCatalogService.addAd(new AdTitle("Title"), new AdDescription("Description"), adCatalogId);

        verify(adCatalogRepository).save(adCatalog);
        Assert.assertEquals(expected, adId);
    }

    @Test
    public void not_allow_removing_ads_from_unexisting_catalog() {
        UUID uuid = UUID.randomUUID();
        AdCatalogId adCatalogId = new AdCatalogId(uuid);
        AdId adId = new AdId(uuid);


        Assertions.assertThrows(AdCatalogDoesNotExistException.class,
                () -> adCatalogService.removeAd(adId, adCatalogId));
    }

    @Test
    public void remove_ad_from_catalog_and_save_it() {
        UUID uuid = UUID.randomUUID();
        AdCatalogId adCatalogId = new AdCatalogId(uuid);
        AdCatalog adCatalog =AdCatalog.create()
                .withId(adCatalogId)
                .withAdStorageLimit(100)
                .withExpirationStrategy(ExpirationStrategy.OLDEST)
                .build();

        AdId adId = new AdId(uuid);
        Ad ad = Ad.create()
                .withId(adId)
                .withTitle(new AdTitle("Title"))
                .withDescription(new AdDescription("Description"))
                .withPublicationDate(new AdPublicationDate(LocalDate.parse("20/03/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))))
                .build();
        adCatalog.add(adId, ad);
        when(adCatalogRepository.findById(adCatalogId)).thenReturn(Optional.ofNullable(adCatalog));

        adCatalogService.removeAd(adId, adCatalogId);

        verify(adCatalogRepository).save(adCatalog);
    }

    @Test
    public void not_list_any_ad_when_catalog_does_not_exist() {
        UUID uuid = UUID.randomUUID();
        AdCatalogId adCatalogId = new AdCatalogId(uuid);

        Assertions.assertThrows(AdCatalogDoesNotExistException.class,
                () -> adCatalogService.listAds(adCatalogId));
    }

    @Test
    public void list_all_ads_from_a_given_catalog() {
        UUID uuid = UUID.randomUUID();
        AdCatalogId adCatalogId = new AdCatalogId(uuid);
        AdCatalog adCatalog = AdCatalog.create()
                .withId(adCatalogId)
                .withAdStorageLimit(100)
                .withExpirationStrategy(ExpirationStrategy.OLDEST)
                .build();

        AdId adId = new AdId(uuid);
        Ad ad = Ad.create()
                .withId(adId)
                .withTitle(new AdTitle("Title"))
                .withDescription(new AdDescription("Description"))
                .withPublicationDate(new AdPublicationDate(LocalDate.parse("20/03/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))))
                .build();
        adCatalog.add(adId, ad);
        AdCatalogDTO expectedAdListing = adCatalog.listAds();
        when(adCatalogRepository.findById(adCatalogId)).thenReturn(Optional.ofNullable(adCatalog));

        AdCatalogDTO adListing = adCatalogService.listAds(adCatalogId);

        Assert.assertEquals(expectedAdListing, adListing);
    }

}
