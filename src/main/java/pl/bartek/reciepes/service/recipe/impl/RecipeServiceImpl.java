package pl.bartek.reciepes.service.recipe.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartek.reciepes.model.RecipeModel;
import pl.bartek.reciepes.repository.recipe.RecipeRepository;
import pl.bartek.reciepes.service.recipe.RecipeService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(final RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<RecipeModel> findAll() {
        log.debug("Log from service");

        final Set<RecipeModel> recipes = new HashSet<>();
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }

    @Override
    public Optional<RecipeModel> findById(final Long id) {
        return recipeRepository.findById(id);
    }
}
