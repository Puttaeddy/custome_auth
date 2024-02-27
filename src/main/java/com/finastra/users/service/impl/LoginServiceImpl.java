package com.finastra.users.service.impl;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.finastra.users.dto.AccessTokenResponse;
import com.finastra.users.feign.TokenFeignClient;
import com.finastra.users.service.LoginService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    @Value("${azure.adb2c.application.id}")
    private String clientId;

    @Value("${azure.host.uri}")
    private String azureHost;

    @Value("${azure.adb2c.client.secret}")
    private String clientSecret;

    @Value("${azure.adb2c.redirect.uri}")
    private String redirectUri;

    @Value("${azure.adb2c.tenant.id}")
    private String tenantId;

    private final TokenFeignClient tokenFeignClient;

    @Override
    public String login() {
        return azureHost + "/" + tenantId + "/oauth2/authorize?" +
                "client_id=" + clientId +
                "&redirect_uri=" + redirectUri +
                "&scope=openid profile email phone address &response_type=code" +
                "&state=" + getRandomNumber();
    }

    @Override
    public AccessTokenResponse getToken(String code, String state) {
        MultiValueMap<String, Object> tokenRequestMap = createTokenRequestMap("authorization_code");
        tokenRequestMap.set("code", code);
        return tokenFeignClient.getToken(tokenRequestMap);
    }

    @Override
    public AccessTokenResponse getRefreshToken(String accessToken) {
        // get refresh token from database using access token
        String reFreshToken = accessToken.substring(7);
        MultiValueMap<String, Object> refreshTokenMap = createTokenRequestMap("refresh_token");
        refreshTokenMap.set("refresh_token", reFreshToken);
        return tokenFeignClient.getToken(refreshTokenMap);

    }

    @NotNull
    private MultiValueMap<String, Object> createTokenRequestMap(String grantType) {
        MultiValueMap<String, Object> tokenRequestMap = new LinkedMultiValueMap<>();
        tokenRequestMap.set("grant_type", grantType);
        tokenRequestMap.set("client_id", clientId);
        tokenRequestMap.set("client_secret", clientSecret);
        tokenRequestMap.set("redirect_uri", redirectUri);
        return tokenRequestMap;
    }

    private long getRandomNumber() {
        long min = 10000000000L;
        long max = 90000000000L;
        return (long) (Math.random() * (max - min + 1) + min);
    }
}
