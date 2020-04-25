package com.embi.core.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class ExceptionAdvice {
    @Value("${drees.stacktrace}")
    boolean stackTrace;
   @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse<List<StackTraceElement>> processAllError(Exception ex) {
        List<StackTraceElement> ele = null;
        if (stackTrace == true) {
            ele = Arrays.asList(ex.getStackTrace());
        }
        ErrorResponse<List<StackTraceElement>> response = new ErrorResponse<>(ele, ex.getMessage());
        return response;
    }
}
