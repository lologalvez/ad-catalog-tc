package adcatalog.infrastructure.repositories;

import adcatalog.domain.models.entities.AdCatalog;
import adcatalog.domain.models.entities.User;
import adcatalog.domain.models.valueobjects.AdCatalogId;
import adcatalog.domain.models.valueobjects.UserId;
import adcatalog.domain.repositories.UserRepository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {

    Map<UserId, User> users = new LinkedHashMap<>();

    @Override
    public void save(User user) {
        this.users.put(user.getUserId(), user);
    }

    @Override
    public Optional<User> findById(UserId userId) {
        if (this.users.containsKey(users)) return Optional.ofNullable(this.users.get(userId));
        return Optional.empty();
    }
}
