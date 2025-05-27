package ru.germes.plus.site.enums;

import lombok.Getter;

@Getter
public enum DeliveryType {
    DELIVERY("Доставка"),
    PICKUP("Самовывоз");

    private String title;

    DeliveryType(String title) {
        this.title = title;
    }
}
