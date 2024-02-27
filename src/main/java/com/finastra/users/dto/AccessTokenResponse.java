package com.finastra.users.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.catalina.connector.Response;


@Data
public class AccessTokenResponse  {
    private String token_type;
    private String expires_in;
    private String ext_expires_in;
    private String expires_on;
    private String access_token;
    private String refresh_token;
    private String id_token;

    // add getters and setters
}
