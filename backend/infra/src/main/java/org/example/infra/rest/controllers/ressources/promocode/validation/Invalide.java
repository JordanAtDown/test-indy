package org.example.infra.rest.controllers.ressources.promocode.validation;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Invalide extends PromocodeEtat {
    List<Cause> causes;

    public Invalide(String nom, Statut statut, List<Cause> causes) {
        super(nom, statut);
        this.causes = causes;
    }
}
