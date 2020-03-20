import domain.catalog.*;
import domain.catalog.serialized.AdCatalogDTO;
import domain.catalog.valueobjects.AdCatalogId;
import domain.catalog.valueobjects.AdDescription;
import domain.catalog.valueobjects.AdId;
import domain.catalog.valueobjects.AdTitle;
import domain.exceptions.AdAlreadyExistsInTheCatalogException;
import domain.exceptions.AdDoesNotExistInTheCatalog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class AdCatalogShould {

    @Test
    public void not_allow_an_ad_in_the_catalog_if_already_exists_an_ad_with_same_title_and_description() {
        AdCatalogId adCatalogId = new AdCatalogId(UUID.randomUUID());
        AdCatalog adCatalog = new AdCatalog(adCatalogId);
        AdId adId = new AdId(UUID.randomUUID());
        Ad ad =  Ad.create()
                .withId(adId)
                .withTitle(new AdTitle("Title"))
                .withDescription(new AdDescription("Description"))
                .withPublicationDate("11/11/2011").build();

        adCatalog.add(adId, ad);

        Assertions.assertThrows(AdAlreadyExistsInTheCatalogException.class, () -> adCatalog.add(adId, ad));
    }

    @Test
    public void not_allow_removing_an_ad_if_it_does_not_exist_in_the_catalog() {
        AdCatalogId adCatalogId = new AdCatalogId(UUID.randomUUID());
        AdCatalog adCatalog = new AdCatalog(adCatalogId);
        AdId adId = new AdId(UUID.randomUUID());

        Assertions.assertThrows(AdDoesNotExistInTheCatalog.class, () -> adCatalog.remove(adId));
    }

    @Test
    public void return_a_list_containing_all_the_ads_in_the_catalog_when_requested() {
        AdCatalogId adCatalogId = new AdCatalogId(UUID.randomUUID());
        AdCatalog adCatalog = new AdCatalog(adCatalogId);
        AdId adId = new AdId(UUID.randomUUID());
        Ad ad =  Ad.create()
                .withId(adId)
                .withTitle(new AdTitle("Title"))
                .withDescription(new AdDescription("Description"))
                .withPublicationDate("11/11/2011").build();

        adCatalog.add(adId, ad);
        AdCatalogDTO adCatalogDTO = new AdCatalogDTO();
        adCatalogDTO.adCatalogId = adCatalogId.serialize();
        adCatalogDTO.ads = new ArrayList<>();
        adCatalogDTO.ads.add(ad.serialize());

        Assert.assertEquals(adCatalogDTO, adCatalog.list());
    }

}
