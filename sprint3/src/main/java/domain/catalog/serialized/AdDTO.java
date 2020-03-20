package domain.catalog.serialized;

import java.util.Objects;

public class AdDTO {
    public AdTitleDTO title;
    public AdDescriptionDTO description;
    public AdPublicationDateDTO publicationDate;
    public AdIdDTO id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdDTO adDTO = (AdDTO) o;

        if (!Objects.equals(title, adDTO.title)) return false;
        if (!Objects.equals(description, adDTO.description)) return false;
        return Objects.equals(publicationDate, adDTO.publicationDate);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        return result;
    }
}
