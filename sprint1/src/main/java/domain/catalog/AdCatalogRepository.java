package domain.catalog;

import domain.catalog.valueobjects.AdCatalogId;

import java.util.Optional;

public interface AdCatalogRepository {

    void save(AdCatalog adCatalog);

    Optional<AdCatalog> findById(AdCatalogId adCatalogId);
}
