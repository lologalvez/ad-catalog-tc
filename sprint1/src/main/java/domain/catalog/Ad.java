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

    private Ad(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }


    public static AdBuilder create() {
        return new AdBuilder();

    }

    public static class AdBuilder {
        private String title;
        private String description;
        private String date;

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

        public Ad build() {
            if (this.title.equals(this.description)) throw new SameTitleAndDescriptionException();
            return new Ad(this.title, this.description, this.date);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ad ad = (Ad) o;

        if (!Objects.equals(title, ad.title)) return false;
        if (!Objects.equals(description, ad.description)) return false;
        return Objects.equals(date, ad.date);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}