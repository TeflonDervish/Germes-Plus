package ru.germes.plus.site.model.korzina;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.germes.plus.site.model.persons.PointManager;
import ru.germes.plus.site.model.products.ProductForIndividual;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class KorzinaOnPointOfSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "korzina_product_on_point",
            joinColumns = @JoinColumn(name = "korzina_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductForIndividual> products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_manager_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private PointManager pointManager;

}
