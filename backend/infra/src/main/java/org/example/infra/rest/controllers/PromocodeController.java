package org.example.infra.rest.controllers;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.NoSuchElementException;

import org.example.domain.promocode.api.Validation;
import org.example.domain.promocode.spi.PromocodeRegistre;
import org.example.infra.rest.controllers.ressources.promocode.Promocode;
import org.example.infra.rest.controllers.ressources.promocode.validation.PromocodeEtat;
import org.example.infra.rest.controllers.ressources.promocode.validation.PromocodeAValider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/promocode")
@RequiredArgsConstructor
public class PromocodeController {

    private final Validation validation;
    private final PromocodeRegistre registre;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void creeationDUn(@RequestBody Promocode promocode) {
        try {
            registre.save(promocode.toDomainObject());
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(BAD_REQUEST, exception.getMessage());
        }
    }

    @PostMapping("/validation")
    public ResponseEntity<PromocodeEtat> validation(@RequestBody PromocodeAValider promocodeAValider) {
        try {
            return PromocodeEtat.toRessource(validation.valider(promocodeAValider.toDomainObject()))
                    .map(promocodeEtat -> new ResponseEntity<>(
                            promocodeEtat,
                            HttpStatus.OK
                    )).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(BAD_REQUEST, exception.getMessage());
        }
    }

}
