package ru.germes.plus.site.model.orders;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.germes.plus.site.enums.OrderStatus;
import ru.germes.plus.site.model.Fabric;
import ru.germes.plus.site.model.PointOfSale;
import ru.germes.plus.site.model.persons.FabricManager;
import ru.germes.plus.site.model.products.ProductForIndividual;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderForFabric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "order_for_fabric_product",
            joinColumns = @JoinColumn(name = "order_ir"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductForIndividual> products;

    private Integer totalPrice;

    @ManyToOne
    @JoinColumn(name = "point_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PointOfSale pointOfSale;

    @ManyToOne
    @JoinColumn(name = "fabric_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Fabric fabric;

    @ManyToOne
    @JoinColumn(name = "fabric_manager_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private FabricManager fabricManager;

    private Integer price;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
