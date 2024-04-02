package org.example.domain.promocode.restriction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;

import org.example.domain.promocode.Utilisateur;
import org.example.domain.promocode.validation.Cause;
import org.example.domain.promocode.validation.ValidationEtat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AgeEgaleTest {

    @Test
    @DisplayName("Devrait lever une exception quand l'âge définit est inférieur à l'âge légale")
    void doitLeverUneExceptionQuandLAgeDefinitEstInférieurALAgeLegale() {
        assertThatThrownBy(() ->
                new AgeEgale(15)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("L'âge doit être dans la limite de l'âge légale");
    }

    @Test
    @DisplayName("Devrait être valide quand l'âge du client est équivalent à la restriction")
    void doitEtreValideQuandLAgeEstEquivalent() {
        Utilisateur utilisateur = new Utilisateur(20, LocalDate.parse("2019-01-02"), 24, "clear");
        AgeEgale restriction = new AgeEgale(20);
        ValidationEtat validationEtat = new ValidationEtat();

        ValidationEtat valider = restriction.valider(utilisateur, validationEtat);

        assertThat(valider.isValide()).isTrue();
    }

    @Test
    @DisplayName("Devrait définir quand l'âge du client n'est pas équivalent à la restriction")
    void doitDéfinirQuantLAgeDuClientNEestPasEquivalentALaRestriction() {
        Utilisateur utilisateur = new Utilisateur(15, LocalDate.parse("2019-01-02"), 24, "clear");
        AgeEgale restriction = new AgeEgale(20);
        ValidationEtat validationEtat = new ValidationEtat();

        ValidationEtat valider = restriction.valider(utilisateur, validationEtat);

        assertThat(valider.isValide()).isFalse();
        assertThat(valider.getCauses())
                .extracting(Cause::message)
                .contains("L'âge n'est pas égale à 20 ans");
    }
}
