package org.magma.falae.dto.response;

import org.magma.falae.model.Message;

import java.util.Set;
import java.util.UUID;

public record ChatResponseDTO(UUID id, Set<Message> messages, boolean isGroupChat) {
}
