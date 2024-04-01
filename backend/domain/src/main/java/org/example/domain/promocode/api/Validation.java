package org.example.domain.promocode.api;

import org.example.domain.promocode.PromocodeAValider;
import org.example.domain.promocode.validation.PromocodeEtat;

public interface Validation {
    PromocodeEtat valider(PromocodeAValider promocodeAValider);
}
