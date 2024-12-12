package ru.germes.plus.site.model.summaries;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.germes.plus.site.model.PointOfSale;
import ru.germes.plus.site.model.orders.OrderForFabric;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.model.products.ProductForLegal;

@Entity
@Data
@NoArgsConstructor
public class SummaryForFabric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "point_id")
    private PointOfSale pointOfSale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductForIndividual productForIndividual;

    @ManyToOne
    @JoinColumn(name = "order_for_fabric_id")
    private OrderForFabric orderForFabric;

    private Integer quantity;

}
