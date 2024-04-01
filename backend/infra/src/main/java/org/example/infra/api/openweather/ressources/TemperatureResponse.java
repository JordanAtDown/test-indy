package org.example.infra.api.openweather.ressources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonRootName("main")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemperatureResponse {
    @JsonProperty("temp")
    private int temperature;
}
