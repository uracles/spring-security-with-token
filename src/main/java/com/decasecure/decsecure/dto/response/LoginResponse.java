package com.decasecure.decsecure.dto.response;

import lombok.Data;

@Data
public class LoginResponse {

    private String username;
    private String token;

    private final String tokenType = "Bearer";
}
