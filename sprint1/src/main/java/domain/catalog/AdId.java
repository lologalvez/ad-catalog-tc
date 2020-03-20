package domain.catalog;

import domain.catalog.serialized.AdIdDTO;

import java.util.Objects;
import java.util.UUID;

public class AdId {
    private UUID id;

    public AdId(UUID id) {
        this.id = id;
    }

    public AdIdDTO serialize() {
        AdIdDTO adIdDTO = new AdIdDTO();
        adIdDTO.id = this.id.toString();
        return adIdDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdId adId = (AdId) o;

        return Objects.equals(id, adId.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
