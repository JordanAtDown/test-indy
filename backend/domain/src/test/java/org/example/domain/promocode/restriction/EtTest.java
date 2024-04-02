package org.example.domain.promocode.restriction;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.example.domain.promocode.Utilisateur;
import org.example.domain.promocode.validation.ValidationEtat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EtTest {

    @Test
    @DisplayName("Devrait être valide quand les deux operateur sont valide")
    void doitEtreValideQuandLesDeuxRestrictionsSontValide() {
        DateInterval dateInterval = new DateInterval(LocalDate.parse("2019-01-01"), LocalDate.parse("2020-06-30"));
        AgeEgale ageEgale = new AgeEgale(20);
        Utilisateur utilisateur = new Utilisateur(20, LocalDate.parse("2019-01-02"), 24, "clear");
        Et et = new Et(dateInterval, ageEgale);

        ValidationEtat etat = et.valider(utilisateur, new ValidationEtat());

        assertThat(etat.isValide()).isTrue();
    }

    @Test
    @DisplayName("Devrait être invalide quand aux moin l'un des deux operateur est invalide")
    void doitEtreInvalideQuandAuMoinsLUnDeuxOperateursestInvalide() {
        DateInterval dateInterval = new DateInterval(LocalDate.parse("2019-01-01"), LocalDate.parse("2020-06-30"));
        AgeEgale ageEgale = new AgeEgale(40);
        Utilisateur utilisateur = new Utilisateur(20, LocalDate.parse("2019-01-02"), 24, "clear");
        Et et = new Et(dateInterval, ageEgale);

        ValidationEtat etat = et.valider(utilisateur, new ValidationEtat());

        assertThat(etat.isValide()).isFalse();
    }
}
