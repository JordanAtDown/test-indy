package org.example.domain.promocode.restriction;

import org.example.domain.promocode.Utilisateur;
import org.example.domain.promocode.validation.ValidationEtat;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Et implements Restriction {

    private Restriction operateurGauche;
    private Restriction operateurDroit;

    @Override
    public ValidationEtat valider(Utilisateur utilisateur, ValidationEtat validationEtat) {
        if (estValide(utilisateur)) {
            validationEtat.valide();
        } else {
            operateurGauche.valider(utilisateur, validationEtat);
            operateurDroit.valider(utilisateur, validationEtat);
        }
        return validationEtat;
    }

    @Override
    public boolean estValide(Utilisateur utilisateur) {
        return operateurGauche.estValide(utilisateur)
                && operateurDroit.estValide(utilisateur);
    }
}
