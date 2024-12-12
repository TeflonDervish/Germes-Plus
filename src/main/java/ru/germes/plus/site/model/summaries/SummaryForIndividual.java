package ru.germes.plus.site.model.summaries;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.germes.plus.site.model.orders.OrderForIndividual;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.model.products.ProductForLegal;

@Entity
@Data
@NoArgsConstructor
public class SummaryForIndividual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private IndividualPerson individualPerson;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductForIndividual productForIndividual;

    @ManyToOne
    @JoinColumn(name = "order_for_individual")
    private OrderForIndividual orderForIndividual;

    private Integer quantity;

}
