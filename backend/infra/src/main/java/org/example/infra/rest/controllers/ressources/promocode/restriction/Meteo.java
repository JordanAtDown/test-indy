package org.example.infra.rest.controllers.ressources.promocode.restriction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Meteo extends Restriction {
    private String condition;
    private int temperature;

    @Override
    public org.example.domain.promocode.restriction.Restriction toDomainObject() {
        return new org.example.domain.promocode.restriction.Meteo(condition, temperature);
    }
}
