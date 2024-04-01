package org.example.domain.promocode;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Utilisateur {
    private int age;
    private LocalDate date;
    private int temperature;
    private String conditionMeteo;
}
