import domain.AdCatalogService;
import domain.catalog.*;
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

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountServiceShould {

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
        AdCatalog adCatalog = new AdCatalog(adCatalogId);
        when(uuidProvider.getUUID()).thenReturn(uuid);

        adCatalogService.create();

        verify(adCatalogRepository).save(adCatalog);
    }

    @Test
    public void not_allow_adding_ads_to_unexisting_catalog() {
        UUID uuid = UUID.randomUUID();
        AdCatalogId adCatalogId = new AdCatalogId(uuid);

        Assertions.assertThrows(AdCatalogDoesNotExistException.class,
                () -> adCatalogService.add("Title", "Description", adCatalogId));
    }

    @Test
    public void add_ad_to_catalog_and_save_it() {
        UUID uuid = UUID.randomUUID();
        AdCatalogId adCatalogId = new AdCatalogId(uuid);
        AdCatalog adCatalog = new AdCatalog(adCatalogId);
        when(adCatalogRepository.findById(adCatalogId)).thenReturn(adCatalog);
        when(uuidProvider.getUUID()).thenReturn(uuid);
        when(timeService.getDate()).thenReturn("11/11/2011");
        AdId adId = new AdId(uuid);
        Ad ad = Ad.create()
                .withId(adId)
                .withTitle("Title")
                .withDescription("Description")
                .withPublicationDate("11/11/2011").build();

        AdId expected = adCatalogService.add("Title", "Description", adCatalogId);

        verify(adCatalogRepository).save(adCatalog);
        Assert.assertEquals(expected, adId);
    }

    @Test
    public void not_allow_removing_ads_from_unexisting_catalog() {
        UUID uuid = UUID.randomUUID();
        AdCatalogId adCatalogId = new AdCatalogId(uuid);
        AdId adId = new AdId(uuid);


        Assertions.assertThrows(AdCatalogDoesNotExistException.class,
                () -> adCatalogService.remove(adId, adCatalogId));
    }

    @Test
    public void remove_ad_from_catalog_and_save_it() {
        UUID uuid = UUID.randomUUID();
        AdCatalogId adCatalogId = new AdCatalogId(uuid);
        AdCatalog adCatalog = new AdCatalog(adCatalogId);
        AdId adId = new AdId(uuid);
        Ad ad = Ad.create()
                .withId(adId)
                .withTitle("Title")
                .withDescription("Description")
                .withPublicationDate("11/11/2011").build();
        adCatalog.add(adId, ad);
        when(adCatalogRepository.findById(adCatalogId)).thenReturn(adCatalog);

        adCatalogService.remove(adId, adCatalogId);

        verify(adCatalogRepository).save(adCatalog);
    }

}
