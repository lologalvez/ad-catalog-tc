package domain.catalog;

import domain.exceptions.EmptyDescriptionException;
import domain.exceptions.EmptyTitleException;
import domain.exceptions.SameTitleAndDescriptionException;
import domain.exceptions.TitleLongerThanFiftyCharactersException;
import java.util.Objects;

public class Ad {

    private final String title;
    private final String description;
    private final String date;
    private final AdId id;

    private Ad(String title, String description, String date, AdId adId) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.id = adId;
    }


    public static AdBuilder create() {
        return new AdBuilder();

    }

    public static class AdBuilder {
        private String title;
        private String description;
        private String date;
        private AdId id;

        public AdBuilder withTitle(String title) {
            if (title.isEmpty()) throw new EmptyTitleException();
            if (title.length() > 50) throw new TitleLongerThanFiftyCharactersException();
            this.title = title;
            return this;
        }

        public AdBuilder withDescription(String description) {
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
            if (this.title.equals(this.description)) throw new SameTitleAndDescriptionException();
            return new Ad(this.title, this.description, this.date, this.id);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ad ad = (Ad) o;

        if (title != null ? !title.equals(ad.title) : ad.title != null) return false;
        if (description != null ? !description.equals(ad.description) : ad.description != null) return false;
        if (date != null ? !date.equals(ad.date) : ad.date != null) return false;
        return id != null ? id.equals(ad.id) : ad.id == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}