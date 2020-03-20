package adcatalog.domain.models.dto;
import java.util.List;
import java.util.Objects;

public class AdCatalogDTO {
    public AdCatalogIdDTO adCatalogId;
    public List<AdDTO> ads;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdCatalogDTO that = (AdCatalogDTO) o;

        if (!Objects.equals(adCatalogId, that.adCatalogId)) return false;
        return Objects.equals(ads, that.ads);
    }

    @Override
    public int hashCode() {
        int result = adCatalogId != null ? adCatalogId.hashCode() : 0;
        result = 31 * result + (ads != null ? ads.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("AdCatalogId: " + adCatalogId.id + "\n");
        for (AdDTO adDTO : ads) {
            output.append("Ad: {id: " + adDTO.id.id + ", title: " + adDTO.title.title + ", description: " + adDTO.description.description + ", publication_date: " + adDTO.publicationDate.date + "}\n");
        }
        return output.toString();
    }
}
