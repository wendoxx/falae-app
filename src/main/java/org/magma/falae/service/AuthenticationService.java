package org.magma.falae.service;

import org.magma.falae.dto.request.LoginRequestDTO;
import org.magma.falae.dto.request.RegisterRequestDTO;
import org.magma.falae.dto.response.LoginResponseDTO;
import org.magma.falae.dto.response.RegisterResponseDTO;
import org.magma.falae.infra.TokenService;
import org.magma.falae.model.User;
import org.magma.falae.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    public RegisterResponseDTO Register(RegisterRequestDTO registerRequestDTO) {
        if (userRepository.existsByEmail(registerRequestDTO.email())) {
            throw new RuntimeException("User already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerRequestDTO.password());
        User user = new User(registerRequestDTO.email(), registerRequestDTO.username(), encryptedPassword, registerRequestDTO.phoneNumber(), registerRequestDTO.birthdate(), registerRequestDTO.role());
        User savedUser = userRepository.save(user);

        return new RegisterResponseDTO(savedUser.getEmail(), savedUser.getRole());
    }

    public LoginResponseDTO Login(LoginRequestDTO loginRequestDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password());
        var authentication = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) authentication.getPrincipal());
        return new LoginResponseDTO(token);
    }
}