package org.magma.falae.dto.response;

import org.magma.falae.model.UserRole;

public record RegisterResponseDTO(String email, UserRole role) {
}
