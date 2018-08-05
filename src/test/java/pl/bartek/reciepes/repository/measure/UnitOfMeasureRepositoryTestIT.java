package pl.bartek.reciepes.repository.measure;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pl.bartek.reciepes.model.UnitOfMeasureModel;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryTestIT {

    @Autowired
    UnitOfMeasureRepository measureRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    @DirtiesContext
    public void findByDescription() throws Exception {
        final Optional<UnitOfMeasureModel> measureModel = measureRepository.findByDescription("Teaspoon");
        assertEquals("Teaspoon", measureModel.get().getDescription());
    }

    @Test
    public void findByDescriptionCup() throws Exception {
        final Optional<UnitOfMeasureModel> measureModel = measureRepository.findByDescription("Cup");
        assertEquals("Cup", measureModel.get().getDescription());
    }

}