package adcatalog.domain.models.valueobjects;

import java.util.Objects;
import java.util.UUID;

public class UserId {
    private UUID uuid;

    public UserId(UUID uuid) {
        this.uuid = uuid;
    }

    public UserIdDTO serialize() {
        UserIdDTO userIdDTO =  new UserIdDTO();
        userIdDTO.id = this.uuid;
        return userIdDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserId userId = (UserId) o;

        return Objects.equals(uuid, userId.uuid);
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }
}
