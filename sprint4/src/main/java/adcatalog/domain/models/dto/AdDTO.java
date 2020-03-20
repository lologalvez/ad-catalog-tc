package adcatalog.domain.models.dto;
import adcatalog.domain.models.valueobjects.UserIdDTO;

import java.util.List;
import java.util.Objects;

public class AdDTO {
    public AdTitleDTO title;
    public AdDescriptionDTO description;
    public AdPublicationDateDTO publicationDate;
    public AdIdDTO id;
    public int visits;
    public List<UserIdDTO> subscribers;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdDTO adDTO = (AdDTO) o;

        if (visits != adDTO.visits) return false;
        if (!Objects.equals(title, adDTO.title)) return false;
        if (!Objects.equals(description, adDTO.description)) return false;
        if (!Objects.equals(publicationDate, adDTO.publicationDate))
            return false;
        if (!Objects.equals(id, adDTO.id)) return false;
        return Objects.equals(subscribers, adDTO.subscribers);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + visits;
        result = 31 * result + (subscribers != null ? subscribers.hashCode() : 0);
        return result;
    }
}


