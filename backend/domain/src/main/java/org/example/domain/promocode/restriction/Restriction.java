package org.example.domain.promocode.restriction;

import org.example.domain.promocode.Utilisateur;
import org.example.domain.promocode.validation.ValidationEtat;

public interface Restriction {
    ValidationEtat valider(Utilisateur utilisateur, ValidationEtat validationEtat);
    boolean estValide(Utilisateur utilisateur);
}
