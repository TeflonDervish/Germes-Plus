package ru.germes.plus.site.model.orders;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.germes.plus.site.enums.OrderStatus;
import ru.germes.plus.site.model.Fabric;
import ru.germes.plus.site.model.PointOfSale;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.model.summaries.SummaryForFabric;
import ru.germes.plus.site.model.summaries.SummaryForLegal;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class OrderForFabric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "orderForFabric")
    private List<SummaryForFabric> summaryForFabrics;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PointOfSale pointOfSale;

    @ManyToOne
    @JoinColumn(name = "fabric_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Fabric fabric;

    private Integer price;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
