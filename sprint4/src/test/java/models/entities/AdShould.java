package models.entities;

import adcatalog.domain.models.entities.Ad;
import adcatalog.domain.models.valueobjects.AdDescription;
import adcatalog.domain.models.valueobjects.AdPublicationDate;
import adcatalog.domain.models.valueobjects.AdTitle;
import adcatalog.domain.models.exceptions.EmptyDescriptionException;
import adcatalog.domain.models.exceptions.EmptyTitleException;
import adcatalog.domain.models.exceptions.SameTitleAndDescriptionException;
import adcatalog.domain.models.exceptions.TitleLongerThanFiftyCharactersException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AdShould {

    @Test
    public void not_allow_title_longer_than_50_characters() {
        Assertions.assertThrows(TitleLongerThanFiftyCharactersException.class, () -> Ad.create()
                .withTitle(new AdTitle("This is a super long title, way longer than 50 characters"))
                .withDescription(new AdDescription("Description"))
                .withPublicationDate(new AdPublicationDate(LocalDate.parse("20/03/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))))
                .build());
    }

    @Test
    public void not_allow_title_and_description_to_be_equal() {
        Assertions.assertThrows(SameTitleAndDescriptionException.class, () -> Ad.create()
                .withTitle(new AdTitle("Random text"))
                .withDescription(new AdDescription("Random text"))
                .withPublicationDate(new AdPublicationDate(LocalDate.parse("20/03/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))))
                .build());
    }

    @Test
    public void not_allow_being_created_with_an_empty_title() {
        Assertions.assertThrows(EmptyTitleException.class, () -> Ad.create()
                .withTitle(new AdTitle(""))
                .withDescription(new AdDescription("Random text"))
                .withPublicationDate(new AdPublicationDate(LocalDate.parse("20/03/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))))
                .build());
    }

    @Test
    public void not_allow_being_created_with_an_empty_description() {
        Assertions.assertThrows(EmptyDescriptionException.class, () -> Ad.create()
                .withTitle(new AdTitle("Title"))
                .withDescription(new AdDescription(""))
                .withPublicationDate(new AdPublicationDate(LocalDate.parse("20/03/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))))
                .build());
    }
}
