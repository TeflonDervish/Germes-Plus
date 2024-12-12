package ru.germes.plus.site.model.persons;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.germes.plus.site.enums.Role;

@Entity
@Data
@NoArgsConstructor
public class IndividualPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String surname;
    private String name;
    private String middleName;

    private String phoneNumber;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
