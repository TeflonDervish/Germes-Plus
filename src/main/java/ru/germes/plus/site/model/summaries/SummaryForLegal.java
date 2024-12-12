package ru.germes.plus.site.model.summaries;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.germes.plus.site.model.orders.OrderForLegal;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.model.products.ProductForLegal;

@Entity
@Data
@NoArgsConstructor
public class SummaryForLegal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private LegalPerson legalPerson;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductForLegal productForLegal;

    @ManyToOne
    @JoinColumn(name = "order_legal_id")
    private OrderForLegal orderForLegal;

    private Integer quantity;

}
