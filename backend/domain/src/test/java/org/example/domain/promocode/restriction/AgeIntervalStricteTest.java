package org.example.domain.promocode.restriction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.example.domain.promocode.Utilisateur;
import org.example.domain.promocode.validation.Cause;
import org.example.domain.promocode.validation.ValidationEtat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AgeIntervalStricteTest {

    @Test
    @DisplayName("Devrait être valide lorsque l'âge du client est strictement compris dans l'interval")
    void doitEtreValideQuandLAgeEstComprisDansUnIntervalStricte() {
        AgeIntervalStricte ageIntervalStricte = new AgeIntervalStricte(30, 18);
        Utilisateur utilisateur = new Utilisateur(20, LocalDate.parse("2019-01-02"), 24, "clear");

        ValidationEtat etat = ageIntervalStricte.valider(utilisateur, new ValidationEtat());

        assertThat(etat.isValide()).isTrue();
    }

    @Test
    @DisplayName("Devrait être invalide lorsque l'âge du client n'est pas compris dans l'interval")
    void doitEtreInvalideQuandLAgeNEstPasComprisDansUnIntervalStricte() {
        AgeIntervalStricte ageIntervalStricte = new AgeIntervalStricte(30, 18);
        Utilisateur utilisateur = new Utilisateur(40, LocalDate.parse("2019-01-02"), 24, "clear");

        ValidationEtat etat = ageIntervalStricte.valider(utilisateur, new ValidationEtat());

        assertThat(etat.isValide()).isFalse();
        assertThat(etat.getCauses())
                .extracting(Cause::message)
                .contains("L'âge n'est pas compris entre  18 ans et 30 ans");
    }

    @ParameterizedTest
    @DisplayName("Devrait lever une exception quand")
    @MethodSource("getArguments")
    void name(int borneInferieur, int borneSuperieur, String message) {
        assertThatThrownBy(() ->
                new AgeIntervalStricte(borneSuperieur, borneInferieur)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    private static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of(
                        15, 0,
                        "La borne inférieur doit être dans la limite de l'âge légale"
                ),
                Arguments.of(30, 10,
                        "La restriction d'âge interval stricte est incorrect"
                )
        );
    }
}
