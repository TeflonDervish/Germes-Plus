package ru.germes.plus.site.model.orders;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.germes.plus.site.enums.DeliveryType;
import ru.germes.plus.site.enums.OrderStatus;
import ru.germes.plus.site.model.Fabric;
import ru.germes.plus.site.model.PointOfSale;
import ru.germes.plus.site.model.persons.FabricManager;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.model.products.ProductForLegal;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private LegalPerson legalPerson;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "order_for_legal_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductForLegal> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "fabric_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Fabric fabric;

    @ManyToOne
    @JoinColumn(name = "fabric_manager_order_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private FabricManager fabricManager;

    private LocalDate orderDate;

    private Integer totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @Column(length = 100)
    private String deliveryAddress;
}
