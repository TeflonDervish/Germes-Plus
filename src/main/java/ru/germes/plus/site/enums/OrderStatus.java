package ru.germes.plus.site.enums;


import lombok.Getter;

@Getter
public enum OrderStatus {
    WAITING_ACCESS("Ожидает подтверждения", "#4975ef", "white"),
    IN_PROGRESS("В работе", "green", "black"),
    ON_THE_WAY("В пути", "green", "black"),
    WAITING("Ожидает получения", "yellow", "black"),
    COMPLETED("Завершен", " #5e5e5e", "white"),
    CANCELLED("Отменен", "red", "white"),
    REFUND("Возврат", "red", "white"),;

    private String title;
    private String color;
    private String textColor;

    OrderStatus(String title, String color, String textColor) {
        this.title = title;
        this.color = color;
        this.textColor = textColor;
    }
}
