package adcatalog.domain.models.entities;

import adcatalog.domain.models.dto.AdDTO;
import adcatalog.domain.models.valueobjects.*;
import adcatalog.domain.models.exceptions.EmptyDescriptionException;
import adcatalog.domain.models.exceptions.EmptyTitleException;
import adcatalog.domain.models.exceptions.SameTitleAndDescriptionException;
import adcatalog.domain.models.exceptions.TitleLongerThanFiftyCharactersException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ad {

    private final AdTitle title;
    private final AdDescription description;
    private final AdPublicationDate publicationDate;
    private final AdId id;
    private int visits;
    private List<UserId> subscribers;

    private Ad(AdTitle title, AdDescription description, AdPublicationDate publicationDate, AdId id) {
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.id = id;
        this.visits = 0;
        this.subscribers = new ArrayList<>();
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
        adDTO.publicationDate = this.publicationDate.serialize();
        adDTO.id = this.id.serialize();
        adDTO.visits = this.visits;
        adDTO.subscribers = new ArrayList<>();
        for (UserId userId : this.subscribers) {
            adDTO.subscribers.add(userId.serialize());
        }
        return adDTO;
    }

    public boolean isOlderThan(AdPublicationDate limitDate) {
        return this.publicationDate.isBefore(limitDate);
    }

    public void incrementVisits() {
        this.visits += 1;
    }

    public void subscribe(UserId userId) {
        this.subscribers.add(userId);
    }

    public void unsubscribe(UserId userId) {
        this.subscribers.remove(userId);
    }

    public static class AdBuilder {
        private AdTitle title;
        private AdDescription description;
        private AdPublicationDate date;
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

        public AdBuilder withPublicationDate(AdPublicationDate date) {
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
            return new Ad(this.title, this.description, this.date, this.id);
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