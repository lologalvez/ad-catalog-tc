package domain.catalog.valueobjects;

import domain.catalog.serialized.AdPublicationDateDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Objects;

public class AdPublicationDate {
    private LocalDate date;

    public AdPublicationDate(LocalDate date) {
        this.date = date;
    }

    public AdPublicationDateDTO serialize() {
        AdPublicationDateDTO adPublicationDateDTO = new AdPublicationDateDTO();
        adPublicationDateDTO.date = this.date;
        return adPublicationDateDTO;
    }

    public boolean isBefore(AdPublicationDate incomingDate) {
        return this.date.isBefore(incomingDate.date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdPublicationDate that = (AdPublicationDate) o;

        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return date != null ? date.hashCode() : 0;
    }



}
