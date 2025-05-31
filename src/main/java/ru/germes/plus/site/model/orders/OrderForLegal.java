package ru.germes.plus.site.model.orders;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.germes.plus.site.model.Fabric;
import ru.germes.plus.site.model.PointOfSale;
import ru.germes.plus.site.model.persons.FabricManager;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.model.products.ProductForLegal;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class OrderForLegal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "legal_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LegalPerson legalPerson;

    @ElementCollection
    @CollectionTable(name = "orderForLegalProduct", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "products")
    private List<ProductForLegal> products;

    private Integer totalPrice;

    @ManyToOne
    @JoinColumn(name = "fabric_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Fabric fabric;

    @ManyToOne
    @JoinColumn(name = "fabric_manager_order_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private FabricManager fabricManager;

}
