package org.example.domain.promocode.restriction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;

import org.example.domain.promocode.Utilisateur;
import org.example.domain.promocode.validation.Cause;
import org.example.domain.promocode.validation.ValidationEtat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateIntervalTest {

    @Test
    @DisplayName("Devrait être valide lorsque la date du jour est compris dans l'interval")
    void doitPermettreDeValiderUneDateDansLIntervalleDeRestriction() {
        DateInterval dateInterval = new DateInterval(LocalDate.parse("2019-01-01"), LocalDate.parse("2020-06-30"));
        Utilisateur utilisateur = new Utilisateur(20, LocalDate.parse("2019-01-02"), 24, "clear");
        ValidationEtat validationEtat = new ValidationEtat();

        ValidationEtat etat = dateInterval.valider(utilisateur, validationEtat);

        assertThat(etat.isValider()).isTrue();
    }

    @Test
    @DisplayName("Devrait être invalide lorsque la date du jour n'est pas compris dans l'interval")
    void doitEtreInvaliderQuandUneDateNestPasDansLIntervalleDeRestriction() {
        DateInterval dateInterval = new DateInterval(LocalDate.parse("2019-01-01"), LocalDate.parse("2020-06-30"));
        Utilisateur utilisateur = new Utilisateur(20, LocalDate.parse("2018-01-02"), 24, "clear");
        ValidationEtat validationEtat = new ValidationEtat();

        ValidationEtat etat = dateInterval.valider(utilisateur, validationEtat);

        assertThat(etat.isValider()).isFalse();
        assertThat(etat.getCauses())
                .extracting(Cause::message)
                .contains("La date du jour n'est pas comprise entre le 1/01/2019 et 30/06/2020");
    }

    @Test
    @DisplayName("Devrait lever une exception quand la date de la borne inférieur est plus grande que la date de la borne supérieur")
    void doitLeverUneExeceptionQuandLaBorneInferieurEstSuperieur() {
        assertThatThrownBy(() ->
                new DateInterval(LocalDate.parse("2020-06-30"), LocalDate.parse("2019-01-01"))
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("La date de la borne supérieur doit être plus grande que la borne inférieur");
    }

}
