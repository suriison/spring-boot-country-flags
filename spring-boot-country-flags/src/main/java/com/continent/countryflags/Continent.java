package com.continent.countryflags;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "continents")
public class Continent {
	
	@Id
	private String Id;
	
    private String continent;
    private List<Country> countries;

    public Continent() {
    }
    
    public Continent(String continent, List<Country> countries) {
        this.continent = continent;
        this.countries = countries;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "{\"continent\": \"" + continent + "\", \"countries\": " + Arrays.deepToString(countries.toArray()) + "} ";
    }

}
