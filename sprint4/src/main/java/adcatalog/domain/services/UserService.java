package adcatalog.domain.services;

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

}
