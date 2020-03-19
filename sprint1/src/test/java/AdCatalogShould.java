import domain.catalog.Ad;
import domain.catalog.AdCatalog;
import domain.exceptions.AdAlreadyExistsInTheCatalogException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

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

}
