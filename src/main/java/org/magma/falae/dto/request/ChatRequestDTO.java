package org.magma.falae.dto.request;

import org.magma.falae.model.Message;
import org.magma.falae.model.User;

import java.util.Set;
import java.util.UUID;

public record ChatRequestDTO(UUID id, Set<Message> messages, boolean isGroupChat, Set<User> participants) {
}
