package org.example.domain.promocode.validation;

import lombok.Getter;

@Getter
public abstract class PromocodeEtat {
    private final String nom;
    private final Statut statut;

    public PromocodeEtat(String nom, Statut statut) {
        this.nom = nom;
        this.statut = statut;
    }
}
