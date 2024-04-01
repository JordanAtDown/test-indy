package org.example.domain.promocode;

import java.util.List;

import org.example.domain.promocode.restriction.Restriction;
import org.example.domain.promocode.validation.ValidationEtat;

import lombok.Getter;

public class Promocode {
    @Getter
    private final String nom;
    @Getter
    private final Avantage avantage;
    private final List<Restriction> restrictions;
    private final ValidationEtat validationEtat = new ValidationEtat();

    public Promocode(String nom, int pourcentage, List<Restriction> restrictions) {
        this.nom = nom;
        this.avantage = new Avantage(pourcentage);
        this.restrictions = restrictions;
    }

    public ValidationEtat validation(Utilisateur utilisateur) {
        restrictions.forEach(restriction -> restriction.valider(utilisateur, validationEtat));
        return validationEtat;
    }

}
