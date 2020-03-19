package domain.uuid;

import java.util.UUID;

public class CatalogUUIDProvider implements UUIDProvider {
    @Override
    public UUID getUUID() {
        return UUID.randomUUID();
    }
}
