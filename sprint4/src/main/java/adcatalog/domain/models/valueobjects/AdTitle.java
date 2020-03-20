package adcatalog.domain.models.valueobjects;

import adcatalog.domain.models.dto.AdTitleDTO;

import java.util.Objects;

public class AdTitle {
    private String title;

    public AdTitle(String title) {

        this.title = title;
    }

    public AdTitleDTO serialize() {
        AdTitleDTO adTitleDTO = new AdTitleDTO();
        adTitleDTO.title = this.title;
        return adTitleDTO;
    }
    public boolean isEmpty() {
        return this.title.isEmpty();
    }

    public boolean isLongerThanFiftyCharacters() {
        return this.title.length() > 50;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdTitle adTitle = (AdTitle) o;

        return Objects.equals(title, adTitle.title);
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }


}
