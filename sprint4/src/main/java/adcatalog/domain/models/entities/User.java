package adcatalog.domain.models.entities;

import adcatalog.domain.models.exceptions.AdAlreadyFavorited;
import adcatalog.domain.models.valueobjects.AdId;
import adcatalog.domain.models.valueobjects.UserId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private UserId userId;
    private List<AdId> favs = new ArrayList<>();

    public User(UserId userId) {

        this.userId = userId;
    }

    public void favAd(AdId adId) {
        if (this.favs.contains(adId)) throw new AdAlreadyFavorited();
        this.favs.add(adId);
    }

    public void unFavAd(AdId adId) {
        if (this.favs.contains(adId)) this.favs.remove(adId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!Objects.equals(userId, user.userId)) return false;
        return Objects.equals(favs, user.favs);
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (favs != null ? favs.hashCode() : 0);
        return result;
    }
}
