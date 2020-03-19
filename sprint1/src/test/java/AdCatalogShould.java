import domain.catalog.AdCollection;
import domain.catalog.Ad;
import domain.catalog.AdCatalog;
import domain.exceptions.AdAlreadyExistsInTheCatalogException;
import domain.exceptions.AdDoesNotExistInTheCatalog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class AdCatalogShould {

    @Test
    public void not_allow_an_ad_in_the_catalog_if_already_exists_an_ad_with_same_title_and_description() {
        AdCatalog adCatalog = new AdCatalog();
        Ad ad =  Ad.create()
                .withTitle("Title")
                .withDescription("Description")
                .withPublicationDate("11/11/2011").build();

        adCatalog.add(ad);

        Assertions.assertThrows(AdAlreadyExistsInTheCatalogException.class, () -> adCatalog.add(ad));
    }

    @Test
    public void not_allow_removing_an_ad_if_it_does_not_exist_in_the_catalog() {
        AdCatalog adCatalog = new AdCatalog();
        Ad ad =  Ad.create()
                .withTitle("Title")
                .withDescription("Description")
                .withPublicationDate("11/11/2011").build();

        Assertions.assertThrows(AdDoesNotExistInTheCatalog.class, () -> adCatalog.remove(ad));
    }

    @Test
    public void return_a_list_containing_all_the_ads_in_the_catalog_when_requested() {
        AdCatalog adCatalog = new AdCatalog();
        Ad ad =  Ad.create()
                .withTitle("Title")
                .withDescription("Description")
                .withPublicationDate("11/11/2011").build();

        List<Ad> adList = new ArrayList<>();
        adList.add(ad);
        AdCollection adCollection = new AdCollection(adList);
        adCatalog.add(ad);

        Assert.assertEquals(adCollection, adCatalog.list());
    }

}
