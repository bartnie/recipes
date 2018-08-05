package pl.bartek.reciepes.repository.category;

import org.springframework.data.repository.CrudRepository;
import pl.bartek.reciepes.model.CategoryModel;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryModel, Long> {
    Optional<CategoryModel> findByDescription(final String description);
}
