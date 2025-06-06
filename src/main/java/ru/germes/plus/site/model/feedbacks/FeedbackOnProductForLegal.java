package ru.germes.plus.site.model.feedbacks;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.model.products.ProductForLegal;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class FeedbackOnProductForLegal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private ProductForLegal productForLegal;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private LegalPerson legalPerson;

    @Column(columnDefinition = "TEXT")
    private String text;

    private Double grade;
    private LocalDate date;

    @ElementCollection
    @CollectionTable(name = "urlsFeedbackForProductForLegal", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "urls")
    private List<String> urls;

}
