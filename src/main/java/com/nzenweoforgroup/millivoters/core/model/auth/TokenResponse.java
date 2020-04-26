package com.nzenweoforgroup.millivoters.core.model.auth;

import lombok.Data;

@Data
public class TokenResponse {

    private String accessToken;
    private String tokenType;
    private String jti;
    private String expriresAt;


}