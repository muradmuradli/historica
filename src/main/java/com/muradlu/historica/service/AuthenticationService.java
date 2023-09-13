package com.muradlu.historica.service;

import com.muradlu.historica.auth.AuthenticationRequest;
import com.muradlu.historica.auth.AuthenticationResponse;
import com.muradlu.historica.auth.RegisterRequest;
import org.springframework.stereotype.Service;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
