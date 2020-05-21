package com.continent.countryflags;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

/**
 * A generic services using Spring Boot that allows the user to pick different world flags based on the Continent Collections from MongoDB 
 * A REST endpoint "/countries/flags" would list continents and corresponding countries and their flag. 
 * If a continent is filtered in the URI, then list of countries and their flags are returned. 
 *     For Eg: URI requested like "countries/flags?continent=America" would send corresponding countries and their flags. 
 * If country is filtered in the URI, then return country and its flag. 
 *     For Eg: URI requested like "countries/flags?country=Nigeria" would send Nigeria with flag.  
 */

@RestController
class CountryFlagController{
	
	private static final Logger logger = LoggerFactory.getLogger(CountryFlagController.class);

    CountryFlagController() {

    }

    @Value("classpath:continents.json")
    private Resource continentsJson;
    
    @Autowired
    private IContinentsService continentService;
    
   
    List<Continent> continents;

    @RequestMapping(value = CountriesRestURIConstants.COUNTRIES_FLAG, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    String getCountriesFlags(
        @RequestParam(required = false) String country,
        @RequestParam(required = false) String continent
    ) throws FileNotFoundException,
    IOException, ParseException {

      
        continents = continentService.findAll();
        if ( continents == null) {
            logger.info("Data Load from Continent Service: null ");
        }
        else {
        	logger.info("Data Load from Continent Service: " + continents.size());
        }
        
        
        List<Country> countries = null;

        // Based on query parameter, fetch appropriate list
        if(country != null) {
            countries = filterCountriesByName(continents, country);
            MonitoringConfig.countryRequests.labels(country).inc();
        } else if(continent != null) {
            countries = filterCountriesByContinent(continents, continent);
            MonitoringConfig.continentRequests.labels(continent).inc();
        } else {
            countries = getAllCountries(continents);
            MonitoringConfig.allCountriesRequests.inc();
        }

        Gson gson = new Gson();
        String response = gson.toJson(countries);

        
        return response;
    }

    public List<Country> filterCountriesByName(List<Continent> continents, String countryName) {
        List<Country> countries = new ArrayList<Country>();

        for (Continent continent: continents) {
            List<Country> filteredCountries = continent.getCountries().stream().filter(
                country -> country.getName().equalsIgnoreCase(countryName)).collect(Collectors.toList());

            if(filteredCountries != null && !filteredCountries.isEmpty()) {
                countries = filteredCountries;
                break;
            }
        }
        return countries;
    }

    public List<Country> filterCountriesByContinent(List<Continent> continents, String continentName) {
        List<Country> countries = new ArrayList<Country>();

        List<Continent> filteredContinent = continents.stream().filter(
            continent -> continent.getContinent().equalsIgnoreCase(continentName)).collect(Collectors.toList());

        if(filteredContinent != null && !filteredContinent.isEmpty()) {
            countries = filteredContinent.get(0).getCountries();
        }
        return countries;
    }

    public List<Country> getAllCountries(List<Continent> continents) {
        List<Country> countries = new ArrayList<Country>();
        for (Continent continent: continents) {
            countries.addAll(continent.getCountries());
        }
        return countries;
    }
     
}
