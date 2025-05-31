package ru.germes.plus.site.model.likes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.model.products.ProductForLegal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikesForLegal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private LegalPerson legalPerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private ProductForLegal productForLegal;
}
