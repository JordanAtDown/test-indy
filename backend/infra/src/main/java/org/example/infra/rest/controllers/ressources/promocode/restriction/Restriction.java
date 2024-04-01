package org.example.infra.rest.controllers.ressources.promocode.restriction;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DateIntervalStricte.class, name = "dateIntervalStricte"),
        @JsonSubTypes.Type(value = AgeEgale.class, name = "ageEgale"),
        @JsonSubTypes.Type(value = AgeIntervalStricte.class, name = "ageIntervalStricte"),
        @JsonSubTypes.Type(value = Et.class, name = "et"),
        @JsonSubTypes.Type(value = Ou.class, name = "ou"),
        @JsonSubTypes.Type(value = Meteo.class, name = "meteo")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Restriction {
    private String type;

    public abstract org.example.domain.promocode.restriction.Restriction toDomainObject();
}
