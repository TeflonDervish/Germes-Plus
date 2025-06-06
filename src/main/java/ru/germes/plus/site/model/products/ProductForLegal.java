package ru.germes.plus.site.model.products;

import jakarta.persistence.*;
import lombok.*;
import ru.germes.plus.site.enums.LegalProductType;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProductForLegal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private LegalProductType type;

    @Column(length = 100)
    private String typeCloth;
    @Column(length = 100)
    private Integer durability;
    @Column(length = 100)
    private String color;

    @Column(length = 100)
    private String configuration;
    @Column(length = 100)
    private String gabarit;

    @Column(length = 100)
    private String typeFill;
    @Column(length = 100)
    private String density;

    private Long price;

    @Lob
    private String description;

    @Column
    private String article;

    @ElementCollection
    @CollectionTable(name="urlsForProductForLegal", joinColumns = @JoinColumn(name="id"))
    @Column(name = "urls")
    private List<String> urls;



}
