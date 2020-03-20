package domain.catalog.valueobjects;

import domain.catalog.serialized.AdCatalogIdDTO;

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

    public AdCatalogIdDTO serialize() {
        AdCatalogIdDTO adCatalogIdDTO = new AdCatalogIdDTO();
        adCatalogIdDTO.id = this.id.toString();
        return adCatalogIdDTO;
    }
}
