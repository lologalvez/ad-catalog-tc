package adcatalog.infrastructure.repositories;

import adcatalog.domain.models.entities.AdCatalog;
import adcatalog.domain.models.valueobjects.AdCatalogId;
import adcatalog.domain.repositories.AdCatalogRepository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryAdCatalogRepository implements AdCatalogRepository {

    Map<AdCatalogId, AdCatalog> adCatalogs = new LinkedHashMap<>();

    @Override
    public void save(AdCatalog adCatalog) {
        this.adCatalogs.put(adCatalog.getCatalogId(), adCatalog);
    }

    @Override
    public Optional<AdCatalog> findById(AdCatalogId adCatalogId) {
        if (this.adCatalogs.containsKey(adCatalogId)) return Optional.ofNullable(this.adCatalogs.get(adCatalogId));
        return Optional.empty();
    }
}
