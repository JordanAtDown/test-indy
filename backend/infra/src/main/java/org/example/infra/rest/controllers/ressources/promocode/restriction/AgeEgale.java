package org.example.infra.rest.controllers.ressources.promocode.restriction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AgeEgale extends Restriction {
    private int age;

    @Override
    public org.example.domain.promocode.restriction.Restriction toDomainObject() {
        return new org.example.domain.promocode.restriction.AgeEgale(age);
    }
}
