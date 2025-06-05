package ru.germes.plus.site.model.korzina;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class KorzinaForIndividual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private IndividualPerson individualPerson;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "korzina_product_for_individual",
            joinColumns = @JoinColumn(name = "korzina_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductForIndividual> products = new ArrayList<>();

    public void addProduct(ProductForIndividual product) {
        products.add(product);
    }

    public void deleteProduct(ProductForIndividual product) {
        products.remove(product);
    }

    public boolean isInKorzina(ProductForIndividual product) {
        return products.contains(product);
    }
}
