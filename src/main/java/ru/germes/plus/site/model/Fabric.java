package ru.germes.plus.site.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Fabric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;
    @Column(length = 20)
    private String city;
    @Column(length = 100)
    private String address;

    // ссылка на точку на Яндекс картах
    @Column(nullable = false)
    private String pointOnTheMap;

    @Column(length = 20)
    private String phoneNumber;
    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private String openingHours;

    private String description;

}
