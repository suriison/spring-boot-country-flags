package com.continent.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;

import com.continent.countryflags.Continent;
import com.continent.countryflags.Error;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;  

public class Util {  
    
    public static HashMap<String,String> errorMap = new HashMap<String,String>();  
    public static List<Error>  generateErrorResponse(String serviceId,String errorCode,String descrption) {  
        List<Error> errorList = new ArrayList<Error>();  
        Error oError = new Error();  
        oError.setCode(serviceId+"-"+errorCode);  
        oError.setDescription(descrption);  
        errorList.add(oError);  
       
        return errorList;  
    }  
   
    public static List<Continent> loadContinentsFromJSONFile(File file) throws FileNotFoundException, IOException {
    	List<Continent> continents = null;
        if(continents == null) {
        	
        	Scanner scanner = new Scanner(file);
            String json = scanner.useDelimiter("\\Z").next();
            continents = new Gson().fromJson(json, new TypeToken<ArrayList<Continent>>(){}.getType());

            scanner.close();
        }
        return continents;
    }
    
}