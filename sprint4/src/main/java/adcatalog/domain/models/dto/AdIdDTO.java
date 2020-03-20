package adcatalog.domain.models.dto;

import java.util.Objects;

public class AdIdDTO {
    public String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdIdDTO adIdDTO = (AdIdDTO) o;

        return Objects.equals(id, adIdDTO.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
