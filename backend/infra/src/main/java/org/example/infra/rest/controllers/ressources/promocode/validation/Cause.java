package org.example.infra.rest.controllers.ressources.promocode.validation;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cause {
    private String message;

    public static List<Cause> toRessource(List<org.example.domain.promocode.validation.Cause> causes) {
        return causes.stream()
                .map(cause -> new Cause(cause.message()))
                .toList();
    }
}
