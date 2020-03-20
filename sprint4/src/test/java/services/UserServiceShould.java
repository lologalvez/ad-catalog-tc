package services;

import adcatalog.domain.models.dto.ExpirationStrategy;
import adcatalog.domain.models.entities.Ad;
import adcatalog.domain.models.entities.AdCatalog;
import adcatalog.domain.models.entities.User;
import adcatalog.domain.models.valueobjects.*;
import adcatalog.domain.repositories.AdCatalogRepository;
import adcatalog.domain.repositories.UserRepository;
import adcatalog.domain.services.UserService;
import adcatalog.domain.services.uuidservice.UUIDProvider;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceShould {

    @Mock
    AdCatalogRepository adCatalogRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    UUIDProvider uuidProvider;

    @InjectMocks
    UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create_user_and_save_it_to_repository() {
        UUID uuid = UUID.randomUUID();
        UserId userId = new UserId(uuid);
        User user = new User(userId);
        when(uuidProvider.getUUID()).thenReturn(uuid);

        userService.createUser();

        verify(userRepository).save(user);
    }

    @Test
    public void add_favorite_ad_to_user_and_save_it_to_repository() {
        AdCatalogId adCatalogId = new AdCatalogId(UUID.randomUUID());
        AdCatalog adCatalog = AdCatalog.create()
                .withId(adCatalogId)
                .withAdStorageLimit(100)
                .withExpirationStrategy(ExpirationStrategy.OLDEST)
                .build();
        AdId adId = new AdId(UUID.randomUUID());
        Ad ad =  Ad.create()
                .withId(adId)
                .withTitle(new AdTitle("Title"))
                .withDescription(new AdDescription("Description"))
                .withPublicationDate(new AdPublicationDate(LocalDate.parse("20/03/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))))
                .build();
        adCatalog.add(adId, ad);
        UserId userId = new UserId(UUID.randomUUID());
        User user = new User(userId);
        when(adCatalogRepository.findById(adCatalogId)).thenReturn(Optional.ofNullable(adCatalog));
        when(userRepository.findById(userId)).thenReturn(Optional.ofNullable(user));

        userService.addAdAsFavorite(userId, adId, adCatalogId);

        verify(userRepository).save(user);
        verify(adCatalogRepository).save(adCatalog);
    }

    @Test
    public void remove_favorite_ad_to_user_and_save_updated_user_and_ad_to_repository() {
        AdCatalogId adCatalogId = new AdCatalogId(UUID.randomUUID());
        AdCatalog adCatalog = AdCatalog.create()
                .withId(adCatalogId)
                .withAdStorageLimit(100)
                .withExpirationStrategy(ExpirationStrategy.OLDEST)
                .build();
        AdId adId = new AdId(UUID.randomUUID());
        Ad ad =  Ad.create()
                .withId(adId)
                .withTitle(new AdTitle("Title"))
                .withDescription(new AdDescription("Description"))
                .withPublicationDate(new AdPublicationDate(LocalDate.parse("20/03/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))))
                .build();
        adCatalog.add(adId, ad);
        UserId userId = new UserId(UUID.randomUUID());
        User user = new User(userId);
        user.favAd(adId);
        ad.subscribe(userId);
        when(adCatalogRepository.findById(adCatalogId)).thenReturn(Optional.ofNullable(adCatalog));
        when(userRepository.findById(userId)).thenReturn(Optional.ofNullable(user));

        userService.removeAdFromFavorites(userId, adId, adCatalogId);

        verify(userRepository).save(user);
        verify(adCatalogRepository).save(adCatalog);
    }
}
