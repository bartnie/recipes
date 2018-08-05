package pl.bartek.reciepes.bootstrap.recipe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.bartek.reciepes.enums.Difficulty;
import pl.bartek.reciepes.model.CategoryModel;
import pl.bartek.reciepes.model.IngriedientModel;
import pl.bartek.reciepes.model.NotesModel;
import pl.bartek.reciepes.model.RecipeModel;
import pl.bartek.reciepes.model.UnitOfMeasureModel;
import pl.bartek.reciepes.repository.category.CategoryRepository;
import pl.bartek.reciepes.repository.measure.UnitOfMeasureRepository;
import pl.bartek.reciepes.repository.recipe.RecipeRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class RecipeDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public RecipeDataLoader(final RecipeRepository recipeRepository, final CategoryRepository categoryRepository,
                            final UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Transactional
    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipeList());
        log.debug("Loading bootstrap data");
    }

    private List<RecipeModel> getRecipeList() {
        final List<RecipeModel> recipes = new ArrayList<>(1);
        final UnitOfMeasureModel each = validateAndGetUnitOfMeasurement("Each");
        final UnitOfMeasureModel tablespoon = validateAndGetUnitOfMeasurement("Tablespoon");
        final UnitOfMeasureModel teaspoon = validateAndGetUnitOfMeasurement("Teaspoon");
        final UnitOfMeasureModel dash = validateAndGetUnitOfMeasurement("Dash");
        final UnitOfMeasureModel pint = validateAndGetUnitOfMeasurement("Pint");
        final UnitOfMeasureModel cup = validateAndGetUnitOfMeasurement("Cup");

        final CategoryModel americanCategory = validateAndGetCategory("American");
        final CategoryModel mexicanCategory = validateAndGetCategory("Mexican");

        final RecipeModel guacRecipe = new RecipeModel();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado" +
                " with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will" +
                " provide some balance to the richness of the avocado and will help delay the avocados from turning brown. Add the" +
                " chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with" +
                " a half of one chili pepper and add to the guacamole to your desired degree of hotness. Remember that much of this" +
                " is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air" +
                " reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready" +
                "to serve. Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just" +
                " before serving.");

        final NotesModel guacNotes = new NotesModel();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana" +
                " Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other" +
                "ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists" +
                " may be horrified, but so what? It tastes great.");
        guacNotes.setRecipe(guacRecipe);
        guacRecipe.setNotes(guacNotes);

        guacRecipe.getIngriedients().add(new IngriedientModel("ripe avocados", new BigDecimal(2), each, guacRecipe));
        guacRecipe.getIngriedients().add(new IngriedientModel("Kosher salt", new BigDecimal(".5"), teaspoon, guacRecipe));
        guacRecipe.getIngriedients().add(new IngriedientModel("fresh lime juice or lemon juice", new BigDecimal(1), tablespoon, guacRecipe));
        guacRecipe.getIngriedients().add(new IngriedientModel("minced red onion or thinly sliced green onion", new BigDecimal(2), tablespoon, guacRecipe));
        guacRecipe.getIngriedients().add(new IngriedientModel("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), each, guacRecipe));
        guacRecipe.getIngriedients().add(new IngriedientModel("cilantro", new BigDecimal(2), tablespoon, guacRecipe));
        guacRecipe.getIngriedients().add(new IngriedientModel("freshly grated black pepper", new BigDecimal(1), dash, guacRecipe));
        guacRecipe.getIngriedients().add(new IngriedientModel("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), each, guacRecipe));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);
        recipes.add(guacRecipe);

        return recipes;
    }

    private UnitOfMeasureModel validateAndGetUnitOfMeasurement(final String measurementDescription) {

        return unitOfMeasureRepository.findByDescription(measurementDescription)
                .orElseThrow(() -> new RuntimeException("Excepted unit not found!"));
    }

    private CategoryModel validateAndGetCategory(final String categoryDescription) {

        return categoryRepository.findByDescription(categoryDescription)
                .orElseThrow(() -> new RuntimeException("Excepted category not found!"));
    }
}