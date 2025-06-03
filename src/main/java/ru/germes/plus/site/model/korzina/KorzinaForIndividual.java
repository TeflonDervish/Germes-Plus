package ru.germes.plus.site.model.korzina;

import jakarta.persistence.*;
import lombok.*;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;

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

    @ElementCollection
    @CollectionTable(name = "korzinaProductForIndividual", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "products")
    private List<ProductForIndividual> products;

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
