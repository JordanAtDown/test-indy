package org.example.infra.rest.controllers.ressources.promocode.restriction;

import java.time.LocalDate;

import org.example.domain.promocode.restriction.DateInterval;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DateIntervalStricte extends Restriction {
    private LocalDate borneSuperieur;
    private LocalDate borneInferieur;

    @Override
    public org.example.domain.promocode.restriction.Restriction toDomainObject() {
        return new DateInterval(borneInferieur, borneSuperieur);
    }
}
