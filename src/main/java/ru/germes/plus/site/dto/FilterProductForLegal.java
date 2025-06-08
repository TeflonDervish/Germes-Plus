package ru.germes.plus.site.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FilterProductForLegal {
    private Integer minPrice;
    private Integer maxPrice;
    private List<String> configurations;

    @Override
    public String toString() {
        return "FilterProductForLegal{" +
                "minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", configurations=" + configurations +
                '}';
    }
}
