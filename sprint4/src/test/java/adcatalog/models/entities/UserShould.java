package adcatalog.models.entities;

import adcatalog.domain.models.valueobjects.UserId;
import adcatalog.domain.models.entities.User;
import adcatalog.domain.models.valueobjects.AdId;
import org.junit.Assert;
import org.junit.Test;

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

}
