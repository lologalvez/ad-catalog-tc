## Ad Catalog Management App

The application has been developed incrementally in four sprints. In each sprint a new folder was created on its own branch and aftwerwards merged to master at the end of the sprint. 

Every sprint was the starting point for the next. In each sprint not only new features were added, but also changes in regards o software design and directory structure.

I decided to consider the Ad Catalog the aggregate of this application. I tried to apply a Unit Of Work pattern for this entity and its repository. Persistence its done on every change to the entity, but can be approach in different ways if performance is an issue.

I also tried to design the application following the principles of Hexagonal Architecture.

Feedback is more than welcome.

#### ToDos:
- Apply Observer pattern to the fav usecase (almost hinted in the sprint4 version)
- Split usecases in AdCatalogService into its own classes
- Rethink the role of User with respect to the aggregate AdCatalog
- Enhance scalability and performance by choosing best data structures for each use case.
- ...
