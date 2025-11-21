package org.magma.falae.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record MessageRequestDTO (UUID chatId, UUID userId, @NotNull @NotBlank String content) {
}
