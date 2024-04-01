package org.example.domain.promocode.spi;

import org.example.domain.promocode.meteo.Localisation;
import org.example.domain.promocode.meteo.ConditionMeteorologique;

public interface JournalMeteo {
    ConditionMeteorologique findMeteoBy(Localisation localisation);
}
