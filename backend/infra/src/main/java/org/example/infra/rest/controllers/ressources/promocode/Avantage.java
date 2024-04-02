package org.example.infra.rest.controllers.ressources.promocode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Avantage {
    private int pourcentage;

    public static Avantage toRessource(org.example.domain.promocode.Avantage avantage) {
        return new Avantage(avantage.pourcentage());
    }
}
