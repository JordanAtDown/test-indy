package org.example.domain.promocode;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.example.domain.promocode.meteo.ConditionMeteorologique;
import org.example.domain.promocode.meteo.Localisation;
import org.example.domain.promocode.restriction.AgeEgale;
import org.example.domain.promocode.restriction.AgeIntervalStricte;
import org.example.domain.promocode.restriction.DateInterval;
import org.example.domain.promocode.restriction.Et;
import org.example.domain.promocode.restriction.Meteo;
import org.example.domain.promocode.restriction.Ou;
import org.example.domain.promocode.spi.JournalMeteo;
import org.example.domain.promocode.spi.PromocodeRegistre;
import org.example.domain.promocode.validation.Invalide;
import org.example.domain.promocode.validation.PromocodeEtat;
import org.example.domain.promocode.validation.Statut;
import org.example.domain.promocode.validation.Valide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PromocodeGestionnaireTest {

    @Mock
    private JournalMeteo journalMeteo;
    @Mock
    private PromocodeRegistre registre;
    @Mock
    private Clock clock;

    @InjectMocks
    private PromocodeGestionnaire promocodeGestionnaire;

    @Test
    @DisplayName("Devrait lever une exception quand le promocode n'existe pas")
    void doitLeverUneExeceptionQuandLePromocodeNExistePas() {
        given(registre.findPromocodeBy("WeatherCode")).willReturn(Optional.empty());
        PromocodeAValider weatherCode = new PromocodeAValider("WeatherCode", 20, new Localisation(2.475634833088, 2.475634833088));

        assertThatThrownBy(() -> promocodeGestionnaire.valider(weatherCode)).isInstanceOf(NoSuchElementException.class).hasMessage("le promocode WeatherCode est inconnu");
    }

    @Test
    @DisplayName("Devrait pouvoir recuperer l'avantage du promocode lorsqu'il est valide")
    void doitRecupererAvantagePromocodeQuandIlEstValide() {
        // GIVEN
        Localisation localisation = new Localisation(2.475634833088, 2.475634833088);
        PromocodeAValider weatherCode = new PromocodeAValider("WeatherCode", 20, localisation);

        DateInterval dateInterval = new DateInterval(LocalDate.parse("2019-01-01"), LocalDate.parse("2020-06-30"));
        AgeEgale ageEgale = new AgeEgale(40);
        Meteo meteo = new Meteo("clear", 15);
        AgeIntervalStricte operateurGauche = new AgeIntervalStricte(30, 18);
        Et et = new Et(operateurGauche, meteo);
        Ou ou = new Ou(ageEgale, et);
        Promocode promocode = new Promocode("WeatherCode", 20, asList(dateInterval, ou));

        given(clock.instant()).willReturn(Instant.parse("2019-01-02T12:15:00Z"));
        given(clock.getZone()).willReturn(ZoneId.of("UCT"));
        given(registre.findPromocodeBy("WeatherCode")).willReturn(Optional.of(promocode));
        given(journalMeteo.findMeteoBy(localisation)).willReturn(new ConditionMeteorologique(16, "clear"));

        // WHEN
        PromocodeEtat promocodeEtat = promocodeGestionnaire.valider(weatherCode);

        // THEN
        assertThat(promocodeEtat).isInstanceOfSatisfying(Valide.class, valide -> {
            assertThat(valide.getNom()).isEqualTo("WeatherCode");
            assertThat(valide.getStatut()).isEqualTo(Statut.accepte);
            assertThat(valide.getAvantage().pourcentage()).isEqualTo(20);
        });
    }

    @Test
    @DisplayName("Devrait pouvoir recuperer les causes d'erreur de la validation lorsque le promocode est invalide")
    void doitRecupererLesCausesDeLErreurQuandLePromocodeEstInvalide() {
        // GIVEN
        Localisation localisation = new Localisation(2.475634833088, 2.475634833088);
        PromocodeAValider weatherCode = new PromocodeAValider("WeatherCode", 20, localisation);

        DateInterval dateInterval = new DateInterval(LocalDate.parse("2019-01-01"), LocalDate.parse("2020-06-30"));
        Promocode promocode = new Promocode("WeatherCode", 20, List.of(dateInterval));

        given(clock.instant()).willReturn(Instant.parse("2018-01-02T12:15:00Z"));
        given(clock.getZone()).willReturn(ZoneId.of("UCT"));
        given(registre.findPromocodeBy("WeatherCode")).willReturn(Optional.of(promocode));
        given(journalMeteo.findMeteoBy(localisation)).willReturn(new ConditionMeteorologique(14, "clear"));

        // WHEN
        PromocodeEtat promocodeEtat = promocodeGestionnaire.valider(weatherCode);

        // THEN
        assertThat(promocodeEtat).isInstanceOfSatisfying(Invalide.class, invalide -> {
            assertThat(invalide.getNom()).isEqualTo("WeatherCode");
            assertThat(invalide.getStatut()).isEqualTo(Statut.refuse);
        });
    }
}
