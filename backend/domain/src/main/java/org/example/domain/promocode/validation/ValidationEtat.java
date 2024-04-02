package org.example.domain.promocode.validation;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor

public class ValidationEtat {
    @Getter
    private Statut statut = Statut.refuse;
    @Getter
    private boolean valide = false;
    @Getter
    private final List<Cause> causes = new ArrayList<>();
    private boolean finalise = false;

    public void valide() {
        if (!finalise) {
            statut = Statut.accepte;
            valide = true;
        }
    }

    public void invalideAvec(Cause cause) {
        statut = Statut.refuse;
        valide = false;
        finalise = true;
        causes.add(cause);
    }

}
