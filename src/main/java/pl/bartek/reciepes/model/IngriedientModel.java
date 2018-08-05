package pl.bartek.reciepes.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = "recipe")
@Entity
public class IngriedientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasureModel measure;

    @ManyToOne
    private RecipeModel recipe;

    public IngriedientModel(final String description, final BigDecimal amount, final UnitOfMeasureModel measure, final RecipeModel recipe) {
        this.description = description;
        this.amount = amount;
        this.measure = measure;
        this.recipe = recipe;
    }
}
