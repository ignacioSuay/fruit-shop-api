package com.ignaciosuay.fruitshopapi.rest.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private long timestamp;
    private int status;
    private String error;
    private String exception;
    private String message;
    private String path;

}

