package com.finastra.users.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.finastra.users.dto.AccessTokenResponse;

@FeignClient(name = "azureClient", url = "${azure.host.uri}")
public interface TokenFeignClient {

    @PostMapping(value = "/"+"${azure.adb2c.tenant.id}"+"/oauth2/token")
    AccessTokenResponse getToken(@RequestBody MultiValueMap<String, Object> body
    );
}
