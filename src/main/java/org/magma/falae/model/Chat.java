package org.magma.falae.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @Id
    private UUID id;
    @OneToMany(mappedBy = "chat")
    private Set<Message> messages;
    private boolean isGroupChat;
    //TODO: implement participants field and decide how collection type it will be
}
