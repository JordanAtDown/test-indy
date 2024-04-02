package org.example.domain.promocode.restriction;

import org.example.domain.promocode.validation.Cause;
import org.example.domain.promocode.Utilisateur;
import org.example.domain.promocode.validation.ValidationEtat;
import org.springframework.util.Assert;

public class AgeEgale implements Restriction {

    private final int age;
    private final Cause cause;

    public AgeEgale(int age) {
        this.age = age;
        this.cause = new Cause(String.format("L'âge n'est pas égale à %s ans", age));

        Assert.isTrue(age >= 18, "L'âge doit être dans la limite de l'âge légale");
    }

    @Override
    public ValidationEtat valider(Utilisateur utilisateur, ValidationEtat validationEtat) {
        if (estValide(utilisateur)) {
            validationEtat.valide();
        } else {
            validationEtat.invalideAvec(cause);
        }
        return validationEtat;
    }

    @Override
    public boolean estValide(Utilisateur utilisateur) {
        return age == utilisateur.age();
    }
}
