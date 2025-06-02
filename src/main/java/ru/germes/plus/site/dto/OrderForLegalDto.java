package ru.germes.plus.site.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderForLegalDto {
    private List<Long> productIds;
    private LocalDate date;
    private Long fabricId;
}
