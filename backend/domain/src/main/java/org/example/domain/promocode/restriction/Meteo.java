package org.example.domain.promocode.restriction;

import org.example.domain.promocode.validation.Cause;
import org.example.domain.promocode.Utilisateur;
import org.example.domain.promocode.validation.ValidationEtat;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Meteo implements Restriction {

    private String condition;
    private int temperature;
    private final Cause cause;

    public Meteo(String condition, int temperature) {
        this.condition = condition;
        this.temperature = temperature;
        this.cause = new Cause("Les conditions météorologiques sont incorrect");
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
        return utilisateur.conditionMeteo().equalsIgnoreCase(condition)
                && utilisateur.temperature() > temperature;
    }
}
