package com.continent.util;

import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import com.continent.countryflags.Error;  
import com.google.gson.Gson;  

public class Util {  
    public static HashMap<String,String> errorMap = new HashMap<String,String>();  
    public static String generateErrorResponse(String serviceId,String errorCode,String descrption) {  
        List<Error> errorList = new ArrayList<Error>();  
        Error oError = new Error();  
        oError.setCode(errorCode);  
        oError.setDescription(descrption);  
        errorList.add(oError);  
        Gson gson = new Gson();  
     String response = gson.toJson(errorList);  
     return response;  
    }  
}