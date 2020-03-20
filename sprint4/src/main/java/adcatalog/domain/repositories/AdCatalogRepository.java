package adcatalog.domain.repositories;

import adcatalog.domain.models.entities.AdCatalog;
import adcatalog.domain.models.valueobjects.AdCatalogId;

import java.util.Optional;

public interface AdCatalogRepository {

    void save(AdCatalog adCatalog);

    Optional<AdCatalog> findById(AdCatalogId adCatalogId);
}
