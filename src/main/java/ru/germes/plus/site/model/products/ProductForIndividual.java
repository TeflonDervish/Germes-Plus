package ru.germes.plus.site.model.products;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


//TODO: Сделать в формате nosql добавить поля документации (гарантия, инструкция)

@Data
@NoArgsConstructor
@Entity
public class ProductForIndividual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String price;
    private String size;

    private String article;
    @Lob
    private String description;

    @Column(columnDefinition = "json")
    private String characteristics;

    @ElementCollection
    @CollectionTable(name="urls", joinColumns = @JoinColumn(name="id"))
    @Column(name = "urls")
    private List<String> urls;

}
