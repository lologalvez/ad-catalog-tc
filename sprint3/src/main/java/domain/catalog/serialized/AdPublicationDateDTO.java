package domain.catalog.serialized;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class AdPublicationDateDTO {
    public LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdPublicationDateDTO that = (AdPublicationDateDTO) o;

        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return date != null ? date.hashCode() : 0;
    }
}
