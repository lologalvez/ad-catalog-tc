package domain.catalog;

import java.util.Objects;
import java.util.UUID;

public class AdCatalogId {
    private UUID id;

    public AdCatalogId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdCatalogId that = (AdCatalogId) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
