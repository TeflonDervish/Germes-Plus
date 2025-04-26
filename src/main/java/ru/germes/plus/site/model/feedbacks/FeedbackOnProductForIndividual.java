package ru.germes.plus.site.model.feedbacks;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.germes.plus.site.model.PointOfSale;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class FeedbackOnProductForIndividual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProductForIndividual productForIndividual;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private IndividualPerson individualPerson;

    @Column(columnDefinition = "TEXT")
    private String text;

    private double grade;

    private LocalDate date;

}
