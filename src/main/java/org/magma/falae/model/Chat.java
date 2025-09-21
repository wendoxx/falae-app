package org.magma.falae.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Set;
import java.util.UUID;

@Entity
public class Chat {

    @Id
    private UUID id;
    private Set<Message> messages;
}
