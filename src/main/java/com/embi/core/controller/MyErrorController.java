package com.embi.core.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyErrorController implements ErrorController  {
 
    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request, Exception ex) {
        //logger.error("Request: " + request.getRequestURL() + " raised " + ex);

        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String message = "We are being notified of the error, Please try again after some time";
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
         
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                message = "Sorry, The Page you requested was not found, Please maybe you check, It is likely you mistyped it. ";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                message = "We're unable to fulfill your request. Rest assured we have been notified and are looking into the issue. Please refresh your browser, but if the error continues, please contact our support team.";
            }
        }

        ModelAndView mav = new ModelAndView();
        String code = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();
        ErrorDetails errorDetails = new ErrorDetails(new Date(), code, message, exceptionAsString);
        mav.addObject("error", errorDetails);
        mav.addObject("exception", ex);
        mav.addObject("message", message);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");
        return mav;
        
    }
 
    @Override
    public String getErrorPath() {
        return "/error";
    }
}