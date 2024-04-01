package org.example.domain.promocode;


import org.example.domain.promocode.meteo.Localisation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PromocodeAValider {
    private String nom;
    private int age;
    private Localisation localisation;
}
