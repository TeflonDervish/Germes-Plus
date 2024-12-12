package ru.germes.plus.site.model.products;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

//TODO: Сделать в формате nosql добавить поля документации (гарантия, инструкция)

@Entity
@Data
@NoArgsConstructor
public class ProductForLegal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
