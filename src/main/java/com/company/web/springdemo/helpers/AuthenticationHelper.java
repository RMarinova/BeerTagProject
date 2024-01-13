package com.company.web.springdemo.helpers;

import com.company.web.springdemo.exceptions.AuthorizationException;
import com.company.web.springdemo.exceptions.EntityNotFoundException;
import com.company.web.springdemo.models.User;
import com.company.web.springdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class AuthenticationHelper {
    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private final UserService service;

    @Autowired
    public AuthenticationHelper(UserService service) {
        this.service = service;
    }

    public User tryGetUser(HttpHeaders headers) {
        if (!headers.containsKey(AUTHORIZATION_HEADER_NAME)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "The request resource requires authentication.");
        }
        try {
            String authorizationHeader = headers.getFirst(AUTHORIZATION_HEADER_NAME);
            String username = getUsername(authorizationHeader);
            String password = getPassword(authorizationHeader);

            User user = service.get(username);
            if (!user.getPassword().equals(password)) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid authentication");
            }

            return user;
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid authentication");
        }
    }

    private String getUsername(String authorizationHeader) {
        int firstSpaceIndex = authorizationHeader.indexOf(" ");
        if (firstSpaceIndex == -1) {
            throw new AuthorizationException("Invalid authentication");
        }
        return authorizationHeader.substring(0, firstSpaceIndex);
    }

    private String getPassword(String authorizationHeader) {
        int firstSpaceIndex = authorizationHeader.indexOf(" ");
        if (firstSpaceIndex == -1) {
            throw new AuthorizationException("Invalid authentication");
        }
        return authorizationHeader.substring(firstSpaceIndex + 1);
    }
}
