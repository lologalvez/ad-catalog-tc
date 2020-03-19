package domain.catalog;

import java.util.Objects;
import java.util.UUID;

public class AdId {
    private UUID randomUUID;

    public AdId(UUID randomUUID) {
        this.randomUUID = randomUUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdId adId = (AdId) o;

        return Objects.equals(randomUUID, adId.randomUUID);
    }

    @Override
    public int hashCode() {
        return randomUUID != null ? randomUUID.hashCode() : 0;
    }
}
