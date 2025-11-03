package org.magma.falae.dto.request;

import org.magma.falae.model.UserRole;

public record RegisterRequestDTO(String email, String username, String password, String birthdate, String phoneNumber, UserRole role) {
}