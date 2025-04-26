package ru.germes.plus.site.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Korzina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private IndividualPerson individualPerson;

    @ElementCollection
    @CollectionTable(name="korzinaProduct", joinColumns = @JoinColumn(name="id"))
    @Column(name = "products")
    private List<ProductForIndividual> products;

    public void addProduct(ProductForIndividual product) {
        products.add(product);
    }
}
