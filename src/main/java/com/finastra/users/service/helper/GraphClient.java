package com.finastra.users.service.helper;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.finastra.users.dto.UserDto;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.models.PasswordProfile;
import com.microsoft.graph.models.User;
import com.microsoft.graph.requests.GraphServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
public class GraphClient {
    @Value("${azure.adb2c.application.id}")
    private String clientId;

    @Value("${azure.adb2c.client.secret}")
    private String clientSecret;

    @Value("${azure.adb2c.tenant.id}")
    private String tenantId;

    @Value("${azure.adb2c.tenant.name}")
    private String tenantName;

    @Value("${microsoft.graph.scope}")
    private String scope;
    public GraphServiceClient getGraphServiceClient() {
        final ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
                .clientId(clientId)
                .clientSecret(clientSecret) //required for web apps, do not set for native apps
                .tenantId(tenantId)
                .build();

        final TokenCredentialAuthProvider tokenCredentialAuthProvider = new TokenCredentialAuthProvider(Arrays.asList(scope), clientSecretCredential);

        GraphServiceClient graphClient = GraphServiceClient.builder()
                .authenticationProvider(tokenCredentialAuthProvider)
                .buildClient();
        return graphClient;
    }

    public User createAzureRequest(UserDto userDto) {
        User user = new User();
        user.accountEnabled = true;
        user.displayName = userDto.getFirstName();
        user.mailNickname = userDto.getLastName();
        user.userPrincipalName = userDto.getUserName() + "@" + tenantName + ".onmicrosoft.com";
        user.passwordPolicies = "DisablePasswordExpiration";
        PasswordProfile passwordProfile = new PasswordProfile();
        passwordProfile.forceChangePasswordNextSignIn = false;
        passwordProfile.password = userDto.getPassword(); //"xWwvJ]6NMw+bWH-d";
        user.passwordProfile = passwordProfile;
        return user;
    }
}
