package org.magma.falae.model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.magma.falae.dto.request.ChatRequestDTO;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Chat {

    @Id
    private UUID id;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages;

    private boolean isGroupChat;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "chat_participants",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> participants = new HashSet<>();

    public void addParticipant(User user) {
        this.participants.add(user);

        user.getChats().add(this);
    }

    public Chat(ChatRequestDTO chatRequestDTO, Set<User> participants){
        this.id = chatRequestDTO.id();
        this.isGroupChat = chatRequestDTO.isGroupChat();
        this.participants = participants;
    }
}
