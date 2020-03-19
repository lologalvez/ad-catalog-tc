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

}
