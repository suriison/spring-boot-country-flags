package com.continent.countryflags;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootCountryFlagsApplicationTests extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getAllCountries() throws Exception {
	    String uri = "/countries/flags";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	    		                 .accept(MediaType.APPLICATION_JSON_VALUE))
	                             .andReturn();
	    int status = mvcResult.getResponse().getStatus();
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    List<Country> countries = new Gson().fromJson(content, new TypeToken<ArrayList<Country>>(){}.getType());
	    assertTrue(countries.size() > 0);
	}

}
