package pl.bartek.reciepes.repository.recipe;

import org.springframework.data.repository.CrudRepository;
import pl.bartek.reciepes.model.RecipeModel;

public interface RecipeRepository extends CrudRepository<RecipeModel, Long> {
}
