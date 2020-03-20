package domain.catalog;

import domain.catalog.serialized.AdDTO;
import domain.catalog.valueobjects.AdDescription;
import domain.catalog.valueobjects.AdId;
import domain.catalog.valueobjects.AdPublicationDate;
import domain.catalog.valueobjects.AdTitle;
import domain.exceptions.EmptyDescriptionException;
import domain.exceptions.EmptyTitleException;
import domain.exceptions.SameTitleAndDescriptionException;
import domain.exceptions.TitleLongerThanFiftyCharactersException;

import java.util.Objects;

public class Ad {

    private final AdTitle title;
    private final AdDescription description;
    private final AdPublicationDate publicationDate;
    private final AdId id;

    private Ad(AdTitle title, AdDescription description, AdPublicationDate publicationDate, AdId id) {
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.id = id;
    }


    public static AdBuilder create() {
        return new AdBuilder();

    }

    public boolean hasSameTitleAndDescription(Ad ad) {
        return this.title.equals(ad.title) && this.description.equals(ad.description);
    }

    public AdDTO serialize() {
        AdDTO adDTO = new AdDTO();
        adDTO.title = this.title.serialize();
        adDTO.description = this.description.serialize();
        adDTO.date = this.publicationDate.serialize();
        adDTO.id = this.id.serialize();
        return adDTO;
    }

    public static class AdBuilder {
        private AdTitle title;
        private AdDescription description;
        private String date;
        private AdId id;

        public AdBuilder withTitle(AdTitle title) {
            if (title.isEmpty()) throw new EmptyTitleException();
            if (title.isLongerThanFiftyCharacters()) throw new TitleLongerThanFiftyCharactersException();
            this.title = title;
            return this;
        }

        public AdBuilder withDescription(AdDescription description) {
            if (description.isEmpty()) throw new EmptyDescriptionException();
            this.description = description;
            return this;
        }

        public AdBuilder withPublicationDate(String date) {
            this.date = date;
            return this;
        }

        public AdBuilder withId(AdId adId) {
            this.id = adId;
            return this;
        }

        public Ad build() {
            String serializedTitle = this.title.serialize().title;
            String serializedDescription = this.description.serialize().description;
            if (serializedTitle.equals(serializedDescription)) throw new SameTitleAndDescriptionException();
            return new Ad(this.title, this.description, new AdPublicationDate(this.date), this.id);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ad ad = (Ad) o;

        if (!Objects.equals(title, ad.title)) return false;
        if (!Objects.equals(description, ad.description)) return false;
        if (!Objects.equals(publicationDate, ad.publicationDate))
            return false;
        return Objects.equals(id, ad.id);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}