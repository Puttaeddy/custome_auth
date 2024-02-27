package com.finastra.users.service;


import com.finastra.users.dto.AccessTokenResponse;

public interface LoginService {

    public String login();
    public AccessTokenResponse getToken(String code, String state);
    
	public AccessTokenResponse getRefreshToken(String accessToken);
}
