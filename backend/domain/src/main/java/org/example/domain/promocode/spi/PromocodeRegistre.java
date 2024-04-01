package org.example.domain.promocode.spi;

import java.util.Optional;

import org.example.domain.promocode.Promocode;

public interface PromocodeRegistre {
    void save(Promocode promocode);
    Optional<Promocode> findPromocodeBy(String nom);
}
