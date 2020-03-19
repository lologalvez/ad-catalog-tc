package domain.catalog;

import domain.catalog.Ad;

import java.util.List;
import java.util.Objects;

public class AdCollection {

    private List<Ad> adList;

    public AdCollection(List<Ad> adList) {
        this.adList = adList;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdCollection that = (AdCollection) o;

        return Objects.equals(adList, that.adList);
    }

    @Override
    public int hashCode() {
        return adList != null ? adList.hashCode() : 0;
    }
}

