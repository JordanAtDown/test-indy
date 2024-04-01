package org.example.domain.promocode.restriction;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.example.domain.promocode.Utilisateur;
import org.example.domain.promocode.validation.ValidationEtat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MeteoTest {

    @Test
    @DisplayName("Devrait valider la restriction quand les condition météo sont correcte et que la température est strictement supérieur")
    void doitValiderLaRestrictionQuandLaMeteoEstCorrectEtQueLaTemperatureEstSupérieur() {
        Meteo meteo = new Meteo("clear", 15);
        Utilisateur utilisateur = new Utilisateur(20, LocalDate.parse("2019-01-01"), 20, "clear");

        ValidationEtat etat = meteo.valider(utilisateur, new ValidationEtat());

        assertThat(etat.isValider()).isTrue();
    }

    @Test
    @DisplayName("Devrait invalider la restriction quand les conditions météo sont incorrecte mais que la température est strictement supérieur")
    void doitInvaliderLaRestrictionQuandLaMeteoEstIncorrectMaisQueLaTemperatureEstSupérieur() {
        Meteo meteo = new Meteo("clear", 15);
        Utilisateur utilisateur = new Utilisateur(20, LocalDate.parse("2019-01-01"), 20, "Snow");

        ValidationEtat etat = meteo.valider(utilisateur, new ValidationEtat());

        assertThat(etat.isValider()).isFalse();
    }

    @Test
    @DisplayName("Devrait invalider la restriction quand les conditions météo sont correcte mais que la température est strictement inférieur")
    void doitInvaliderLaRestrictionQuandLaMeteoEstCorrectMaisQueLaTemperatureEstInférieur() {
        Meteo meteo = new Meteo("clear", 15);
        Utilisateur utilisateur = new Utilisateur(20, LocalDate.parse("2019-01-01"), 14, "clear");

        ValidationEtat etat = meteo.valider(utilisateur, new ValidationEtat());

        assertThat(etat.isValider()).isFalse();
    }

    @Test
    @DisplayName("Devrait invalider la restriction quand les conditions météo sont correcte mais que la température est égal")
    void doitInvaliderLaRestrictionQuandLaMeteoEstCorrectMaisQueLaTemperatureEstEgal() {
        Meteo meteo = new Meteo("clear", 15);
        Utilisateur utilisateur = new Utilisateur(20, LocalDate.parse("2019-01-01"), 15, "clear");

        ValidationEtat etat = meteo.valider(utilisateur, new ValidationEtat());

        assertThat(etat.isValider()).isFalse();
    }
}
