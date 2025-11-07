package org.magma.falae.dto.response;

import org.magma.falae.model.Message;
import org.magma.falae.model.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record ChatResponseDTO(UUID id, List<Message> messages, boolean isGroupChat, Set<User> participants) {
}
