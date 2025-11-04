package org.magma.falae.dto.request;

import org.magma.falae.model.UserRole;

import java.util.Date;

public record RegisterRequestDTO(String email, String username, String password, Date birthdate, String phoneNumber, UserRole role) {
}