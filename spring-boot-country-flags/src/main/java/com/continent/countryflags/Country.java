package com.continent.countryflags;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Country {
	@Id
	private String Id;
    private String name;
    private String flag;

    public Country() {
    }
    
    public Country(String name, String flag) {
        this.name = name;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "{" + "\"name\": \"" + name + "\", \"flag\": \"" + flag + "\"} ";
    }
}
