package com.codeBeaters.event.service;

import com.codeBeaters.event.dto.request.AuthenticationRequest;
import com.codeBeaters.event.dto.request.RegisterRequest;
import com.codeBeaters.event.dto.response.AuthenticationResponse;

public interface AuthService {
    boolean userRegistration(RegisterRequest request);

    AuthenticationResponse userAuthentication(AuthenticationRequest request);
}
