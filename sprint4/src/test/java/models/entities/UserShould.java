package models.entities;

import adcatalog.domain.models.exceptions.AdIsNotInUsersFavsException;
import adcatalog.domain.models.valueobjects.UserId;
import adcatalog.domain.models.entities.User;
import adcatalog.domain.models.valueobjects.AdId;
import adcatalog.domain.models.exceptions.AdAlreadyFavorited;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

public class UserShould {

    @Test
    public void add_and_remove_ad_as_favorites() {
        AdId adIdOne = new AdId(UUID.randomUUID());
        AdId adIdTwo = new AdId(UUID.randomUUID());
        UserId userId = new UserId(UUID.randomUUID());
        User userOne = new User(userId);
        User userTwo = new User(userId);

        userOne.favAd(adIdOne);
        userOne.favAd(adIdTwo);
        userOne.unFavAd(adIdOne);
        userTwo.favAd(adIdTwo);

        Assert.assertEquals(userOne, userTwo);

    }

    @Test
    public void not_allow_faving_an_ad_already_favorited() {
        AdId adId = new AdId(UUID.randomUUID());
        UserId userId = new UserId(UUID.randomUUID());
        User user = new User(userId);
        user.favAd(adId);

        Assertions.assertThrows(AdAlreadyFavorited.class, () -> user.favAd(adId));
    }

    @Test
    public void not_allow_unfaving_an_ad_that_is_not_in_users_favs() {
        AdId adId = new AdId(UUID.randomUUID());
        UserId userId = new UserId(UUID.randomUUID());
        User user = new User(userId);

        Assertions.assertThrows(AdIsNotInUsersFavsException.class, () -> user.unFavAd(adId));

    }

}
