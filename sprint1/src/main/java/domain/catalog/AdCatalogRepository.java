package domain.catalog;

import java.util.Optional;

public interface AdCatalogRepository {

    void save(AdCatalog adCatalog);

    Optional<AdCatalog> findById(AdCatalogId adCatalogId);

}
