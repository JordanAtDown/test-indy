package org.example.domain.promocode;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.example.domain.promocode.restriction.AgeEgale;
import org.example.domain.promocode.restriction.AgeIntervalStricte;
import org.example.domain.promocode.restriction.DateInterval;
import org.example.domain.promocode.restriction.Et;
import org.example.domain.promocode.restriction.Meteo;
import org.example.domain.promocode.restriction.Ou;
import org.example.domain.promocode.validation.Statut;
import org.example.domain.promocode.validation.ValidationEtat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PromocodeTest {

    @Test
    @DisplayName("Devrait invalider le promocode dès qu'une restriction est invalide")
    void doitInvaliderUnPromocodeDesQuneRestrictionEstInvalide() {
        DateInterval dateInterval = new DateInterval(LocalDate.parse("2019-01-01"), LocalDate.parse("2020-06-30"));
        AgeEgale ageEgale = new AgeEgale(40);
        Promocode weatherCode = new Promocode("WeatherCode", 20, asList(ageEgale, dateInterval));
        Utilisateur utilisateur = new Utilisateur(20, LocalDate.parse("2019-01-02"), 16, "clear");

        ValidationEtat etat = weatherCode.validation(utilisateur);

        assertThat(etat.isValider()).isFalse();
        assertThat(etat.getStatut()).isEqualTo(Statut.refuse);
        assertThat(etat.getCauses()).hasSize(1);
    }

    @Test
    @DisplayName("Devrait valider le promocode lorsque toutes les restrictions sont validé")
    void doitValiderLePromocodeQuandToutesLesRestrictionsSontValide() {
        DateInterval dateInterval = new DateInterval(LocalDate.parse("2019-01-01"), LocalDate.parse("2020-06-30"));
        AgeEgale ageEgale = new AgeEgale(40);
        Meteo meteo = new Meteo("clear", 15);
        AgeIntervalStricte operateurGauche = new AgeIntervalStricte(30, 18);
        Et et = new Et(operateurGauche, meteo);
        Ou ou = new Ou(ageEgale, et);
        Promocode weatherCode = new Promocode("WeatherCode", 20, asList(dateInterval, ou));
        Utilisateur utilisateur = new Utilisateur(20, LocalDate.parse("2019-01-02"), 16, "clear");

        ValidationEtat etat = weatherCode.validation(utilisateur);

        assertThat(etat.isValider()).isTrue();
        assertThat(etat.getStatut()).isEqualTo(Statut.accepte);
        assertThat(etat.getCauses()).isEmpty();
    }

    @Test
    @DisplayName("Devrait invalider le promocode lorsque l'une des restrictions est invalide")
    void doitInvaliderLePromocodeQuandLUneDesRestrictionsNEstPasValide() {
        DateInterval dateInterval = new DateInterval(LocalDate.parse("2019-01-01"), LocalDate.parse("2020-06-30"));
        AgeEgale ageEgale = new AgeEgale(40);
        Meteo meteo = new Meteo("clear", 15);
        AgeIntervalStricte ageIntervalStricte = new AgeIntervalStricte(30, 18);
        Et et = new Et(ageIntervalStricte, meteo);
        Ou ou = new Ou(ageEgale, et);
        Promocode weatherCode = new Promocode("WeatherCode", 20, asList(dateInterval, ou));
        Utilisateur utilisateur = new Utilisateur(20, LocalDate.parse("2019-01-02"), 14, "clear");

        ValidationEtat etat = weatherCode.validation(utilisateur);

        assertThat(etat.isValider()).isFalse();
        assertThat(etat.getStatut()).isEqualTo(Statut.refuse);
        assertThat(etat.getCauses()).hasSize(2);
    }
}
