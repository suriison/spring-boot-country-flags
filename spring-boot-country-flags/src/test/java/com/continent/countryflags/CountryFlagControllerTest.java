package com.continent.countryflags;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CountryFlagControllerTest {
	CountryFlagController controller = new CountryFlagController();
    List<Continent> continents;
    
    @Before
    public void loadContinentsFromJSONFile() throws FileNotFoundException, IOException {
    	
        if(continents == null) {
        	
        	File file = new File("src/main/resources/test-continents.json");
        	Scanner scanner = new Scanner(file);
            String json = scanner.useDelimiter("\\Z").next();
            continents = new Gson().fromJson(json, new TypeToken<ArrayList<Continent>>(){}.getType());

            scanner.close();
        }

    }
    
    @Before
    public void setUp() 
    		throws FileNotFoundException,
    IOException, ParseException {
        // Load sample continents, country json file for test validation
    	loadContinentsFromJSONFile();

    }

    @Test
	public void testGetAllCountries() {
        System.out.println("testGetAllCountries");
        List<Country> countries = controller.getAllCountries(continents);
        
        assertEquals(4, countries.size());
        assertEquals("Nigeria", countries.get(0).getName());
        assertEquals("🇪🇹", countries.get(1).getFlag());
	}

    @Test
	public void testFilterCountriesByContinent() {
        System.out.println("testFilterCountriesByContinent");
        List<Country> countries = controller.filterCountriesByContinent(continents, "America");
        
        assertEquals(2, countries.size());
        assertEquals("USA", countries.get(0).getName());
        assertEquals("🇺🇸", countries.get(0).getFlag());
	}
    
    @Test
	public void testFilterCountriesByName() {
        System.out.println("testFilterCountriesByName");
        List<Country> countries = controller.filterCountriesByName(continents, "Brazil");
        
        assertEquals(1, countries.size());
        assertEquals("Brazil", countries.get(0).getName());
        assertEquals("🇧🇷", countries.get(0).getFlag());
	}

    @Test
	public void testFilterCountriesByNameNotExistent() {
        System.out.println("testFilterCountriesByNameNotExistent");
        List<Country> countries = controller.filterCountriesByName(continents, "India");
        
        assertEquals(0, countries.size());
	}

    @Test
	public void testFilterCountriesByContinentNotExistent() {
        System.out.println("testFilterCountriesByContinentNotExistent");
        List<Country> countries = controller.filterCountriesByContinent(continents, "Asia");
        
        assertEquals(0, countries.size());
	}
}