package domain.domainservices.uuidservice;

import java.util.UUID;

public class CatalogUUIDProvider implements UUIDProvider {
    @Override
    public UUID getUUID() {
        return UUID.randomUUID();
    }
}
