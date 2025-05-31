package ru.germes.plus.site.model.products;

import jakarta.persistence.*;
import lombok.*;
import ru.germes.plus.site.enums.LegalProductType;


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

    private String typeCloth;
    private Integer durability;
    private String color;

    private String configuration; // не знаю какое слово
    private String gabarit;

    private String typeFill;
    private String density;




}
