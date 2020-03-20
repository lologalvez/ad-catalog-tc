package adcatalog.domain.repositories;

import adcatalog.domain.models.entities.User;

public interface UserRepository {
    void save(User user);
}
