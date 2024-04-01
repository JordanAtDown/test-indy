package org.example.infra.rest.controllers.ressources.promocode.validation;

import org.example.infra.rest.controllers.ressources.promocode.Avantage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Valide extends PromocodeEtat {
    private Avantage avantage;

    public Valide(String nom, Statut statut, Avantage avantage) {
        super(nom, statut);
        this.avantage = avantage;
    }
}
