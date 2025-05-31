package ru.germes.plus.site.model.likes;

import jakarta.persistence.*;
import lombok.*;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikesForIndividual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private IndividualPerson individualPerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private ProductForIndividual productForIndividual;
}
