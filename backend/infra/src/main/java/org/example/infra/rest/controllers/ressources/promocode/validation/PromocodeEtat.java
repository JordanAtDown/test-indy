package org.example.infra.rest.controllers.ressources.promocode.validation;

import java.util.Optional;

import org.example.infra.rest.controllers.ressources.promocode.Avantage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class PromocodeEtat {
    private String nom;
    private Statut statut;

    public static Optional<PromocodeEtat> toRessource(org.example.domain.promocode.validation.PromocodeEtat promocodeEtat) {
        if (promocodeEtat instanceof org.example.domain.promocode.validation.Valide valide) {
            return Optional.of(new Valide(
                    promocodeEtat.getNom(),
                    Statut.toRessource(promocodeEtat.getStatut()),
                    Avantage.toRessource(valide.getAvantage())
            ));
        }

        if (promocodeEtat instanceof org.example.domain.promocode.validation.Invalide invalide) {
            return Optional.of(new Invalide(
                    promocodeEtat.getNom(),
                    Statut.toRessource(promocodeEtat.getStatut()),
                    Cause.toRessource(invalide.getCauses())
            ));
        }
        return Optional.empty();
    }
}
