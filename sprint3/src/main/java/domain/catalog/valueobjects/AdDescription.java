package domain.catalog.valueobjects;

import domain.catalog.serialized.AdDTO;
import domain.catalog.serialized.AdDescriptionDTO;
import domain.catalog.serialized.AdIdDTO;

import java.util.Objects;

public class AdDescription {
    private String description;

    public AdDescription(String description) {
        this.description = description;
    }

    public AdDescriptionDTO serialize() {
        AdDescriptionDTO adDescriptionDTO = new AdDescriptionDTO();
        adDescriptionDTO.description = this.description;
        return adDescriptionDTO;
    }

    public boolean isEmpty() {
        return this.description.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdDescription that = (AdDescription) o;

        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return description != null ? description.hashCode() : 0;
    }


}
