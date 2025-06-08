package ru.germes.plus.site.dto;

import lombok.*;

import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class FilterProductForIndividual {
    private Integer minPrice;
    private Integer maxPrice;

    private Integer depthMin;
    private Integer depthMax;

    private Integer plantingMin;
    private Integer plantingMax;

    private Integer seatDepthMin;
    private Integer seatDepthMax;

    private Integer sizeMin;
    private Integer sizeMax;

    private Integer sleepingSpaceMin;
    private Integer sleepingSpaceMax;

    private List<String> configurations;

    private List<String> mechanisms;

    private List<String> fillings;

    private List<String> armrests;

    private List<String> box;
}
