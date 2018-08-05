package pl.bartek.reciepes.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import pl.bartek.reciepes.model.RecipeModel;
import pl.bartek.reciepes.service.recipe.RecipeService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeControllerTest {

    RecipeController recipeController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeController = new RecipeController(recipeService);

    }

    @Test
    public void testMockMvc() throws Exception {
        final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        mockMvc.perform(get("/recipes"))
                .andExpect(status().isOk())
                    .andExpect(view().name("recipe/list"));

    }

    @Test
    public void index() throws Exception {
        //given
        final Set<RecipeModel> recipes = new HashSet<>();
        recipes.add(new RecipeModel());

        when(recipeService.findAll()).thenReturn(recipes);
        final ArgumentCaptor<Set<RecipeModel>> captor = ArgumentCaptor.forClass(Set.class);

        //when
        final String pageName = recipeController.recipesList(model);

        //then
        assertEquals(pageName, "recipe/list");
        verify(recipeService, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("recipes"), captor.capture());
        final Set<RecipeModel> setInController = captor.getValue();
        assertEquals(setInController.size(), 1);
    }

}