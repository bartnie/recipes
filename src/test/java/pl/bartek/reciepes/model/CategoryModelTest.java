package pl.bartek.reciepes.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryModelTest {

    CategoryModel category;

    @Before
    public void setUp() {
        category = new CategoryModel();
    }

    @Test
    public void getId() throws Exception {
        final Long idValue = 4L;
        category.setId(idValue);

        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() throws Exception {

    }

    @Test
    public void getRecipes() throws Exception {

    }

}