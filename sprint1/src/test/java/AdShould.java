import domain.catalog.Ad;
import domain.exceptions.EmptyDescriptionException;
import domain.exceptions.EmptyTitleException;
import domain.exceptions.SameTitleAndDescriptionException;
import domain.exceptions.TitleLongerThanFiftyCharactersException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class AdShould {

    @Test
    public void not_allow_title_longer_than_50_characters() {
        Assertions.assertThrows(TitleLongerThanFiftyCharactersException.class, () -> Ad.create()
                .withTitle("This is a super long title, way longer than 50 characters")
                .withDescription("Description")
                .withPublicationDate("11/11/2011").build());
    }

    @Test
    public void not_allow_title_and_description_to_be_equal() {
        Assertions.assertThrows(SameTitleAndDescriptionException.class, () -> Ad.create()
                .withTitle("Random text")
                .withDescription("Random text")
                .withPublicationDate("11/11/2011").build());
    }

    @Test
    public void not_allow_being_created_with_an_empty_title() {
        Assertions.assertThrows(EmptyTitleException.class, () -> Ad.create()
                .withTitle("")
                .withDescription("Random text")
                .withPublicationDate("11/11/2011").build());
    }

    @Test
    public void not_allow_being_created_with_an_empty_description() {
        Assertions.assertThrows(EmptyDescriptionException.class, () -> Ad.create()
                .withTitle("Title")
                .withDescription("")
                .withPublicationDate("11/11/2011").build());
    }
}
