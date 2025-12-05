package org.magma.falae.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record MessageResponseDTO(UUID id, UUID userId, UUID chatId, String content, @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime timestamp) {
}
