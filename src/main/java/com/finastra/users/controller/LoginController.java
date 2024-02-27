package com.finastra.users.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finastra.users.dto.AccessTokenResponse;
import com.finastra.users.service.LoginService;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class LoginController {
    private final LoginService loginService;
    @GetMapping("/login")
    public ResponseEntity<Object> login() {
        String redirectUri=loginService.login();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, redirectUri);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
    @GetMapping("/token")
    public ResponseEntity<AccessTokenResponse> getToken(@RequestParam("code") String code, @RequestParam("state") String state) {
        AccessTokenResponse accessTokenResponse=loginService.getToken(code,state);
        return new ResponseEntity<>(accessTokenResponse, HttpStatus.OK);
    }
    
    
    @PostMapping("/refresh-token")
    public ResponseEntity<AccessTokenResponse> getRefreshToken(
            @RequestHeader("authorization") String accessToken){
    	AccessTokenResponse reFreshTokenResponse=loginService.getRefreshToken(accessToken);
         return new ResponseEntity<>(reFreshTokenResponse, HttpStatus.OK);

    }
}
