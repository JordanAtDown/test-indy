package org.example.domain.promocode;

import java.time.Clock;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.example.domain.promocode.api.Validation;
import org.example.domain.promocode.meteo.ConditionMeteorologique;
import org.example.domain.promocode.spi.PromocodeRegistre;
import org.example.domain.promocode.spi.JournalMeteo;
import org.example.domain.promocode.validation.Invalide;
import org.example.domain.promocode.validation.PromocodeEtat;
import org.example.domain.promocode.validation.ValidationEtat;
import org.example.domain.promocode.validation.Valide;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PromocodeGestionnaire implements Validation {

    private final JournalMeteo journalMeteo;
    private final PromocodeRegistre registre;
    private final Clock clock;

    public PromocodeEtat valider(PromocodeAValider promocodeAValider) {
        Optional<Promocode> optionalPromocode = registre.findPromocodeBy(promocodeAValider.getNom());

        if (optionalPromocode.isPresent()) {
            ConditionMeteorologique conditionMeteorologique = journalMeteo.findMeteoBy(promocodeAValider.getLocalisation());

            ValidationEtat validationEtat = optionalPromocode.get().validation(
                    new Utilisateur(
                            promocodeAValider.getAge(),
                            LocalDate.now(clock),
                            conditionMeteorologique.temperature(),
                            conditionMeteorologique.condition()
                    )
            );
            return toPromocodeEtat(validationEtat, optionalPromocode.get());
        } else {
            throw new NoSuchElementException(String.format("le promocode %s est inconnu", promocodeAValider.getNom()));
        }

    }

    private PromocodeEtat toPromocodeEtat(ValidationEtat validationEtat, Promocode promocode) {
        if (validationEtat.isValide()) {
            return new Valide(promocode.getNom(), validationEtat.getStatut(), promocode.getAvantage());
        } else {
            return new Invalide(promocode.getNom(), validationEtat.getStatut(), validationEtat.getCauses());
        }

    }

}
