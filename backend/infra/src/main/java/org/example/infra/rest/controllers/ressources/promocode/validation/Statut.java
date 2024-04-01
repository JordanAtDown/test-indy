package org.example.infra.rest.controllers.ressources.promocode.validation;

public enum Statut {
    accepte,
    refuse;

    public static Statut toRessource(org.example.domain.promocode.validation.Statut statut) {
        return Statut.valueOf(statut.name());
    }
}
