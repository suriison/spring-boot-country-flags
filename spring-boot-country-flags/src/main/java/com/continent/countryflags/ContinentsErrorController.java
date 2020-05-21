package com.continent.countryflags;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Fallback Custom error handler for Whitelabel and any other errors
 *
 */

@RestController
public class ContinentsErrorController implements ErrorController  {
 
	public ContinentsErrorController() {}
    
	@RequestMapping("/error")  
    @ResponseBody  
    public String handleError(HttpServletRequest request) {  
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");  
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");  
        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"  
             + "<div>Exception Message: <b>%s</b></div><body></html>",  
        statusCode, exception==null? "N/A": exception.getMessage());  
    }
    
   @Override
    public String getErrorPath() {
        return "/error";
    }
   
}