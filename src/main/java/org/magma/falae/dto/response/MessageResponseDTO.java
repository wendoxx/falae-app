package org.magma.falae.dto.response;


import java.time.LocalDateTime;
import java.util.UUID;

public record MessageResponseDTO(UUID id, UUID userId, UUID chatId, String content, LocalDateTime timestamp) {
}
