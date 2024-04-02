package org.example.domain.promocode.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValidationEtatTest {

    @Test
    @DisplayName("Devrait invalider l'état dès lorsqu'une invalidite est declaré")
    void doitInvaliderLEtatDesLorsQUneInvaliditeEstDeclare() {
        ValidationEtat etat = new ValidationEtat();
        Cause cause = mock(Cause.class);

        etat.invalideAvec(cause);
        etat.valide();

        assertThat(etat.isValide()).isFalse();
    }

    @Test
    @DisplayName("Devrait invalider l'état dès lorsqu'une invalidite est declaré")
    void doitInvaliderLEtatDesLorsQUneInvaliditeEstDeclare2() {
        ValidationEtat etat = new ValidationEtat();
        Cause cause = mock(Cause.class);

        etat.valide();
        etat.invalideAvec(cause);

        assertThat(etat.isValide()).isFalse();
    }

    @Test
    @DisplayName("Devrait valide l'état lorsque qu'aucune invalidite est déclaré")
    void doitValiderLEtatDesLorsQuAucuneInvaliditeEstDeclare() {
        ValidationEtat etat = new ValidationEtat();

        etat.valide();
        etat.valide();

        assertThat(etat.isValide()).isTrue();
    }
}
