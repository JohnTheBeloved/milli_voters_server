package com.embi.core.controller;
import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String code;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String code, String message, String details) {
         super();
         this.timestamp = timestamp;
         this.code = code;
         this.message = message;
         this.details = details;
    }

    public Date getTimestamp() {
         return timestamp;
    }

    public String getCode() {
     return code;
     }
     public String getMessage() {
          return message;
     }

    public String getDetails() {
         return details;
    }
}