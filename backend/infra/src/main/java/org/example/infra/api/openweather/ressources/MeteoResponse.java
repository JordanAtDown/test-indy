package org.example.infra.api.openweather.ressources;

import java.util.List;

import org.example.domain.promocode.meteo.ConditionMeteorologique;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeteoResponse {
    @JsonProperty("weather")
    private List<ConditionMeteoResponse> conditionMeteoResponse;
    @JsonProperty("main")
    private TemperatureResponse temperatureResponse;

    public ConditionMeteorologique toDomainObject() {
        return new ConditionMeteorologique(temperatureResponse.getTemperature(), conditionMeteoResponse.getFirst().getCondition());
    }
}

