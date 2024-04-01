package org.example.infra.rest.controllers.ressources.promocode.restriction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Et extends Restriction {
    private Restriction operateurGauche;
    private Restriction operateurDroit;

    @Override
    public org.example.domain.promocode.restriction.Restriction toDomainObject() {
        return new org.example.domain.promocode.restriction.Et(operateurGauche.toDomainObject(), operateurDroit.toDomainObject());
    }
}
