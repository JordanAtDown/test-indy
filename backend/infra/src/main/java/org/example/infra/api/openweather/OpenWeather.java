package org.example.infra.api.openweather;

import org.example.domain.promocode.meteo.ConditionMeteorologique;
import org.example.domain.promocode.meteo.Localisation;
import org.example.domain.promocode.spi.JournalMeteo;
import org.example.infra.Configuration.APIConfiguration;
import org.example.infra.api.openweather.ressources.MeteoResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class OpenWeather implements JournalMeteo {

    private static final String METRIC = "metric";
    private final String UNITE = "units";
    private final String LONGITUDE = "lon";
    private final String LATITUDE = "lat";
    private final String API_KEY = "appid";

    private final WebClient client;
    private final APIConfiguration api;

    @Override
    public ConditionMeteorologique findMeteoBy(Localisation localisation) {
        return client.method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path("/data/" + api.getVersion() + "/weather")
                        .queryParam(LATITUDE, localisation.latitude())
                        .queryParam(LONGITUDE, localisation.longitude())
                        .queryParam(UNITE, METRIC)
                        .queryParam(API_KEY, api.getKey())
                        .build()
                ).contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::isError, _ ->
                        Mono.just(new Exception("Impossible de récupérer les conditions météorologiques"))
                ).bodyToFlux(MeteoResponse.class)
                .map(MeteoResponse::toDomainObject)
                .blockFirst();
    }
}
