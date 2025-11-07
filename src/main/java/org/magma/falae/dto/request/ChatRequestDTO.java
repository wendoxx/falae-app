package org.magma.falae.dto.request;

import java.util.Set;
import java.util.UUID;

public record ChatRequestDTO(UUID id, boolean isGroupChat, Set<UUID> participants) {
}
