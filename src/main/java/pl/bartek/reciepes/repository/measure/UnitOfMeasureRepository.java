package pl.bartek.reciepes.repository.measure;

import org.springframework.data.repository.CrudRepository;
import pl.bartek.reciepes.model.UnitOfMeasureModel;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasureModel, Long> {
    Optional<UnitOfMeasureModel> findByDescription(final String description);
}
