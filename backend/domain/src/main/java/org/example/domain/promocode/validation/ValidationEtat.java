package org.example.domain.promocode.validation;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ValidationEtat {

    private Statut statut = Statut.refuse;
    private boolean valider = false;
    private final List<Cause> causes = new ArrayList<>();

    public void valider() {
        statut = Statut.accepte;
        valider = true;
    }

    public void invaliderAvec(Cause cause) {
        statut = Statut.refuse;
        valider = false;
        causes.add(cause);
    }

}
