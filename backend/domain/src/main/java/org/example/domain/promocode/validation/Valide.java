package org.example.domain.promocode.validation;

import org.example.domain.promocode.Avantage;

import lombok.Getter;

@Getter
public class Valide extends PromocodeEtat {
    private final Avantage avantage;

    public Valide(String nom, Statut statut, Avantage avantage) {
        super(nom, statut);
        this.avantage = avantage;
    }
}
