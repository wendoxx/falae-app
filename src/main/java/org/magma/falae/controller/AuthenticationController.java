package org.magma.falae.controller;

import org.magma.falae.dto.request.LoginRequestDTO;
import org.magma.falae.dto.request.RegisterRequestDTO;
import org.magma.falae.dto.response.LoginResponseDTO;
import org.magma.falae.dto.response.RegisterResponseDTO;
import org.magma.falae.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.status(201).body(authenticationService.login(loginRequestDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.status(201).body(authenticationService.register(registerRequestDTO));
    }
}
