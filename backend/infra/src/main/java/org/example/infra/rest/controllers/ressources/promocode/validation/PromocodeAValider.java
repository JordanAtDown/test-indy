package org.example.infra.rest.controllers.ressources.promocode.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PromocodeAValider {
    private String nom;
    private int age;
    private Localisation localisation;

    public org.example.domain.promocode.PromocodeAValider toDomainObject() {
        return new org.example.domain.promocode.PromocodeAValider(nom, age, localisation.toDomainObject());
    }
}
