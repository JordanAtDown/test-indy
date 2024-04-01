package org.example.infra.rest.controllers.ressources.promocode;

import java.util.List;

import org.example.infra.rest.controllers.ressources.promocode.restriction.Restriction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Promocode {
    private String nom;
    private Avantage avantage;
    private List<Restriction> restrictions;

    public org.example.domain.promocode.Promocode toDomainObject() {
        return new org.example.domain.promocode.Promocode(
                nom,
                avantage.getPourcentage(),
                restrictions.stream()
                        .map(Restriction::toDomainObject).
                        toList()
        );
    }
}
