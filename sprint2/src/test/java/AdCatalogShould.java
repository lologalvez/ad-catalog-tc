import domain.catalog.*;
import domain.catalog.serialized.AdCatalogDTO;
import domain.catalog.valueobjects.*;
import domain.exceptions.AdAlreadyExistsInTheCatalogException;
import domain.exceptions.AdDoesNotExistInTheCatalog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                .withPublicationDate(new AdPublicationDate(LocalDate.parse("20/03/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))))
                .build();

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
                .withPublicationDate(new AdPublicationDate(LocalDate.parse("20/03/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))))
                .build();

        adCatalog.add(adId, ad);
        AdCatalogDTO adCatalogDTO = new AdCatalogDTO();
        adCatalogDTO.adCatalogId = adCatalogId.serialize();
        adCatalogDTO.ads = new ArrayList<>();
        adCatalogDTO.ads.add(ad.serialize());

        Assert.assertEquals(adCatalogDTO, adCatalog.list());
    }

    @Test
    public void purge_all_ads_older_than_a_given_date() {
        AdCatalogId adCatalogId = new AdCatalogId(UUID.randomUUID());
        AdCatalog adCatalog = new AdCatalog(adCatalogId);
        AdId adIdOne = new AdId(UUID.randomUUID());
        Ad adOne = Ad.create()
                .withId(adIdOne)
                .withTitle(new AdTitle("Title 1"))
                .withDescription(new AdDescription("Description 1"))
                .withPublicationDate(new AdPublicationDate(LocalDate.parse("18/03/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))))
                .build();

        AdId adIdTwo = new AdId(UUID.randomUUID());
        Ad adTwo = Ad.create()
                .withId(adIdTwo)
                .withTitle(new AdTitle("Title 2"))
                .withDescription(new AdDescription("Description 2"))
                .withPublicationDate(new AdPublicationDate(LocalDate.parse("19/03/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))))
                .build();

        AdId adIdThree = new AdId(UUID.randomUUID());
        Ad adThree = Ad.create()
                .withId(adIdThree)
                .withTitle(new AdTitle("Title 3"))
                .withDescription(new AdDescription("Description 3"))
                .withPublicationDate(new AdPublicationDate(LocalDate.parse("20/03/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))))
                .build();

        adCatalog.add(adIdOne, adOne);
        adCatalog.add(adIdTwo, adTwo);
        adCatalog.add(adIdThree, adThree);

        AdCatalog expectedAdCatalog = new AdCatalog(adCatalogId);
        expectedAdCatalog.add(adIdThree, adThree);

        // Act
        adCatalog.purgeAdsTilDate(new AdPublicationDate(LocalDate.parse("20/03/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        Assert.assertEquals(expectedAdCatalog, adCatalog);
    }
}
