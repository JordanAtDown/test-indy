package org.example.domain.promocode.restriction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.example.domain.promocode.Utilisateur;
import org.example.domain.promocode.validation.Cause;
import org.example.domain.promocode.validation.ValidationEtat;
import org.springframework.util.Assert;

public class DateInterval implements Restriction {
    private final LocalDate borneInferieur;
    private final LocalDate borneSuperieur;
    private final String message;
    private final DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");

    public DateInterval(LocalDate borneInferieur, LocalDate borneSuperieur) {
        this.borneInferieur = borneInferieur;
        this.borneSuperieur = borneSuperieur;
        this.message = String.format("La date du jour n'est pas comprise entre le %s et %s",
                borneInferieur.format(formatters),
                borneSuperieur.format(formatters)
        );

        Assert.isTrue(borneInferieur.isBefore(borneSuperieur), "La date de la borne supérieur doit être plus grande que la borne inférieur");
    }

    @Override
    public ValidationEtat valider(Utilisateur utilisateur, ValidationEtat validationEtat) {
        if (estValide(utilisateur)) {
            validationEtat.valider();
        } else {
            validationEtat.invaliderAvec(new Cause(message));
        }
        return validationEtat;
    }

    @Override
    public boolean estValide(Utilisateur utilisateur) {
        return utilisateur.date().isAfter(borneInferieur)
                && utilisateur.date().isBefore(borneSuperieur);
    }
}
