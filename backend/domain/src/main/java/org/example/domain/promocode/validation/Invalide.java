package org.example.domain.promocode.validation;

import java.util.List;

import lombok.Getter;

@Getter
public class Invalide extends PromocodeEtat {
    List<Cause> causes;

    public Invalide(String nom, Statut statut, List<Cause> causes) {
        super(nom, statut);
        this.causes = causes;
    }
}
