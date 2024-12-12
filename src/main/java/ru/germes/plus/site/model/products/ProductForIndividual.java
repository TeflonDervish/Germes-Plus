package ru.germes.plus.site.model.products;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

//TODO: Сделать в формате nosql добавить поля документации (гарантия, инструкция)

@Data
@NoArgsConstructor
@Entity
public class ProductForIndividual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
