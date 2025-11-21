package org.magma.falae.dto.request;

import java.util.UUID;

public record MessageRequestDTO (UUID chatId, UUID userId, String content) {
}
