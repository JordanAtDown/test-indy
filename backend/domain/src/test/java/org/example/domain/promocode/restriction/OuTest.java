package org.example.domain.promocode.restriction;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.example.domain.promocode.Utilisateur;
import org.example.domain.promocode.validation.ValidationEtat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OuTest {

    @Test
    @DisplayName("Devrait valide la restriction quand au moins l'un des deux operateur est valide")
    void doitEtreValideQuandAuMoinsUnDesDeuxOperateurEstValide() {
        DateInterval dateInterval = new DateInterval(LocalDate.parse("2019-01-01"), LocalDate.parse("2020-06-30"));
        AgeEgale ageEgale = new AgeEgale(40);
        Ou ou = new Ou(dateInterval, ageEgale);
        Utilisateur utilisateur = new Utilisateur(20, LocalDate.parse("2019-01-02"), 24, "clear");
        ValidationEtat validationEtat = new ValidationEtat();

        ValidationEtat etat = ou.valider(utilisateur, validationEtat);

        assertThat(etat.isValide()).isTrue();
    }

    @Test
    @DisplayName("Devrait invalider la restriction quand les deux operateurs sont invalide")
    void doitInvalideQuandLesDeuxOperateurSintInvalide() {
        DateInterval dateInterval = new DateInterval(LocalDate.parse("2019-01-01"), LocalDate.parse("2020-06-30"));
        AgeEgale ageEgale = new AgeEgale(40);
        Ou ou = new Ou(dateInterval, ageEgale);
        Utilisateur utilisateur = new Utilisateur(20, LocalDate.parse("2018-01-02"), 24, "clear");
        ValidationEtat validationEtat = new ValidationEtat();

        ValidationEtat etat = ou.valider(utilisateur, validationEtat);

        assertThat(etat.isValide()).isFalse();
    }
}
