package org.example.domain.promocode.restriction;

import org.example.domain.promocode.validation.Cause;
import org.example.domain.promocode.Utilisateur;
import org.example.domain.promocode.validation.ValidationEtat;
import org.springframework.util.Assert;

public class AgeIntervalStricte implements Restriction {
    private final int borneInferieur;
    private final int borneSuperieur;
    private final Cause cause;

    public AgeIntervalStricte(int borneSuperieur, int borneInferieur) {
        this.borneSuperieur = borneSuperieur;
        this.borneInferieur = borneInferieur;
        this.cause = new Cause(String.format("L'âge n'est pas compris entre  %s ans et %s ans", borneInferieur, borneSuperieur));

        Assert.isTrue(borneInferieur >= 18, "La borne inférieur doit être dans la limite de l'âge légale");
        Assert.isTrue(borneInferieur < borneSuperieur, "La restriction d'âge interval stricte est incorrect");
    }

    @Override
    public ValidationEtat valider(Utilisateur utilisateur, ValidationEtat validationEtat) {
        if (this.estValide(utilisateur)) {
            validationEtat.valider();
        } else {
            validationEtat.invaliderAvec(cause);
        }
        return validationEtat;
    }

    @Override
    public boolean estValide(Utilisateur utilisateur) {
        return utilisateur.getAge() < borneSuperieur
                && utilisateur.getAge() > borneInferieur;
    }
}
