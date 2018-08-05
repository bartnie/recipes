package pl.bartek.reciepes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bartek.reciepes.model.RecipeModel;
import pl.bartek.reciepes.service.recipe.RecipeService;

import java.util.Set;

@RequestMapping("/recipes")
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(final RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/", ""})
    public String recipesList(final Model model) {
        final Set<RecipeModel> recipes = recipeService.findAll();
        model.addAttribute("recipes", recipes);
        return "recipe/list";
    }
}
