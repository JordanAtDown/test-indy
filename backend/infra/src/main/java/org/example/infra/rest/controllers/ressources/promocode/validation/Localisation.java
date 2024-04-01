package org.example.infra.rest.controllers.ressources.promocode.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Localisation {
    private double longitude;
    private double latitude;

    public org.example.domain.promocode.meteo.Localisation toDomainObject() {
        return new org.example.domain.promocode.meteo.Localisation(longitude, latitude);
    }
}
