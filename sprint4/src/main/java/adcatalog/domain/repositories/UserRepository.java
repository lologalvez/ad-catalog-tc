package adcatalog.domain.repositories;

import adcatalog.domain.models.entities.User;
import adcatalog.domain.models.valueobjects.UserId;

import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> findById(UserId userId);
}
