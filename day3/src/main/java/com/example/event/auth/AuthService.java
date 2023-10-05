package com.example.event.auth;

import org.springframework.stereotype.Service;

import com.example.event.dto.request.RegisterRequest;

@Service
public interface AuthService {
	
    boolean userRegistration(RegisterRequest request);

    AuthenticationResponse userAuthentication(AuthenticationRequest request);
}