# Test-Indy

### Required dependencies

Define your `JAVA_HOME` with `openjdk-22`.


#### Backend

Build:
```bash
cd backend
./mvnw clean install
```

Before launch:

1. Define API KEY [Application.yml](backend/infra/src/main/resources/application.yml)
2. Define **available port** on command line **Launch**

Launch:
```bash
cd backend/infra
../mvnw org.springframework.boot:spring-boot-maven-plugin:run -Dspring-boot.run.arguments="--server.port=AVAILABLE PORT"
```
or directly run the `ApplicationRun.java`

### Aller plus loin

* Pour la restriction [Meteo.java](backend/domain/src/main/java/org/example/domain/promocode/restriction/Meteo.java)
On pourrait définir une restriction **TemperatureStrictementSuperieur** pour encapsuler sont propre comportement.

* Pour les restrictions [Ou.java](backend/domain/src/main/java/org/example/domain/promocode/restriction/Ou.java)
et [Et.java](backend/domain/src/main/java/org/example/domain/promocode/restriction/Et.java)
pourrait être potentiellement factoriser pour n'avoir que la condition qui change

* Les tests d'intégrations sur l'infrastructure n'ont pas été réalisé par manque de temps

* Le cas ou OpenWeather n'est pas contactable n'a pas été traité. La restriction sur les condition météo est-elle vraiment importante, lorsque l'API n'est pas joignable ?

* J'ai pris la décision de directement prendre la longitude et latitude au lieu de la ville car l'API OpenWeather ne prend pas directement la ville en tant que paramètres. Il faut passer par un autre endpoint de l'API.

* Il y certainement une meilleur façon d'intégrer l'etat de validation et l'ajout des causes d'erreur des restriction que l'implémentation que j'ai choisi. Notamment car l'état de validation vient complexifier le code des restrictions.

* Les promocode pourrait contenir un **UUID** par exemple plutôt qu'un nom pour l'unicité.

### Algorithme

Les [Restrictions](backend/domain/src/main/java/org/example/domain/promocode/restriction) sont portée par une [interface](backend/domain/src/main/java/org/example/domain/promocode/restriction/Restriction.java) dont chacune des implémentation porte sa propre condition d'évaluation. [Une simple boucle](backend/domain/src/main/java/org/example/domain/promocode/Promocode.java) permet ensuite d'effectuer la **validation** de chaque restriction.

### Choix techniques

Aucun choix techniques en particulier, c'est une stack standard dans l'écosystème **Java**

### Testing features

Features 1 -> Promocode avec une restriction sur la météo :
```bash
curl -X POST -H "Content-Type: application/json" -d '{
  "nom": "WeatherCode",
  "avantage": { "pourcentage": 20 },
  "restrictions": [
    {
      "type": "dateIntervalStricte",
      "borneInferieur": "2024-01-01",
      "borneSuperieur": "2024-06-30"
    },
    {
      "type": "ou",
      "operateurGauche": {
        "type": "ageEgale",
        "age": 40
      },
      "operateurDroit": {
        "type": "et",
        "operateurGauche": {
          "type": "ageIntervalStricte",
          "borneInferieur": 18,
          "borneSuperieur": 30
        },
        "operateurDroit": {
          "type": "meteo",
          "condition": "Clouds",
          "temperature": 14
        }
      }
    }
  ]
}' -i http://localhost:8090/promocode
```

Features 1.1 -> Promocode sans la restriction sur la météo :
```bash
curl -X POST -H "Content-Type: application/json" -d '{
  "nom": "WithoutWeatherCode",
  "avantage": { "pourcentage": 20 },
  "restrictions": [
    {
      "type": "dateIntervalStricte",
      "borneInferieur": "2024-01-01",
      "borneSuperieur": "2024-09-30"
    },
    {
      "type": "ou",
      "operateurGauche": {
        "type": "ageEgale",
        "age": 40
      },
      "operateurDroit": {
        "type": "ageIntervalStricte",
        "borneInferieur": 18,
        "borneSuperieur": 30
      }
    }
  ]
}' -i http://localhost:8090/promocode
```

Features 2 -> Validation accepté pour le promocode WithoutWeatherCode :
```bash
curl -X POST -H "Content-Type: application/json" -d '{
  "nom": "WithoutWeatherCode",
  "age": 20,
  "localisation": {
    "longitude": 4.8048315,
    "latitude": 45.7785178
  }
}' -i http://localhost:8090/promocode/validation
```

Features 2.1 -> Validation refuse pour le promocode WithoutWeatherCode :
```bash
curl -X POST -H "Content-Type: application/json" -d '{
  "nom": "WithoutWeatherCode",
  "age": 50,
  "localisation": {
    "longitude": 4.8048315,
    "latitude": 45.7785178
  }
}' -i http://localhost:8090/promocode/validation
```
