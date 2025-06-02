package ru.germes.plus.site.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderForIndividualDto {
    private List<Long> productIds;
    private String deliveryType; // "delivery" или "pickup"
    private DeliveryDetailsDto deliveryDetails;
    private PickupDetailsDto pickupDetails;
}
