import domain.AdCatalogService;
import domain.catalog.AdCatalog;
import domain.catalog.AdCatalogRepository;
import domain.catalog.AdCatalogId;
import domain.timeservice.TimeService;
import domain.uuid.UUIDProvider;
import org.junit.Before;
import org.junit.Test;
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
    public void create_and_save_a_new_ad_catalogue() {
        UUID uuid = UUID.randomUUID();
        AdCatalogId adCatalogId = new AdCatalogId(uuid);
        AdCatalog adCatalog = new AdCatalog(adCatalogId);
        when(uuidProvider.getUUID()).thenReturn(uuid);

        adCatalogService.create();

        verify(adCatalogRepository).save(adCatalog);
    }




}
