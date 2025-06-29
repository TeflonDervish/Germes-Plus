package ru.germes.plus.site.model.othcet;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.germes.plus.site.enums.LegalProductType;
import ru.germes.plus.site.model.Fabric;
import ru.germes.plus.site.model.PointOfSale;
import ru.germes.plus.site.model.orders.OrderForIndividual;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.model.products.ProductForLegal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtchetForFabric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "otchets_for_fabric_individual",
            joinColumns = @JoinColumn(name = "otchet_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<OrderForIndividual> productsForIndividuals = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "otchets_for_fabric_legal",
            joinColumns = @JoinColumn(name = "otchet_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<OrderForIndividual> productsForLegals = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "fabric_otchet_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Fabric fabric;

    @Column(length = 100)
    private String name;
    private String description;

    private Integer totalPrice;
    private Integer count;
    private Integer meanPrice;
    
}
