package adcatalog.domain.services;

import adcatalog.domain.models.entities.Ad;
import adcatalog.domain.models.entities.AdCatalog;
import adcatalog.domain.models.entities.User;
import adcatalog.domain.models.exceptions.AdCatalogDoesNotExistException;
import adcatalog.domain.models.exceptions.UserDoesNotExistException;
import adcatalog.domain.models.valueobjects.AdCatalogId;
import adcatalog.domain.models.valueobjects.AdId;
import adcatalog.domain.models.valueobjects.UserId;
import adcatalog.domain.repositories.AdCatalogRepository;
import adcatalog.domain.repositories.UserRepository;
import adcatalog.domain.services.uuidservice.UUIDProvider;

public class UserService {

    private final AdCatalogRepository adCatalogRepository;
    private final UserRepository userRepository;
    private final UUIDProvider uuidProvider;

    public UserService(AdCatalogRepository adCatalogRepository, UserRepository userRepository, UUIDProvider uuidProvider) {
        this.adCatalogRepository = adCatalogRepository;
        this.userRepository = userRepository;
        this.uuidProvider = uuidProvider;
    }

    public UserId createUser() {
        UserId userId = new UserId(uuidProvider.getUUID());
        User user = new User(userId);
        userRepository.save(user);
        return userId;
    }

    public void addAdAsFavorite(UserId userId, AdId adId, AdCatalogId adCatalogId) {
        AdCatalog adCatalog = adCatalogRepository.findById(adCatalogId).orElseThrow(AdCatalogDoesNotExistException::new);
        User user = userRepository.findById(userId).orElseThrow(UserDoesNotExistException::new);
        Ad ad = adCatalog.findAdById(adId);
        ad.subscribe(userId);
        user.favAd(adId);
        userRepository.save(user);
        adCatalogRepository.save(adCatalog);
        System.out.println(user.toString());
        System.out.println(ad.serialize().toString());
    }

    public void removeAdFromFavorites(UserId userId, AdId adId, AdCatalogId adCatalogId) {
        AdCatalog adCatalog = adCatalogRepository.findById(adCatalogId).orElseThrow(AdCatalogDoesNotExistException::new);
        User user = userRepository.findById(userId).orElseThrow(UserDoesNotExistException::new);
        Ad ad = adCatalog.findAdById(adId);
        ad.unsubscribe(userId);
        user.unFavAd(adId);
        userRepository.save(user);
        adCatalogRepository.save(adCatalog);
    }
}
