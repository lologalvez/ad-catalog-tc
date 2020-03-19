package domain.catalog;

public interface AdCatalogRepository {

    void save(AdCatalog adCatalog);

    AdCatalog findById(AdCatalogId adCatalogId);

}
