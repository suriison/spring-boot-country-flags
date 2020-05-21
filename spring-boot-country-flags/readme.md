# Spring Boot Service to fetch Continent, Country Flags from MongoDB 

Simple REST Service to fetch Continent, Country Flag json mapping using endpoint "/countries/flags" URI

# Prometheus for metrics

The configuration for Metrics configuration is available in "/src/main/java/com/continent/countryflags/MonitoringConfig.java" class:

## Setup required

Install `Java 8`
Install and setup `maven`
Make sure `curl` is available or use a rest api tool such as `postman`

Continents, countries and flags are fetched from the MongoDB. This json mapping information is also available from below file
[continents.json] ("/src/main/resources/continents.json")

### Metrics Configuration :

`Prometheus` is used for Metrics gathering and same can be setup as optional.

## MongoDB Schema (using existing dump for database and Collections Import)
```
mongorestore -d <db_name> -c <collection_name> "/scripts/db/mongodb/Continents/continents.bson"
```

Mongodb restore command example
```mongorestore -d Continents -c continents ./Continents/continents.bson```

```2020-05-21T03:11:32.293-0700	checking for collection data in Continents/continents.bson```

2020-05-21T03:11:32.295-0700	reading metadata for tempCont.tempcont from Continents/continents.metadata.json
2020-05-21T03:11:32.325-0700	restoring tempCont.tempcont from Continents/continents.bson
2020-05-21T03:11:32.386-0700	no indexes to restore
2020-05-21T03:11:32.387-0700	finished restoring tempCont.tempcont (5 documents)
2020-05-21T03:11:32.387-0700	done
```

NOTE: The above DB setup is mandatory before running the Service, without which the Continent, Country Flag informations cannot be viewed in the client browser

## Build
```
mvn clean package
```

## Run
```
mvn spring-boot:run
```

## Test
```
mvn test
```
## Usage
```
curl -X GET http://localhost:8080/countries/flags
```

```
curl -X GET http://localhost:8080/countries/flags?continent=America
```

```
curl -X GET http://localhost:8080/countries/flags?country=Nigeria
```

## Metrics URL
```
curl localhost:8080/metrics
```

There are 3 functional metrics added 

### Counter for requests that try to get all countries
`country_flags_all_countries_requests_total`

### Counter for requests that try to filter by continent. 
`country_flags_by_continent_requests_total`
Continent name is added as a label and can be used to query using [promql](https://prometheus.io/docs/prometheus/latest/querying/basics/) 


### Counter for requests that try to filter by country.
`country_flags_by_country_requests_total`
Country name is added as a label and can be used to query using [promql](https://prometheus.io/docs/prometheus/latest/querying/basics/) 

## Audit logging 
Below is log snippet for Interceptor logs

2020-05-21 02:00:04,192 INFO com.continent.countryflags.InterceptorTrace [http-nio-8080-exec-2] Request URL::http://localhost:8080/countries/flags:: Time Taken=20
2020-05-21 02:00:07,065 INFO com.continent.countryflags.InterceptorTrace [http-nio-8080-exec-3] Request URL: http://localhost:8080/error, Query String: null
2020-05-21 02:00:07,073 INFO com.continent.countryflags.InterceptorTrace [http-nio-8080-exec-3] Request URL::http://localhost:8080/error Sent to Handler :: Current Time=1590051607073
2020-05-21 02:00:07,074 INFO com.continent.countryflags.InterceptorTrace [http-nio-8080-exec-3] Request URL::http://localhost:8080/error:: Time Taken=9
2020-05-21 02:11:36,527 INFO com.continent.countryflags.InterceptorTrace [main] Request URL: http://localhost/countries/flags, Query String: null
2020-05-21 02:11:36,618 INFO com.continent.countryflags.InterceptorTrace [main] Request URL::http://localhost/countries/flags Sent to Handler :: Current Time=1590052296618
2020-05-21 02:11:36,619 INFO com.continent.countryflags.InterceptorTrace [main] Request URL::http://localhost/countries/flags:: Time Taken=92
2020-05-21 02:12:09,553 INFO com.continent.countryflags.InterceptorTrace [main] Request URL: http://localhost/countries/flags, Query String: null
2020-05-21 02:12:09,663 INFO com.continent.countryflags.InterceptorTrace [main] Request URL::http://localhost/countries/flags Sent to Handler :: Current Time=1590052329663
2020-05-21 02:12:09,663 INFO com.continent.countryflags.InterceptorTrace [main] Request URL::http://localhost/countries/flags:: Time Taken=110


## Swagger JavaDoc for RESTful API documentation
Swagger JavaDoc is configured to provide documentation on RESTful API endpoint and Error handling.

`http://localhost:8080/swagger-ui.html` URL is used to run swagger for Documentation