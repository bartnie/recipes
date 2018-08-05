package pl.bartek.reciepes.service.recipe;

import pl.bartek.reciepes.model.RecipeModel;

import java.util.Optional;
import java.util.Set;

public interface RecipeService {
    Set<RecipeModel> findAll();
    Optional<RecipeModel> findById(Long id);
}
