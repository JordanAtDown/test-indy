package org.example.infra.rest.controllers.ressources.promocode.restriction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AgeIntervalStricte extends Restriction {
    private int borneInferieur;
    private int borneSuperieur;

    @Override
    public org.example.domain.promocode.restriction.Restriction toDomainObject() {
        return new org.example.domain.promocode.restriction.AgeIntervalStricte(borneSuperieur, borneInferieur);
    }
}
