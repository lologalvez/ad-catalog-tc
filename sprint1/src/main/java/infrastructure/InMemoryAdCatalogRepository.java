package infrastructure;

import domain.catalog.AdCatalog;
import domain.catalog.AdCatalogId;
import domain.catalog.AdCatalogRepository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryAdCatalogRepository implements AdCatalogRepository {

    Map<AdCatalogId, AdCatalog> adCatalogs = new LinkedHashMap<>();

    @Override
    public void save(AdCatalog adCatalog) {

    }

    @Override
    public Optional<AdCatalog> findById(AdCatalogId adCatalogId) {
        if (this.adCatalogs.containsKey(adCatalogId)) return Optional.ofNullable(this.adCatalogs.get(adCatalogId));
        return Optional.empty();
    }
}
