package org.example.domain.promocode.stub;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.example.domain.promocode.Promocode;
import org.example.domain.promocode.spi.PromocodeRegistre;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryRegistre implements PromocodeRegistre {

    private final Map<String, Promocode> registre = new HashMap<>();

    @Override
    public void save(Promocode promocode) {
        registre.put(promocode.getNom().toLowerCase(), promocode);
    }

    @Override
    public Optional<Promocode> findPromocodeBy(String nom) {
        return Optional.ofNullable(registre.get(nom.toLowerCase()));
    }
}
