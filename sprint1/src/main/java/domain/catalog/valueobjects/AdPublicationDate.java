package domain.catalog.valueobjects;

import domain.catalog.Ad;
import domain.catalog.serialized.AdPublicationDateDTO;

import java.util.Objects;

public class AdPublicationDate {
    private String date;

    public AdPublicationDate(String date) {
        this.date = date;
    }

    public AdPublicationDateDTO serialize() {
        AdPublicationDateDTO adPublicationDateDTO = new AdPublicationDateDTO();
        adPublicationDateDTO.date = this.date;
        return adPublicationDateDTO;
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
