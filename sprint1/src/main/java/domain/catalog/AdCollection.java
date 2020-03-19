package domain.catalog;
import java.util.Map;
import java.util.Objects;

public class AdCollection {

    private final Map<AdId, Ad> adMap;

    public AdCollection(Map<AdId, Ad> adMap) {
        this.adMap = adMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdCollection that = (AdCollection) o;

        return Objects.equals(adMap, that.adMap);
    }

    @Override
    public int hashCode() {
        return adMap != null ? adMap.hashCode() : 0;
    }
}

